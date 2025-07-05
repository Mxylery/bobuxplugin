package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.block.DestroyBlock;

public class CubeRemoverAbility extends AbilityOneTime {

    public CubeRemoverAbility() {
        super("Cube Remover Ability", false, 20);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        componentHead = new AbilityComponent(new DestroyBlock(1, 5, 1), user, user.getLocation().getDirection());

        return true;
    }

}
