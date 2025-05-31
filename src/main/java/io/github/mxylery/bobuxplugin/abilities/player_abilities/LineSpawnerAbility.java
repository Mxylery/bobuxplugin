package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityPassive;
import io.github.mxylery.bobuxplugin.actions.PlayParticle;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;

public class LineSpawnerAbility extends AbilityPassive {

    public LineSpawnerAbility(String name, boolean muteCD, long cooldown, long period) {
        super(name, muteCD, cooldown, period);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Location elevatedPlayerLoc = new Location(user.getWorld(), user.getLocation().getX(), user.getLocation().getY() + 1, user.getLocation().getZ());
        Entity[][] targetList = {null};
        Vector[] vectorList = {user.getEyeLocation().getDirection()};
        Location[] locationList = {elevatedPlayerLoc};
        Inventory[] inventoryList = {null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        ParticleSequence particleSequence1 = 
        new ParticleSequence(ParticleSequenceOrientations.NORMAL, Particle.END_ROD, 30, 2.0, 0.0, null);
        BobuxAction[] railgunActionList = {new PlayParticle(particleSequence1)};
        
        super.actionList = railgunActionList;
        return true;
    }

}
