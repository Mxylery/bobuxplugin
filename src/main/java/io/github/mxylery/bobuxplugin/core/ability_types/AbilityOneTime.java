package io.github.mxylery.bobuxplugin.core.ability_types;


import io.github.mxylery.bobuxplugin.conditions.PlayerAbilityInstanceCondition;
import io.github.mxylery.bobuxplugin.core.*;

public class AbilityOneTime extends BobuxAbility {

    public AbilityOneTime(long cooldown, PlayerAbilityInstanceCondition[] conditions, BobuxAction[] actionList, String name, boolean muteCD) {
        super(cooldown, conditions, name, muteCD);
        this.actionList = actionList;
    }

    public BobuxAction[] getActionList() {
        return actionList;
    }

}
