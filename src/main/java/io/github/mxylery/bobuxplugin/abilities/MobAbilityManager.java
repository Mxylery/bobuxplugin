package io.github.mxylery.bobuxplugin.abilities;

import org.bukkit.entity.Entity;

import io.github.mxylery.bobuxplugin.entities.BobuxEntity;
import io.github.mxylery.bobuxplugin.entities.BobuxHostile;
import io.github.mxylery.bobuxplugin.entities.entities.BobuxProjectile;

//This is where all cooldowns get registered to entities and whatnot. Also take into account mobs, can have seperate methods or even a seperate class
public class MobAbilityManager {

    public static boolean verifyAbilityCD(BobuxEntity bobuxEntity, int index, Entity entity) {
        if (index == -1) {
            BobuxHostile hostile = (BobuxHostile) bobuxEntity;
            BobuxAbility ability = hostile.getHitAbility();
            long cooldown = ability.getCooldown();
            long lastUse = bobuxEntity.getAbilityHistory().checkForAbilityCD(ability, cooldown, bobuxEntity.getEntity());
            ability.setUser(bobuxEntity.getEntity());
            ability.setTarget(entity);
            if (lastUse == -1 && ability.setActionList()) {
                hostile.useAbility(-1, entity);
                return true;
            } else {
                return false;
            }
        }
        BobuxAbility ability = bobuxEntity.getAbility(index);
        long cooldown = ability.getCooldown();
        if (bobuxEntity instanceof BobuxProjectile) {
            ability.setUser(bobuxEntity.getEntity());
            ability.setTarget(entity);
            if (ability.setActionList()) {
                bobuxEntity.useAbility(index, entity);
                return true;
            } else {
                return false;
            }
        }
        long lastUse = bobuxEntity.getAbilityHistory().checkForAbilityCD(ability, cooldown, bobuxEntity.getEntity());
        ability.setUser(bobuxEntity.getEntity());
        ability.setTarget(entity);
        //If no such ability was casted in the past #cooldown ticks
        if (lastUse == -1 && ability.setActionList()) {
            bobuxEntity.useAbility(index, entity);
            return true;
        } else {
            return false;
        }
    }

    public static boolean verifyAbilityCD(BobuxEntity bobuxEntity, int index) {
        BobuxAbility ability = bobuxEntity.getAbility(index);
        long cooldown = ability.getCooldown();
        if (bobuxEntity instanceof BobuxProjectile) {
            ability.setUser(bobuxEntity.getEntity());
            if (ability.setActionList()) {
                bobuxEntity.useAbility(index);
                return true;
            } else {
                return false;
            }
        }
        long lastUse = bobuxEntity.getAbilityHistory().checkForAbilityCD(ability, cooldown, bobuxEntity.getEntity());
        ability.setUser(bobuxEntity.getEntity());
        //If no such ability was casted in the past #cooldown ticks
        if (lastUse == -1 && ability.setActionList()) {
            bobuxEntity.useAbility(index);
            return true;
        } else {
            return false;
        }
    }
}