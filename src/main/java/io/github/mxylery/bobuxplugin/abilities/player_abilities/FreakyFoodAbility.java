package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.spawn.SpawnEntity;

public class FreakyFoodAbility extends AbilityOneTime {

    public FreakyFoodAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        
        componentHead = new AbilityComponent(
        new SpawnEntity(singleTarget.getType(), true), singleTarget.getLocation());
        componentHead.addComponent(new AbilityComponent
        (new SpawnEntity(singleTarget.getType(), true), singleTarget.getLocation()));

        return true;
    }

}
