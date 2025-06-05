package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.entity.Projectile;
import org.bukkit.entity.*;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.entities.entities.BobuxProjectile;
import io.github.mxylery.bobuxplugin.entities.entities.projectiles.BobuxEgg;
import io.github.mxylery.bobuxplugin.entities.entities.projectiles.BobuxPearl;


public class SpawnPearl extends BobuxAction {
    
    private double magnitude;
    private boolean hasGravity;
    private BobuxAbility startAbility;
    private BobuxAbility endAbility;

    public SpawnPearl(double magnitude, boolean hasGravity, BobuxAbility startAbility, BobuxAbility endAbility) {
        this.magnitude = magnitude;
        this.hasGravity = hasGravity;
        this.startAbility = startAbility;
        this.endAbility = endAbility;
        super.requiresEntity = true;
        super.requiresLocation = true;
        super.requiresVector = true;
    }   

    public void run() {
        BobuxPearl pearl = new BobuxPearl(BobuxUtils.offsetLocation(location, vector, 1, 1), vector, magnitude, hasGravity, startAbility, endAbility);
        Player player = (Player) entityList[0];
        pearl.setOwner(player);
    }
}
