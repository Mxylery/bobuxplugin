package io.github.mxylery.bobuxplugin.core;

import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.*;;

public class PlayerAbilityInstance {
    private Player player;
    private long tick;
    private BobuxAbility abilityUsed;

    public PlayerAbilityInstance(Player player, long tick, BobuxAbility abilityUsed) {
        this.player = player;
        this.tick = tick;
        this.abilityUsed = abilityUsed;
    }

    public long getTick() {
        return tick;
    }

    public BobuxAbility getAbility() {
        return abilityUsed;
    }

    public Player getPlayer() {
        return player;
    }

}
