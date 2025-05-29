package io.github.mxylery.bobuxplugin.actions;

import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;

import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitScheduler;

/**
 * First example of a bobux action: all of the abilities will use them 
 * in conjunction to allow for as much expression as the creator wants 
 * and with relative ease
 */
public class StunMob extends BobuxAction {
    
private int time;

//This action needs an entity to damage and a number to damage for
public StunMob(int time, boolean requiresCondition) {
    this.time = time;
    super.requiresCondition = requiresCondition;
    super.requiresEntity = true;
}

public void adjustFlat(double increase) {
    this.time += increase;
}

public void adjustPerc(double increase) {
    this.time = this.time*( (int) increase/100 + 1);
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
