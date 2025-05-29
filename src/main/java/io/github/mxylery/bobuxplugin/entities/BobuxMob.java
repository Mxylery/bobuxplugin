package io.github.mxylery.bobuxplugin.entities;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.AbilityInstance;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.data_structures.AbilityInstanceStructure;
import io.github.mxylery.bobuxplugin.listeners.BobuxEntityListener;

public abstract class BobuxMob extends BobuxLivingEntity {

    protected double attackDamage;
    protected boolean isDead;
    protected Mob mob;

    public BobuxMob(BobuxPlugin plugin, Location location) {
        super(plugin, location);
        abilityStructure = new AbilityInstanceStructure();
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        super.onDeath(e);
    }

    public void setTarget(LivingEntity target) {
        Mob mob = (Mob) entity;
        mob.setTarget(target);
    }
    
}
