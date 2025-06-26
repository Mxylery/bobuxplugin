package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.PhaseAbility;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.entity.DamageEntity;
import io.github.mxylery.bobuxplugin.actions.entity.EffectGive;
import io.github.mxylery.bobuxplugin.actions.entity.StunMob;
import io.github.mxylery.bobuxplugin.actions.velocity.ChangeVelocity;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class KungFuGlovesAbility extends PhaseAbility {

    public KungFuGlovesAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        Player player = (Player) user;
        Vector eyeVector = player.getLocation().getDirection();
        RegistererOption registererOption1 = new RegistererOption(RegistererType.LINE, 8, 2, 1, eyeVector);
        BobuxRegisterer<Mob> registerer1 = new BobuxRegisterer<Mob>(registererOption1, player, Mob.class);

        Vector slightKnockUp = new Vector(eyeVector.getX()*0.1, 1, eyeVector.getZ()*0.1);
        Vector slightLeap = new Vector(eyeVector.getX(), eyeVector.getY()*0.5 + 0.8, eyeVector.getZ());

        //phase 0
        if (registerer1.getEntityList() == null) {
            componentHead = new AbilityComponent(new ChangeVelocity(10), player, slightLeap);
        //phase 1
        } else {
            ParticleSequence kungFuParticle = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.CRIT,  null);
            kungFuParticle.setExplosionOptions(8, 1, 1);

            componentHead = new AbilityComponent
            (new ChangeVelocity(8), player, slightLeap);
            componentHead.addComponent(new AbilityComponent
            (new ChangeVelocity(7), registerer1.getEntityList()[0], slightKnockUp));
            componentHead.addComponent(new AbilityComponent
            (new DamageEntity(3), registerer1.getEntityList()[0]));
            componentHead.addComponent(new AbilityComponent
            (new PlaySound(Sound.BLOCK_WOOD_BREAK, 1.0f, 1.0f), player.getLocation()));
            componentHead.addComponent(new AbilityComponent
            (new PlayParticle(kungFuParticle), slightKnockUp, registerer1.getEntityList()[0].getLocation()));
            componentHead.addComponent(new AbilityComponent
            (new EffectGive(PotionEffectType.RESISTANCE, 60, 2), player));
            componentHead.addComponent(new AbilityComponent
            (new StunMob(14), registerer1.getEntityList()[0]));
            
            triggerPhase(player, BobuxItemInterface.kungFuGloves, 15, 1);
        } 
        return true;
    }

}
