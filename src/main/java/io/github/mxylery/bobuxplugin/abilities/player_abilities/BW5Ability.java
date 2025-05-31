package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.DamageEntity;
import io.github.mxylery.bobuxplugin.actions.DeleteItem;
import io.github.mxylery.bobuxplugin.actions.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.PlaySound;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class BW5Ability extends AbilityOneTime {

    public BW5Ability(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Location elevatedPlayerLoc = new Location(user.getWorld(), user.getLocation().getX(), user.getLocation().getY() + 1, user.getLocation().getZ());
        Vector playerDirection = user.getLocation().getDirection();
        RegistererOption registererOption1 = new RegistererOption(RegistererType.LINE, 30, 1, 1, user.getEyeLocation().getDirection());
        BobuxRegisterer<Mob> registerer1 = new BobuxRegisterer<Mob>(registererOption1, user, Mob.class);
        if (registerer1.getEntityList() == null) {
            return false;
        }
        Entity[][] targetList = {registerer1.getEntityList(),{},{},{},{},{},{},{},{}};
        Location enemyLocation = new Location(user.getWorld(), registerer1.getEntityList()[0].getLocation().getX(), registerer1.getEntityList()[0].getLocation().getY() + 1, registerer1.getEntityList()[0].getLocation().getZ());
        Vector[] vectorList = {null, playerDirection, playerDirection, playerDirection, playerDirection, playerDirection, playerDirection, playerDirection, null};
        Location[] locationList = {null, elevatedPlayerLoc, enemyLocation, enemyLocation, enemyLocation, enemyLocation, enemyLocation, enemyLocation, enemyLocation};
        Inventory[] inventoryList = {null, null, null, null, null, null, null, null, user.getInventory()};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        ParticleSequence BW5ParticleSequence1 = new ParticleSequence
        (ParticleSequenceOrientations.NORMAL, Particle.WHITE_SMOKE, 30, 1, 0, null);
        ParticleSequence BW5ParticleSequence2 = new ParticleSequence
        (ParticleSequenceOptions.RING, ParticleSequenceOrientations.NORMAL, Particle.DUST, 5, 3, 2, 2, 0, new DustOptions(Color.RED, 2));
        ParticleSequence BW5ParticleSequence3 = new ParticleSequence
        (ParticleSequenceOrientations.UP, Particle.DUST, 2, 3, 1, new DustOptions(Color.RED, 2));
        ParticleSequence BW5ParticleSequence4 = new ParticleSequence
        (ParticleSequenceOrientations.RIGHT, Particle.DUST, 2, 3, 1, new DustOptions(Color.RED, 2));
        ParticleSequence BW5ParticleSequence5 = new ParticleSequence
        (ParticleSequenceOrientations.DOWN, Particle.DUST, 2, 3, 1, new DustOptions(Color.RED, 2));
        ParticleSequence BW5ParticleSequence6 = new ParticleSequence
        (ParticleSequenceOrientations.LEFT, Particle.DUST, 2, 3, 1, new DustOptions(Color.RED, 2));
        BobuxAction[] BW5ActionList = 
        {new DamageEntity(30), 
        new PlayParticle(BW5ParticleSequence1),
        new PlayParticle(BW5ParticleSequence2),
        new PlayParticle(BW5ParticleSequence3),
        new PlayParticle(BW5ParticleSequence4),
        new PlayParticle(BW5ParticleSequence5),
        new PlayParticle(BW5ParticleSequence6),
        new PlaySound(Sound.ENTITY_GENERIC_EXPLODE, 0.4f, 0.5f),
        new DeleteItem(BobuxItemInterface.BW5Ammo.getStack(), 1)};
        
        super.actionList = BW5ActionList;
        return true;
    }

}
