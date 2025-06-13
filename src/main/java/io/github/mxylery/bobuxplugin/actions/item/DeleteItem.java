package io.github.mxylery.bobuxplugin.actions.item;

import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;

public class DeleteItem extends BobuxAction {
    
    private ItemStack stack;
    private int amount;

    public DeleteItem(ItemStack stack, int amount) {
        this.stack = stack;
        this.amount = amount;
        super.requiresInventory = true;
    }
    
    public void run() {
        BobuxUtils.removeTotalItems(inventory, stack, amount);
     }
}

