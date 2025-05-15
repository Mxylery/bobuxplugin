package io.github.mxylery.bobuxplugin.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;

public class BobuxItem {
    private int price;
    private ItemStack stack;
    private String[] description;
    private String name;
    private BobuxAbility ability;
    private Enchantment[] enchants;
    private int[] enchantLevels;
    private boolean unbreakable;

    public BobuxItem (ItemStack stack, String[] description, String name, BobuxAbility ability, 
    Enchantment[] enchants, int[] enchantLevels, boolean unbreakable, int price) {
        this.stack = stack;
        this.description = description;
        this.name = name;
        this.ability = ability; //Could be null if desired
        this.enchants = enchants;
        this.enchantLevels = enchantLevels;
        this.unbreakable = unbreakable;
        this.price = price;
    }

    public BobuxItem (ItemStack stack, String[] description, String name, BobuxAbility ability, 
    Enchantment[] enchants, int[] enchantLevels, boolean unbreakable) {
        this.stack = stack;
        this.description = description;
        this.name = name;
        this.ability = ability; //Could be null if desired
        this.enchants = enchants;
        this.enchantLevels = enchantLevels;
        this.unbreakable = unbreakable;
        this.price = 1;
    }

    //The stack meta is initialized (in BobuxItemInterface), then this method appends the desired name and lore to the item. 
    public void initializeStack() {
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
		
        //Each element of description is a seperate line
        for (int i = 0; i < description.length; i++) {
            lore.add(description[i]);
        }

        if (enchants != null) {
            System.out.print("Tried to enchant.");
            for (int i = 0; i < enchants.length; i++) {
                meta.addEnchant(enchants[i], enchantLevels[i], unbreakable);
            }
        }

		meta.setDisplayName(name);
		meta.setLore(lore);
		stack.setItemMeta(meta);
    }

    public ItemStack getStack() {
        this.initializeStack();
        return stack;
    }

    public BobuxAbility getAbility() {
        return ability;
    }

}
