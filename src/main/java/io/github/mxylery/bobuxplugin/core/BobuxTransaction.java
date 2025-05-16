package io.github.mxylery.bobuxplugin.core;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BobuxTransaction {
    
    private int cost;
    private Player firstPlayer;
    private Player secondPlayer;
    private ItemStack stack;

    public BobuxTransaction(Player firstPlayer, int cost, ItemStack stack) {
        this.firstPlayer = firstPlayer;
        this.cost = cost;
        this.stack = stack;
    }

    private void validate() {

    }

    private int calculateTotalBBX() {
        return 0;
    }

    private void removeTotalBBX() {
        
    }

}
