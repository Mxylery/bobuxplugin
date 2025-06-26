package io.github.mxylery.bobuxplugin.abilities.mob_abilities;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.entity.EffectGive;
import io.github.mxylery.bobuxplugin.actions.spawn.SpawnItem;
import io.github.mxylery.bobuxplugin.actions.velocity.ChangeVelocity;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;

public class SandbaggerAbilityOne extends AbilityOneTime {

    public SandbaggerAbilityOne(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        double rng = Math.random();
        ItemStack bobuxStack;
        int bobuxAmnt;

        if (rng < 0.6) {
            bobuxStack = BobuxItemInterface.bobux.getStack();
            double rngTwo = Math.random();
            if (rngTwo < 0.5) {
                bobuxAmnt = 2;
            } else {
                bobuxAmnt = 3;
            }
        } else if (rng < 0.9) {
            bobuxStack = BobuxItemInterface.bobuxSquare.getStack();
            double rngTwo = Math.random();
            if (rngTwo < 0.8) {
                bobuxAmnt = 1;
            } else {
                bobuxAmnt = 2;
            }
        } else {
            bobuxStack = BobuxItemInterface.bobuxCube.getStack();
            bobuxAmnt = 1;
        }

        ParticleSequence particleSequence = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.DUST, new DustOptions(Color.YELLOW, 3));
        particleSequence.setExplosionOptions(1, 16, 1);

        componentHead = new AbilityComponent
        (new SpawnItem(bobuxStack, bobuxAmnt), user.getLocation());
        componentHead.addComponent(new AbilityComponent
        (new PlayParticle(particleSequence), new Vector(0,1,0), user.getLocation()));
        componentHead.addComponent(new AbilityComponent
        (new PlaySound(Sound.ENTITY_VILLAGER_TRADE, 1.0f, 1.0f), user.getLocation()));

        return true;
        
    }
}