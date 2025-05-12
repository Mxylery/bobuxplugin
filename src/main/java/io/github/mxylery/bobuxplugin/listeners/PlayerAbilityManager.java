package io.github.mxylery.bobuxplugin.listeners;

import java.util.HashMap;

import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import io.github.mxylery.bobuxplugin.*;
import io.github.mxylery.bobuxplugin.conditions.PlayerAbilityInstanceCondition;
import io.github.mxylery.bobuxplugin.core.*;
import io.github.mxylery.bobuxplugin.core.ability_types.*;
import io.github.mxylery.bobuxplugin.data_structures.*;
import io.github.mxylery.bobuxplugin.items.BobuxItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

//This is where all cooldowns get registered to players and whatnot. Also take into account mobs, can have seperate methods or even a seperate class
public class PlayerAbilityManager implements Listener {
    
    //PAI = Player Ability Instance, ../core/PlayerAbilityInstance.java
    private final BobuxPlugin plugin;
    private static HashMap<Player, PAIStructure> PAImap = new HashMap<Player, PAIStructure>();
    //This number is the highest radius that a PAI will be put into nearby players' PAI maps.
    private final double maxRegistrationRadius = 32;

    public PlayerAbilityManager(BobuxPlugin plugin) {
        this.plugin = plugin;
    }

    //Used in all of the listener methods below.
    private static void useAbility(BobuxAbility ability, Player player) {
        //Checks if the ability is a one-time ability
        if (ability instanceof AbilityOneTime) {
            //Registers the first-time player
            if (!PAImap.containsKey(player)) {
                PAIStructure newStruct = new PAIStructure();
                PAImap.put(player, newStruct);
            }

            AbilityOneTime polyAbility = (AbilityOneTime) ability;

            //Initializes the PAI Structure holding all of the ability instances
            PAIStructure abilityInstanceHistory = PAImap.get(player);
            BobuxAction[] actionList = polyAbility.getActionList();
            polyAbility.setUser(player);

            //If it has PAI conditions
            if (polyAbility.getConditionList() != null) {
                PlayerAbilityInstanceCondition[] conditionList = polyAbility.getConditionList();
                for (int i = 0; i < conditionList.length; i++) {
                    
                    BobuxAbility conditionAbility = conditionList[i].getAbility();
                    double conditionRadius = conditionList[i].getRadius();
                    long conditionTimeFrame = conditionList[i].getTimeFrame();
                    Player conditionPlayer = conditionList[i].getPlayer();
                    
                    if (conditionRadius == 0) {
                        abilityInstanceHistory.removeAbilityInstance(conditionAbility, conditionTimeFrame, conditionPlayer);
                    } else {
                        abilityInstanceHistory.removeAbilityInstance(conditionAbility, conditionTimeFrame, conditionRadius, conditionPlayer);
                    }
                }
            } else {
                //Runs each action of the ability
                for (int i = 0; i < actionList.length; i++) {
                    actionList[i].run();
                }
            }
            
            //Registers the instance to the PAIStructure, which is then registered to the map.
            PlayerAbilityInstance abilityInstance = new PlayerAbilityInstance(player, BobuxTimer.getTicksPassed(), ability);
            abilityInstanceHistory.addPAI(abilityInstance);
            PAImap.put(player, abilityInstanceHistory);

        } else if (ability instanceof AbilityMultiStep) {

        } else {

        }
    }

    private boolean verifyItemCD(Player player, BobuxAbility ability) {
            long cooldown = ability.getCooldown();
                if (!PAImap.containsKey(player)) {
                    return true;
                } else {
                    PAIStructure playerAbilHistory = PAImap.get(player);
                    long cd = playerAbilHistory.checkForAbilityCD(ability, cooldown, player);
                    //If no such ability was casted in the past #cooldown ticks
                    if (cd == -1) {
                        return true;
                    } else {
                        plugin.getServer().broadcastMessage("You need to wait" + (double) cd/(double) 20 + "more seconds until using this ability.");
                        return false;
                    }
                } 
    }

    //Sets the triggerer of the actions to be the user of the ability
    private void defaultTriggererSetting(Player player, BobuxAbility ability) {
        
        if (ability instanceof AbilityOneTime) {
            AbilityOneTime polyAbility = (AbilityOneTime) ability;
            BobuxAction[] actionList = polyAbility.getActionList();
            for (int i = 0; i < actionList.length; i++) {
                actionList[i].setTriggerer(player);
            }
        } else {

        }
        
    }

    //All items that require clicks
    public void playerClickListener(PlayerInteractEvent e) {
        /*
         * ALL LEFT CLICK ACTIVATED ITEMS
         */
        if (e.getAction().equals(Action.LEFT_CLICK_AIR)) {
            Player currentPlayer = e.getPlayer();

            //Testing Item
            if (e.getItem().equals(BobuxItemInterface.testingItem.getStack())) {
                BobuxItem item = BobuxItemInterface.testingItem;
                AbilityOneTime ability = (AbilityOneTime) item.getAbility();
                BobuxAction[] actionList = ability.getActionList();
                actionList[0].setTriggerer(currentPlayer);
                if (verifyItemCD(currentPlayer, ability)) {
                    useAbility(ability, currentPlayer);
                }
            }
        }

    }

}
