package io.github.mxylery.bobuxplugin.entities;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.abilities.AbilityInstance;
import io.github.mxylery.bobuxplugin.abilities.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.data_structures.AbilityInstanceStructure;
import io.github.mxylery.bobuxplugin.events.BobuxEntityWithinRangeEvent;

public abstract class BobuxEntity implements Listener {
    
    protected BobuxPlugin plugin = BobuxTimer.getPlugin();
    protected Entity entity;
    protected Location location;
    protected String name;
    protected BobuxAbility[] abilityList;
    protected AbilityInstanceStructure abilityStructure;
    protected double nearbyEntityRadius;
    protected int nearbyEntityTaskID;
    protected List<Entity> nearbyEntityList;
    protected BukkitScheduler scheduler = BobuxPlugin.getScheduler();

    /**
     * Initializes a bobux entity at a given location.
     * @param location Location where the entity will spawn.
     */
    public BobuxEntity(Location location) {
        this.location = location;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        setUpEntity();
        if (nearbyEntityRadius != 0) {
            nearbyEntityTaskID = scheduler.runTaskTimer(plugin, new Runnable() {
                public void run() {
                    if (entity.isDead()) {
                        scheduler.cancelTask(nearbyEntityTaskID);
                    }
                    nearbyEntityList = entity.getNearbyEntities(nearbyEntityRadius, nearbyEntityRadius, nearbyEntityRadius);
                    if (nearbyEntityList.size() != 0) {
                        BobuxEntityWithinRangeEvent event = new BobuxEntityWithinRangeEvent(entity, nearbyEntityList);
                        Bukkit.getPluginManager().callEvent(event);
                    }
                }
            }, 0, 20).getTaskId();
        }

    }

    /**
     * This method is used to make all of the instances of different bobux entities.
     */
    protected abstract void setUpEntity();

    /**
     * Method for using an bobux entity's ability. Takes in the index of the ability to use of the ability list (initialized in setUpEntity()).
     * @param index
     */
    public void useAbility(int index) {
        abilityList[index].setTarget(entity);
        if (abilityList[index].setActionList() && abilityStructure.checkForAbilityCD(abilityList[index], abilityList[index].getCooldown(), entity) == -1) {
            abilityList[index].use();
            AbilityInstance abilityInstance = new AbilityInstance(entity, BobuxTimer.getTicksPassed(), abilityList[index]);
            abilityStructure.addAbilityInstanceLast(abilityInstance);
        }
    }

    /**
     * Method for using an bobux entity's ability with a single target involved. Takes in the index of the ability to use of the ability list (initialized in setUpEntity()), and the target to intake.
     * @param index
     */
    public void useAbility(int index, Entity target) {
        abilityList[index].setUser(entity);
        abilityList[index].setTarget(target);
        if (abilityList[index].setActionList() && abilityStructure.checkForAbilityCD(abilityList[index], abilityList[index].getCooldown(), entity) == -1) {
            abilityList[index].use();
            AbilityInstance abilityInstance = new AbilityInstance(entity, BobuxTimer.getTicksPassed(), abilityList[index]);
            abilityStructure.addAbilityInstanceLast(abilityInstance);
        }
    }

    public AbilityInstanceStructure getAbilityHistory() {
        return abilityStructure;
    }

    public BobuxAbility getAbility(int index) {
        return abilityList[index];
    }

    public Location getLocation() {
        location = entity.getLocation();
        return entity.getLocation();
    }

    public Entity getEntity() {
        return entity;
    }

    public EntityType getEntityType() {
        return entity.getType();
    }

    public String getName() {
        return name;
    }

    public double getRegistrationRadius() {
        return nearbyEntityRadius;
    }

}
