package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.entity.EffectGive;
import io.github.mxylery.bobuxplugin.actions.entity.SaturateEntity;
import io.github.mxylery.bobuxplugin.actions.misc.RandomAction;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;

public class FruitcakeAndCookiesAbility extends AbilityOneTime {

    public FruitcakeAndCookiesAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {

        Player player = (Player) user;

        Entity[][] targetList = {{player},{player},{player},{player},null,null};
        Vector[] vectorList = {null,null,null,null,player.getLocation().getDirection(),null};
        Location[] locationList = {null,null,null,null,player.getLocation(),player.getLocation()};
        Inventory[] inventoryList = {null,null,null,null,null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        float saturation = player.getSaturation();
        float hunger = player.getFoodLevel();
        float endSat = Math.min(saturation + 10, 20);
        float endHung = Math.min(hunger + 7, 20);

        PotionEffectType potionEffectType1;
        PotionEffectType potionEffectType2;
        PotionEffectType potionEffectType3;
        int potionEffectStrength1;
        int potionEffectStrength2;
        int potionEffectStrength3;

        double rng1 = Math.random();
        if (rng1 < 0.5) {
            potionEffectType1 = PotionEffectType.REGENERATION;
            potionEffectStrength1 = 0;
        } else if (rng1 < 0.8) {
            potionEffectType1 = PotionEffectType.ABSORPTION;
            potionEffectStrength1 = 0;
        } else {
            potionEffectType1 = PotionEffectType.RESISTANCE;
            potionEffectStrength1 = 2;
        }

        double rng2 = Math.random();
        if (rng2 < 0.5) {
            potionEffectType2 = PotionEffectType.SPEED;
            potionEffectStrength2 = 1;
        } else  {
            potionEffectType2 = PotionEffectType.HASTE;
            potionEffectStrength2 = 1;
        } 

        double rng3 = Math.random();
        if (rng3 < 0.5) {
            potionEffectType3 = PotionEffectType.STRENGTH;
            potionEffectStrength3 = 0;
        } else if (rng3 < 0.9) {
            potionEffectType3 = PotionEffectType.FIRE_RESISTANCE;
            potionEffectStrength3 = 0;
        } else {
            potionEffectType3 = PotionEffectType.STRENGTH;
            potionEffectStrength3 = 0;
            potionEffectStrength2 = 1;
        }

        ParticleSequence eatParticleSequence = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.HEART, null);
        eatParticleSequence.setExplosionOptions(1, 4, 1);
        
        BobuxAction[] actionList =  
        {new EffectGive(potionEffectType1, 160, potionEffectStrength1),
        new EffectGive(potionEffectType2, 160, potionEffectStrength2),
        new EffectGive(potionEffectType3, 160, potionEffectStrength3),
        new SaturateEntity(endHung, endSat),
        new PlayParticle(eatParticleSequence),
        new PlaySound(Sound.ENTITY_CAT_EAT, 1.0f, 1.0f)};

        super.actionList = actionList;
        return true;
    }

}