package io.github.mxylery.bobuxplugin.items;

import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.abilities.BobuxAbility;

//Helmet to boots
public class BobuxArmorSet {
    
    private BobuxAbility ability;
    private BobuxItem[] armorSetStacks;
    private int pieces;
    
    public BobuxArmorSet(BobuxItem helmet, BobuxItem chestplate, BobuxItem leggings, BobuxItem boots) {
        armorSetStacks = new BobuxItem[4];
        armorSetStacks[0] = helmet;
        armorSetStacks[1] = chestplate;
        armorSetStacks[2] = leggings;
        armorSetStacks[3] = boots;
    }


}
