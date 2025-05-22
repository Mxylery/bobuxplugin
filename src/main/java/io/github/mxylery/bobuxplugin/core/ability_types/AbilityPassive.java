package io.github.mxylery.bobuxplugin.core.ability_types;

import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.conditions.PlayerAbilityInstanceCondition;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;

//In the playerabilitymanager, these abilities will recursively call useAbility at later dates specified by the rep cycle until the condition is removed.
public class AbilityPassive extends BobuxAbility {

    private BukkitScheduler scheduler = BobuxTimer.getScheduler();

    public AbilityPassive(long cooldown, PlayerAbilityInstanceCondition[] conditions, BobuxAction[] actionList, String name, boolean muteCD) {
        super(cooldown, conditions, name, muteCD);
        this.actionList = actionList;
    }

    public BobuxAction[] getActionList() {
        return actionList;
    }

    public void checkPassive() {

    }

}
