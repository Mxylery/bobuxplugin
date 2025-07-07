package io.github.mxylery.bobuxplugin.abilities.projectile_abilities;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.entity.TrueDamageEntity;
import io.github.mxylery.bobuxplugin.actions.velocity.RepulseFromPoint;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class PogoLauncherProjectileAbility extends AbilityOneTime {

    public PogoLauncherProjectileAbility() {
        super("Pogo Launcher Projectile Ability", true, 5);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        RegistererOption option = new RegistererOption(RegistererType.SPHERE, 0.0, 5.0, 0, new Vector(0,1,0));
        BobuxRegisterer<Player> registerer = new BobuxRegisterer<Player>(option, user, new Vector(0,0,0), Player.class);
        if (registerer.getEntityList() != null) {

            ParticleSequence particleSequence = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.EXPLOSION, null);
            particleSequence.setExplosionOptions(0.25, 5, 0.5);

            componentHead = new AbilityComponent
            (new TrueDamageEntity(1), registerer.getEntityList(), new Vector(0,1,0));
            componentHead.addComponent(new AbilityComponent
            (new RepulseFromPoint(5.0, 12.0, 0), registerer.getEntityList(), user.getLocation()));
            componentHead.addComponent(new AbilityComponent
            (new PlaySound(Sound.ENTITY_GENERIC_EXPLODE, 0.5f, 1.0f), user.getLocation()));
            componentHead.addComponent(new AbilityComponent
            (new PlayParticle(particleSequence), user.getLocation().getDirection(), user.getLocation()));
        
            return true;
        }
        return false;
    }
}