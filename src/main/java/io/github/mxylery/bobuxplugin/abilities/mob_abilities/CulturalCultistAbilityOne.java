package io.github.mxylery.bobuxplugin.abilities.mob_abilities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.entity.EffectGive;
import io.github.mxylery.bobuxplugin.actions.velocity.ChangeVelocity;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;

public class CulturalCultistAbilityOne extends AbilityOneTime {

    public CulturalCultistAbilityOne(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Vector leap = new Vector(0,1,0);

        Entity[][] targetList = {{user}, {user}, null};
        Vector[] vectorList = {leap, null, null};
        Location[] locationList = {null, null, user.getLocation()};
        Inventory[] inventoryList = {null, null, null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        ParticleSequence particleSequence = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.ENCHANT, null);
        particleSequence.setExplosionOptions(1, 8, 1);

        BobuxAction[] actionList = {new ChangeVelocity(15), new EffectGive(PotionEffectType.SLOW_FALLING, 100, 5), new PlaySound(Sound.ENTITY_WITCH_CELEBRATE, 1.5f, 1.5f)};
        
        super.actionList = actionList;
        return true;
    }
}