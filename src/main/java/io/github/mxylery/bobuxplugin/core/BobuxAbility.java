package io.github.mxylery.bobuxplugin.core;

import org.bukkit.Server;
import org.bukkit.scheduler.BukkitScheduler;

/* This will be used for the ability manager and to 
* give each bobux item a given ability (defining them 
* in their class def, not a random other managing class)
*/

//NAMING CONVENTION: CAPITALIZE THE NAME OF THE ABILITY THE EXACT SAME WAY AS THE NAME OF THE ITEM THAT IT IS THE ABILITY FOR.
//IF THERE ARE SEVERAL, NUMBER THEM.
public abstract class BobuxAbility {
    
    protected long cooldown;
    protected BukkitScheduler scheduler;
    protected String name;
    protected BobuxAction[] actionList;

    public BobuxAbility(long cooldown, BukkitScheduler scheduler, String name) {
        this.cooldown = cooldown;
        this.scheduler = scheduler;
        this.name = name;
        actionList = new BobuxAction[10];
    }


    public String getName() {
        return name;
    }

    public long getCooldown() {
        return cooldown;
    }

    public BobuxAction[] getActionList() {
        return actionList;
    }

    protected void updateListSize() {
        BobuxAction[] newActionList = new BobuxAction[actionList.length*2];
        System.arraycopy(actionList, 0, newActionList, 0, actionList.length);
        actionList = newActionList;
    }

}
