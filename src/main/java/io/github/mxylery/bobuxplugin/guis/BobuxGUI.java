package io.github.mxylery.bobuxplugin.guis;

import org.bukkit.inventory.*;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.data_structures.GUIStructure;

import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public abstract class BobuxGUI implements Listener {

    protected BobuxPlugin plugin;
    protected Inventory inventory;
    protected GUIStructure guiStructure;

    protected abstract void setGUI();
    protected abstract void slotHit(InventoryClickEvent e, Player player, int slot);

    //When making a GUI, the player 
    public BobuxGUI(Inventory inventory, BobuxPlugin plugin) {
        this.inventory = inventory;
        this.guiStructure = new GUIStructure(inventory.getSize());
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    //When making a GUI, the player 
    public BobuxGUI(Inventory inventory, Player player, BobuxPlugin plugin) {
        this.inventory = inventory;
        this.guiStructure = new GUIStructure(inventory.getSize());
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onInvInteract(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {

            e.setCancelled(true);
            slotHit(e, (Player) e.getWhoClicked(), e.getSlot());

        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(inventory)) {

            

        }
    }

    public void updateGUI() {
        setGUI();
        generateGUI();
    }

    protected void generateSlot(int index) {
        if (guiStructure.hasSlot(index)) {
            inventory.setItem(index, guiStructure.getStack(index));
        } else {
            inventory.setItem(index, null);
        }
    }

    protected void generateGUI() {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (guiStructure.hasSlot(i)) {
                inventory.setItem(i, guiStructure.getStack(i));
            } else {
                inventory.setItem(i, null);
            }
        }
    }

    public void openGUI(Player player) {
        player.openInventory(inventory);
    }

    protected void deleteSlots(int[] slotsToDelete) {
        for (int i = 0; i < slotsToDelete.length; i++) {
            guiStructure.removeSlot(slotsToDelete[i]);
        }
        generateGUI();
    }

    protected void deleteSlot(int slotToDelete) {
        guiStructure.removeSlot(slotToDelete);
        generateGUI();
    }

    protected void addSlot(ItemStack stack, int slotToAdd) {
        guiStructure.addSlot(slotToAdd, stack);
        generateGUI();
    }

    public void adjustSlots(GUIStructure guiStructure) {
        this.guiStructure = guiStructure;
        generateGUI();
    }
    
}