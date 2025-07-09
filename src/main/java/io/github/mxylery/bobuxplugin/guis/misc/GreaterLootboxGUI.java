package io.github.mxylery.bobuxplugin.guis.misc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.guis.BobuxGUI;
import io.github.mxylery.bobuxplugin.items.BobuxLoot;

public class GreaterLootboxGUI extends BobuxGUI {

    private int itemsToCollect = 3;

    public GreaterLootboxGUI() {
        super(Bukkit.createInventory(null, 27), BobuxTimer.getPlugin());
        updateGUI();
    }

    protected void setGUI() {
        ItemStack[] slotList = BobuxLoot.generateGreaterStack(3);
        guiStructure.addSlot(11, slotList[0]);
        guiStructure.addSlot(13, slotList[1]);
        guiStructure.addSlot(15, slotList[2]);
    }

    protected void slotHit(InventoryClickEvent e, Player player, int slot) {
        if (guiStructure.hasSlot(slot)) {
            player.getInventory().addItem(guiStructure.getStack(slot));
            deleteSlot(slot);
        }
    }

    protected void specialGUIOption() {
        
    }


}
