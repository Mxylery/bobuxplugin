package io.github.mxylery.bobuxplugin.entities;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.listeners.BobuxEntityListener;

public abstract class BobuxEntity implements Listener {
    
    protected BobuxPlugin plugin;
    protected Entity entity;
    protected Location location;
    protected String name;

    public BobuxEntity(BobuxPlugin plugin, Location location) {
        this.location = location;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        setUpEntity();
    }

    protected abstract void setUpEntity();

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

}
