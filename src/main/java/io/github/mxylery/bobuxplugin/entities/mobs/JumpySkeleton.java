package io.github.mxylery.bobuxplugin.entities.mobs;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import io.github.mxylery.bobuxplugin.abilities.BobuxAbility;
import io.github.mxylery.bobuxplugin.abilities.MobAbilityManager;
import io.github.mxylery.bobuxplugin.abilities.mob_abilities.JumpySkeletonAbilityOne;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.entities.BobuxMob;
import io.github.mxylery.bobuxplugin.items.BobuxAttributeSet;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class JumpySkeleton extends BobuxMob {

    public JumpySkeleton(Location location) {
        super(location);
    }

    protected void setUpEntity() {
        ItemStack chestplateStack = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplateStack.getItemMeta();
        chestplateMeta.setColor(Color.fromRGB(200,50,50));
        chestplateStack.setItemMeta(chestplateMeta);

        Skeleton skeleton = (Skeleton) location.getWorld().spawnEntity(location, EntityType.SKELETON);
        skeleton.getEquipment().setChestplate(chestplateStack);

        BobuxAbility[] abilityList = {new JumpySkeletonAbilityOne("Jumpy Skeleton Ability", true, 250)};
        BobuxAttributeSet[] attributeSet = {new BobuxAttributeSet(Attribute.MOVEMENT_SPEED, 0.2, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.ANY), new BobuxAttributeSet(Attribute.ATTACK_DAMAGE, -1.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY)};

        ItemStack[] dropTable = {BobuxItemInterface.abcBlood.getStack()};
        double[] dropWeights = {0.5};
        int[][] dropRanges = {{1,1}};

        super.maxHealth = 14;
        super.dropTable = dropTable;
        super.dropWeights = dropWeights;
        super.dropRanges = dropRanges;
        super.attributeSet = attributeSet;
        super.abilityList = abilityList;
        super.name = "Jumpy Skeleton";
        super.entity = skeleton;
        applyAttributes();
    }

    @EventHandler
    public void whenHit(EntityDamageByEntityEvent e) {
        if (BobuxUtils.getLocationDifferenceMagnitude(e.getEntity().getLocation(), e.getDamager().getLocation()) < 4) {
            if (MobAbilityManager.verifyAbilityCD(this, 0, e.getDamager())) {
                e.setCancelled(true);
            }
        }
    }

}
