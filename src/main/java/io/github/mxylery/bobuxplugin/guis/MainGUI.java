package io.github.mxylery.bobuxplugin.guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.listeners.BobuxGUI;

public class MainGUI extends BobuxGUI {

    public MainGUI(Inventory inventory, Player player, BobuxPlugin plugin) {
        super(inventory, player, plugin);
    }

    protected void setGUI() {
        ItemStack[] slotList = {BobuxItemInterface.market.getStack(), BobuxItemInterface.bounty.getStack()};
        super.slotList = slotList;
        int[] slotIndex = {11, 15};
        super.slotIndex = slotIndex;
    }

    protected void slotHit(int slot) {
        if (BobuxTimer.getDaysPassed() != -1) {
            switch (slot) {
                case 11: new MarketGUI(Bukkit.createInventory(player, 36), player, plugin);
                break;
                case 15: new BountyGUI(Bukkit.createInventory(player, 27), player, plugin);
                break;
            }
        } else {
            switch (slot) {
                case 11: player.sendMessage("The market is currently closed...");
                break;
                case 15: new BountyGUI(Bukkit.createInventory(player, 27), player, plugin);
                break;
            }
        }
    }

    protected void specialGUIOption() {
        
    }


}
