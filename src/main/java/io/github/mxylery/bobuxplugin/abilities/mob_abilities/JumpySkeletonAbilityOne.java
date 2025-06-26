package io.github.mxylery.bobuxplugin.abilities.mob_abilities;

import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.velocity.ChangeVelocity;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;

public class JumpySkeletonAbilityOne extends AbilityOneTime {

    public JumpySkeletonAbilityOne(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        Vector leap = BobuxUtils.getLocationDifference(singleTarget.getLocation(), user.getLocation());
        leap.add(new Vector(0,1,0));
        leap.normalize();

        componentHead = new AbilityComponent
        (new ChangeVelocity(15), user, leap);

        return true;
    }
}