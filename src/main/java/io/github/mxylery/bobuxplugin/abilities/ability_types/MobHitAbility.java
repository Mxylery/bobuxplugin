package io.github.mxylery.bobuxplugin.abilities.ability_types;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.actions.entity.DamageEntity;
import io.github.mxylery.bobuxplugin.actions.velocity.ChangeVelocity;
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
    public boolean assignVariables() {

        Vector differenceVector = BobuxUtils.getLocationDifference(user.getLocation(), singleTarget.getLocation());
        double magnitude = user.getLocation().distance(singleTarget.getLocation());
        differenceVector.add(new Vector(0, -differenceVector.getY() + 1*magnitude, 0));
        if (magnitude != 0) {
            differenceVector.normalize();
        } else {
            differenceVector = new Vector(0,0,0);
        }

        componentHead = new AbilityComponent(new ChangeVelocity(magnitude), singleTarget, differenceVector);
        componentHead.addComponent(new AbilityComponent(new DamageEntity(damage), singleTarget));
        
        return true;
    }

    

}
