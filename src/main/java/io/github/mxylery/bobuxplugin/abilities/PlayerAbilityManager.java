package io.github.mxylery.bobuxplugin.abilities;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.abilities.ability_types.*;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.data_structures.*;
import io.github.mxylery.bobuxplugin.items.BobuxItem;

//This is where all cooldowns get registered to players and whatnot. Also take into account mobs, can have seperate methods or even a seperate class
public class PlayerAbilityManager {
    
    //PAI = Player Ability Instance, ../core/AbilityInstance.java
    private static HashMap<Player, AbilityInstanceStructure> abilityHistoryMap = new HashMap<Player, AbilityInstanceStructure>();
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

        //Registers the first-time player
        if (!abilityHistoryMap.containsKey(player)) {
            AbilityInstanceStructure newStruct = new AbilityInstanceStructure();
            newStruct.setOwner(player);
            abilityHistoryMap.put(player, newStruct);
        }

         //Initializes the PAI Structure holding all of the ability instances
        AbilityInstanceStructure abilityInstanceHistory = abilityHistoryMap.get(player);
        ability.use();
            
         //Registers the instance to the AbilityInstanceStructure, which is then registered to the map.
        AbilityInstance abilityInstance = new AbilityInstance(player, BobuxTimer.getTicksPassed(), ability);

        //Puts this instance in the history of all players' historys within a radius of 32 blocks
        ArrayList<Player> playerList = BobuxUtils.getNearbyPlayers(player, maxRegistrationRadius);
        for (int i = 0; i < playerList.size(); i++) {
            Player analyzedPlayer = playerList.get(i);
            //If any of the players aren't registered yet
            if (!abilityHistoryMap.containsKey(analyzedPlayer)) {
                AbilityInstanceStructure struct = new AbilityInstanceStructure();
                struct.setOwner(analyzedPlayer);
                abilityHistoryMap.put(analyzedPlayer, struct);
            }
            AbilityInstanceStructure analyzedPlayerHistory = abilityHistoryMap.get(analyzedPlayer);
            analyzedPlayerHistory.addAbilityInstanceLast(abilityInstance);
            abilityHistoryMap.put(analyzedPlayer, analyzedPlayerHistory);
        }   
             
        abilityInstanceHistory.addAbilityInstanceLast(abilityInstance);
        abilityHistoryMap.put(player, abilityInstanceHistory);
    } 


    private static void usePassive(Player player, BobuxAbility ability) {
        if (ability instanceof AbilityPassive) {
            //Registers the first-time player
            if (!abilityHistoryMap.containsKey(player)) {
                AbilityInstanceStructure newStruct = new AbilityInstanceStructure();
                newStruct.setOwner(player);
                abilityHistoryMap.put(player, newStruct);
            }

            AbilityPassive polyAbility = (AbilityPassive) ability;

            //Initializes the PAI Structure holding all of the ability instances
            AbilityInstanceStructure abilityInstanceHistory = abilityHistoryMap.get(player);
            BobuxAction[] actionList = polyAbility.getActionList();
            for (int i = 0; i < actionList.length; i++) {
                actionList[i].run();
            }
            
            //Registers the instance to the AbilityInstanceStructure, which is then registered to the map.
            AbilityInstance abilityInstance = new AbilityInstance(player, BobuxTimer.getTicksPassed(), ability);

            //Puts this instance in the history of all players' historys within a radius of 32 blocks
            ArrayList<Player> playerList = BobuxUtils.getNearbyPlayers(player, maxRegistrationRadius);
            for (int i = 0; i < playerList.size(); i++) {
                Player analyzedPlayer = playerList.get(i);
                //If any of the players aren't registered yet
                if (!abilityHistoryMap.containsKey(analyzedPlayer)) {
                    AbilityInstanceStructure struct = new AbilityInstanceStructure();
                    struct.setOwner(analyzedPlayer);
                    abilityHistoryMap.put(analyzedPlayer, struct);
                }
                AbilityInstanceStructure analyzedPlayerHistory = abilityHistoryMap.get(analyzedPlayer);
                analyzedPlayerHistory.addAbilityInstanceLast(abilityInstance);
                abilityHistoryMap.put(analyzedPlayer, analyzedPlayerHistory);
            }   
            abilityInstanceHistory.addAbilityInstanceLast(abilityInstance);
            abilityHistoryMap.put(player, abilityInstanceHistory);
        } 
    }

    private static boolean verifyItemCD(Player player, BobuxAbility ability) {
        long cooldown = ability.getCooldown();
        if (!abilityHistoryMap.containsKey(player)) {
            return true;
        } else {
            AbilityInstanceStructure playerAbilHistory = abilityHistoryMap.get(player);
            long lastUse = playerAbilHistory.checkForAbilityCD(ability, cooldown, player);
            //If no such ability was casted in the past #cooldown ticks
            if (lastUse == -1) {
                 return true;
            } else {
                if (!ability.isMuted() && !(ability instanceof AbilityPassive)) {
                    double timeRemaining = (double) cooldown / (double) 20 - (double) lastUse / (double) 20;
                    String numberString = String.format("§c%.2f", timeRemaining);
                    player.sendMessage("You need to wait " + numberString + " §fmore seconds until using this ability."); 
                }
                return false;
            }
        } 
    }

    public static boolean verifyItemCD(Entity entity, BobuxAbility ability, boolean noCDActivate) {
        long cooldown = ability.getCooldown();
        if (!abilityHistoryMap.containsKey(entity)) {
            return true;
        } else {
            AbilityInstanceStructure playerAbilHistory = abilityHistoryMap.get(entity);
            long lastUse = playerAbilHistory.checkForAbilityCD(ability, cooldown, entity);
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
                ability.setTarget(otherEntity);
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

    public static boolean checkForSlotMatch(BobuxItem bobuxitem, Player holder, EquipmentSlot slot, boolean passive) {
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
                        return true;
                    }
                    return false;
                } else {
                    if (ability.setActionList() && verifyItemCD(holder, ability)) {
                        usePassive(holder, ability);  
                        //Cooldown is actually the delay, so if the delay isn't 0 (if it relies on a delay)
                        if (ability.getCooldown() != 0) {
                            Runnable passiveRunnable = new Runnable(){
                                public void run() {
                                    checkForSlotMatch(bobuxitem, holder, slot, passive);
                                }
                            };
                        scheduler.runTaskLater(plugin, passiveRunnable, ability.getCooldown());
                        return true;
                        }
                    }
                    return false;
                }
            } return false;
        } return false;
    }

    public static boolean checkForSlot(BobuxItem bobuxitem, Player holder, EquipmentSlot slot) {
        PlayerInventory currentInventory = holder.getInventory();
        if (currentInventory.getItem(slot) != null) {
            if (BobuxUtils.checkWithoutDuraAmnt(currentInventory.getItem(slot), bobuxitem)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //For items that affect entities which don't need tracking or the player involved
    public static boolean checkForItemUse(ItemStack stack, BobuxItem bobuxitem, Entity entity) {
        if (BobuxUtils.checkWithoutDuraAmnt(stack, bobuxitem)) {
            BobuxAbility ability = bobuxitem.getAbility();
            ability.setTarget(entity);
            ability.setActionList();
            ability.use();
            return true;
        } else {
            return false;
        }
    }

    public static HashMap<Player, AbilityInstanceStructure> getAbilityHistoryMap() {
        return abilityHistoryMap;
    }

}
