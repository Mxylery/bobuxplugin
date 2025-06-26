package io.github.mxylery.bobuxplugin.abilities.mob_abilities;

import org.bukkit.Sound;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.entity.EffectGive;
import io.github.mxylery.bobuxplugin.actions.velocity.ChangeVelocity;

public class CulturalCultistAbilityOne extends AbilityOneTime {

    public CulturalCultistAbilityOne(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        Vector leap = new Vector(0,1,0);

        componentHead = new AbilityComponent
        (new ChangeVelocity(15), user, leap);
        componentHead.addComponent(new AbilityComponent
        (new EffectGive(PotionEffectType.SLOW_FALLING, 100, 5), user));
        componentHead.addComponent(new AbilityComponent
        (new PlaySound(Sound.ENTITY_WITCH_CELEBRATE, 1.5f, 1.5f), user.getLocation()));

        return true;
    }
}