package io.github.mxylery.bobuxplugin.entities;

import org.bukkit.Location;
import org.bukkit.entity.Mob;

public abstract class BobuxMob extends BobuxLivingEntity {

    protected double attackDamage;
    protected boolean isDead;
    protected Mob mob;

    public BobuxMob(Location location) {
        super(location);
        this.mob = (Mob) super.livingEntity;
    }
}
