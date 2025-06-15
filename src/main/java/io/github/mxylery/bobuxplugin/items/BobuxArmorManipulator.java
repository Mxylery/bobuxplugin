package io.github.mxylery.bobuxplugin.items;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class BobuxArmorManipulator {
    
    public BobuxArmorManipulator(ItemStack stack, LeatherArmorMeta meta, Color color) {

        meta.setColor(color);
        stack.setItemMeta(meta);

    }

}
