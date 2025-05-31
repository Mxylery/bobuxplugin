package io.github.mxylery.bobuxplugin.entities.entities;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.AbilityInstance;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.entities.BobuxEntity;
import io.github.mxylery.bobuxplugin.listeners.MobAbilityManager;
import io.github.mxylery.bobuxplugin.vectors.ParticlePlayer;

public class BobuxProjectile extends BobuxEntity {
    
    protected Class<? extends Projectile> clazz;
    protected Vector velocity;
    protected double magnitude;
    protected boolean hasGravity;
    protected BobuxAbility startAbility;
    protected BobuxAbility endAbility;
    protected ParticlePlayer trail;

    public BobuxProjectile(Class<? extends Projectile> clazz, Location location, Vector velocity, double magnitude, boolean hasGravity, BobuxAbility startAbility, BobuxAbility endAbility) {
        super(location);
        this.hasGravity = hasGravity;
        this.velocity = velocity;
        this.magnitude = magnitude;
        launchProjectile();
    }

    @Override
    public void useAbility(int index) {
        abilityList[index].setOtherEntity(entity);
        if (abilityList[index].setActionList()) {
            abilityList[index].use();
        }
    }

    @Override
    public void useAbility(int index, Player player) {
        abilityList[index].setUser(player);
        abilityList[index].setOtherEntity(entity);
        if (abilityList[index].setActionList()) {
            abilityList[index].use();
        }
    }

    public void setUpEntity() {
        Projectile newProjectile = location.getWorld().spawn(location, clazz);
        BobuxAbility[] abilityList = {startAbility, endAbility};
        super.entity = newProjectile;
        super.abilityList = abilityList;
    }

    private void launchProjectile() {
        Vector vector = new Vector(velocity.getX(), velocity.getY(), velocity.getZ());
        vector.normalize();
        vector.multiply(magnitude);
        Projectile projectile = (Projectile) super.entity;
        projectile.setGravity(hasGravity);
        projectile.setVelocity(vector);
    }

    public boolean hasGravity() {
        return hasGravity;
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
            if (abilityList[1] != null && e.getHitEntity() instanceof Player) {
                Player player = (Player) e.getHitEntity();
                MobAbilityManager.verifyAbilityCD(this, 1, player);
            } else if (abilityList[1] != null) {
                MobAbilityManager.verifyAbilityCD(this, 1);
            }
        }
    }

}
