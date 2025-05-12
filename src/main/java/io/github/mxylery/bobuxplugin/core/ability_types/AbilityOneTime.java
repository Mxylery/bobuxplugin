package io.github.mxylery.bobuxplugin.core.ability_types;

import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.core.*;

public class AbilityOneTime extends BobuxAbility {

    private int actions = 0;

    public AbilityOneTime(long cooldown, BukkitScheduler scheduler, String name) {
        super(cooldown, scheduler, name);
    }

    public void abilityExecute() {

    }

    public void addToActionList(BobuxAction action) {
        if (actions == actionList.length - 1) {
            super.updateListSize();
        }
        actionList[actions] = action;
        actions++;
    }

}
