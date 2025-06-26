package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.item.GiveItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;

public class BobuxinatorAbility extends AbilityOneTime {

    public BobuxinatorAbility() {
        super("Bobuxinator Ability", false, 1199);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        Player player = (Player) user;

        ParticleSequence particleSequence = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.DUST, new DustOptions(Color.fromRGB(50,255,50), 2));
        particleSequence.setExplosionOptions(2, 10, 1);

        componentHead = new AbilityComponent(new GiveItem(BobuxItemInterface.bobux.getStack(), 1), player.getInventory());

        componentHead.addComponent(new AbilityComponent
        (new PlaySound(Sound.ENTITY_ITEM_PICKUP, 1.0f, 4.0f), player.getLocation()));
        componentHead.addComponent(new AbilityComponent
        (new PlayParticle(particleSequence), player.getLocation().getDirection(), player.getLocation()));
        
        return true;
    }

}
