package io.github.mxylery.bobuxplugin.abilities.ability_types;


import io.github.mxylery.bobuxplugin.conditions.PlayerAbilityInstanceCondition;
import io.github.mxylery.bobuxplugin.core.*;

public abstract class AbilityOneTime extends BobuxAbility {

    public AbilityOneTime(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    public void adjustPerc() {

    }

    public void adjustFlat() {

    }

    protected void assignVariables() {

    }

    public BobuxAction[] getActionList() {
        return actionList;
    }

}
