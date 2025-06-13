package io.github.mxylery.bobuxplugin.entities.entities.projectiles;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.BobuxAbility;
import io.github.mxylery.bobuxplugin.entities.entities.BobuxProjectile;

//The generic is what class it will target.
public class BobuxArrow extends BobuxProjectile {

    private Arrow arrow;

    public BobuxArrow(Location location, Vector velocity, double magnitude, boolean hasGravity, BobuxAbility startAbility, BobuxAbility endAbility) {
        super(location, velocity, magnitude, hasGravity, startAbility, endAbility);
        this.arrow = (Arrow) super.entity;
        arrow.getLocation().setDirection(velocity);
    }

    protected void setProjClass() {
        super.clazz = Arrow.class;
    }
}
