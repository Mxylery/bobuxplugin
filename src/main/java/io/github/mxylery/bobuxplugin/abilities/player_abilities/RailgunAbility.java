package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Mob;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.entity.DamageEntity;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class RailgunAbility extends AbilityOneTime {

    public RailgunAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        Location elevatedPlayerLoc = new Location(user.getWorld(), user.getLocation().getX(), user.getLocation().getY() + 1, user.getLocation().getZ());
        RegistererOption registererOption = new RegistererOption(RegistererType.LINE, 30, 1, 10, user.getLocation().getDirection());
        BobuxRegisterer<Mob> registerer = new BobuxRegisterer<Mob>(registererOption, user, Mob.class);
        if (registerer.getEntityList() == null) {
            return false;
        }

        ParticleSequence particleSequence1 = 
        new ParticleSequence(ParticleSequenceOptions.LINE, ParticleSequenceOrientations.NORMAL, Particle.END_ROD, null);
        particleSequence1.setLineOptions(30, 2, 0);
        ParticleSequence particleSequence2 = 
        new ParticleSequence(ParticleSequenceOptions.SPIRAL, ParticleSequenceOrientations.NORMAL, Particle.END_ROD, null);
        particleSequence2.setSpiralOptions(30, 5, 0, 1, 1);

        componentHead = new AbilityComponent
        (new DamageEntity(10), registerer.getEntityList());
        componentHead.addComponent(new AbilityComponent
        (new PlayParticle(particleSequence1), user.getLocation().getDirection(), elevatedPlayerLoc));
        componentHead.addComponent(new AbilityComponent
        (new PlayParticle(particleSequence2), user.getLocation().getDirection(), elevatedPlayerLoc));
        componentHead.addComponent(new AbilityComponent
        (new PlaySound(Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1.0f, 1.0f), elevatedPlayerLoc));

        return true;
    }

}
