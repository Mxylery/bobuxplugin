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
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class TestingItemAbility extends AbilityOneTime {

    public TestingItemAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Ass8uming the player is a user
    protected void assignVariables() {
        RegistererOption registererOption1 = new RegistererOption(RegistererType.SPHERE, user.getLocation(), 0, 5, 0, user.getEyeLocation().getDirection());
        BobuxRegisterer registerer1 = new BobuxRegisterer(registererOption1, user);
        Entity[][] targetList = {registerer1.getEntityList()};
        Vector[] vectorList = {null};
        Location[] locationList = {null};
        Inventory[] inventoryList = {null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        BobuxAction[] railgunActionList = {new DamageEntity(10, false)};
        
        super.actionList = railgunActionList;
    }

}
