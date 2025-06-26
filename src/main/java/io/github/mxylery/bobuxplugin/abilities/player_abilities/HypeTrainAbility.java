package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.velocity.ConstantVelocity;

public class HypeTrainAbility extends AbilityOneTime {

    public HypeTrainAbility() {
        super("Hype Train Ability", false, 300);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        componentHead = new AbilityComponent
        (new ConstantVelocity(10, 40), user, user.getLocation().getDirection());

        return true;
    }

}
