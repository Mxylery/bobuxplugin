package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.entity.Mob;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.entity.StunMob;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class PeaceTreatyAbility extends AbilityOneTime {

    public PeaceTreatyAbility() {
        super("Peace Treaty Ability", false, 300);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        RegistererOption options = new RegistererOption(RegistererType.SPHERE, 0, 5, 0, user.getLocation().getDirection());
        BobuxRegisterer<Mob> registerer = new BobuxRegisterer<Mob>(options, user, Mob.class);

        if (registerer.getEntityList() == null) {
            return false;
        }
        componentHead = new AbilityComponent
        (new StunMob(60), registerer.getEntityList());

        return true;
    }

}
