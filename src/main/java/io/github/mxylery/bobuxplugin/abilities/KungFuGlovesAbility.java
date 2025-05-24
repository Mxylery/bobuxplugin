package io.github.mxylery.bobuxplugin.abilities;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.ChangeVelocity;
import io.github.mxylery.bobuxplugin.actions.DamageEntity;
import io.github.mxylery.bobuxplugin.actions.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.PlaySound;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.core.PlayerAbilityManager;
import io.github.mxylery.bobuxplugin.data_structures.PAIStructure;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class KungFuGlovesAbility extends AbilityOneTime {

    public KungFuGlovesAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Vector eyeVector = user.getEyeLocation().getDirection();
        RegistererOption registererOption1 = new RegistererOption(RegistererType.LINE, 8, 2, 1, eyeVector);
        BobuxRegisterer registerer1 = new BobuxRegisterer(registererOption1, user);
        Vector slightKnockUp = new Vector(eyeVector.getX(), eyeVector.getY() + 1.5, eyeVector.getZ());
        Entity[][] targetList;
        Vector[] vectorList;
        Location[] locationList;
        Inventory[] inventoryList;

        if (registerer1.getEntityList() == null) {
            targetList = new Entity[1][1];
            vectorList = new Vector[1];
            locationList = new Location[1];
            inventoryList = new Inventory[1];
            actionList = new BobuxAction[1];
            targetList[0][0] = user;
            vectorList[0] = eyeVector;
            locationList[0] = null;
            inventoryList[0] = null;
            actionList[0] = new ChangeVelocity(1, false);
            super.ignoreCD = false;
        } else {
            targetList = new Entity[3][1];
            vectorList = new Vector[3];
            locationList = new Location[3];
            inventoryList = new Inventory[3];
            actionList = new BobuxAction[3];
            targetList[0][0] = user;
            targetList[1][0] = registerer1.getEntityList()[0];
            targetList[2][0] = registerer1.getEntityList()[0];
            vectorList[0] = slightKnockUp;
            vectorList[1] = slightKnockUp;
            vectorList[2] = slightKnockUp;
            locationList[0] = null;
            locationList[1] = null;
            locationList[2] = null;
            inventoryList[0] = null;
            inventoryList[1]= null;
            inventoryList[2]= null;
            actionList[0] = new ChangeVelocity(0.7, false);
            actionList[1] = new ChangeVelocity(0.8, false);
            actionList[2] = new DamageEntity(5, false);
            if (PlayerAbilityManager.verifyItemCD(user, this, muteCD)) {
                super.ignoreCD = true;
            }
            retrigger(registerer1, user, BobuxItemInterface.kungFuGloves, 10);
        }
        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;
        super.actionList = actionList;
        return true;
    }

}
