package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;

public abstract class BobuxAction implements Runnable {

protected Location location;
protected Vector vector;
protected Entity[] entityList;
protected Inventory inventory;
protected boolean requiresEntity = false;
protected boolean requiresVector = false;
protected boolean requiresLocation = false;
protected boolean requiresInventory = false;

public void runLater(long delay) {
    BukkitScheduler scheduler = BobuxPlugin.getScheduler();
    scheduler.runTaskLater(BobuxTimer.getPlugin(), this, delay);
}

public abstract void run();

public void initializeEntityList(Entity[] entityList) {
    this.entityList = entityList;
}

public void initializeVector(Vector vector) {
    this.vector = vector;
}

public void initializeLocation(Location location) {
    this.location = location;
}

public void initializeInventory(Inventory inventory) {
    this.inventory = inventory;
}

public boolean requiresEntities() {
    return requiresEntity;
}

public boolean requiresVector() {
    return requiresVector;
}

public boolean requiresLocation() {
    return requiresLocation;
}

public boolean requiresInventory() {
    return requiresInventory;
}
    
}
