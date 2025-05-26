package io.github.mxylery.bobuxplugin.guis;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxBounty;
import io.github.mxylery.bobuxplugin.core.BobuxTransaction;
import io.github.mxylery.bobuxplugin.listeners.BobuxGUI;
import io.github.mxylery.bobuxplugin.listeners.BobuxGUIGenerator;

public class BountyGUI extends BobuxGUI {
    
    private BobuxBounty[] bounties;

    public BountyGUI(Inventory inventory, Player player, BobuxPlugin plugin) {
        super(inventory, player, plugin);
    }

    protected void setGUI() {
        bounties = BobuxGUIGenerator.playerBountyMap.get(player);
        ItemStack[] finalList = new ItemStack[3];
        int compensation = 0;
        for (int i = 0; i < 3; i++) {
            ArrayList<String> lore = new ArrayList<String>();
            ItemStack bountyStack;
            String colorString;
            if (!bounties[i].getState()) {
                bountyStack = new ItemStack(Material.PAPER);
                colorString = "§7";
            } else {
                bountyStack = new ItemStack(Material.MAP);
                colorString = "§c";
            }
            ItemMeta bountyMeta = bountyStack.getItemMeta();
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
            bountyMeta.setItemName("§f§l" + "Bobux Bounty " + (i + 1));
            bountyStack.setItemMeta(bountyMeta);
            finalList[i] = bountyStack;
            compensation = 0;
        }
        super.slotList = finalList; 
        int[] finalIndices = {10, 13, 16};
        super.slotIndex = finalIndices;
    }

    protected void slotHit(int slot) {
        switch (slot) {
            case 10: new BobuxTransaction(player, bounties[0]);
            break;
            case 13: new BobuxTransaction(player, bounties[1]);
            break;
            case 16: new BobuxTransaction(player, bounties[2]);
            break;
        }
    }

    protected void specialGUIOption() {
        
    }
}
