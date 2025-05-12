package io.github.mxylery.bobuxplugin.core.ability_types;

import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.core.*;

public class AbilityOneTime extends BobuxAbility {

    public AbilityOneTime(long cooldown, BukkitScheduler scheduler, String name) {
        super(cooldown, scheduler, name);
    }

    

}
