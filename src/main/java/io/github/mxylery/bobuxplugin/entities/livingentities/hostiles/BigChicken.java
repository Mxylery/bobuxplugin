package io.github.mxylery.bobuxplugin.entities.livingentities.hostiles;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.abilities.BobuxAbility;
import io.github.mxylery.bobuxplugin.abilities.MobAbilityManager;
import io.github.mxylery.bobuxplugin.abilities.mob_abilities.BigChickenAbilityOne;
import io.github.mxylery.bobuxplugin.entities.BobuxHostile;
import io.github.mxylery.bobuxplugin.items.BobuxAttributeSet;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class BigChicken extends BobuxHostile {

    public BigChicken(Location location) {
        super(location, 1, 1, 2);
    }

    protected void setUpEntity() {
        Chicken chicken = (Chicken) location.getWorld().spawnEntity(location, EntityType.CHICKEN);

        BobuxAttributeSet[] attributeSet = {new BobuxAttributeSet(Attribute.SCALE, 3.0, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.ANY)};
        BobuxAbility[] abilityList = {new BigChickenAbilityOne("Big Chicken Ability One", true, 100)};

        ItemStack[] dropTable = {new ItemStack(Material.CHICKEN), new ItemStack(Material.FEATHER), BobuxItemInterface.bouncingItem.getStack(), BobuxItemInterface.mutantOrb.getStack()};
        double[] dropWeights = {1, 0.5, 0.25, 1};
        int[][] dropRanges = {{3,8}, {2,6}, {1,2}, {1,1}};

        super.nearbyEntityRadius = 10;

        super.maxHealth = 40;
        super.dropTable = dropTable;
        super.dropWeights = dropWeights;
        super.dropRanges = dropRanges;
        super.attributeSet = attributeSet;
        super.abilityList = abilityList;
        super.name = "Big Chicken";
        super.entity = chicken;
        applyAttributes();
    }

    @Override
    protected void normalAction(List<Entity> entityList) {
        ArrayList<Player> playerList = new ArrayList<Player>();
        for (Entity entity : entityList) {
            if (entity instanceof Player) {
                playerList.add((Player) entity);
            }
        }
        if (playerList.size() != 0) {
            Player player = playerList.get(0);
            if (player.getLocation().distance(entity.getLocation()) > 5) {
                MobAbilityManager.verifyAbilityCD(this, 0, player);
            }
        }
    }
}
