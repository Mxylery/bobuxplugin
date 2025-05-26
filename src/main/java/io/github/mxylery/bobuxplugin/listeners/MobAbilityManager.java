package io.github.mxylery.bobuxplugin.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.entities.BobuxEntity;
import io.github.mxylery.bobuxplugin.entities.BobuxMob;
import io.github.mxylery.bobuxplugin.entities.StinkyMob;

//This is where all cooldowns get registered to players and whatnot. Also take into account mobs, can have seperate methods or even a seperate class
public class MobAbilityManager {

    private static boolean verifyItemCD(BobuxMob bobuxMob, BobuxAbility ability) {
        long cooldown = ability.getCooldown();
        long lastUse = bobuxMob.getAbilityHistory().checkForAbilityCD(ability, cooldown, bobuxMob.getEntity());
        //If no such ability was casted in the past #cooldown ticks
        if (lastUse == -1) {
                return true;
        } else {
            return false;
        }
    }

    public static void checkForMobMatch(int index, int entityID, Entity entity, Player player) {
        BobuxEntity bobuxEntity = BobuxEntityListener.getBobuxEntity(entity);
        System.out.println("Checking...");
        if (bobuxEntity instanceof BobuxMob) {
            BobuxMob bobuxMob = (BobuxMob) bobuxEntity;
            boolean isPreciseMob = false;
            switch (entityID) {
                case 0: if (bobuxMob instanceof StinkyMob) {
                    isPreciseMob = true;
                }
                break;
            } 
            BobuxAbility ability = bobuxMob.getAbility(0);
            ability.setOtherEntity(entity);
            ability.setUser(player);
            if (ability.setActionList() && verifyItemCD(bobuxMob, ability) && isPreciseMob) {
                bobuxMob.useAbility(0, player);
            } 
            System.out.println("Is a bobux mob, not a stinky one.");       
        } else { 
            System.out.println("Not a bobux mob");
        }
    }
}