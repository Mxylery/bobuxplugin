package io.github.mxylery.bobuxplugin.guis.raffle;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.guis.BobuxGUI;

public class RaffleGUI extends BobuxGUI {

    private BobuxRaffle currentRaffle;
    private int currentDonation;
    private int interval;
    
    public RaffleGUI() {
        super(Bukkit.createInventory(null, 45), BobuxTimer.getPlugin());
        this.currentRaffle = BobuxPlugin.getRaffle();
        this.currentDonation = 0;
        this.interval = 0;
        updateGUI();
    }

    private void switchInterval(boolean increase) {
        if (increase && interval <= 2) {
            interval++;
        } else if (!increase && interval >= 0) {
            interval--;
        }
        updateGUI();
    }

    private void adjustDonation(boolean positive) {
        int toAdd = 1;
        if (!positive) {
            toAdd *= -1;
        }
        switch(interval) {
            case 0: currentDonation += toAdd;
            currentDonation = Math.max(0, currentDonation);
            break;
            case 1: currentDonation += 8*toAdd;
            currentDonation = Math.max(0, currentDonation);
            break;
            case 2: currentDonation += 64*toAdd;
            currentDonation = Math.max(0, currentDonation);
            break;
            case 3: currentDonation += 512*toAdd;
            currentDonation = Math.max(0, currentDonation);
            break;
            default: currentDonation++;
            break;
        }
        updateGUI();
    }

    protected void setGUI() {
        int intervalAmnt = 1;
        switch(interval) {
            case 0: intervalAmnt = 1;
            break;
            case 1: intervalAmnt = 8;
            break;
            case 2: intervalAmnt = 64;
            break;
            case 3: intervalAmnt = 512;
            break;
            default: intervalAmnt = 1;
            break;
        }
        ItemStack signStack = new ItemStack(Material.OAK_SIGN);
        ArrayList<String> signLore = new ArrayList<String>();
        signLore.add("§fWelcome to Bobux Raffles!");
        signLore.add("");
        signLore.add("§fEvery 50 bobux donated results in getting a Lesser Loot Box");
        signLore.add("§fEvery 100 bobux donated results in getting a Loot Box");
        signLore.add("§fEvery 250 bobux donated results in getting a Greater Loot Box");
        signLore.add("");
        signLore.add("§fYou will receive the participation attribute if you donate at all.");
        ItemMeta signMeta = signStack.getItemMeta();
        signMeta.setLore(signLore);
        signMeta.setDisplayName("§fRaffle");
        signStack.setItemMeta(signMeta);
        guiStructure.addSlot(1, signStack);
        
        ItemStack bobuxAdjustStack = new ItemStack(Material.LIME_CONCRETE);
        ArrayList<String> bobuxAdjustLore = new ArrayList<String>();
        bobuxAdjustLore.add("§aClick to add/remove bobux: " + intervalAmnt);
        bobuxAdjustLore.add("");
        bobuxAdjustLore.add("§7Left click to increase amount");
        bobuxAdjustLore.add("§7Right click to decrease amount");
        ItemMeta bobuxAdjustMeta = bobuxAdjustStack.getItemMeta();
        bobuxAdjustMeta.setDisplayName("§aAdjust Amount");
        bobuxAdjustMeta.setLore(bobuxAdjustLore);
        bobuxAdjustStack.setItemMeta(bobuxAdjustMeta);

        ItemStack bobuxIntervalStack = new ItemStack(Material.YELLOW_CONCRETE);
        ArrayList<String> bobuxIntervalLore = new ArrayList<String>();
        bobuxIntervalLore.add("§7Left click to increase interval");
        bobuxIntervalLore.add("§7Right click to decrease interval");
        ItemMeta bobuxIntervalMeta = bobuxIntervalStack.getItemMeta();
        bobuxIntervalMeta.setDisplayName("§eAdjust Interval");
        bobuxIntervalMeta.setLore(bobuxIntervalLore);
        bobuxIntervalStack.setItemMeta(bobuxIntervalMeta);

        ItemStack bobuxConfirmStack = new ItemStack(Material.EMERALD_BLOCK);
        ArrayList<String> bobuxConfirmLore = new ArrayList<String>();
        bobuxConfirmLore.add("§7Donation: " + currentDonation);
        ItemMeta bobuxConfirmMeta = bobuxConfirmStack.getItemMeta();
        bobuxConfirmMeta.setDisplayName("§c§lCONFIRM");
        bobuxConfirmMeta.setLore(bobuxConfirmLore);
        bobuxConfirmStack.setItemMeta(bobuxConfirmMeta);

        ItemStack bobuxRaffleInfoStack = currentRaffle.getWinningStack();
        ArrayList<String> bobuxRaffleInfoLore = new ArrayList<String>();
        bobuxRaffleInfoLore.add("§7Current pot: " + currentRaffle.getCurrentTotal() + "/" + BobuxPlugin.getRaffle().getGoal());
        bobuxRaffleInfoLore.add("§7Participation Attribute: " + currentRaffle.getParticipationAttribute().toString());
        ItemMeta bobuxRaffleInfoMeta = bobuxRaffleInfoStack.getItemMeta();
        bobuxRaffleInfoMeta.setDisplayName("§f§lGrand Prize");
        bobuxRaffleInfoMeta.setLore(bobuxRaffleInfoLore);
        bobuxRaffleInfoStack.setItemMeta(bobuxRaffleInfoMeta);

        guiStructure.addSlot(19, bobuxAdjustStack);
        guiStructure.addSlot(20, bobuxIntervalStack);
        guiStructure.addSlot(25, bobuxRaffleInfoStack);
        guiStructure.addSlot(31, bobuxConfirmStack);
        
        ItemStack paneStack = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        guiStructure.addSlot(15, paneStack);
        guiStructure.addSlot(16, paneStack);
        guiStructure.addSlot(17, paneStack);
        guiStructure.addSlot(24, paneStack);
        guiStructure.addSlot(26, paneStack);
        guiStructure.addSlot(33, paneStack);
        guiStructure.addSlot(34, paneStack);
        guiStructure.addSlot(35, paneStack);
    }

    protected void slotHit(InventoryClickEvent e, Player player, int slotHit) {
        switch(slotHit) {
            case 19: if (e.getClick().isLeftClick()) {
                adjustDonation(true);
            } else {
                adjustDonation(false);
            }
            break;
            case 20: if (e.getClick().isLeftClick()) {
                switchInterval(true);
            } else {
                switchInterval(false);
            }
            break;
            case 31: currentRaffle.updateRaffle(player, Math.min(currentDonation, currentRaffle.getGoal() - currentRaffle.getCurrentTotal()));
            player.closeInventory();
            break;
        }
    }

}
