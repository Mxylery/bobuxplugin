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
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.core.PlayerAbilityManager;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

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
        Location playerEyeLoc = player.getEyeLocation();
        //This location is specifically for items that registers in lines and can accidentally include the player
        Location slightlyExtendedPlayerLoc = new Location(playerLocation.getWorld(), 
        playerLocation.getX() + playerEyeVector.getX(), 
        playerLocation.getY() + playerEyeVector.getY(), 
        playerLocation.getZ() + playerEyeVector.getZ());
        Inventory playerInventory = player.getInventory();
        if (e.getAction().equals(Action.LEFT_CLICK_AIR)) {
            Entity[] playerAsArray = {player};

            RegistererOption testingItemOption = new RegistererOption(RegistererType.SPHERE, playerLocation, 0, 3, 0, playerEyeVector);
            BobuxRegisterer testingItemRegisterer = new BobuxRegisterer(testingItemOption, player);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.testingItem, player, EquipmentSlot.HAND, testingItemRegisterer, false);

            BobuxRegisterer bouncingItemRegisterer = new BobuxRegisterer(player, playerEyeVector);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.bouncingItem, player, EquipmentSlot.HAND, bouncingItemRegisterer, false);

        //Air right clicks
        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {

            BobuxRegisterer hurriedStopwatchRegisterer = new BobuxRegisterer(player);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.hurriedStopwatch, player, EquipmentSlot.HAND, hurriedStopwatchRegisterer, false);

            //Anything that plays particles needs the arrays
            Location[] railgunLocs = {playerEyeLoc, playerEyeLoc};
            Vector[] railgunVectors = {playerEyeVector, playerEyeVector};
            RegistererOption railgunOption = new RegistererOption(RegistererType.LINE, playerEyeLoc, 30, 1, 10, playerEyeVector);
            BobuxRegisterer railgunRegisterer = new BobuxRegisterer(railgunOption, player, railgunVectors, railgunLocs);=
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.railgun, player, EquipmentSlot.HAND, railgunRegisterer, false);

            Entity[] hotStickEntity = BobuxRegisterer.getEntitiesLine(slightlyExtendedPlayerLoc, 30, 1, 1, playerEyeVector);
            if (hotStickEntity != null) {
                Vector[] theHotStickVectors = {playerEyeVector, playerEyeVector};
                Location[] theHotStickLocs = {playerEyeLoc, hotStickEntity[0].getLocation()};
                RegistererOption theHotStickOption = new RegistererOption(RegistererType.LINE, playerEyeLoc, 30, 1, 1, playerEyeVector);
                BobuxRegisterer theHotStickRegisterer = new BobuxRegisterer(theHotStickOption, player, playerLocation, theHotStickVectors, theHotStickLocs);
                PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.theHotStick, player, EquipmentSlot.HAND, theHotStickRegisterer, false);
            }

            Entity[] BW5Entity = BobuxRegisterer.getEntitiesLine(slightlyExtendedPlayerLoc, 30, 1, 1, playerEyeVector);
            if (BW5Entity != null && BobuxUtils.checkTotalItems(player.getInventory(), BobuxItemInterface.BW5Ammo.getStack()) != null) {
                Vector[] BW5Vectors = {playerEyeVector, playerEyeVector, playerEyeVector, playerEyeVector, playerEyeVector, playerEyeVector};
                Location elevatedEnemyLoc = new Location(player.getWorld(), 
                BW5Entity[0].getLocation().getX(), 
                BW5Entity[0].getLocation().getY() + 1, 
                BW5Entity[0].getLocation().getZ());
                Location[] BW5Locs = {playerEyeLoc, elevatedEnemyLoc, elevatedEnemyLoc, elevatedEnemyLoc, elevatedEnemyLoc, elevatedEnemyLoc};
                RegistererOption BW5Option = new RegistererOption(RegistererType.LINE, playerEyeLoc, 30, 1, 1, playerEyeVector);
                BobuxRegisterer BW5Registerer = new BobuxRegisterer(BW5Option, player, playerLocation, BW5Vectors, BW5Locs);
                PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.BW5, player, EquipmentSlot.HAND, BW5Registerer, false);
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

            BobuxRegisterer harmfulSubstanceRegisterer = new BobuxRegisterer(player, damagedEntity);

            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.harmfulSubstance, player, EquipmentSlot.HAND, harmfulSubstanceRegisterer, false);

            //Set up for 
            Vector slightKnockUp = new Vector(playerEyeVector.getX(), playerEyeVector.getY() + 1, playerEyeVector.getZ());
            RegistererOption cleaverOption = new RegistererOption(RegistererType.SPHERE, damagedEntityLoc, 2, 3, 0, playerEyeVector);
            BobuxRegisterer cleaverRegisterer = new BobuxRegisterer(player, damagedEntity);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.cleaver, player, EquipmentSlot.HAND, cleaverRegisterer, false);

        }
    }

    @EventHandler
    public void onSpace(PlayerInputEvent e) {
        Player player = e.getPlayer();
        Vector playerEyeVector = player.getEyeLocation().getDirection();
        if (player.isOnGround()) {
            Entity[] playerAsArray = {player};
            if (e.getInput().isJump()) {
                BobuxRegisterer bounceBootsRegisterer = new BobuxRegisterer(player, new Vector(0,1,0));
                PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.bounceBoots, player, EquipmentSlot.FEET, bounceBootsRegisterer, false);
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
        Location playerEyeLocation = player.getEyeLocation();

        BobuxRegisterer lineSpawnerRegisterer = new BobuxRegisterer(player);
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
                BobuxRegisterer lineSpawnerRegisterer = new BobuxRegisterer(player);
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

            //Stupid stupid stupid
            Runnable passiveRunnable = new Runnable(){
            public void run() {
                BobuxRegisterer lineSpawnerRegisterer = new BobuxRegisterer(player);
                PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.lineSpawner, player, EquipmentSlot.HAND, lineSpawnerRegisterer, true);
                }
            };
            scheduler.runTaskLater(plugin, passiveRunnable, 1);
        }
    }
}
