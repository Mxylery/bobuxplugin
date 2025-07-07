package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Mob;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.entity.StunMob;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class PeaceTreatyAbility extends AbilityOneTime {

    public PeaceTreatyAbility() {
        super("Peace Treaty Ability", false, 1200);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        RegistererOption options = new RegistererOption(RegistererType.SPHERE, 0, 10, 0, user.getLocation().getDirection());
        BobuxRegisterer<Mob> registerer = new BobuxRegisterer<Mob>(options, user, Mob.class);

        if (registerer.getEntityList() == null) {
            return false;
        }

        ParticleSequence particleSequence = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.END_ROD, null);
        particleSequence.setExplosionOptions(0.5, 5, 0.2);

        componentHead = new AbilityComponent
        (new StunMob(100), registerer.getEntityList());
        componentHead.addComponent(new AbilityComponent(new PlaySound(Sound.ITEM_BOOK_PUT, 1.0f, 1.0f), user.getLocation()));
        componentHead.addComponent(new AbilityComponent(new PlayParticle(particleSequence), user.getLocation().getDirection(), user.getLocation()));

        return true;
    }

}
