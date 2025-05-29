package io.github.mxylery.bobuxplugin.entities.mobs;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.abilities.mob_abilities.SandbaggerAbilityOne;
import io.github.mxylery.bobuxplugin.abilities.mob_abilities.StinkyMobAbilityOne;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.entities.BobuxMob;
import io.github.mxylery.bobuxplugin.items.BobuxAttributeSet;
import io.github.mxylery.bobuxplugin.listeners.MobAbilityManager;

public class Sandbagger extends BobuxMob {
    
    public Sandbagger(BobuxPlugin plugin, Location location) {
        super(plugin, location);
    }

    protected void setUpEntity() {
        ItemStack bootStack = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootMeta = (LeatherArmorMeta) bootStack.getItemMeta();
        bootMeta.setColor(Color.fromRGB(255,255,40));
        bootStack.setItemMeta(bootMeta);
        ItemStack leggingsStack = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) bootStack.getItemMeta();
        leggingsMeta.setColor(Color.fromRGB(255,255,40));
        leggingsStack.setItemMeta(bootMeta);
        ItemStack chestplateStack = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) bootStack.getItemMeta();
        chestplateMeta.setColor(Color.fromRGB(255,255,40));
        chestplateStack.setItemMeta(chestplateMeta);

        Husk husk = (Husk) location.getWorld().spawnEntity(location, EntityType.HUSK);
        husk.getEquipment().setBoots(bootStack);
        husk.getEquipment().setLeggings(leggingsStack);
        husk.getEquipment().setChestplate(chestplateStack);
        husk.setAware(false);

        BobuxAbility[] abilityList = {new SandbaggerAbilityOne("Sandbagger Ability One", false, 10)};

        ItemStack[] dropTable = {bootStack};
        double[] dropWeights = {1};
        int[][] dropRanges = {{1,3}};

        super.maxHealth = 4;
        super.dropTable = dropTable;
        super.dropWeights = dropWeights;
        super.dropRanges = dropRanges;
        super.attributeSet = attributeSet;
        super.abilityList = abilityList;
        super.name = "Sandbagger";
        super.entity = husk;
        applyAttributes();
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getEntity().equals(entity)) {
            MobAbilityManager.verifyAbilityCD(this, 0);
        }
    }


}
