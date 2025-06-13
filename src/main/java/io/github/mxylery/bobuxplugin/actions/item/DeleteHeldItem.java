package io.github.mxylery.bobuxplugin.actions.item;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;

//This class will be used very often for consumable-type items (one-time use)
public class DeleteHeldItem extends BobuxAction {

    private EquipmentSlot equSlot;
    private int amount;
    
    public DeleteHeldItem(EquipmentSlot slot, int amount) {
        this.equSlot = slot;
        this.amount = amount;
        super.requiresEntity = true;
    }

    public void run() {
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
