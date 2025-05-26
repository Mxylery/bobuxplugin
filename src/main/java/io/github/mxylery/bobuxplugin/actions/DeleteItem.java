package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;

public class DeleteItem extends BobuxAction {
    
    private ItemStack stack;
    private int amount;

    public DeleteItem(ItemStack stack, int amount, boolean requiresCondition) {
        this.stack = stack;
        this.amount = amount;
        super.requiresCondition = requiresCondition;
        super.requiresInventory = true;
    }

    public void adjustPerc(double adjustment) {
        
    }

    public void adjustFlat(double adjustment) {

    }

    public void run() {
        BobuxUtils.removeTotalItems(inventory, stack, amount);
     }
}

