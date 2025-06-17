package io.github.mxylery.bobuxplugin.entities.mobs;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Witch;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.abilities.BobuxAbility;
import io.github.mxylery.bobuxplugin.abilities.MobAbilityManager;
import io.github.mxylery.bobuxplugin.abilities.mob_abilities.CulturalCultistAbilityOne;
import io.github.mxylery.bobuxplugin.entities.BobuxMob;
import io.github.mxylery.bobuxplugin.events.BobuxEntityWithinRangeEvent;
import io.github.mxylery.bobuxplugin.items.BobuxAttributeSet;

public class CulturalCultist extends BobuxMob {
    
    public CulturalCultist(Location location) {
        super(location);
    }

    protected void setUpEntity() {
        Witch witch = (Witch) location.getWorld().spawnEntity(location, EntityType.WITCH);

        BobuxAttributeSet[] attributeSet = {new BobuxAttributeSet(Attribute.SCALE, -0.2, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.ANY)};

        //maybe cultural rabbit fragments
        ItemStack[] dropTable = {new ItemStack(Material.CAULDRON)};
        double[] dropWeights = {0.5};
        int[][] dropRanges = {{1,1}};

        BobuxAbility[] abilityList = {new CulturalCultistAbilityOne("Cultural Cultist Ability One", true, 1000)};

        super.nearbyEntityRadius = 5;

        super.maxHealth = 16;
        super.dropTable = dropTable;
        super.dropWeights = dropWeights;
        super.dropRanges = dropRanges;
        super.attributeSet = attributeSet;
        super.abilityList = abilityList;
        super.name = "Cultural Cultist";
        super.entity = witch;
        applyAttributes();
    }

    @EventHandler
    public void onPlayerNear(BobuxEntityWithinRangeEvent e) {
        if (e.getEntity().equals(super.entity)) {
            ArrayList<Player> playerList = new ArrayList<Player>();
            for (Entity entity : nearbyEntityList) {
                if (entity instanceof Player) {
                    MobAbilityManager.verifyAbilityCD(this, 0);
                }
            }
        }
    }

}
