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
    protected Player player;

    protected abstract void setGUI();
    protected abstract void slotHit(int slot);

    //When making a GUI, the player 
    public BobuxGUI(Inventory inventory, Player player, BobuxPlugin plugin) {
        this.inventory = inventory;
        this.guiStructure = new GUIStructure(inventory.getSize());
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

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (e.getInventory().equals(inventory)) {

            

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

    protected void openGUI() {
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

    public void adjustSlots(GUIStructure guiStructure) {
        this.guiStructure = guiStructure;
        generateGUI();
    }

    protected void specialGUIOption() {

    }
    
}