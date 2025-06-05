package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.entity.Projectile;
import org.bukkit.entity.*;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.entities.entities.BobuxProjectile;
import io.github.mxylery.bobuxplugin.entities.entities.projectiles.BobuxEgg;


public class SpawnEgg extends BobuxAction {
    
    private double magnitude;
    private boolean hasGravity;
    private BobuxAbility startAbility;
    private BobuxAbility endAbility;

    public SpawnEgg(double magnitude, boolean hasGravity, BobuxAbility startAbility, BobuxAbility endAbility) {
        this.magnitude = magnitude;
        this.hasGravity = hasGravity;
        this.startAbility = startAbility;
        this.endAbility = endAbility;
        super.requiresLocation = true;
        super.requiresVector = true;
    }   

    public void run() {
        BobuxEgg egg = new BobuxEgg(BobuxUtils.offsetLocation(location, vector, 1, 1), vector, magnitude, hasGravity, startAbility, endAbility);
    }
}
