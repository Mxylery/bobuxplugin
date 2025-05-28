package io.github.mxylery.bobuxplugin.guis;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxDay;
import io.github.mxylery.bobuxplugin.core.BobuxTransaction;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.core.BobuxDay.DayType;
import io.github.mxylery.bobuxplugin.listeners.BobuxGUI;
import io.github.mxylery.bobuxplugin.listeners.BobuxGUIGenerator;

public class MarketGUI extends BobuxGUI{

    public MarketGUI(Inventory inventory, Player player, BobuxPlugin plugin) {
        super(inventory, player, plugin);
    }

    protected void specialGUIOption() {

    }

    protected void slotHit(int slot) {
        if (BobuxDay.getDay() == DayType.AVARICIOUS) {
            switch (slot) {
            case 18: new BobuxTransaction(player, (int) (BobuxGUIGenerator.marketMenu[0].getPrice()*0.9), BobuxGUIGenerator.marketMenu[0].getStack());
            break;
            case 20: new BobuxTransaction(player, (int) (BobuxGUIGenerator.marketMenu[1].getPrice()*0.9), BobuxGUIGenerator.marketMenu[1].getStack());
            break; 
            case 22: new BobuxTransaction(player, (int) (BobuxGUIGenerator.marketMenu[2].getPrice()*0.9), BobuxGUIGenerator.marketMenu[2].getStack());
            break;
            case 24: new BobuxTransaction(player, (int) (BobuxGUIGenerator.marketMenu[3].getPrice()*0.9), BobuxGUIGenerator.marketMenu[3].getStack());
            break;
            case 26: new BobuxTransaction(player, (int) (BobuxGUIGenerator.marketMenu[4].getPrice()*0.9), BobuxGUIGenerator.marketMenu[4].getStack());
            break;
            }
        } else {
            switch (slot) {
            case 19: new BobuxTransaction(player, BobuxGUIGenerator.marketMenu[0].getPrice(), BobuxGUIGenerator.marketMenu[0].getStack());
            break;
            case 22: new BobuxTransaction(player, BobuxGUIGenerator.marketMenu[1].getPrice(), BobuxGUIGenerator.marketMenu[1].getStack());
            break; 
            case 25: new BobuxTransaction(player, BobuxGUIGenerator.marketMenu[2].getPrice(), BobuxGUIGenerator.marketMenu[2].getStack());
            break;
            }
        }
    }

    protected void setGUI() {
        if (BobuxDay.getDay() != DayType.AVARICIOUS) {
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
        } else {
            ItemStack[] finalList = new ItemStack[10];
            for (int i = 0; i < 5; i++) {
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
                buyButtonMeta.setItemName(colorString + "§l$" + (int) (BobuxGUIGenerator.marketMenu[i].getPrice()*0.9) + "BBX (10% OFF!)");
                buyButtonStack.setItemMeta(buyButtonMeta);
                finalList[2*i + 1] = buyButtonStack;
            }
            super.slotList = finalList; 
            int[] finalIndices = {9, 18, 11, 20, 13, 22, 15, 24, 17, 26};
            super.slotIndex = finalIndices;
        }
    }
}
