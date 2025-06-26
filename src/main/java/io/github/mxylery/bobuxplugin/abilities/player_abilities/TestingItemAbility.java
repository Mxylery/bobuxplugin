package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.entity.Mob;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.entity.DamageEntity;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class TestingItemAbility extends AbilityOneTime {

    public TestingItemAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        RegistererOption registererOption1 = new RegistererOption(RegistererType.SPHERE, 0, 5, 0, user.getLocation().getDirection());
        BobuxRegisterer<Mob> registerer1 = new BobuxRegisterer<Mob>(registererOption1, user, Mob.class);

        componentHead = new AbilityComponent(new DamageEntity(10), registerer1.getEntityList());

        return true;
    }

}
