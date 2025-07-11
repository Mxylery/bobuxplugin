package io.github.mxylery.bobuxplugin.guis.questboard;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.BobuxTransaction;
import io.github.mxylery.bobuxplugin.events.BobuxTransactionEvent;
import io.github.mxylery.bobuxplugin.guis.BobuxGUI;
import io.github.mxylery.bobuxplugin.guis.BobuxGUIGenerator;

public class QuestBoardGUI extends BobuxGUI {
    
    private BukkitScheduler scheduler = BobuxPlugin.getScheduler();
    private BobuxQuest[] bobuxQuests;

    public QuestBoardGUI() {
        super(Bukkit.createInventory(null, 45), BobuxTimer.getPlugin());
        updateGUI();
    }

    protected void setGUI() {
        bobuxQuests = BobuxGUIGenerator.questMenu;
        ItemStack[] finalList = new ItemStack[3 + 8];
        for (int i = 0; i < 3; i++) {
            ArrayList<String> lore = new ArrayList<String>();
            ItemStack questStack;
            String colorString;
            if (!bobuxQuests[i].getState()) {
                questStack = new ItemStack(Material.BOOK);
                colorString = "§a";
            } else {
                questStack = new ItemStack(Material.WRITABLE_BOOK);
                colorString = "§c";
            }
            ItemMeta questMeta = questStack.getItemMeta();
            for (int j = 0; j < 3; j++) {
                lore.add("");
                lore.add("§7Quest: Kill " + bobuxQuests[i].getQuests()[j].getName() + "s");
                lore.add("§8" + bobuxQuests[i].getDrop(j).getName() + " x" + bobuxQuests[i].getDropAmount(j));
            }
            lore.add("");
            lore.add(colorString + "Reward: $" + bobuxQuests[i].getCompensation() + "BBX");
            questMeta.setLore(lore);
            questMeta.setItemName("§f§l" + "Bobux Beginner Quest " + (i + 1));
            questStack.setItemMeta(questMeta);
            finalList[i] = questStack;
        }
        ItemStack paneStack = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        for (int i = 3; i < 11; i++) {
            finalList[i] = paneStack;
        }
        guiStructure.addSlot(11, finalList[0]);
        guiStructure.addSlot(20, finalList[1]);
        guiStructure.addSlot(29, finalList[2]);
        guiStructure.addSlot(14, finalList[3]);
        guiStructure.addSlot(15, finalList[4]);
        guiStructure.addSlot(16, finalList[5]);
        guiStructure.addSlot(23, finalList[6]);
        guiStructure.addSlot(25, finalList[7]);
        guiStructure.addSlot(32, finalList[8]);
        guiStructure.addSlot(33, finalList[9]);
        guiStructure.addSlot(34, finalList[10]);
    }

    protected void slotHit(InventoryClickEvent e, Player player, int slot) {
        switch (slot) {
            case 11: 
            if (new BobuxTransaction(player, bobuxQuests[0]).wentThrough()) {
                scheduler.runTaskLater(BobuxTimer.getPlugin(), new Runnable(){
                    public void run() {
                        if (bobuxQuests[0].completed) {
                            BobuxGUIGenerator.randomizeQuest(0, 3);
                        }
                    }
                }, 1200);
            }
            break;
            case 20: 
            if (new BobuxTransaction(player, bobuxQuests[1]).wentThrough()) {
                scheduler.runTaskLater(BobuxTimer.getPlugin(), new Runnable(){
                    public void run() {
                        if (bobuxQuests[1].completed) {
                            BobuxGUIGenerator.randomizeQuest(1, 3);
                        }
                    }
                }, 1200);
            }
            break;
            case 29: 
            if (new BobuxTransaction(player, bobuxQuests[2]).wentThrough()) {
                scheduler.runTaskLater(BobuxTimer.getPlugin(), new Runnable(){
                    public void run() {
                        if (bobuxQuests[2].completed) {
                            BobuxGUIGenerator.randomizeQuest(2, 3);
                        }
                    }
                }, 1200);
            }
            break;
        }
    }

    protected void specialGUIOption() {
        
    }

    @EventHandler
    public void onClaim(BobuxTransactionEvent e) {
        
    }
}
