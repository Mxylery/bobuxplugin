package io.github.mxylery.bobuxplugin.actions.spawn;

import io.github.mxylery.bobuxplugin.abilities.BobuxAbility;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.entities.entities.projectiles.BobuxArrow;


public class SpawnArrow extends BobuxAction {
    
    private double magnitude;
    private boolean hasGravity;
    private BobuxAbility startAbility;
    private BobuxAbility endAbility;

    public SpawnArrow(double magnitude, boolean hasGravity, BobuxAbility startAbility, BobuxAbility endAbility) {
        this.magnitude = magnitude;
        this.hasGravity = hasGravity;
        this.startAbility = startAbility;
        this.endAbility = endAbility;
        super.requiresLocation = true;
        super.requiresVector = true;
    }   

    public void run() {
        BobuxArrow arrow = new BobuxArrow(BobuxUtils.offsetLocation(location, vector, 1, 1), vector, magnitude, hasGravity, startAbility, endAbility);
    }
}
