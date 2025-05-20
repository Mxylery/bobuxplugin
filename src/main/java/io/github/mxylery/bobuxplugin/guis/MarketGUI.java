package io.github.mxylery.bobuxplugin.guis;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.BobuxTransaction;
import io.github.mxylery.bobuxplugin.items.BobuxItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.listeners.BobuxGUI;
import io.github.mxylery.bobuxplugin.listeners.BobuxGUIGenerator;
import io.github.mxylery.bobuxplugin.vectors.BobuxUtils;

public class MarketGUI extends BobuxGUI{

    public MarketGUI(Inventory inventory, Player player, BobuxPlugin plugin) {
        super(inventory, player, plugin);
    }

    protected void specialGUIOption() {

    }

    protected void slotHit(int slot) {
        switch (slot) {
            case 19: new BobuxTransaction(player, BobuxGUIGenerator.marketMenu[0].getPrice(), BobuxGUIGenerator.marketMenu[0].getStack());
            break;
            case 22: new BobuxTransaction(player, BobuxGUIGenerator.marketMenu[1].getPrice(), BobuxGUIGenerator.marketMenu[1].getStack());
            break; 
            case 25: new BobuxTransaction(player, BobuxGUIGenerator.marketMenu[2].getPrice(), BobuxGUIGenerator.marketMenu[2].getStack());
            break;
        }
    }

    protected void setGUI() {
        ItemStack[] finalList = new ItemStack[6];
        for (int i = 0; i < 3; i++) {
            finalList[2*i] = BobuxGUIGenerator.marketMenu[i].getStack();
            ItemStack buyButtonStack;
            String colorString;
            if (BobuxGUIGenerator.marketMenu[i].getPrice() <= BobuxUtils.calculateTotalBBX(player.getInventory())) {
                buyButtonStack = new ItemStack(Material.LIME_CONCRETE);
                colorString = "§a";
            } else {
                buyButtonStack = new ItemStack(Material.RED_CONCRETE);
                colorString = "§c";
            }
            ItemMeta buyButtonMeta = buyButtonStack.getItemMeta();
            ArrayList<String> lore = new ArrayList<String>();
            lore.add(colorString + BobuxGUIGenerator.marketMenu[i].getName());
            buyButtonMeta.setLore(lore);
            buyButtonMeta.setItemName(colorString + "§l$" + BobuxGUIGenerator.marketMenu[i].getPrice() + "BBX");
            buyButtonStack.setItemMeta(buyButtonMeta);
            finalList[2*i + 1] = buyButtonStack;
        }
        super.slotList = finalList; 
        int[] finalIndices = {10, 19, 13, 22, 16, 25};
        super.slotIndex = finalIndices;
    }
}
