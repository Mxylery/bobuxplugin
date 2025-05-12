package io.github.mxylery.bobuxplugin.core;

import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.conditions.PlayerAbilityInstanceCondition;

/* This will be used for the ability manager and to 
* give each bobux item a given ability (defining them 
* in their class def, not a random other managing class)
*/

//NAMING CONVENTION: CAPITALIZE THE NAME OF THE ABILITY THE EXACT SAME WAY AS THE NAME OF THE ITEM THAT IT IS THE ABILITY FOR.
//IF THERE ARE SEVERAL, NUMBER THEM.
public abstract class BobuxAbility {
    
    protected long cooldown;
    protected BobuxAction[] actionList;
    protected PlayerAbilityInstanceCondition[] conditions;
    protected Entity[] entityList;

    public BobuxAbility(long cooldown, PlayerAbilityInstanceCondition[] conditions) {
        this.cooldown = cooldown;
        this.conditions = conditions;
        entityList = new Entity[10];
    }

    public long getCooldown() {
        return cooldown;
    }

    public PlayerAbilityInstanceCondition[] getConditionList() {
        return conditions;
    }

    public Entity[] getEntityList() {
        return entityList;
    }

    protected void updateListSize() {
        BobuxAction[] newActionList = new BobuxAction[actionList.length*2];
        System.arraycopy(actionList, 0, newActionList, 0, actionList.length);
        actionList = newActionList;
    }

}
