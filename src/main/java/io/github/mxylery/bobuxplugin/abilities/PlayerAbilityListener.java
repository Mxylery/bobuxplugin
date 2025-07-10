package io.github.mxylery.bobuxplugin.abilities;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInputEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class PlayerAbilityListener implements Listener {

    private BobuxPlugin plugin;
    private BukkitScheduler scheduler = BobuxTimer.getScheduler();  

    public PlayerAbilityListener(BobuxPlugin plugin) {
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
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.testingItem, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.bouncingItem, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.peaceTreaty, player, EquipmentSlot.HAND);
        //Air right clicks
        } else if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {

            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.hurriedStopwatch, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.railgun, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.theHotStick, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.kungFuGloves, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.fruitcakeAndCookies, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.bobuxinator, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.lesserLootbox, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.lootbox, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.greaterLootbox, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.flockingFeather, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.hypeTrain, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.slowPogo, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.pogoLauncher, player, EquipmentSlot.HAND);
            if (BobuxUtils.checkTotalItems(player.getInventory(), BobuxItemInterface.BW5Ammo.getStack()) != null) {
                PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.BW5, player, EquipmentSlot.HAND);
            } 
        } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (PlayerAbilityManager.checkForSlot(BobuxItemInterface.fruitcakeAndCookies, player, EquipmentSlot.HAND)
            || PlayerAbilityManager.checkForSlot(BobuxItemInterface.kungFuGloves, player, EquipmentSlot.HAND) 
            || PlayerAbilityManager.checkForSlot(BobuxItemInterface.lesserLootbox, player, EquipmentSlot.HAND) 
            || PlayerAbilityManager.checkForSlot(BobuxItemInterface.lootbox, player, EquipmentSlot.HAND) 
            || PlayerAbilityManager.checkForSlot(BobuxItemInterface.greaterLootbox, player, EquipmentSlot.HAND) 
            || BobuxUtils.checkWithoutDuraAmnt(e.getItem(), BobuxItemInterface.bobuxSquare)
            || BobuxUtils.checkWithoutDuraAmnt(e.getItem(), BobuxItemInterface.bobuxCube)
            || BobuxUtils.checkWithoutDuraAmnt(e.getItem(), BobuxItemInterface.bobuxTesseract)) {
                e.setCancelled(true);
            }
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.slowPogo, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.pogoLauncher, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.flockingFeather, player, EquipmentSlot.HAND);
        } else if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.bouncingItem, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.peaceTreaty, player, EquipmentSlot.HAND);
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        ItemStack cursorStack = e.getCursor();
        ItemStack clickedOnStack = e.getCurrentItem();

        if (cursorStack != null && clickedOnStack != null) {
            ItemMeta clickedOnMeta = clickedOnStack.getItemMeta();
            if (e.getClick().isRightClick()) {
                if (clickedOnStack.getType().equals(Material.FISHING_ROD)) {
                    if (BobuxUtils.checkWithoutDuraAmnt(cursorStack, BobuxItemInterface.culturalCoreLOTS)) {
                        clickedOnMeta.addEnchant(Enchantment.LUCK_OF_THE_SEA, 4, true);
                        clickedOnStack.setItemMeta(clickedOnMeta);
                        int prevAmnt = cursorStack.getAmount();
                        cursorStack.setAmount(prevAmnt - 1);
                    } else if (BobuxUtils.checkWithoutDuraAmnt(cursorStack, BobuxItemInterface.culturalCoreLure)) {
                        clickedOnMeta.addEnchant(Enchantment.LURE, 4, true);
                        clickedOnStack.setItemMeta(clickedOnMeta);
                        int prevAmnt = cursorStack.getAmount();
                        cursorStack.setAmount(prevAmnt - 1);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onBreed(EntityBreedEvent e) {
        ItemStack food = e.getBredWith();
        Entity mother = e.getMother();
        
        PlayerAbilityManager.checkForItemUse(food, BobuxItemInterface.freakyCarrot, mother);
        PlayerAbilityManager.checkForItemUse(food, BobuxItemInterface.freakyWheat, mother);
        PlayerAbilityManager.checkForItemUse(food, BobuxItemInterface.freakySeeds, mother);
    }

    @EventHandler
    public void onProjThrow(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Player) {
            Player player = (Player) e.getEntity().getShooter();
            if (PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.straightPearl, player, EquipmentSlot.HAND)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        Player player = e.getPlayer();
        PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.stinkyPants, player, EquipmentSlot.LEGS);
    }

    //Any abilities that should be activated when hitting an enemy
    @EventHandler
    public void onDealingHit(EntityDamageEvent e) {
        if (e.getDamageSource().getCausingEntity() instanceof Player) {
            Player player = (Player) e.getDamageSource().getCausingEntity();
            Entity damagedEntity = e.getEntity();

            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.cleaver, player, EquipmentSlot.HAND, damagedEntity);
            if (!player.isSneaking()) {
                PlayerAbilityManager.checkForPassiveSlotMatch(BobuxItemInterface.kungFuGloves, player, EquipmentSlot.HAND, damagedEntity);            
            }
        }
    }

    @EventHandler
    public void onSpace(PlayerInputEvent e) {
        Player player = e.getPlayer();
        if (e.getInput().isJump()) {
           
        }
    }

    @EventHandler
    public void onItemSwap(PlayerItemHeldEvent e) {
        Player player = e.getPlayer();

        //Stupid stupid stupid 
        Runnable passiveRunnable = new Runnable(){
            public void run() {

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
                    PlayerAbilityManager.checkForPassiveArmorSetMatch(BobuxItemInterface.bardSet, player);
                }
            };
            scheduler.runTaskLater(plugin, passiveRunnable, 1);
        }
    }

    @EventHandler
    public void onFish(PlayerFishEvent e) {
        Player player = e.getPlayer();

        if (e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
            PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.fishermansPole, player, EquipmentSlot.HAND);
            PlayerAbilityManager.checkForArmorSetMatch(BobuxItemInterface.fishermansSet, player);
        }
    }

    /**
     * This method handles player consume events 
     */
    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();
        if (PlayerAbilityManager.checkForSlotMatch(BobuxItemInterface.bobuxBrew, player, EquipmentSlot.HAND)) {
            e.setCancelled(true);
            Runnable runnable = new Runnable(){
                public void run() { 
                    int[] indexList = BobuxUtils.checkTotalItems(player.getInventory(), BobuxItemInterface.bobuxBrewRemnants.getStack());
                    if (indexList != null) {
                        player.getInventory().setItem(indexList[0], BobuxItemInterface.bobuxBrew.getStack());
                    }
                    System.out.println("Happened");
                }
            };
            scheduler.runTaskLater(plugin, runnable, 600);
        }
    }
}

