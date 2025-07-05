package io.github.mxylery.bobuxplugin.guis.bounty;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.BobuxTransaction;
import io.github.mxylery.bobuxplugin.guis.BobuxGUI;
import io.github.mxylery.bobuxplugin.guis.BobuxGUIGenerator;

public class BountyGUI extends BobuxGUI {
    
    private BobuxBounty[] bounties;
    private Player player;

    public BountyGUI(Player player) {
        super(Bukkit.createInventory(null, 45), BobuxTimer.getPlugin());
        this.player = player;
        updateGUI();
    }

    protected void setGUI() {
        bounties = BobuxGUIGenerator.playerBountyMap.get(player);
        ItemStack[] finalList = new ItemStack[6];
        int compensation = 0;
        for (int i = 0; i < 6; i++) {
            ArrayList<String> lore = new ArrayList<String>();
            ItemStack bountyStack;
            String colorString;
            //Sets the font color and item of each bounty stack depending on completion state
            if (!bounties[i].getState()) {
                bountyStack = new ItemStack(Material.PAPER);
                colorString = "§7";
            } else {
                bountyStack = new ItemStack(Material.MAP);
                colorString = "§c";
            }
            ItemMeta bountyMeta = bountyStack.getItemMeta();
            //Sets description of each bounty
            for (int j = 0; j < 3; j++) {
                String finalString = "";
                String[] ogStringArray = bounties[i].getStacks()[j].getType().toString().split("_");
                for (int h = 0; h < ogStringArray.length; h++) {
                    String firstLetter = ogStringArray[h].substring(0,1);
                    String lastLetters = ogStringArray[h].substring(1,ogStringArray[h].length()).toLowerCase();
                    finalString = finalString + firstLetter + lastLetters + " ";
                }
                lore.add(colorString + finalString + "x" + bounties[i].getStacks()[j].getAmount());
            }
            compensation += bounties[i].getCompensation();
            lore.add(colorString + "Reward: $" + compensation + "BBX");
            bountyMeta.setLore(lore);
            String bountyType;
            switch (bounties[i].getType()) {
                case 0: bountyType = "Junk";
                break;
                case 1: bountyType = "Goods";
                break;
                case 2: bountyType = "Rarity";
                break;
                default: bountyType = "Junk";
                break;
            }
            bountyMeta.setItemName("§f§l" + bountyType + " Grant ");
            bountyStack.setItemMeta(bountyMeta);
            finalList[i] = bountyStack;
            //Resets the compensation for each loop
            compensation = 0;
        }
        guiStructure.addSlot(10, finalList[0]);
        guiStructure.addSlot(11, finalList[1]);
        guiStructure.addSlot(12, finalList[2]);
        guiStructure.addSlot(29, finalList[3]);
        guiStructure.addSlot(30, finalList[4]);
        guiStructure.addSlot(25, finalList[5]);
    }

    protected void slotHit(InventoryClickEvent e, Player player, int slot) {
        switch (slot) {
            case 10: new BobuxTransaction(player, bounties[0]);
            break;
            case 11: new BobuxTransaction(player, bounties[1]);
            break;
            case 12: new BobuxTransaction(player, bounties[2]);
            break;
            case 20: new BobuxTransaction(player, bounties[3]);
            break;
            case 21: new BobuxTransaction(player, bounties[4]);
            break;
            case 25: new BobuxTransaction(player, bounties[5]);
            break;
        }
    }
}
