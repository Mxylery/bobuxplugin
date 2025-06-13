package io.github.mxylery.bobuxplugin.actions.spawn;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;

public class SpawnItem extends BobuxAction {
    
    private ItemStack stack;
    private int amount;

    public SpawnItem(ItemStack stack, int amount) {
        this.stack = stack;
        this.amount = amount;
        super.requiresLocation = true;
    }

    public void run() {
        for (int i = 0; i < amount; i++) {
            if (!stack.getType().equals(Material.AIR)) {
                super.location.getWorld().dropItem(location, stack);
            }
        }
    }
}
