package io.github.mxylery.bobuxplugin.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInputEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.PlayerAbilityManager;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;
import io.github.mxylery.bobuxplugin.vectors.BobuxUtils;

public class BobuxAbilityListener implements Listener {

    private BobuxPlugin plugin;
    private BukkitScheduler scheduler = BobuxTimer.getScheduler();  

    public BobuxAbilityListener(BobuxPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * The following procedure is to be used when implementing item abilities.
     * 1. Initialize the item, ability and particles in ../items/BobuxItemInterface.java
     * 2. Go under the desired handler in this class
     * 3. Under the if statements, use the appropriate PlayerAbilityManager.PlayerAbilityManager.checkForSlotMatch constructor for what you need
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
        Location elevatedPlayerLoc = new Location(playerLocation.getWorld(), playerLocation.getX(), playerLocation.getY() + 1, playerLocation.getZ());
        //This location is specifically for items that registers in lines and can accidentally include the player
        Location slightlyExtendedPlayerLoc = new Location(playerLocation.getWorld(), 
        playerLocation.getX() + playerEyeVector.getX(), 
        playerLocation.getY() + playerEyeVector.getY(), 
        playerLocation.getZ() + playerEyeVector.getZ());
        if (e.getAction().equals(Action.LEFT_CLICK_AIR)) {
            Entity[] playerAsArray = {player};
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.testingItem, player, EquipmentSlot.HAND, 
            BobuxRegisterer.getEntitiesSphere(player, 3), false);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.bouncingItem, player, EquipmentSlot.HAND, playerAsArray, playerEyeVector, false);

        //Air right clicks
        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            Entity[] playerAsArray = {player};
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.hurriedStopwatch, player, EquipmentSlot.HAND, playerAsArray, null, null, false);

            //Anything that plays several particles needs the arrays
            Location[] railgunLocs = {elevatedPlayerLoc, elevatedPlayerLoc};
            Vector[] railgunVectors = {playerEyeVector, playerEyeVector};

            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.railgun, player, EquipmentSlot.HAND, 
            BobuxRegisterer.getEntitiesLine(slightlyExtendedPlayerLoc, 30, 1, 10, playerEyeVector), null, playerLocation, railgunVectors, railgunLocs, false);

            Entity[] hotStickEntity = BobuxRegisterer.getEntitiesLine(slightlyExtendedPlayerLoc, 30, 1, 1, playerEyeVector);
            if (hotStickEntity != null) {
                Vector[] theHotStickVectors = {playerEyeVector, playerEyeVector};
                Location[] theHotStickLocs = {elevatedPlayerLoc, hotStickEntity[0].getLocation()};
                PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.theHotStick, player, EquipmentSlot.HAND, 
                hotStickEntity, null, playerLocation, theHotStickVectors, theHotStickLocs, false);
            }

            Entity[] BW5Entity = BobuxRegisterer.getEntitiesLine(slightlyExtendedPlayerLoc, 30, 1, 1, playerEyeVector);
            if (BW5Entity != null && BobuxUtils.checkTotalItems(player.getInventory(), BobuxItemInterface.BW5Ammo.getStack()) != null) {
                Vector[] BW5Vectors = {playerEyeVector, playerEyeVector, playerEyeVector, playerEyeVector, playerEyeVector, playerEyeVector};
                Location[] BW5Locs = {elevatedPlayerLoc, BW5Entity[0].getLocation(), BW5Entity[0].getLocation(), BW5Entity[0].getLocation(), BW5Entity[0].getLocation(), BW5Entity[0].getLocation()};
                PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.BW5, player, EquipmentSlot.HAND, 
                BW5Entity, null, playerLocation, BW5Vectors, BW5Locs, false);
            } 
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

            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.harmfulSubstance, player, EquipmentSlot.HAND, damagedAsArray, false);

            //Set up for 
            Vector slightKnockUp = new Vector(playerEyeVector.getX(), playerEyeVector.getY() + 1, playerEyeVector.getZ());

            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.cleaver, player, EquipmentSlot.HAND,
            BobuxRegisterer.getEntitiesSphere(player, damagedEntity.getLocation(), 3,2.5,playerEyeVector), slightKnockUp, 
            null, playerEyeVector, BobuxUtils.offsetLocation(damagedEntityLoc, playerEyeVector, 2, 0.5), false);

        }
    }

    @EventHandler
    public void onSpace(PlayerInputEvent e) {
        Player player = e.getPlayer();
        Vector playerEyeVector = player.getEyeLocation().getDirection();
        if (player.isOnGround()) {
            Entity[] playerAsArray = {player};
            if (e.getInput().isJump()) {
                PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.bounceBoots, player, EquipmentSlot.FEET, playerAsArray, new Vector(0,1,0), false);
            }
        } else { //If you want mid-air OK
            if (e.getInput().isJump()) {

            }
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        Player player = e.getPlayer();
        Vector playerEyeVector = player.getEyeLocation().getDirection();
        Location playerLocation = player.getLocation();

        RegistererOption lineSpawnerRegOption = new RegistererOption(RegistererType.LINE, playerLocation, 30, 0, 0, playerEyeVector);
        BobuxRegisterer lineSpawnerRegisterer = new BobuxRegisterer(lineSpawnerRegOption, player, null, null, null, playerEyeVector, playerLocation, false);
        PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.lineSpawner, player, EquipmentSlot.HAND, lineSpawnerRegisterer, true);

    }

    @EventHandler
    public void onItemSwap(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();
        Vector playerEyeVector = player.getEyeLocation().getDirection();
        Location playerLocation = player.getLocation();

        //Stupid stupid stupid 
        Runnable passiveRunnable = new Runnable(){
            public void run() {
                RegistererOption lineSpawnerRegOption = new RegistererOption(RegistererType.LINE, playerLocation, 30, 0, 0, playerEyeVector);
                BobuxRegisterer lineSpawnerRegisterer = new BobuxRegisterer(lineSpawnerRegOption, player, null, null, null, playerEyeVector, playerLocation, false);
                PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.lineSpawner, player, EquipmentSlot.HAND, lineSpawnerRegisterer, true);
            }
        };
        scheduler.runTaskLater(plugin, passiveRunnable, 1);
    }

    @EventHandler
    public void onEquipSwap(InventoryClickEvent e) {

        if (e.getClickedInventory() instanceof PlayerInventory) {
            PlayerInventory inventory = (PlayerInventory) e.getClickedInventory();
            Player player = (Player) inventory.getHolder();
            Vector playerEyeVector = player.getEyeLocation().getDirection();
            Location playerLocation = player.getLocation();
        }
    }
}
