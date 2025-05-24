package io.github.mxylery.bobuxplugin.abilities.ability_types;

import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.conditions.PlayerAbilityInstanceCondition;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;

//In the playerabilitymanager, these abilities will recursively call useAbility at later dates specified by the rep cycle until the condition is removed.
public class AbilityPassive extends BobuxAbility {

    public AbilityPassive(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    public BobuxAction[] getActionList() {
        return actionList;
    }

    public void adjustPerc() {

    }

    public void adjustFlat() {

    }

    protected boolean assignVariables() {
        return false;
    }

}
