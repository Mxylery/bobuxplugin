package io.github.mxylery.bobuxplugin.guis.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.guis.BobuxGUI;
import io.github.mxylery.bobuxplugin.guis.bounty.BountyGUI;
import io.github.mxylery.bobuxplugin.guis.questboard.QuestBoardGUI;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class MainGUI extends BobuxGUI {

    public MainGUI(Inventory inventory, Player player, BobuxPlugin plugin) {
        super(inventory, player, plugin);
    }

    protected void setGUI() {
        guiStructure.addSlot(11, BobuxItemInterface.market.getStack());
        guiStructure.addSlot(13, BobuxItemInterface.questboard.getStack());
        guiStructure.addSlot(15, BobuxItemInterface.bounty.getStack());
    }

    protected void slotHit(int slot) {
        switch (slot) {
            case 11: new MarketGUI(Bukkit.createInventory(player, 36), player, plugin);
            break;
            case 13: new QuestBoardGUI(Bukkit.createInventory(player, 45), player, plugin);
            break;
            case 15: new BountyGUI(Bukkit.createInventory(player, 27), player, plugin);
            break;
        }
    }


}
