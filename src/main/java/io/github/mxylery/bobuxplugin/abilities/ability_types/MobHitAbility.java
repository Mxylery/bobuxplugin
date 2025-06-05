package io.github.mxylery.bobuxplugin.abilities.ability_types;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.actions.ChangeVelocity;
import io.github.mxylery.bobuxplugin.actions.DamageEntity;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;

/**
 * This class is used for bobux hostile mobs that don't attack using their normal AI,
 * Ex: Big Chicken (chicken, has a riding zombie that guides it to player, this does the melee damage)
 */
public class MobHitAbility extends AbilityOneTime {
    
    protected double magnitude;
    protected double damage;
    protected double range;

    public MobHitAbility(String name, boolean muteCD, double magnitude, double damage, double range) {
        super(name, muteCD, 12);
        this.magnitude = magnitude;
        this.damage = damage;
        this.range = range;
    }

    //Assuming the player is a user
    protected boolean assignVariables() {

        Vector differenceVector = BobuxUtils.getLocationDifference(user.getLocation(), singleTarget.getLocation());
        double magnitude = user.getLocation().distance(singleTarget.getLocation());
        differenceVector.add(new Vector(0, -differenceVector.getY() + 1*magnitude, 0));
        if (magnitude != 0) {
            differenceVector.normalize();
        } else {
            differenceVector = new Vector(0,0,0);
        }

        Entity[][] targetList = {{singleTarget}, {singleTarget}};
        Vector[] vectorList = {differenceVector, null};
        Location[] locationList = {null, null};
        Inventory[] inventoryList = {null, null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        BobuxAction[] actionList = {new ChangeVelocity(magnitude), new DamageEntity(damage)};
        
        super.actionList = actionList;
        return true;
    }

    

}
