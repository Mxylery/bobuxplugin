package io.github.mxylery.bobuxplugin.core;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class AbilityInstance {
    private Entity entity;
    private long tick;
    private BobuxAbility abilityUsed;

    public AbilityInstance(Entity entity, long tick, BobuxAbility abilityUsed) {
        this.entity = entity;
        this.tick = tick;
        this.abilityUsed = abilityUsed;
    }

    public long getTick() {
        return tick;
    }

    public BobuxAbility getAbility() {
        return abilityUsed;
    }

    public Entity getEntity() {
        return entity;
    }

}
