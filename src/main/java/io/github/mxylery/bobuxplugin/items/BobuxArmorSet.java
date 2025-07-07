package io.github.mxylery.bobuxplugin.items;

import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.abilities.BobuxAbility;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityPassive;

//Helmet to boots
public class BobuxArmorSet {
    
    private BobuxAbility ability;
    private AbilityPassive passive;
    private BobuxItem[] armorSetStacks;
    
    public BobuxArmorSet(BobuxItem helmet, BobuxItem chestplate, BobuxItem leggings, BobuxItem boots, BobuxAbility ability, AbilityPassive passive) {
        armorSetStacks = new BobuxItem[4];
        armorSetStacks[0] = helmet;
        armorSetStacks[1] = chestplate;
        armorSetStacks[2] = leggings;
        armorSetStacks[3] = boots;
        this.ability = ability;
        this.passive = passive;
    }

    public BobuxItem[] getSetArray() {
        return armorSetStacks;
    }

    public BobuxAbility getAbility() {
        return ability;
    }

    public AbilityPassive getPassive() {
        return passive;
    }

}
