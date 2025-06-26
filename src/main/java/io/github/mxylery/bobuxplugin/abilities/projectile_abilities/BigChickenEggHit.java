package io.github.mxylery.bobuxplugin.abilities.projectile_abilities;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.entity.DamageEntity;

public class BigChickenEggHit extends AbilityOneTime {

    public BigChickenEggHit(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        componentHead = new AbilityComponent(new DamageEntity(5), singleTarget);

        return true;
    }

}

