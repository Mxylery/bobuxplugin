package io.github.mxylery.bobuxplugin.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInputEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.spawner.SpawnerEntry.Equipment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import io.github.mxylery.bobuxplugin.*;
import io.github.mxylery.bobuxplugin.actions.PlayParticle;
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

        } else {

        }
    }

    private boolean setTargetSettings(Player player, BobuxAbility ability, Entity[] entityList, Vector vector, Location location, Vector particleDir, Location particleLoc) {
        if (ability instanceof AbilityOneTime) {
            AbilityOneTime polyAbility = (AbilityOneTime) ability;
            BobuxAction[] actionList = polyAbility.getActionList();
            //Registeres the user of the ability as the entity list of the action
            for (int i = 0; i < actionList.length; i++) {
                if (actionList[i].requiresEntities()) {
                    if (entityList == null) {
                        return false;
                    } else {
                        actionList[i].initializeEntityList(entityList);
                    }
                }
                //Default vector is where player is facing
                if (actionList[i].requiresVector()) {
                    if (vector == null) {
                        return false;
                    } else {
                        actionList[i].initializeVector(vector);
                    }
                }
                //Default location is player location
                if (actionList[i].requiresLocation()) {
                    if (location == null) {
                        return false;
                    } else {
                        actionList[i].initializeLocation(location);
                    }
                }
                if (actionList[i] instanceof PlayParticle) {
                    actionList[i].initializeVector(particleDir);
                    actionList[i].initializeLocation(particleLoc);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    //Leaving the playerList null will use the activating player; leaving the vector null will use the direction the player is facing.
    private boolean setTargetSettings(Player player, BobuxAbility ability, Entity[] entityList, Vector vector, Location location, Vector[] particleDir, Location[] particleLoc) {
        int j = 0;
        if (ability instanceof AbilityOneTime) {
            AbilityOneTime polyAbility = (AbilityOneTime) ability;
            BobuxAction[] actionList = polyAbility.getActionList();
            //Registeres the user of the ability as the entity list of the action
            for (int i = 0; i < actionList.length; i++) {
                if (actionList[i].requiresEntities()) {
                    if (entityList == null) {
                        return false;
                    } else {
                        actionList[i].initializeEntityList(entityList);
                    }
                }
                //Default vector is where player is facing
                if (actionList[i].requiresVector()) {
                    if (vector == null) {
                        return false;
                    } else {
                        actionList[i].initializeVector(vector);
                    }
                }
                //Default location is player location
                if (actionList[i].requiresLocation()) {
                    if (location == null) {
                        return false;
                    } else {
                        actionList[i].initializeLocation(location);
                    }
                }
                if (actionList[i] instanceof PlayParticle) {
                    actionList[i].initializeVector(particleDir[j]);
                    actionList[i].initializeLocation(particleLoc[j]);
                    j++;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean setTargetSettings(Player player, BobuxAbility ability, Entity[] entityList, Vector vector, Location location) {
        if (ability instanceof AbilityOneTime) {
            AbilityOneTime polyAbility = (AbilityOneTime) ability;
            BobuxAction[] actionList = polyAbility.getActionList();
            //Registeres the user of the ability as the entity list of the action
            for (int i = 0; i < actionList.length; i++) {
                if (actionList[i].requiresEntities()) {
                    if (entityList == null) {
                        return false;
                    } else {
                        actionList[i].initializeEntityList(entityList);
                    }
                }
                //Default vector is where player is facing
                if (actionList[i].requiresVector()) {
                    if (vector == null) {
                        return false;
                    } else {
                        actionList[i].initializeVector(vector);
                    }
                }
                //Default location is player location
                if (actionList[i].requiresLocation()) {
                    if (location == null) {
                        return false;
                    } else {
                        actionList[i].initializeLocation(location);
                    }
                }
            }
            return true;
        } else {
            return false;
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
                    if (!ability.isMuted())
                        plugin.getServer().broadcastMessage("You need to wait " + ( (double) cooldown / (double) 20 - (double) lastUse / (double) 20 ) + " more seconds until using this ability.");
                    return false;
                }
            } 
        }

    /**
     * Used to check certain item slots before triggering abilities
     * @param bobuxitem
     * @param holder
     * @param slot
     * @param playerList
     * @param vector
     */
    private void checkForSlotMatch(BobuxItem bobuxitem, Player holder, EquipmentSlot slot, Entity[] entityList, Vector vector, Location location) {
        PlayerInventory currentInventory = holder.getInventory();
        if (currentInventory.getItem(slot) != null) {
            if (BobuxUtils.checkWithoutDuraAmnt(currentInventory.getItem(slot), bobuxitem)) {
                BobuxItem item = bobuxitem;
                BobuxAbility ability = item.getAbility();
                if (setTargetSettings(holder, ability, entityList, vector, location) && verifyItemCD(holder, ability)) {
                    useAbility(holder, ability);
                }
            }
        }
    }

    private void checkForSlotMatch(BobuxItem bobuxitem, Player holder, EquipmentSlot slot, Entity[] entityList, Vector vector, Location location, Vector particleDir, Location particleLoc) {
        PlayerInventory currentInventory = holder.getInventory();
        if (currentInventory.getItem(slot) != null) {
            if (BobuxUtils.checkWithoutDuraAmnt(currentInventory.getItem(slot), bobuxitem)) {
                BobuxItem item = bobuxitem;
                BobuxAbility ability = item.getAbility();
                if (setTargetSettings(holder, ability, entityList, vector, location, particleDir, particleLoc) && verifyItemCD(holder, ability)) {
                    useAbility(holder, ability);
                }
            }
        }
    }

    /**
     * Used to check certain item slots before triggering abilities with particles
     * @param bobuxitem
     * @param holder
     * @param slot
     * @param playerList
     * @param vector
     */
    private void checkForSlotMatch(BobuxItem bobuxitem, Player holder, EquipmentSlot slot, Entity[] entityList, Vector vector, Location location, Vector[] particleDir, Location[] particleLoc) {
        PlayerInventory currentInventory = holder.getInventory();
        if (currentInventory.getItem(slot) != null) {
            if (BobuxUtils.checkWithoutDuraAmnt(currentInventory.getItem(slot), bobuxitem)) {
                BobuxItem item = bobuxitem;
                BobuxAbility ability = item.getAbility();
                if (setTargetSettings(holder, ability, entityList, vector, location, particleDir, particleLoc) && verifyItemCD(holder, ability)) {
                    useAbility(holder, ability);
                }
            }
        }
    }

    /**
     * The following procedure is to be used when implementing item abilities.
     * 1. Initialize the item and ability in ../items/BobuxItemInterface.java
     * 2. Go under the desired handler in this class
     * 3. Under the if statements, use the appropriate method checkForHandMatch() or checkForSlotMatch() 
     * 4. When checking for a slot: 9-35 = inventory, 200-226 = ender chest, or use EquipmentSlot enums.
     * 5. Use the appropriate initialized variables and BobuxRegisterer methods to edit the ability targeting 
     * (You can derive a lot of locations/vectors from player from given variables)
     * @param e
     */
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        //Air left clicks
        Player player = e.getPlayer();
        Vector playerEyeVector = player.getEyeLocation().getDirection();
        Location playerLocation = player.getLocation();
        if (e.getAction().equals(Action.LEFT_CLICK_AIR)) {
            Entity[] playerAsArray = {player};
            checkForSlotMatch(BobuxItemInterface.testingItem, player, EquipmentSlot.HAND, 
            BobuxUtils.getEntitiesSphere(player, 3), null, null);
            checkForSlotMatch(BobuxItemInterface.bouncingItem, player, EquipmentSlot.HAND, playerAsArray, playerEyeVector, null);

        //Air right clicks
        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            Entity[] playerAsArray = {player};
            checkForSlotMatch(BobuxItemInterface.hurriedStopwatch, player, EquipmentSlot.HAND, playerAsArray, null, null);
            checkForSlotMatch(BobuxItemInterface.lineSpawner, player, EquipmentSlot.HAND, null, playerEyeVector, playerLocation, playerEyeVector, playerLocation);

            //Anything that plays several particles needs this
            Location elevatedPlayerLoc = new Location(playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY() + 1, playerLocation.getZ());
            Location[] railgunLocs = {elevatedPlayerLoc, elevatedPlayerLoc};
            Vector[] railgunVectors = {playerEyeVector, playerEyeVector};

            checkForSlotMatch(BobuxItemInterface.railgun, player, EquipmentSlot.HAND, 
            BobuxUtils.getEntitiesLine(playerLocation, 30, 1, 10, playerEyeVector), null, playerLocation, railgunVectors, railgunLocs);
        }
    }

    //Any abilities that should be activated when hitting an enemy
    @EventHandler
    public void onDealingHit(EntityDamageEvent e) {
        if (e.getDamageSource().getCausingEntity() instanceof Player) {
            Player player = (Player) e.getDamageSource().getCausingEntity();
            Entity[] playerAsArray = {player};
            Entity damagedEntity = e.getEntity();
            Entity[] damagedAsArray = {damagedEntity};
            Vector playerEyeVector = player.getEyeLocation().getDirection();
            Location damagedEntityLoc = damagedEntity.getLocation();

            checkForSlotMatch(BobuxItemInterface.harmfulSubstance, player, EquipmentSlot.HAND, damagedAsArray, null, null);

            //Set up for 
            Vector slightKnockUp = new Vector(playerEyeVector.getX(), playerEyeVector.getY() + 1, playerEyeVector.getZ());

            checkForSlotMatch(BobuxItemInterface.cleaver, player, EquipmentSlot.HAND,
            BobuxUtils.getEntitiesSphere(player, damagedEntity.getLocation(), 3,2.5,playerEyeVector), slightKnockUp, 
            null, playerEyeVector, BobuxUtils.offsetLocation(damagedEntityLoc, playerEyeVector, 2, 0.5));

        }
    }

    @EventHandler
    public void onSpace(PlayerInputEvent e) {
        Player player = e.getPlayer();
        Vector playerEyeVector = player.getEyeLocation().getDirection();
        if (player.isOnGround()) {
            Entity[] playerAsArray = {player};
            if (e.getInput().isJump()) {
                checkForSlotMatch(BobuxItemInterface.bounceBoots, player, EquipmentSlot.FEET, playerAsArray, new Vector(0,1,0), null);
            }
        } else { //If you want mid-air OK
            if (e.getInput().isJump()) {

            }
        }
    }

    //Will be used for passive abilities along onEquipmentSwitch and onLogin
    public void onItemSwitch(PlayerItemHeldEvent e) {

    }

    /**
     * 100 = feet, 101 = leggings, 102 = chestplate, 103 = helmet
     * 200-226 = echest
     * 9-35 = non-hotbar inventory
     * 
     * Have fun!
     * 
     * @param e
     */
    public void onEquipmentSwitch(InventoryClickEvent e) {
        InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof Player) {
            Player player = (Player) holder;
        }
    }

    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        switch (e.getFormat()) {
            case "BallsSecretCode": 
            break;
        }
    }

    public void onEat(PlayerItemConsumeEvent e) {

    }

    public void onVelocityMet(PlayerVelocityEvent e) {

    }

    public static HashMap<Player, PAIStructure> getPAIMap() {
        return PAImap;
    }

}
