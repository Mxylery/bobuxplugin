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
import io.github.mxylery.bobuxplugin.actions.RepulseFromPoint;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
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
        RegistererOption option = new RegistererOption(RegistererType.SPHERE, 0.0, 4.0, 0, new Vector(0,1,0));
        BobuxRegisterer<Player> registerer = new BobuxRegisterer<Player>(option, user, new Vector(0,0,0), Player.class);
        if (registerer.getEntityList() != null) {
            Entity[][] targetList = {{user}, null, registerer.getEntityList(), registerer.getEntityList()};
            Vector[] vectorList = {new Vector(0,1,0), new Vector(0,1,0), null, null};
            Location[] locationList = {null, user.getLocation(), null, user.getLocation()};
            Inventory[] inventoryList = {null, null, null, null};

            super.targetList = targetList;
            super.vectorList = vectorList;
            super.locationList = locationList;
            super.inventoryList = inventoryList;

            ParticleSequence particleSequence = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.DUST, 16, 1, 0, 0, 0.5, new DustOptions(Color.GREEN, 3));

            BobuxAction[] actionList = 
            {new ChangeVelocity(8),
            new PlayParticle(particleSequence),
            new DamageEntity(2),
            new RepulseFromPoint(4.0, 5.0, 0.5)};
        
            super.actionList = actionList;
            return true;
        }
        return false;
    }
}