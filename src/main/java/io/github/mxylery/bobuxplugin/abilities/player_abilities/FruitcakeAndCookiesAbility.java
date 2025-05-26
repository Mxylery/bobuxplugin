package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.EffectGive;
import io.github.mxylery.bobuxplugin.actions.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.PlaySound;
import io.github.mxylery.bobuxplugin.actions.RandomAction;
import io.github.mxylery.bobuxplugin.actions.SaturateEntity;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;

public class FruitcakeAndCookiesAbility extends AbilityOneTime {

    public FruitcakeAndCookiesAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Entity[][] targetList = {{user}};
        Vector[] vectorList = {user.getEyeLocation().getDirection()};
        Location[] locationList = {user.getLocation()};
        Inventory[] inventoryList = {user.getInventory()};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        float saturation = user.getSaturation();
        float hunger = user.getFoodLevel();
        float randomAddition = 4 + (float) Math.random()*4;
        float saturationTotal = (float) Math.min(saturation + 2*randomAddition, 20);
        float hungerTotal = (float) Math.min(hunger + randomAddition, 20);

        ParticleSequence eatParticleSequence = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.HEART, 8, 3, 0, 0, 1, null);

        BobuxAction[][] randomActionList = {{new SaturateEntity(saturationTotal, hungerTotal, false), 
        new EffectGive(PotionEffectType.REGENERATION, 100, 1, false), 
        new EffectGive(PotionEffectType.SPEED, 100, 1, false),
        new PlaySound(Sound.ENTITY_GENERIC_EAT, 1.0f, 1.5f, false),
        new PlayParticle(eatParticleSequence, false)},
        {new SaturateEntity(saturationTotal, hungerTotal, false), 
        new EffectGive(PotionEffectType.RESISTANCE, 100, 1, false), 
        new EffectGive(PotionEffectType.ABSORPTION, 100, 0, false),
        new EffectGive(PotionEffectType.REGENERATION, 100, 1, false), 
        new PlaySound(Sound.ENTITY_GENERIC_EAT, 1.0f, 1.5f, false),
        new PlayParticle(eatParticleSequence, false)},
        {new SaturateEntity(saturationTotal, hungerTotal, false), 
        new EffectGive(PotionEffectType.STRENGTH, 200, 0, false), 
        new EffectGive(PotionEffectType.SPEED, 200, 1, false),
        new EffectGive(PotionEffectType.ABSORPTION, 200, 1, false),
        new PlaySound(Sound.ENTITY_GENERIC_EAT, 1.5f, 2.0f, false),
        new PlaySound(Sound.BLOCK_NOTE_BLOCK_CHIME, 1.5f, 1.5f, false),
        new PlayParticle(eatParticleSequence, false)}};
        double[] weights = {0.4,0.4,0.2};

        RandomAction randomAction = new RandomAction(randomActionList, weights, false);

        BobuxAction[] actionList = {randomAction};
        
        super.actionList = actionList;
        return true;
    }

}