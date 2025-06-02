package io.github.mxylery.bobuxplugin.abilities.mob_abilities;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.Particle.DustOptions;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.ChangeVelocity;
import io.github.mxylery.bobuxplugin.actions.DamageEntity;
import io.github.mxylery.bobuxplugin.actions.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.PlaySound;
import io.github.mxylery.bobuxplugin.actions.RepulseFromPoint;
import io.github.mxylery.bobuxplugin.actions.SpawnItem;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class SandbaggerAbilityOne extends AbilityOneTime {

    private int limit = 0;

    public SandbaggerAbilityOne(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {

        Entity[][] targetList = {null, null, null};
        Vector[] vectorList = {null, new Vector(0,1,0), null};
        Location[] locationList = {user.getLocation(), user.getLocation(), user.getLocation()};
        Inventory[] inventoryList = {null, null, null};

        double rng = Math.random();
        ItemStack bobuxStack;
        int bobuxAmnt;
        ItemStack otherStack;

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

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        ParticleSequence particleSequence = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.DUST, 16, 1, 0, 0, 0.5, new DustOptions(Color.YELLOW, 3));

        BobuxAction[] actionList = 
        {new SpawnItem(bobuxStack, bobuxAmnt),
        new PlayParticle(particleSequence),
        new PlaySound(Sound.ENTITY_VILLAGER_TRADE, 1.0f, 1.0f)};
        
        super.actionList = actionList;
        limit++;
        return true;
        
    }
}