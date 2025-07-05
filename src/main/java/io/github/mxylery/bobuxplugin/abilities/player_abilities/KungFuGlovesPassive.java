package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityPassive;
import io.github.mxylery.bobuxplugin.actions.velocity.ChangeVelocity;

public class KungFuGlovesPassive extends AbilityPassive {
    public KungFuGlovesPassive(String name, boolean muteCD, long cooldown, long period) {
        super(name, muteCD, cooldown, period);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        if (verifyPassivePeriod()) {
            
            componentHead = new AbilityComponent(new ChangeVelocity(5), user, new Vector(0,1,0));
            componentHead.addComponent(new AbilityComponent((new ChangeVelocity(2)), singleTarget, new Vector(0,1,0)));

            return true;        
        } else {
            return false;
        }
    }
}
