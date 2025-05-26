package io.github.mxylery.bobuxplugin.entities;

import org.bukkit.Location;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.listeners.BobuxEntityListener;

public abstract class BobuxLivingEntity extends BobuxEntity {

    protected int maxHealth;
    protected boolean isDead;

    public BobuxLivingEntity(BobuxPlugin plugin, BobuxEntityListener listener, Location location) {
        super(plugin, listener, location);
        isDead = false;
    }

    public boolean isDead() {
        return entity.isDead();
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }
    
}
