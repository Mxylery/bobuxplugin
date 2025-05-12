package io.github.mxylery.bobuxplugin.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;

//https://www.gamergeeks.net/apps/minecraft/color-codes for examples of colored text
public class BobuxItem {
    private ItemStack stack;
    private String[] description;
    private String name;
    private BobuxAbility ability;

    public BobuxItem (ItemStack stack, String[] description, String name) {
        this.stack = stack;
        this.description = description;
        this.name = name;
    }

    //The stack meta is initialized, then this method appends the desired name and lore to the item. 
    public void initializeStack() {
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
        //Each element of description is a seperate line
        for (int i = 0; i < description.length; i++) {
            lore.add(description[i]);
        }

		meta.setDisplayName(name);
		meta.setLore(lore);
		
		stack.setItemMeta(meta);
    }

}
