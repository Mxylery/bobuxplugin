package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.entity.EffectGive;
import io.github.mxylery.bobuxplugin.actions.misc.DelayedAction;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
public class HurriedStopwatchAbility extends AbilityOneTime {

    public HurriedStopwatchAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Entity[][] targetList = {{user},null,null,null,null,null};
        Vector[] vectorList = {null,null,null,null,user.getLocation().getDirection(), user.getLocation().getDirection()};
        Location[] locationList = {null,user.getLocation(),user.getLocation(),user.getLocation(),user.getLocation(),user.getLocation()};
        Inventory[] inventoryList = {null,null,null,null,null,null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        ParticleSequence particleSequence1 = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.DUST, new DustOptions(Color.YELLOW, 2));
        particleSequence1.setExplosionOptions(2, 10, 1);
        ParticleSequence particleSequence2 = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.DUST, new DustOptions(Color.fromRGB(0,0,50), 2));
        particleSequence2.setExplosionOptions(2, 10, 1);

        BobuxAction[] actionList = 
        {new EffectGive(PotionEffectType.SPEED, 100, 2),
        new PlaySound(Sound.BLOCK_END_PORTAL_FRAME_FILL, 0.5f, 0.5f),
        new DelayedAction(new PlaySound(Sound.BLOCK_END_PORTAL_FRAME_FILL, 0.5f, 0.75f), 5),
        new DelayedAction(new PlaySound(Sound.BLOCK_END_PORTAL_FRAME_FILL, 0.5f, 1.0f), 10),
        new PlayParticle(particleSequence1),
        new PlayParticle(particleSequence2)};
        
        super.actionList = actionList;
        return true;
    }

}
