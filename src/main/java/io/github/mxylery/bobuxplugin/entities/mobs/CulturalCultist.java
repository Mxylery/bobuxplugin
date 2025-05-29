package io.github.mxylery.bobuxplugin.entities.mobs;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.abilities.mob_abilities.StinkyMobAbilityOne;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.entities.BobuxMob;
import io.github.mxylery.bobuxplugin.items.BobuxAttributeSet;
import io.github.mxylery.bobuxplugin.listeners.MobAbilityManager;

public class CulturalCultist extends BobuxMob {
    
    public CulturalCultist(BobuxPlugin plugin, Location location) {
        super(plugin, location);
    }

    protected void setUpEntity() {
        Witch witch = (Witch) location.getWorld().spawnEntity(location, EntityType.WITCH);

        BobuxAttributeSet[] attributeSet = {new BobuxAttributeSet(Attribute.SCALE, -0.2, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.ANY)};

        ItemStack[] dropTable = {new ItemStack(Material.CAULDRON)};
        double[] dropWeights = {0.5};
        int[][] dropRanges = {{1,3}};

        super.maxHealth = 20;
        super.dropTable = dropTable;
        super.dropWeights = dropWeights;
        super.dropRanges = dropRanges;
        super.attributeSet = attributeSet;
        super.abilityList = abilityList;
        super.name = "Cultural Cultist";
        super.entity = witch;
        applyAttributes();
    }



}
