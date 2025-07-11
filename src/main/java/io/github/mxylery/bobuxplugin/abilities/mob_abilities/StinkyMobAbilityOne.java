package io.github.mxylery.bobuxplugin.abilities.mob_abilities;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.entity.DamageEntity;
import io.github.mxylery.bobuxplugin.actions.velocity.ChangeVelocity;
import io.github.mxylery.bobuxplugin.actions.velocity.RepulseFromPoint;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class StinkyMobAbilityOne extends AbilityOneTime {

    public StinkyMobAbilityOne(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        RegistererOption option = new RegistererOption(RegistererType.SPHERE, 0.0, 4.0, 0, new Vector(0,1,0));
        BobuxRegisterer<Player> registerer = new BobuxRegisterer<Player>(option, user, new Vector(0,0,0), Player.class);
        if (registerer.getEntityList() != null) {
            ParticleSequence particleSequence = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.DUST, new DustOptions(Color.GREEN, 3));
            particleSequence.setExplosionOptions(1, 16, 1);

            componentHead = new AbilityComponent
            (new ChangeVelocity(8), user, new Vector(0,1,0));
            componentHead.addComponent(new AbilityComponent
            (new PlayParticle(particleSequence), new Vector(0,1,0), user.getLocation()));
            componentHead.addComponent(new AbilityComponent
            (new DamageEntity(2), registerer.getEntityList()));
            componentHead.addComponent(new AbilityComponent
            (new RepulseFromPoint(4.0, 8, 0.6), registerer.getEntityList(), user.getLocation()));

            return true;
        }
        return false;
    }
}