package io.github.mxylery.bobuxplugin.guis.misc;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.guis.BobuxGUI;
import io.github.mxylery.bobuxplugin.items.BobuxLoot;

public class LesserLootboxGUI extends BobuxGUI {

    private int itemsToCollect = 3;

    public LesserLootboxGUI(Inventory inventory, Player player, BobuxPlugin plugin) {
        super(inventory, player, plugin);
    }

    protected void setGUI() {
        ItemStack[] slotList = BobuxLoot.generateLesserStack(3);
        guiStructure.addSlot(11, slotList[0]);
        guiStructure.addSlot(13, slotList[1]);
        guiStructure.addSlot(15, slotList[2]);
    }

    protected void slotHit(int slot) {
        if (guiStructure.hasSlot(slot)) {
            player.getInventory().addItem(guiStructure.getStack(slot));
            deleteSlot(slot);
        }
    }

    protected void specialGUIOption() {
        
    }


}
