package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.vectors.BobuxUtils;

//This class will be used very often for consumable-type items (one-time use)
public class DeleteSelf extends BobuxAction {

    private boolean requiresStack;
    private ItemStack stack;
    private EquipmentSlot equSlot;
    private int amount;
    
    public DeleteSelf(EquipmentSlot slot, int amount, boolean requiresCondition) {
        this.stack = null;
        this.equSlot = slot;
        this.amount = amount;
        super.requiresCondition = requiresCondition;
        super.requiresEntity = true;
        this.requiresStack = false;
    }

    public DeleteSelf(ItemStack stack, int amount, boolean requiresCondition) {
        this.stack = stack;
        this.equSlot = null;
        this.amount = amount;
        super.requiresCondition = requiresCondition;
        super.requiresEntity = true;
        this.requiresStack = true;
    }

    public void adjustPerc(double adjustment) {
        
    }

    public void adjustFlat(double adjustment) {

    }

    public void run() {
    if (requiresStack) {
        for (int i = 0; i < super.entityList.length; i++) {
            Entity currentEntity = super.entityList[i];
            if (currentEntity instanceof Player) {
                Player currentPlayer = (Player) currentEntity;
                PlayerInventory inventory = currentPlayer.getInventory();
                BobuxUtils.removeTotalItems(inventory, stack, amount);
            } 
        }
    } else {
        for (int i = 0; i < super.entityList.length; i++) {
            Entity currentEntity = super.entityList[i];
            if (currentEntity instanceof Player) {
                Player currentPlayer = (Player) currentEntity;
                ItemStack stack = currentPlayer.getInventory().getItem(equSlot);
                stack.setAmount(stack.getAmount() - amount);
                currentPlayer.getInventory().setItem(equSlot, stack);
            } 
        }
    }
        
    }

}
