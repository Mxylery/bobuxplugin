package io.github.mxylery.bobuxplugin.actions.item;

import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;

public class GiveItem extends BobuxAction {
    
    private ItemStack stack;
    private int amount;

    public GiveItem(ItemStack stack, int amount) {
        this.stack = stack;
        this.amount = amount;
        super.requiresInventory = true;
    }

    public void run() {
        for (int i = 0; i < amount; i++) {
            inventory.addItem(stack);
        }
     }
}

