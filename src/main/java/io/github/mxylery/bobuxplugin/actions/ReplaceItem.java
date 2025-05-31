package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;

public class ReplaceItem extends BobuxAction {
    
    private ItemStack stackToReplace;
    private ItemStack stackToGive;

    public ReplaceItem(ItemStack stackToReplace, ItemStack stackToGive) {
        this.stackToReplace = stackToReplace;
        this.stackToGive = stackToGive;
        super.requiresInventory = true;
    }

    public void adjustPerc(double adjustment) {
        
    }

    public void adjustFlat(double adjustment) {

    }

    public void run() {
        int[] indexList = BobuxUtils.checkTotalItems(super.inventory, stackToReplace);
        if (indexList != null) {
            inventory.setItem(indexList[0], stackToGive);
        }
    }
}

