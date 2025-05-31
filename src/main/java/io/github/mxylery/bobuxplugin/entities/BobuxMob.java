package io.github.mxylery.bobuxplugin.entities;

import org.bukkit.Location;
import org.bukkit.entity.Mob;
import io.github.mxylery.bobuxplugin.data_structures.AbilityInstanceStructure;

public abstract class BobuxMob extends BobuxLivingEntity {

    protected double attackDamage;
    protected boolean isDead;
    protected Mob mob;

    public BobuxMob(Location location) {
        super(location);
        abilityStructure = new AbilityInstanceStructure();
        this.mob = (Mob) super.livingEntity;
    }
}
