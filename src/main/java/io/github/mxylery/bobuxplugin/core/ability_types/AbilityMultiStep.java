package io.github.mxylery.bobuxplugin.core.ability_types;

import io.github.mxylery.bobuxplugin.conditions.PlayerAbilityInstanceCondition;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxAction;

public class AbilityMultiStep extends BobuxAbility {
    
    private BobuxAction[][] stepList;
    private int step;
    private int actions = 0;
    private long delays[];

    /**
     * 
     * @param cooldown Cooldown in ticks (20 ticks/s) of the ability
     * @param name Name of the ability (should be same name as item, numbered if it has multiple abilities)
     * @param conditions Ability conditions to activate (if any)
     * @param steps Number of different steps/stages the ability has
     */
    public AbilityMultiStep(long cooldown, PlayerAbilityInstanceCondition[] conditions, int steps, String name) {
        super(cooldown, conditions, name);
        delays = new long[steps];
        delays[0] = 0;
        stepList = new BobuxAction[steps][10];
        stepList[0] = actionList;
    }

    public void addActionList(BobuxAction[] actionList) {
        if (actions == stepList[step].length - 1) {
            super.updateListSize();
            stepList[step] = actionList;
        }
        stepList[step] = actionList;
        actions++;
    }

    /**
     * Ysed to add steps and indicate their delays in the BobuxItemInterface
     * @param delay The delay in ticks (20 ticks/s)
     */
    public void newStep(long delay) {
        actionList = new BobuxAction[10];
        actions = 0;
        step++;
        stepList[step] = actionList;
        delays[step] = delay;
    }

    public void repeat() {
        stepList[step+1] = stepList[step];
        step++;
    }

}
