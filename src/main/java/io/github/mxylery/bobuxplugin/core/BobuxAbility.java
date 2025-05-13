package io.github.mxylery.bobuxplugin.core;

import org.bukkit.entity.Player;

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
    protected String name;

    public BobuxAbility(long cooldown, PlayerAbilityInstanceCondition[] conditions, String name) {
        this.cooldown = cooldown;
        this.conditions = conditions;
        this.name = name;
    }

    public long getCooldown() {
        return cooldown;
    }

    public String getName() {
        return name;
    }

    public PlayerAbilityInstanceCondition[] getConditionList() {
        return conditions;
    }

    //Adjusts number of an action (if applicable) by a flat amount
    public void increaseFlat() {

    }

    //Adjusts numbers of an action (if applicable) by a percent (-100% = 0x, +100% = 2x)
    public void adjustPerc() {
        
    }

    protected void updateListSize() {
        BobuxAction[] newActionList = new BobuxAction[actionList.length*2];
        System.arraycopy(actionList, 0, newActionList, 0, actionList.length);
        actionList = newActionList;
    }

}
