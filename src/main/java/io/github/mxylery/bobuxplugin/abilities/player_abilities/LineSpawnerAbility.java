package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityPassive;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;

public class LineSpawnerAbility extends AbilityPassive {

    public LineSpawnerAbility() {
        super("Line Spawner Ability", true, 5, 0);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        Location elevatedPlayerLoc = new Location(user.getWorld(), user.getLocation().getX(), user.getLocation().getY() + 1, user.getLocation().getZ());

        ParticleSequence particleSequence1 = 
        new ParticleSequence(ParticleSequenceOptions.LINE, ParticleSequenceOrientations.NORMAL, Particle.END_ROD, null);
        particleSequence1.setLineOptions(30, 2, 0);

        componentHead = new AbilityComponent(new PlayParticle(particleSequence1), user.getLocation().getDirection(), elevatedPlayerLoc);

        return true;
    }

}
