package io.github.mxylery.bobuxplugin.core;

import org.bukkit.inventory.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class BobuxGUI implements Listener {

    private Inventory inventory;

    public BobuxGUI (Inventory inventory) {
        this.inventory = inventory;
    }

    @EventHandler
    public void onInvInteract(InventoryClickEvent e) {
        if (e.getInventory().equals(inventory)) {

            e.setCancelled(true);
            slotHit(e.getSlot());

        }
    }

    public abstract void slotHit(int slot);

}