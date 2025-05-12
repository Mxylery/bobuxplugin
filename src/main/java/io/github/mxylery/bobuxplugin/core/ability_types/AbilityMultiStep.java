package io.github.mxylery.bobuxplugin.core.ability_types;

import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxAction;

public class AbilityMultiStep extends BobuxAbility {
    
    private BobuxAction[][] stepList;
    private int steps;
    private int step;
    private int actions = 0;
    private int delays[];

    public AbilityMultiStep(long cooldown, BukkitScheduler scheduler, String name, int steps) {
        super(cooldown, scheduler, name);
        this.steps = steps;
        delays = new int[steps];
        delays[0] = 0;
        stepList = new BobuxAction[steps][10];
        stepList[0] = actionList;
    }

    public void addToActionList(BobuxAction action) {
        if (actions == stepList[step].length - 1) {
            super.updateListSize();
            stepList[step] = actionList;
        }
        stepList[step][actions] = action;
        actions++;
    }

    public void newStep(int delay) {
        actionList = new BobuxAction[10];
        actions = 0;
        step++;
        stepList[step] = actionList;
    }

    public void repeat() {
        stepList[step+1] = stepList[step];
        step++;
    }

}
