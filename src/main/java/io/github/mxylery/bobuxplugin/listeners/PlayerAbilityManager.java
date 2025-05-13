package io.github.mxylery.bobuxplugin.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;
import io.github.mxylery.bobuxplugin.*;
import io.github.mxylery.bobuxplugin.conditions.PlayerAbilityInstanceCondition;
import io.github.mxylery.bobuxplugin.core.*;
import io.github.mxylery.bobuxplugin.core.ability_types.*;
import io.github.mxylery.bobuxplugin.data_structures.*;
import io.github.mxylery.bobuxplugin.items.BobuxItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.vectors.BobuxUtils;

//This is where all cooldowns get registered to players and whatnot. Also take into account mobs, can have seperate methods or even a seperate class
public final class PlayerAbilityManager implements Listener {
    
    //PAI = Player Ability Instance, ../core/PlayerAbilityInstance.java
    private final BobuxPlugin plugin;
    private static HashMap<Player, PAIStructure> PAImap = new HashMap<Player, PAIStructure>();
    //This number is the highest radius that a PAI will be put into nearby players' PAI maps.
    private static final double maxRegistrationRadius = 32;

    public PlayerAbilityManager(BobuxPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //Used in all of the listener methods below.
    private static void useAbility(Player player, BobuxAbility ability) {
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

            //If it has PAI conditions (if it relies on other people using their abilities in a certain radius)
            if (polyAbility.getConditionList() != null) {
                PlayerAbilityInstanceCondition[] conditionList = polyAbility.getConditionList();
                //Stops if conditions aren't met or if all conditions have been checked and are met
                boolean conditionsMet = true;
                for (int i = 0; i < conditionList.length && conditionsMet == true; i++) {
                    
                    BobuxAbility conditionAbility = conditionList[i].getAbility();
                    double conditionRadius = conditionList[i].getRadius();
                    long conditionTimeFrame = conditionList[i].getTimeFrame();
                    Player conditionPlayer = conditionList[i].getPlayer();
                    
                    //Only activates if condition abilities 
                    if (conditionRadius == 0) {
                        conditionsMet = abilityInstanceHistory.removeAbilityInstance(conditionAbility, conditionTimeFrame, conditionPlayer);
                    } else {
                        conditionsMet = abilityInstanceHistory.removeAbilityInstance(conditionAbility, conditionTimeFrame, conditionRadius, conditionPlayer);
                    }
                }
                for (int i = 0; i < actionList.length; i++) {
                    if (actionList[i].requiresCondition()) {
                        if (conditionsMet) {
                            actionList[i].run();
                        }
                    } else {
                        actionList[i].run();
                    }
                }
            } else {
                /*
                 * Runs each action of the ability with no conditions otherwise
                 */
                for (int i = 0; i < actionList.length; i++) {
                    actionList[i].run();
                }
            }
            
            //Registers the instance to the PAIStructure, which is then registered to the map.
            PlayerAbilityInstance abilityInstance = new PlayerAbilityInstance(player, BobuxTimer.getTicksPassed(), ability);

            //Puts this instance in the history of all players' historys within a radius of 32 blocks
            ArrayList<Player> playerList = BobuxUtils.getNearbyPlayers(player, maxRegistrationRadius);
            for (int i = 0; i < playerList.size(); i++) {
                Player analyzedPlayer = playerList.get(i);
                //If any of the players aren't registered yet
                if (!PAImap.containsKey(analyzedPlayer)) {
                    PAIStructure struct = new PAIStructure();
                    PAImap.put(analyzedPlayer, struct);
                }
                PAIStructure analyzedPlayerHistory = PAImap.get(analyzedPlayer);
                analyzedPlayerHistory.addPAI(abilityInstance);
                PAImap.put(analyzedPlayer, analyzedPlayerHistory);
            }   
             
            abilityInstanceHistory.addPAI(abilityInstance);
            PAImap.put(player, abilityInstanceHistory);

        } else if (ability instanceof AbilityMultiStep) {

        } else {

        }
    }

    //Sets entity list to be the player triggering the ability
    private void defaultTargetSettings(Player player, BobuxAbility ability) {

        if (ability instanceof AbilityOneTime) {
            Player[] playerList = {player};
            AbilityOneTime polyAbility = (AbilityOneTime) ability;
            BobuxAction[] actionList = polyAbility.getActionList();
            //Registeres the user of the ability as the entity list of the action
            for (int i = 0; i < actionList.length; i++) {
                actionList[i].initializeEntityList(playerList);
            }
        } else {

        }
    }

    private boolean verifyItemCD(Player player, BobuxAbility ability) {
        long cooldown = ability.getCooldown();
            if (!PAImap.containsKey(player)) {
                return true;
            } else {
                PAIStructure playerAbilHistory = PAImap.get(player);
                long lastUse = playerAbilHistory.checkForAbilityCD(ability, cooldown, player);
                //If no such ability was casted in the past #cooldown ticks
                if (lastUse == -1) {
                    return true;
                } else {
                    plugin.getServer().broadcastMessage("You need to wait " + ( (double) cooldown / (double) 20 - (double) lastUse / (double) 20 ) + " more seconds until using this ability.");
                    return false;
                }
            } 
        }

    //If you want the listener to check in the mainhand, make the boolean false. If you want the listener to check the offhand, make the boolean true.
    private void checkForHandMatch(BobuxItem bobuxitem, PlayerInteractEvent e, boolean isOffHand) {
        Player currentPlayer = e.getPlayer();
        //Mainhand
        if (!isOffHand && e.getPlayer().getInventory().getItemInMainHand() != null) {
            if (e.getPlayer().getInventory().getItemInMainHand().equals(bobuxitem.getStack())) {
                BobuxItem item = BobuxItemInterface.testingItem;
                BobuxAbility ability = item.getAbility();
                defaultTargetSettings(currentPlayer, ability);
                if (verifyItemCD(currentPlayer, ability)) {
                    useAbility(currentPlayer, ability);
                }
            }
        //Offhand
        } else if (e.getPlayer().getInventory().getItemInOffHand() != null) {
            if (e.getPlayer().getInventory().getItemInOffHand().equals(bobuxitem.getStack())) {
                BobuxItem item = BobuxItemInterface.testingItem;
                BobuxAbility ability = item.getAbility();
                defaultTargetSettings(currentPlayer, ability);
                if (verifyItemCD(currentPlayer, ability)) {
                    useAbility(currentPlayer, ability);
                }
            }
        }
    }

    private void checkForSlotMatch(BobuxItem bobuxitem, PlayerInteractEvent e) {

    }

    private void overrideTargetSettings(Player player, BobuxAbility ability, Player[] playerList) {

    }

    /**
     * The following procedure is to be used when implementing item abilities.
     * 1. Initialize the item and ability in ../items/BobuxItemInterface.java
     * 2. Go under the desired handler in this class
     * 3. Under the if statements, use the appropriate method checkForHandMatch() or checkForSlotMatch()
     * @param e
     */
    @EventHandler
    public void onLeftClick(PlayerInteractEvent e) {

        if (e.getAction().equals(Action.LEFT_CLICK_AIR)) {
            checkForHandMatch(BobuxItemInterface.testingItem, e, false);
        }

    }

}
