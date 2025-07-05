package io.github.mxylery.bobuxplugin.guis.core;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.mxylery.bobuxplugin.core.BobuxDay;
import io.github.mxylery.bobuxplugin.core.BobuxTransaction;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.guis.BobuxGUI;
import io.github.mxylery.bobuxplugin.guis.BobuxGUIGenerator;
import io.github.mxylery.bobuxplugin.core.BobuxDay.DayType;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;

public class MarketGUI extends BobuxGUI {

    private Player player;

    public MarketGUI(Player player) {
        super(Bukkit.createInventory(null, 36), BobuxTimer.getPlugin());
        this.player = player;
        updateGUI();
    }

    protected void slotHit(InventoryClickEvent e, Player player, int slot) {
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
            guiStructure.addSlot(10, finalList[0]);
            guiStructure.addSlot(19, finalList[1]);
            guiStructure.addSlot(13, finalList[2]);
            guiStructure.addSlot(22, finalList[3]);
            guiStructure.addSlot(16, finalList[4]);
            guiStructure.addSlot(25, finalList[5]);
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
            guiStructure.addSlot(9, finalList[0]);
            guiStructure.addSlot(18, finalList[1]);
            guiStructure.addSlot(11, finalList[2]);
            guiStructure.addSlot(20, finalList[3]);
            guiStructure.addSlot(13, finalList[4]);
            guiStructure.addSlot(22, finalList[5]);
            guiStructure.addSlot(15, finalList[6]);
            guiStructure.addSlot(24, finalList[7]);
            guiStructure.addSlot(17, finalList[8]);
            guiStructure.addSlot(26, finalList[9]);
        }
    }
}
