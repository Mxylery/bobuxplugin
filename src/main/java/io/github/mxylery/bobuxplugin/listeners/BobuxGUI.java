package io.github.mxylery.bobuxplugin.listeners;

import org.bukkit.inventory.*;

import io.github.mxylery.bobuxplugin.BobuxPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class BobuxGUI implements Listener {

    protected BobuxPlugin plugin;
    protected Inventory inventory;
    protected int inventorySize;
    protected ItemStack[] slotList;
    protected int[] slotIndex;
    protected Player player;

    protected abstract void setGUI();
    protected abstract void slotHit(int slot);
    protected abstract void specialGUIOption();

    //When making a GUI, the player 
    public BobuxGUI(Inventory inventory, Player player, BobuxPlugin plugin) {
        this.inventory = inventory;
        this.inventorySize = inventory.getSize();
        this.player = player;
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        specialGUIOption();
        setGUI();
        generateGUI();
        openGUI();
    }

    @EventHandler
    public void onInvInteract(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {

            e.setCancelled(true);
            slotHit(e.getSlot());

        }
    }

    protected void generateGUI() {
        for (int i = 0; i < slotList.length; i++) {
            inventory.setItem(slotIndex[i], slotList[i]);
        }
    }

    protected void openGUI() {
        player.openInventory(inventory);
    }

    public void adjustSlots(ItemStack[] slotList, int[] slotIndex) {
        this.slotList = slotList;
        this.slotIndex = slotIndex;
        generateGUI();
    }
}