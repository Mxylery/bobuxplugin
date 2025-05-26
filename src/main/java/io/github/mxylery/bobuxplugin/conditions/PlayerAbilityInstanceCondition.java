package io.github.mxylery.bobuxplugin.conditions;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;


public class PlayerAbilityInstanceCondition {
    private long timeFrame;
    private BobuxAbility ability;
    private double radius;

    public PlayerAbilityInstanceCondition(long timeFrame, BobuxAbility ability, double radius) {
        this.timeFrame = timeFrame;
        this.ability = ability;
        this.radius = radius;
    }

    public PlayerAbilityInstanceCondition(long timeFrame, BobuxAbility ability) {
        this.timeFrame = timeFrame;
        this.ability = ability;
        this.radius = 0;
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
