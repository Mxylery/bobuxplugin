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
import io.github.mxylery.bobuxplugin.actions.entity.SetFire;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class TheHotStickAbility extends AbilityOneTime {

    public TheHotStickAbility() {
        super("The Hot Stick Ability", false, 100);
    }

    public boolean assignVariables() {
        Location elevatedPlayerLoc = new Location(user.getWorld(), user.getLocation().getX(), user.getLocation().getY() + 1, user.getLocation().getZ());
        RegistererOption registererOption1 = new RegistererOption(RegistererType.LINE, 30, 1, 1, user.getLocation().getDirection());
        BobuxRegisterer<Mob> registerer1 = new BobuxRegisterer<Mob>(registererOption1, user, Mob.class);
        if (registerer1.getEntityList() == null) {
            return false;
        }
        Location entityLoc = registerer1.getEntityList()[0].getLocation();

        ParticleSequence theHotStickParticleSequence1 = new ParticleSequence
        (ParticleSequenceOptions.LINE, ParticleSequenceOrientations.NORMAL, Particle.FLAME, null);
        theHotStickParticleSequence1.setLineOptions(30, cooldown, actionListLength);
        ParticleSequence theHotStickParticleSequence2 = new ParticleSequence
        (ParticleSequenceOptions.RING, ParticleSequenceOrientations.NORMAL, Particle.FLAME, null);
        theHotStickParticleSequence2.setRingOptions(3, 0, 5, 5);
        
        componentHead = new AbilityComponent
        (new DamageEntity(10), registerer1.getEntityList());
        componentHead.addComponent(new AbilityComponent
        (new PlayParticle(theHotStickParticleSequence1), user.getLocation().getDirection(), elevatedPlayerLoc));
        componentHead.addComponent(new AbilityComponent
        (new PlayParticle(theHotStickParticleSequence2), user.getLocation().getDirection(), entityLoc));
        componentHead.addComponent(new AbilityComponent
        (new SetFire(200), registerer1.getEntityList()));
        componentHead.addComponent(new AbilityComponent
        (new PlaySound(Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1.0f, 1.0f), elevatedPlayerLoc));
        
        return true;
    }

}
