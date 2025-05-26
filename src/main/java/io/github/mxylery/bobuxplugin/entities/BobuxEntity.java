package io.github.mxylery.bobuxplugin.entities;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.listeners.BobuxEntityListener;

public abstract class BobuxEntity {
    
    protected BobuxPlugin plugin;
    protected BobuxEntityListener listener;
    protected Entity entity;
    protected EntityType entityType;
    protected Location location;

    public BobuxEntity(BobuxPlugin plugin, BobuxEntityListener listener, Location location) {
        this.listener = listener;
        this.location = location;
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

}
