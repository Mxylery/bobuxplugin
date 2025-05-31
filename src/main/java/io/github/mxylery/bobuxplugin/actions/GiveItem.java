package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.core.BobuxAction;

public class GiveItem extends BobuxAction {
    
    private ItemStack stack;
    private int amount;

    public GiveItem(ItemStack stack, int amount) {
        this.stack = stack;
        this.amount = amount;
        super.requiresInventory = true;
    }

    public void adjustPerc(double adjustment) {
        
    }

    public void adjustFlat(double adjustment) {

    }

    public void run() {
        for (int i = 0; i < amount; i++) {
            inventory.addItem(stack);
        }
     }
}

