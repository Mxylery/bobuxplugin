package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.item.GiveItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;

public class BobuxinatorAbility extends AbilityOneTime {

    public BobuxinatorAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {

        Player player = (Player) user;
        
        Entity[][] targetList = {{null},{null},{null}};
        Vector[] vectorList = {null, null, player.getLocation().getDirection()};
        Location[] locationList = {null, player.getLocation(), player.getLocation()};
        Inventory[] inventoryList = {player.getInventory(), null, null};

        ParticleSequence particleSequence = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.DUST, new DustOptions(Color.fromRGB(50,255,50), 2));
        particleSequence.setExplosionOptions(2, 10, 1);

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        BobuxAction[] actionList = 
        {new GiveItem(BobuxItemInterface.bobux.getStack(), 1),
        new PlaySound(Sound.ENTITY_ITEM_PICKUP, 1.0f, 4.0f),
        new PlayParticle(particleSequence)};
        
        super.actionList = actionList;
        return true;
    }

}
