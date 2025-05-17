package io.github.mxylery.bobuxplugin.guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxTransaction;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.listeners.BobuxGUI;

public class MainGUI extends BobuxGUI {

    public MainGUI(Inventory inventory, Player player, BobuxPlugin plugin) {
        super(inventory, player, plugin);
    }

    protected void setGUI() {
        ItemStack[] slotList = {BobuxItemInterface.market.getStack()};
        super.slotList = slotList;
        int[] slotIndex = {13};
        super.slotIndex = slotIndex;
    }

    protected void slotHit(int slot) {
        if (slot == 13) {
            new MarketGUI(Bukkit.createInventory(player, 36), player, plugin);
        }
    }

    protected void specialGUIOption() {
        
    }


}
