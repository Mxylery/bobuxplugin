package io.github.mxylery.bobuxplugin.core;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.abilities.ability_types.*;
import io.github.mxylery.bobuxplugin.actions.PlayParticle;
import io.github.mxylery.bobuxplugin.conditions.PlayerAbilityInstanceCondition;
import io.github.mxylery.bobuxplugin.data_structures.*;
import io.github.mxylery.bobuxplugin.items.BobuxItem;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;

//This is where all cooldowns get registered to players and whatnot. Also take into account mobs, can have seperate methods or even a seperate class
public class PlayerAbilityManager {
    
    //PAI = Player Ability Instance, ../core/PlayerAbilityInstance.java
    private static HashMap<Player, PAIStructure> PAImap = new HashMap<Player, PAIStructure>();
    //This number is the highest radius that a PAI will be put into nearby players' PAI maps.
    private static final double maxRegistrationRadius = 32;
    private static BukkitScheduler scheduler = BobuxTimer.getScheduler();
    private static Plugin plugin = BobuxTimer.getPlugin();

    /**
     * This method is statically used in the PlayerAbilityManager class to activate abilities.
     * @param player
     * @param ability
     */
    private static void useAbility(Player player, BobuxAbility ability) {

        if (ability instanceof AbilityOneTime) {
            //Registers the first-time player
            if (!PAImap.containsKey(player)) {
                PAIStructure newStruct = new PAIStructure();
                newStruct.setOwner(player);
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
                    
                    //Only activates if condition abilities 
                    if (conditionRadius == 0) {
                        conditionsMet = abilityInstanceHistory.removeAbilityInstance(conditionAbility, conditionTimeFrame);
                    } else {
                        conditionsMet = abilityInstanceHistory.removeAbilityInstance(conditionAbility, conditionTimeFrame, conditionRadius);
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
                    struct.setOwner(analyzedPlayer);
                    PAImap.put(analyzedPlayer, struct);
                }
                PAIStructure analyzedPlayerHistory = PAImap.get(analyzedPlayer);
                analyzedPlayerHistory.addPAILast(abilityInstance);
                PAImap.put(analyzedPlayer, analyzedPlayerHistory);
            }   
             
            abilityInstanceHistory.addPAILast(abilityInstance);
            PAImap.put(player, abilityInstanceHistory);

        } else if (ability instanceof AbilityMultiStep) {

        } 
    }

    private static void usePassive(Player player, BobuxAbility ability) {
        if (ability instanceof AbilityPassive) {
            //Registers the first-time player
            if (!PAImap.containsKey(player)) {
                PAIStructure newStruct = new PAIStructure();
                newStruct.setOwner(player);
                PAImap.put(player, newStruct);
            }

            AbilityPassive polyAbility = (AbilityPassive) ability;

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
                    
                    //Only activates if condition abilities 
                    if (conditionRadius == 0) {
                        conditionsMet = abilityInstanceHistory.removeAbilityInstance(conditionAbility, conditionTimeFrame);
                    } else {
                        conditionsMet = abilityInstanceHistory.removeAbilityInstance(conditionAbility, conditionTimeFrame, conditionRadius);
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
                    struct.setOwner(analyzedPlayer);
                    PAImap.put(analyzedPlayer, struct);
                }
                PAIStructure analyzedPlayerHistory = PAImap.get(analyzedPlayer);
                analyzedPlayerHistory.addPAILast(abilityInstance);
                PAImap.put(analyzedPlayer, analyzedPlayerHistory);
            }   
            abilityInstanceHistory.addPAILast(abilityInstance);
            PAImap.put(player, abilityInstanceHistory);
        } 
    }

    private static boolean verifyItemCD(Player player, BobuxAbility ability) {
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
                if (!ability.isMuted() && !(ability instanceof AbilityPassive))
                    player.sendMessage("You need to wait " + ( (double) cooldown / (double) 20 - (double) lastUse / (double) 20 ) + " more seconds until using this ability.");
                return false;
            }
        } 
    }

    public static boolean verifyItemCD(Player player, BobuxAbility ability, boolean noCDActivate) {
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
                return false;
            }
        } 
    }

    public static void checkForSlotMatch(BobuxItem bobuxitem, Player holder, EquipmentSlot slot, Entity otherEntity, boolean passive) {
        PlayerInventory currentInventory = holder.getInventory();
        if (currentInventory.getItem(slot) != null) {
            if (BobuxUtils.checkWithoutDuraAmnt(currentInventory.getItem(slot), bobuxitem)) {
                BobuxItem item = bobuxitem;
                BobuxAbility ability;
                if (!passive) {
                    ability = item.getAbility();
                } else {
                    ability = item.getPassive();
                }
                ability.setOtherEntity(otherEntity);
                ability.setUser(holder);
                if (!passive) {
                    if (ability.setActionList() && verifyItemCD(holder, ability)) {
                        useAbility(holder, ability);
                    }
                } else {
                    if (ability.setActionList() && verifyItemCD(holder, ability)) {
                        usePassive(holder, ability);  
                        Runnable passiveRunnable = new Runnable(){
                            public void run() {
                                checkForSlotMatch(bobuxitem, holder, slot, passive);
                            }
                        };
                        scheduler.runTaskLater(plugin, passiveRunnable, ability.getCooldown());
                    }
                }
            }
        }
    }

    public static void checkForSlotMatch(BobuxItem bobuxitem, Player holder, EquipmentSlot slot, boolean passive) {
        PlayerInventory currentInventory = holder.getInventory();
        if (currentInventory.getItem(slot) != null) {
            if (BobuxUtils.checkWithoutDuraAmnt(currentInventory.getItem(slot), bobuxitem)) {
                BobuxItem item = bobuxitem;
                BobuxAbility ability;
                if (!passive) {
                    ability = item.getAbility();
                } else {
                    ability = item.getPassive();
                }
                ability.setUser(holder);
                if (!passive) {
                    if (ability.setActionList() && verifyItemCD(holder, ability)) {
                        useAbility(holder, ability);
                    }
                } else {
                    if (ability.setActionList() && verifyItemCD(holder, ability)) {
                        usePassive(holder, ability);  
                        if (ability.getCooldown() != 0) {
                            Runnable passiveRunnable = new Runnable(){
                                public void run() {
                                    checkForSlotMatch(bobuxitem, holder, slot, passive);
                                }
                            };
                        scheduler.runTaskLater(plugin, passiveRunnable, ability.getCooldown());
                        }
                    }
                }
            }
        }
    }

    public static HashMap<Player, PAIStructure> getPAIMap() {
        return PAImap;
    }

}
