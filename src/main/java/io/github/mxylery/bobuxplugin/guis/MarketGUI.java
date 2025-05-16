package io.github.mxylery.bobuxplugin.guis;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.listeners.BobuxGUI;

public class MarketGUI extends BobuxGUI{
    
    private ItemStack[] shopList;

    public MarketGUI(Inventory inventory, Player player, BobuxPlugin plugin) {
        super(inventory, player, plugin);
    }

    public void slotHit(int slot) {
        
    }

    protected void setGUI() {

    }

}
