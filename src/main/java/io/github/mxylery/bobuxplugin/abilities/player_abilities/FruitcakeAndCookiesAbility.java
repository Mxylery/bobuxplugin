package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.entity.EffectGive;
import io.github.mxylery.bobuxplugin.actions.entity.SaturateEntity;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;

public class FruitcakeAndCookiesAbility extends AbilityOneTime {

    public FruitcakeAndCookiesAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        Player player = (Player) user;
        Entity[] playerAsArray = {player};

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

        componentHead = new AbilityComponent
        (new EffectGive(potionEffectType1, 160, potionEffectStrength1), playerAsArray);
        componentHead.addComponent(new AbilityComponent
        (new EffectGive(potionEffectType2, 160, potionEffectStrength2), playerAsArray));
        componentHead.addComponent(new AbilityComponent
        (new EffectGive(potionEffectType3, 160, potionEffectStrength3), playerAsArray));
        componentHead.addComponent(new AbilityComponent
        (new SaturateEntity(endHung, endSat), playerAsArray));
        componentHead.addComponent(new AbilityComponent
        (new PlayParticle(eatParticleSequence), player.getLocation().getDirection(), player.getLocation()));
        componentHead.addComponent(new AbilityComponent
        (new PlaySound(Sound.ENTITY_CAT_EAT, 1.0f, 1.0f), player.getLocation()));
        
        return true;
    }

}