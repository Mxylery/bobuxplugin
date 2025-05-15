package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.core.BobuxAction;

//This class will be used very often for consumable-type items (one-time use)
public class DeleteSelf extends BobuxAction {

    private EquipmentSlot equSlot;
    private int amount;
    
    public DeleteSelf(EquipmentSlot slot, int amount, boolean requiresCondition) {
        this.equSlot = slot;
        this.amount = amount;
        super.requiresCondition = requiresCondition;
        super.requiresEntity = true;
        super.requiresVector = false;
    }

    public void adjustPerc(double adjustment) {
        
    }

    public void adjustFlat(double adjustment) {

    }

    public void run() {
        for (int i = 0; i < super.entityList.length; i++) {
            Entity currentEntity = super.entityList[i];
            if (currentEntity instanceof Player) {
                Player currentPlayer = (Player) currentEntity;
                ItemStack stack = currentPlayer.getInventory().getItem(equSlot);
                stack.setAmount(stack.getAmount() - amount);
            } 
        }
    }

}
