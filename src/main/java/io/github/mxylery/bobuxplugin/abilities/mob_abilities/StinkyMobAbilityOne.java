package io.github.mxylery.bobuxplugin.abilities.mob_abilities;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.ChangeVelocity;
import io.github.mxylery.bobuxplugin.actions.DamageEntity;
import io.github.mxylery.bobuxplugin.actions.PlayParticle;
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
        Vector vector = BobuxUtils.getLocationDifference(otherEntity.getLocation(), user.getLocation());
        Vector repulseVector = new Vector(vector.getX()*-1, vector.getY()*-1, vector.getZ()*-1);
        vector.normalize();
        vector.add(new Vector(0,2,0));
        RegistererOption option = new RegistererOption(RegistererType.SPHERE, 0.0, 6.0, 0,  vector);
        BobuxRegisterer<Player> registerer = new BobuxRegisterer<Player>(option, user, Player.class);
        if (registerer.getEntityList() != null) {
            Entity[][] targetList = {{otherEntity}, null, {user}, {user}};
            Vector[] vectorList = {vector, vector, null, repulseVector};
            Location[] locationList = {null, otherEntity.getLocation(), null, null};
            Inventory[] inventoryList = {null, null, null, null};

            super.targetList = targetList;
            super.vectorList = vectorList;
            super.locationList = locationList;
            super.inventoryList = inventoryList;

            ParticleSequence particleSequence = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.DUST, 16, 1, 0, 0, 0.5, new DustOptions(Color.GREEN, 3));

            BobuxAction[] actionList = 
            {new ChangeVelocity(1, false),
            new PlayParticle(particleSequence, false),
            new DamageEntity(3,  false),
            new ChangeVelocity(0.5, false)};
        
            super.actionList = actionList;
            return true;
        }
        return false;
    }
}