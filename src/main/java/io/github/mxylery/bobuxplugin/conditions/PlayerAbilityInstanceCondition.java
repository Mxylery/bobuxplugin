package io.github.mxylery.bobuxplugin.core;

import org.bukkit.entity.Player;


public class PlayerAbilityInstanceCondition {
    private Player player;
    private long timeFrame;
    private BobuxAbility ability;
    private double radius;

    public PlayerAbilityInstanceCondition(Player player, long timeFrame, BobuxAbility ability, double radius) {
        this.player = player;
        this.timeFrame = timeFrame;
        this.ability = ability;
        this.radius = radius;
    }

    public PlayerAbilityInstanceCondition(long timeFrame, BobuxAbility ability, double radius) {
        this.player = null;
        this.timeFrame = timeFrame;
        this.ability = ability;
        this.radius = radius;
    }

    public PlayerAbilityInstanceCondition(Player player, long timeFrame, BobuxAbility ability) {
        this.player = player;
        this.timeFrame = timeFrame;
        this.ability = ability;
        this.radius = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public long getTimeFrame() {
        return timeFrame;
    }

    public BobuxAbility getAbility() {
        return ability;
    }

    public double getRadius() {
        return radius;
    }

}
