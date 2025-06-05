package io.github.mxylery.bobuxplugin.entities.entities.projectiles;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.entities.entities.BobuxProjectile;

//The generic is what class it will target.
public class BobuxEgg extends BobuxProjectile {

    public BobuxEgg(Location location, Vector velocity, double magnitude, boolean hasGravity, BobuxAbility startAbility, BobuxAbility endAbility) {
        super(location, velocity, magnitude, hasGravity, startAbility, endAbility);
    }

    protected void setProjClass() {
        super.clazz = Egg.class;
    }

}
