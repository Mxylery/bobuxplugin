package io.github.mxylery.bobuxplugin.entities.mobs;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import io.github.mxylery.bobuxplugin.entities.BobuxMob;
import io.github.mxylery.bobuxplugin.items.BobuxAttributeSet;

public class ScoutZombie extends BobuxMob {
    
    public ScoutZombie(Location location) {
        super(location);
    }

    protected void setUpEntity() {
        ItemStack bootStack = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootMeta = (LeatherArmorMeta) bootStack.getItemMeta();
        bootMeta.setColor(Color.fromRGB(200,200,255));
        bootStack.setItemMeta(bootMeta);
        ItemStack leggingsStack = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) bootStack.getItemMeta();
        leggingsMeta.setColor(Color.fromRGB(200,200,255));
        leggingsStack.setItemMeta(leggingsMeta);
        ItemStack chestplateStack = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) bootStack.getItemMeta();
        chestplateMeta.setColor(Color.fromRGB(200,200,255));
        chestplateStack.setItemMeta(chestplateMeta);
        ItemStack helmetStack = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) bootStack.getItemMeta();
        helmetMeta.setColor(Color.fromRGB(200,200,255));
        helmetStack.setItemMeta(helmetMeta);

        Zombie zombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        zombie.getEquipment().setBoots(bootStack);
        zombie.getEquipment().setLeggings(leggingsStack);
        zombie.getEquipment().setChestplate(chestplateStack);
        zombie.getEquipment().setHelmet(helmetStack);

        BobuxAttributeSet[] attributeSet = {new BobuxAttributeSet(Attribute.MOVEMENT_SPEED, 1.0, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.ANY), new BobuxAttributeSet(Attribute.ATTACK_DAMAGE, 1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY)};

        ItemStack[] dropTable = {bootStack};
        double[] dropWeights = {1};
        int[][] dropRanges = {{1,3}};

        super.maxHealth = 16;
        super.dropTable = dropTable;
        super.dropWeights = dropWeights;
        super.dropRanges = dropRanges;
        super.attributeSet = attributeSet;
        super.abilityList = abilityList;
        super.name = "Scout Mob";
        super.entity = zombie;
        applyAttributes();
    }


}
