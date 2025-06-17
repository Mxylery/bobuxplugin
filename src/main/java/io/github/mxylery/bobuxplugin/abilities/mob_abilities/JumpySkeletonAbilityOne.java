package io.github.mxylery.bobuxplugin.abilities.mob_abilities;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.actions.velocity.ChangeVelocity;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;

public class JumpySkeletonAbilityOne extends AbilityOneTime {

    public JumpySkeletonAbilityOne(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Vector leap = BobuxUtils.getLocationDifference(singleTarget.getLocation(), user.getLocation());
        leap.add(new Vector(0,1,0));
        leap.normalize();

        Entity[][] targetList = {{user}};
        Vector[] vectorList = {leap};
        Location[] locationList = {null};
        Inventory[] inventoryList = {null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        BobuxAction[] actionList = {new ChangeVelocity(15)};
        
        super.actionList = actionList;
        return true;
    }
}