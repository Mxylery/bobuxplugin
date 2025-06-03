package io.github.mxylery.bobuxplugin.entities.entities;

import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.entities.BobuxEntity;
import io.github.mxylery.bobuxplugin.listeners.MobAbilityManager;
import io.github.mxylery.bobuxplugin.vectors.ParticlePlayer;

//The generic is what class it will target.
public abstract class BobuxProjectile extends BobuxEntity {
    
    protected Class<? extends Projectile> clazz;
    protected Vector velocity;
    protected double magnitude;
    protected boolean hasGravity;
    protected ParticlePlayer trail;

    public BobuxProjectile(Location location, Vector velocity, double magnitude, boolean hasGravity, BobuxAbility startAbility, BobuxAbility endAbility) {
        super(location);
        this.velocity = velocity;
        this.magnitude = magnitude;
        this.hasGravity = hasGravity;
        BobuxAbility[] abilityList = new BobuxAbility[2];
        abilityList[0] = startAbility;
        abilityList[1] = endAbility;
        super.abilityList = abilityList;
        launchProjectile();
    }

    //This defines 
    protected abstract void setProjClass();

    @Override
    public void useAbility(int index) {
        abilityList[index].setUser(entity);
        if (abilityList[index].setActionList()) {
            abilityList[index].use();
        }
    }

    @Override
    public void useAbility(int index, Entity target) {
        abilityList[index].setUser(entity);
        abilityList[index].setTarget(target);
        if (abilityList[index].setActionList()) {
            abilityList[index].use();
        }
    }

    public void setUpEntity() {
        setProjClass();
        Projectile newProjectile = (Projectile) location.getWorld().spawn(location, clazz);
        super.entity = newProjectile;
    }

    private void launchProjectile() {
        Vector vector = new Vector(velocity.getX(), velocity.getY(), velocity.getZ());
        if (vector.isZero()) {
            vector = new Vector(1,0,0);
        }
        vector.normalize();
        vector.multiply(magnitude);
        Projectile projectile = (Projectile) super.entity;
        projectile.setGravity(hasGravity);
        projectile.setVelocity(vector);
    }

    public boolean hasGravity() {
        return hasGravity;
    }

    public void setParticleTrail() {

    }

    @EventHandler
    public void onLaunch(ProjectileLaunchEvent e) {
        if (e.getEntity().equals(entity)) {
            if (abilityList[0] != null) {
                MobAbilityManager.verifyAbilityCD(this, 0);
            }
        }
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e) {
        if (e.getEntity().equals(entity)) {
            if (abilityList[1] != null && e.getHitEntity() != null) {
                Entity target = e.getHitEntity();
                System.out.println("Hit player");
                MobAbilityManager.verifyAbilityCD(this, 1, target);
            } else if (abilityList[1] != null) {
                MobAbilityManager.verifyAbilityCD(this, 1);
            } else {
                System.out.println("Hit block!");
            }
        }
    }

}
