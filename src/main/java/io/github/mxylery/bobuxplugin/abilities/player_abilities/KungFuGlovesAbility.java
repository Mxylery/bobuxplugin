package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.abilities.ability_types.PhaseAbility;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlayParticle;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.entity.DamageEntity;
import io.github.mxylery.bobuxplugin.actions.entity.EffectGive;
import io.github.mxylery.bobuxplugin.actions.entity.StunMob;
import io.github.mxylery.bobuxplugin.actions.velocity.ChangeVelocity;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOptions;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class KungFuGlovesAbility extends PhaseAbility {

    public KungFuGlovesAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Player player = (Player) user;
        Vector eyeVector = player.getLocation().getDirection();
        RegistererOption registererOption1 = new RegistererOption(RegistererType.LINE, 8, 2, 1, eyeVector);
        BobuxRegisterer<Mob> registerer1 = new BobuxRegisterer<Mob>(registererOption1, player, Mob.class);

        Vector slightKnockUp = new Vector(eyeVector.getX()*0.1, 1, eyeVector.getZ()*0.1);
        Vector slightLeap = new Vector(eyeVector.getX(), eyeVector.getY()*0.5 + 0.8, eyeVector.getZ());
        Entity[][] targetList;
        Vector[] vectorList;
        Location[] locationList;
        Inventory[] inventoryList;

        //phase 0
        if (registerer1.getEntityList() == null) {
            targetList = new Entity[1][1];
            vectorList = new Vector[1];
            locationList = new Location[1];
            inventoryList = new Inventory[1];
            actionList = new BobuxAction[1];
            targetList[0][0] = player;
            vectorList[0] = slightLeap;
            actionList[0] = new ChangeVelocity(10);
            phase = 0;
            super.ignoreCD = false;
        //phase 1
        } else {
            targetList = new Entity[7][1];
            vectorList = new Vector[7];
            locationList = new Location[7];
            inventoryList = new Inventory[7];
            actionList = new BobuxAction[7];
            targetList[0][0] = player;
            targetList[1][0] = registerer1.getEntityList()[0];
            targetList[2][0] = registerer1.getEntityList()[0];
            targetList[3][0] = player;
            targetList[5][0] = player;
            targetList[6][0] = registerer1.getEntityList()[0];
            vectorList[0] = slightLeap;
            vectorList[1] = slightKnockUp;
            vectorList[2] = slightKnockUp;
            vectorList[3] = slightKnockUp;
            vectorList[4] = slightKnockUp;
            locationList[3] = player.getLocation();
            locationList[4] = registerer1.getEntityList()[0].getLocation();
            actionList[0] = new ChangeVelocity(8);
            actionList[1] = new ChangeVelocity(7);
            actionList[2] = new DamageEntity(3);
            actionList[3] = new PlaySound(Sound.BLOCK_WOOD_BREAK, 1.0f, 1.0f);
            ParticleSequence kungFuParticle = new ParticleSequence(ParticleSequenceOptions.EXPLOSION, ParticleSequenceOrientations.NORMAL, Particle.CRIT, 8, 1.0, 0, 0.0, 1,  null);
            actionList[4] = new PlayParticle(kungFuParticle);
            actionList[5] = new EffectGive(PotionEffectType.RESISTANCE, 60, 2);
            actionList[6] = new StunMob(14);
            phase = 1;
            triggerPhase(registerer1, player, BobuxItemInterface.kungFuGloves, 15);
        } 
        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;
        super.actionList = actionList;
        return true;
    }

}
