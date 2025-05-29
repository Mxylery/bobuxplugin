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
        Entity[][] targetList = {{user},{user},{user},null,null};
        Vector[] vectorList = {null,null,null,user.getEyeLocation().getDirection(),null};
        Location[] locationList = {null,null,null,user.getLocation(),user.getLocation()};
        Inventory[] inventoryList = {null,null,null,null,null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        float saturation = user.getSaturation();
        float hunger = user.getFoodLevel();
        float saturationTotal = (float) Math.min(saturation + 8, 20);
        float hungerTotal = (float) Math.min(hunger + 4, 20);

        PotionEffectType potionEffectType1;
        PotionEffectType potionEffectType2;
        PotionEffectType potionEffectType3;

        double rng1 = Math.random();
        if (rng1 < 0.5) {
            potionEffectType1 = PotionEffectType.REGENERATION;
        } else if (rng1 < 0.8) {
            potionEffectType1 = PotionEffectType.ABSORPTION;
        } else {
            potionEffectType1 = PotionEffectType.RESISTANCE;
        }

        double rng2 = Math.random();
        if (rng2 < 0.5) {
            potionEffectType2 = PotionEffectType.SPEED;
        } else if (rng2 < 0.9) {
            potionEffectType2 = PotionEffectType.HASTE;
        } else {
            potionEffectType2 = PotionEffectType.HUNGER;
        }

        double rng3 = Math.random();
        if (rng3 < 0.5) {
            potionEffectType3 = PotionEffectType.STRENGTH;
        } else if (rng3 < 0.9) {
            potionEffectType3 = PotionEffectType.FIRE_RESISTANCE;
        } else {
            potionEffectType3 = PotionEffectType.WATER_BREATHING;
        }

        ParticleSequence eatParticleSequence = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.HEART, 8, 3, 0, 0, 0.5, null);
        
        BobuxAction[] actionList =  
        {new EffectGive(potionEffectType1, 200, 0, false),
        new EffectGive(potionEffectType2, 200, 1, false),
        new EffectGive(potionEffectType3, 200, 0, false),
        new PlayParticle(eatParticleSequence, false),
        new PlaySound(Sound.ENTITY_CAT_EAT, 1.0f, 1.0f, false)};

        super.actionList = actionList;
        return true;
    }

}