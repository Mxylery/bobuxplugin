package io.github.mxylery.bobuxplugin.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.entities.BobuxEntity;
import io.github.mxylery.bobuxplugin.entities.mobs.StinkyMob;

//This is where all cooldowns get registered to players and whatnot. Also take into account mobs, can have seperate methods or even a seperate class
public class MobAbilityManager {

    public static boolean verifyAbilityCD(BobuxEntity bobuxEntity, int index, Player player) {
        BobuxAbility ability = bobuxEntity.getAbility(index);
        long cooldown = ability.getCooldown();
        long lastUse = bobuxEntity.getAbilityHistory().checkForAbilityCD(ability, cooldown, bobuxEntity.getEntity());
        ability.setOtherEntity(bobuxEntity.getEntity());
        ability.setUser(player);    
        //If no such ability was casted in the past #cooldown ticks
        if (lastUse == -1 && ability.setActionList()) {
            bobuxEntity.useAbility(index, player);
            return true;
        } else {
            return false;
        }
    }

    public static boolean verifyAbilityCD(BobuxEntity bobuxEntity, int index) {
        BobuxAbility ability = bobuxEntity.getAbility(index);
        long cooldown = ability.getCooldown();
        long lastUse = bobuxEntity.getAbilityHistory().checkForAbilityCD(ability, cooldown, bobuxEntity.getEntity());
        ability.setOtherEntity(bobuxEntity.getEntity());
        //If no such ability was casted in the past #cooldown ticks
        if (lastUse == -1 && ability.setActionList()) {
            bobuxEntity.useAbility(index);
            return true;
        } else {
            return false;
        }
    }
}