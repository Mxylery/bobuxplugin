package io.github.mxylery.bobuxplugin.entities.entities.projectiles;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.entities.entities.BobuxProjectile;

//The generic is what class it will target.
public class BobuxPearl extends BobuxProjectile {

    private EnderPearl pearl;

    public BobuxPearl(Location location, Vector velocity, double magnitude, boolean hasGravity, BobuxAbility startAbility, BobuxAbility endAbility) {
        super(location, velocity, magnitude, hasGravity, startAbility, endAbility);
        this.pearl = (EnderPearl) entity;
    }

    protected void setProjClass() {
        super.clazz = EnderPearl.class;
    }

    public void setOwner(ProjectileSource source) {
        pearl.setShooter(source);
    }

}
