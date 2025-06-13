package io.github.mxylery.bobuxplugin.actions.entity;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;

import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitScheduler;

public class StunMob extends BobuxAction {
    
private int time;

/**
 * Requires an entity.
 * @param time
 * @param requiresCondition
 */
public StunMob(int time) {
    this.time = time;
    super.requiresEntity = true;
}

public void run() {
    int j = -1;
    Mob[] mobArray = new Mob[super.entityList.length];
    //Filter through all of the non-damageable entities
    for (int i = 0; i < super.entityList.length; i++) {
        if (super.entityList[i] instanceof Damageable) {
            mobArray[j + 1] = (Mob) super.entityList[i];
            j++;
        }
    }
    for (int i = 0; i < j + 1; i++) {
        Mob mob = mobArray[i];
        if (mob.isAware()) {
            mob.setAware(false);
            Runnable runnable = new Runnable() {
                public void run() {
                    mob.setAware(true);
                }
            };
            BukkitScheduler scheduler = BobuxTimer.getScheduler();
            scheduler.runTaskLater(BobuxTimer.getPlugin(), runnable, time);
            }
        }
    }
}
