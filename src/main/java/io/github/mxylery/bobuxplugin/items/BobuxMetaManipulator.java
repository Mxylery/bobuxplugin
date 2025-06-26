package io.github.mxylery.bobuxplugin.items;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

//Used in bobuxiteminterface since for some reason I made items by defining them all as public static variables lol
public class BobuxMetaManipulator {
    
    public BobuxMetaManipulator(ItemStack stack, ArmorMeta meta, TrimMaterial material, TrimPattern pattern) {
        ArmorTrim trim = new ArmorTrim(material, pattern);
        meta.setTrim(trim);
        stack.setItemMeta(meta);
    }

    public BobuxMetaManipulator(ItemStack stack, LeatherArmorMeta meta, Color color) {
        meta.setColor(color);
        stack.setItemMeta(meta);
    }

    
    



}
