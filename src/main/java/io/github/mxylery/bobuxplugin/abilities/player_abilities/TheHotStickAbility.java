package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.DamageEntity;
import io.github.mxylery.bobuxplugin.actions.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.PlaySound;
import io.github.mxylery.bobuxplugin.actions.SetFire;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class TheHotStickAbility extends AbilityOneTime {

    public TheHotStickAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Location elevatedPlayerLoc = new Location(user.getWorld(), user.getLocation().getX(), user.getLocation().getY() + 1, user.getLocation().getZ());
        RegistererOption registererOption1 = new RegistererOption(RegistererType.LINE, 30, 1, 1, user.getEyeLocation().getDirection());
        BobuxRegisterer<Mob> registerer1 = new BobuxRegisterer<Mob>(registererOption1, user, Mob.class);
        if (registerer1.getEntityList() == null) {
            return false;
        }
        Entity[][] targetList = {registerer1.getEntityList(),{},{},registerer1.getEntityList(),{}};
        Location entityLoc = registerer1.getEntityList()[0].getLocation();
        Vector[] vectorList = {null, user.getEyeLocation().getDirection(), user.getEyeLocation().getDirection(), null, null};
        Location[] locationList = {null, elevatedPlayerLoc, entityLoc, null, elevatedPlayerLoc};
        Inventory[] inventoryList = {null, null, null, null, null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        ParticleSequence theHotStickParticleSequence1 = new ParticleSequence
        (ParticleSequenceOrientations.NORMAL, Particle.FLAME, 30, 5, 0, null);
        ParticleSequence theHotStickParticleSequence2 = new ParticleSequence
        (ParticleSequenceOptions.RING, ParticleSequenceOrientations.NORMAL, Particle.FLAME, 5, 5, 5, 5, 0, null);

        BobuxAction[] theHotStickActionList = 
        {new DamageEntity(10), 
        new PlayParticle(theHotStickParticleSequence1),
        new PlayParticle(theHotStickParticleSequence2),
        new SetFire(200),
        new PlaySound(Sound.ITEM_FIRECHARGE_USE, 0.5f, 1.0f)};
        
        super.actionList = theHotStickActionList;
        return true;
    }

}
