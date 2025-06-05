package io.github.mxylery.bobuxplugin.entities.livingentities.hostiles;

import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.abilities.mob_abilities.BigChickenAbilityOne;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.entities.BobuxHostile;
import io.github.mxylery.bobuxplugin.items.BobuxAttributeSet;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.listeners.MobAbilityManager;

public class BigChicken extends BobuxHostile {

    public BigChicken(Location location) {
        super(location, 1, 1, 2);
    }

    protected void setUpEntity() {
        Chicken chicken = (Chicken) location.getWorld().spawnEntity(location, EntityType.CHICKEN);

        BobuxAttributeSet[] attributeSet = {new BobuxAttributeSet(Attribute.SCALE, 3.0, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.ANY)};
        BobuxAbility[] abilityList = {new BigChickenAbilityOne("Big Chicken Ability One", true, 100)};

        ItemStack[] dropTable = {new ItemStack(Material.CHICKEN), new ItemStack(Material.FEATHER), BobuxItemInterface.bouncingItem.getStack()};
        double[] dropWeights = {1, 0.5, 0.25};
        int[][] dropRanges = {{3,8}, {2,6}, {1,2}};

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

    protected void normalAction() {
        ArrayList<Player> playerList = BobuxUtils.getNearbyPlayers(entity.getLocation(), 10);
        if (playerList.size() != 0) {
            Player player = playerList.get(0);
            if (player.getLocation().distance(entity.getLocation()) > 5) {
                MobAbilityManager.verifyAbilityCD(this, 0, player);
            }
        }
    }
}
