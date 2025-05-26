package io.github.mxylery.bobuxplugin.abilities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.ChangeVelocity;
import io.github.mxylery.bobuxplugin.actions.DamageEntity;
import io.github.mxylery.bobuxplugin.actions.DeleteSelf;
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

public class StinkyMobAbilityOne extends AbilityOneTime {

    public StinkyMobAbilityOne(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Vector vector = BobuxUtils.getLocationDifference(user.getLocation(), otherEntity.getLocation());
        vector.normalize();
        vector.add(new Vector(0,1,0));
        RegistererOption option = new RegistererOption(RegistererType.SPHERE, 0.0, 20.0, 0,  vector);
        BobuxRegisterer<Player> registerer = new BobuxRegisterer<Player>(option, user, Player.class);
        if (registerer.getEntityList() != null) {
            Entity[][] targetList = {{otherEntity}};
            Vector[] vectorList = {vector};
            Location[] locationList = {null};
            Inventory[] inventoryList = {null};

            super.targetList = targetList;
            super.vectorList = vectorList;
            super.locationList = locationList;
            super.inventoryList = inventoryList;

            BobuxAction[] actionList = 
            {new ChangeVelocity(1, false)};
        
            super.actionList = actionList;
            return true;
        }
        return false;
    }
}