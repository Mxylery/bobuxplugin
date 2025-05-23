package io.github.mxylery.bobuxplugin.abilities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.DamageEntity;
import io.github.mxylery.bobuxplugin.actions.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.PlaySound;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
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

    //Ass8uming the player is a user
    protected void assignVariables() {
        Location elevatedPlayerLoc = new Location(user.getWorld(), user.getLocation().getX(), user.getLocation().getY() + 1, user.getLocation().getZ());
        RegistererOption railgunRegistererOption1 = new RegistererOption(RegistererType.LINE, elevatedPlayerLoc, 30, 1, 10, user.getEyeLocation().getDirection());
        BobuxRegisterer railgunRegisterer1 = new BobuxRegisterer(railgunRegistererOption1, user);
        Entity[][] targetList = {railgunRegisterer1.getEntityList(),{},{},{}};
        Vector[] vectorList = {null, user.getEyeLocation().getDirection(), user.getEyeLocation().getDirection(), null};
        Location[] locationList = {null, elevatedPlayerLoc, elevatedPlayerLoc, elevatedPlayerLoc};
        Inventory[] inventoryList = {null, null, null, null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        ParticleSequence railgunParticleSequence1 = 
        new ParticleSequence(ParticleSequenceOrientations.NORMAL, Particle.END_ROD, 30, 2.0, 0.0, null);
        ParticleSequence railgunParticleSequence2 = 
        new ParticleSequence(ParticleSequenceOptions.SPIRAL, ParticleSequenceOrientations.NORMAL, Particle.END_ROD, 5, 0, 1, 1, 30, null);
        BobuxAction[] railgunActionList = 
        {new DamageEntity(10, false), 
        new PlayParticle(railgunParticleSequence1,  false), 
        new PlayParticle(railgunParticleSequence2,  false), 
        new PlaySound(Sound.ENTITY_FIREWORK_ROCKET_BLAST, 0.1f, 1.0f, false)};
        
        super.actionList = railgunActionList;
    }

}
