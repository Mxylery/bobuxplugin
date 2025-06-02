package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
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

public class CleaverAbility extends AbilityOneTime {

    public CleaverAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {

        Vector userEyeVector = user.getLocation().getDirection();
        Location sphereLoc = BobuxUtils.offsetLocation(singleTarget.getLocation(), userEyeVector, 3, 0);
        RegistererOption option = new RegistererOption(RegistererType.SPHERE, 2.5, 3, 0, user.getLocation().getDirection());
        BobuxRegisterer<Mob> registerer = new BobuxRegisterer<Mob>(option, singleTarget, user, Mob.class);
        Vector slightKnockUp = new Vector(userEyeVector.getX(), userEyeVector.getY() + 1, userEyeVector.getZ());

        if (registerer.getEntityList() == null) {
            return false;
        }
        Entity[][] targetList = {registerer.getEntityList(),registerer.getEntityList(),null};
        Vector[] vectorList = {null, slightKnockUp, user.getLocation().getDirection()};
        Location[] locationList = {null, null, sphereLoc};
        Inventory[] inventoryList = {null, null, null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        ParticleSequence particleSequence = new ParticleSequence
        (ParticleSequenceOptions.RING, ParticleSequenceOrientations.DOWN, Particle.DUST, 2, 0, 0, 3, 0, new DustOptions(Color.YELLOW, 2));
        BobuxAction[] actionList = 
        {new DamageEntity(5), 
        new ChangeVelocity(8), 
        new PlayParticle(particleSequence)};
        
        super.actionList = actionList;
        return true;
    }

}
