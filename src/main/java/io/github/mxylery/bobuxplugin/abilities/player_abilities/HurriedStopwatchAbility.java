package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.potion.PotionEffectType;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.entity.EffectGive;
import io.github.mxylery.bobuxplugin.actions.misc.DelayedAction;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
public class HurriedStopwatchAbility extends AbilityOneTime {

    public HurriedStopwatchAbility() {
        super("Hurried Stopwatch Ability", false, 600);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        Entity[] userList = {user};

        ParticleSequence particleSequence1 = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.DUST, new DustOptions(Color.fromRGB(100, 100, 0), 2));
        particleSequence1.setExplosionOptions(1, 10, 1);
        ParticleSequence particleSequence2 = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.DUST, new DustOptions(Color.fromRGB(50,50,50), 2));
        particleSequence2.setExplosionOptions(1, 10, 1);
        ParticleSequence particleSequence3 = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.DUST, new DustOptions(Color.fromRGB(0,0,100), 2));
        particleSequence2.setExplosionOptions(1, 10, 1);

        componentHead = new AbilityComponent
        (new EffectGive(PotionEffectType.SPEED, 100, 2), userList);
        componentHead.addComponent(new AbilityComponent
        (new PlaySound(Sound.BLOCK_END_PORTAL_FRAME_FILL, 0.5f, 0.5f), user.getLocation()));
        componentHead.addComponent(new AbilityComponent
        (new DelayedAction(new PlaySound(Sound.BLOCK_END_PORTAL_FRAME_FILL, 0.5f, 0.75f), 5), user.getLocation()));
        componentHead.addComponent(new AbilityComponent
        (new DelayedAction(new PlaySound(Sound.BLOCK_END_PORTAL_FRAME_FILL, 0.5f, 1.0f), 10), user.getLocation()));
        componentHead.addComponent(new AbilityComponent
        (new PlayParticle(particleSequence1), user.getLocation().getDirection(), user.getLocation()));
        componentHead.addComponent(new AbilityComponent
        (new DelayedAction(new PlayParticle(particleSequence2), 5), user.getLocation().getDirection(), user.getLocation()));
        componentHead.addComponent(new AbilityComponent
        (new DelayedAction(new PlayParticle(particleSequence3), 10), user.getLocation().getDirection(), user.getLocation()));

        return true;
    }

}
