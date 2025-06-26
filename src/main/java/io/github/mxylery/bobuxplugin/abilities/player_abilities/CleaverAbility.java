package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Mob;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.entity.DamageEntity;
import io.github.mxylery.bobuxplugin.actions.velocity.ChangeVelocity;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class CleaverAbility extends AbilityOneTime {

    public CleaverAbility() {
        super("Cleaver Ability", true, 100);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        Vector userEyeVector = user.getLocation().getDirection();
        Location sphereLoc = BobuxUtils.offsetLocation(singleTarget.getLocation(), userEyeVector, 3, 0);
        RegistererOption option = new RegistererOption(RegistererType.SPHERE, 2.5, 3, 0, user.getLocation().getDirection());
        BobuxRegisterer<Mob> registerer = new BobuxRegisterer<Mob>(option, singleTarget, user, Mob.class);
        Vector slightKnockUp = new Vector(userEyeVector.getX(), userEyeVector.getY() + 1, userEyeVector.getZ());
        if (registerer.getEntityList() == null) {
            return false;
        }

        ParticleSequence particleSequence = new ParticleSequence
        (ParticleSequenceOptions.RING, ParticleSequenceOrientations.DOWN, Particle.DUST, new DustOptions(Color.YELLOW, 2));
        particleSequence.setRingOptions(2, 3, 0, 3);

        componentHead = new AbilityComponent
        (new DamageEntity(5), registerer.getEntityList());
        componentHead.addComponent(new AbilityComponent(
        new ChangeVelocity(8), registerer.getEntityList(), slightKnockUp));
        componentHead.addComponent(new AbilityComponent(
        new PlayParticle(particleSequence), user.getLocation().getDirection(), sphereLoc));

        return true;
    }

}
