package io.github.mxylery.bobuxplugin.guis.core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.guis.BobuxGUI;
import io.github.mxylery.bobuxplugin.guis.bounty.BountyGUI;
import io.github.mxylery.bobuxplugin.guis.questboard.QuestBoardGUI;
import io.github.mxylery.bobuxplugin.guis.raffle.RaffleGUI;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class MainGUI extends BobuxGUI {

    public MainGUI() {
        super(Bukkit.createInventory(null, 36), BobuxTimer.getPlugin());
        updateGUI();
    }

    protected void setGUI() {
        guiStructure.addSlot(11, BobuxItemInterface.market.getStack());
        guiStructure.addSlot(15, BobuxItemInterface.questboard.getStack());
        guiStructure.addSlot(20, BobuxItemInterface.grants.getStack());
        guiStructure.addSlot(24, BobuxItemInterface.raffle.getStack());
    }

    protected void slotHit(InventoryClickEvent e, Player player, int slot) {
        switch (slot) {
            case 11: MarketGUI marketGUI = new MarketGUI(player);
            marketGUI.openGUI(player);
            break;
            case 15: QuestBoardGUI questboardGUI = new QuestBoardGUI();
            questboardGUI.updateGUI();
            questboardGUI.openGUI(player);
            break;
            case 20: BountyGUI bountyGUI = new BountyGUI(player);
            bountyGUI.updateGUI();
            bountyGUI.openGUI(player);
            break;
            case 24: if (!BobuxPlugin.getRaffle().isCompleted()) {
                RaffleGUI raffleGUI = new RaffleGUI();
                raffleGUI.openGUI(player);
            } else {
                player.sendMessage("The raffle is over! Wait until the next one.");
            }
            break;
        }
    }


}
