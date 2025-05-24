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
        if (e.getAction().equals(Action.LEFT_CLICK_AIR)) {
            Entity[] playerAsArray = {player};

            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.testingItem, player, EquipmentSlot.HAND, false);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.bouncingItem, player, EquipmentSlot.HAND, false);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.kungFuGloves, player, EquipmentSlot.HAND, false);

        //Air right clicks
        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {

            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.hurriedStopwatch, player, EquipmentSlot.HAND, false);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.railgun, player, EquipmentSlot.HAND, false);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.theHotStick, player, EquipmentSlot.HAND, false);
            if (BobuxUtils.checkTotalItems(player.getInventory(), BobuxItemInterface.BW5Ammo.getStack()) != null) {
                PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.BW5, player, EquipmentSlot.HAND, false);
            } 
        }
    }

    //Any abilities that should be activated when hitting an enemy
    @EventHandler
    public void onDealingHit(EntityDamageEvent e) {
        if (e.getDamageSource().getCausingEntity() instanceof Player) {
            Player player = (Player) e.getDamageSource().getCausingEntity();
            Entity damagedEntity = e.getEntity();

            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.cleaver, player, EquipmentSlot.HAND, damagedEntity, false);

        }
    }

    @EventHandler
    public void onSpace(PlayerInputEvent e) {
        Player player = e.getPlayer();
        Vector playerEyeVector = player.getEyeLocation().getDirection();
        if (e.getInput().isJump()) {
           
        }
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        Player player = e.getPlayer();

        PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.lineSpawner, player, EquipmentSlot.HAND, true);

    }

    @EventHandler
    public void onItemSwap(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();

        //Stupid stupid stupid 
        Runnable passiveRunnable = new Runnable(){
            public void run() {
                PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.lineSpawner, player, EquipmentSlot.HAND, true);
            }
        };
        scheduler.runTaskLater(plugin, passiveRunnable, 1);
    }

    @EventHandler
    public void onEquipSwap(InventoryClickEvent e) {

        if (e.getClickedInventory() instanceof PlayerInventory) {
            PlayerInventory inventory = (PlayerInventory) e.getClickedInventory();
            Player player = (Player) inventory.getHolder();

            Runnable passiveRunnable = new Runnable(){
            public void run() {
                    PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.lineSpawner, player, EquipmentSlot.HAND, true);
                }
            };
            scheduler.runTaskLater(plugin, passiveRunnable, 1);
        }
    }
}
