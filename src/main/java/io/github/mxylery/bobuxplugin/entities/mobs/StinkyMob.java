package io.github.mxylery.bobuxplugin.entities.mobs;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import io.github.mxylery.bobuxplugin.abilities.BobuxAbility;
import io.github.mxylery.bobuxplugin.abilities.MobAbilityManager;
import io.github.mxylery.bobuxplugin.abilities.mob_abilities.StinkyMobAbilityOne;
import io.github.mxylery.bobuxplugin.entities.BobuxMob;
import io.github.mxylery.bobuxplugin.items.BobuxAttributeSet;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class StinkyMob extends BobuxMob {
    
    public StinkyMob(Location location) {
        super(location);
    }

    protected void setUpEntity() {
        ItemStack bootStack = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta bootMeta = (LeatherArmorMeta) bootStack.getItemMeta();
        bootMeta.setColor(Color.fromRGB(0,100,0));
        bootStack.setItemMeta(bootMeta);
        ItemStack chestplateStack = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) bootStack.getItemMeta();
        chestplateMeta.setColor(Color.fromRGB(0,100,0));
        chestplateStack.setItemMeta(chestplateMeta);

        Zombie zombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        zombie.getEquipment().setBoots(bootStack);
        zombie.getEquipment().setChestplate(chestplateStack);

        BobuxAttributeSet[] attributeSet = {new BobuxAttributeSet(Attribute.MOVEMENT_SPEED, -0.5, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.ANY)};
        BobuxAbility[] abilityList = {new StinkyMobAbilityOne("Stinky Mob Ability One", false, 100)};

        ItemStack[] dropTable = {BobuxItemInterface.stinkyResidue.getStack(), BobuxItemInterface.stinkyPants.getStack()};
        double[] dropWeights = {1, 0.05};
        int[][] dropRanges = {{1,1},{1,1}};

        super.maxHealth = 20;
        super.dropTable = dropTable;
        super.dropWeights = dropWeights;
        super.dropRanges = dropRanges;
        super.attributeSet = attributeSet;
        super.abilityList = abilityList;
        super.name = "Stinky Mob";
        super.entity = zombie;
        applyAttributes();
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getEntity().equals(entity)) {
            MobAbilityManager.verifyAbilityCD(this, 0);
        }
    }


}
