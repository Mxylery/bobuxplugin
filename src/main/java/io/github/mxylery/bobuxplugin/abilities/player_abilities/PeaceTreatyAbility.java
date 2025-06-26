package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.velocity.ConstantVelocity;

public class PeaceTreatyAbility extends AbilityOneTime {

    public PeaceTreatyAbility() {
        super("Peace Treaty Ability", false, 300);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        componentHead = new AbilityComponent
        (new ConstantVelocity(1, 40), user, user.getLocation().getDirection());

        return true;
    }

}
