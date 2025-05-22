package io.github.mxylery.bobuxplugin.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.core.ability_types.AbilityPassive;

public class BobuxItem {
    private int price;
    private ItemStack stack;
    private String[] description;
    private String name;
    private BobuxAbility ability;
    private Enchantment[] enchants;
    private int[] enchantLevels;
    private boolean unbreakable;
    private AbilityPassive passive;
    private boolean hideFlag;

    public BobuxItem (ItemStack stack, String[] description, String name) {
        this.stack = stack;
        this.description = description;
        this.name = name;
        this.ability = null;
        this.enchants = null;
        this.enchantLevels = null;
        this.unbreakable = false;
        this.price = 1;
        this.passive = null;
        this.hideFlag = true;
    }

    public BobuxItem (ItemStack stack, String[] description, String name, int price) {
        this.stack = stack;
        this.description = description;
        this.name = name;
        this.ability = null;
        this.enchants = null;
        this.enchantLevels = null;
        this.unbreakable = false;
        this.price = price;
        this.passive = null;
        this.hideFlag = false;
    }

    public BobuxItem (ItemStack stack, String[] description, String name, AbilityPassive passive, 
    int price) {
        this.stack = stack;
        this.description = description;
        this.name = name;
        this.ability = null;
        this.enchants = null;
        this.enchantLevels = null;
        this.unbreakable = false;
        this.price = price;
        this.passive = passive;
        this.hideFlag = false;
    }

    public BobuxItem (ItemStack stack, String[] description, String name, BobuxAbility ability, 
    int price) {
        this.stack = stack;
        this.description = description;
        this.name = name;
        this.ability = ability; 
        this.enchants = null;
        this.enchantLevels = null;
        this.unbreakable = false;
        this.price = price;
        this.passive = null;
        this.hideFlag = false;
    }
     
    public BobuxItem (ItemStack stack, String[] description, String name, BobuxAbility ability, AbilityPassive passive, 
    int price) {
        this.stack = stack;
        this.description = description;
        this.name = name;
        this.ability = ability; 
        this.enchants = null;
        this.enchantLevels = null;
        this.unbreakable = false;
        this.price = 1;
        this.passive = passive;
        this.hideFlag = false;
    }

    public BobuxItem (ItemStack stack, String[] description, String name, BobuxAbility ability, 
    boolean unbreakable, int price) {
        this.stack = stack;
        this.description = description;
        this.name = name;
        this.ability = ability; 
        this.enchants = null;
        this.enchantLevels = null;
        this.unbreakable = unbreakable;
        this.price = price;
        this.passive = null;
        this.hideFlag = false;
    }

    public BobuxItem (ItemStack stack, String[] description, String name, BobuxAbility ability, AbilityPassive passive,
    boolean unbreakable, int price) {
        this.stack = stack;
        this.description = description;
        this.name = name;
        this.ability = ability; 
        this.enchants = null;
        this.enchantLevels = null;
        this.unbreakable = unbreakable;
        this.price = price;
        this.passive = null;
        this.hideFlag = false;
    }

    public BobuxItem (ItemStack stack, String[] description, String name, BobuxAbility ability, 
    Enchantment[] enchants, int[] enchantLevels, boolean unbreakable, int price) {
        this.stack = stack;
        this.description = description;
        this.name = name;
        this.ability = ability;
        this.enchants = enchants;
        if (enchants == null) {
            this.enchantLevels = null;
        } else if (enchantLevels == null) {
            this.enchantLevels = new int[enchants.length];
            for (int i = 0; i < enchants.length; i++) {
                this.enchantLevels[i] = 1;
            }
        } else {
            this.enchantLevels = enchantLevels;
        }
        this.unbreakable = unbreakable;
        this.price = price;
        this.passive = null;
        this.hideFlag = false;
    }

    public BobuxItem (ItemStack stack, String[] description, String name, BobuxAbility ability, AbilityPassive passive,
    Enchantment[] enchants, int[] enchantLevels, boolean unbreakable, int price) {
        this.stack = stack;
        this.description = description;
        this.name = name;
        this.ability = ability;
        this.enchants = enchants;
        if (enchants == null) {
            this.enchantLevels = null;
        } else if (enchantLevels == null) {
            this.enchantLevels = new int[enchants.length];
            for (int i = 0; i < enchants.length; i++) {
                this.enchantLevels[i] = 1;
            }
        } else {
            this.enchantLevels = enchantLevels;
        }
        this.unbreakable = unbreakable;
        this.price = price;
        this.passive = passive;
        this.hideFlag = false;
    }

    //The stack meta is initialized (in BobuxItemInterface), then this method appends the desired name and lore to the item. 
    public void initializeStack() {
		ItemMeta meta = (ItemMeta) stack.getItemMeta();
		List<String> lore = new ArrayList<>();
        lore.add("§n_______________");
        lore.add("");
		
        //Each element of description is a seperate line
        for (int i = 0; i < description.length; i++) {
            lore.add(description[i]);
        }

        if (enchants != null) {
            for (int i = 0; i < enchants.length; i++) {
                meta.addEnchant(enchants[i], enchantLevels[i], true);
            }
        }
        if (unbreakable) {
            meta.setUnbreakable(unbreakable);
        }
        if (hideFlag) {
            meta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES);
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

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public BobuxAbility getPassive() {
        return passive;
    }

}
