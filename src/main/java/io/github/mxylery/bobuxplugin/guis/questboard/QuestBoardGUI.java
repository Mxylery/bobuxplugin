package io.github.mxylery.bobuxplugin.guis.questboard;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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

    public QuestBoardGUI(Inventory inventory, Player player, BobuxPlugin plugin) {
        super(inventory, player, plugin);
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
        super.slotList = finalList; 
        int[] finalIndices = {11, 20, 29, 14, 15, 16, 23, 25, 32, 33, 34};
        super.slotIndex = finalIndices;
    }

    protected void slotHit(int slot) {
        switch (slot) {
            case 11: 
            if (new BobuxTransaction(player, bobuxQuests[0]).wentThrough()) {
                scheduler.runTaskLater(BobuxTimer.getPlugin(), new Runnable(){
                    public void run() {
                        if (bobuxQuests[0].completed) {
                            BobuxGUIGenerator.randomizeQuest(0, 3);
                        }
                    }
                }, 600);
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
                }, 600);
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
                }, 600);
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
