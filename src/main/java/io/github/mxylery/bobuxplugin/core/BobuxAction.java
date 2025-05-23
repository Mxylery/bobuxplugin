package io.github.mxylery.bobuxplugin.core;

import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

public abstract class BobuxAction implements Runnable {

protected Location location;
protected Vector vector;
protected Entity[] entityList;
protected Inventory inventory;
protected boolean requiresCondition;
protected boolean requiresEntity = false;
protected boolean requiresVector = false;
protected boolean requiresLocation = false;
protected boolean requiresInventory = false;

public abstract void run();
public abstract void adjustFlat(double adjustment);
public abstract void adjustPerc(double adjustment);

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

public boolean requiresCondition() {
    return requiresCondition;
}

public boolean requiresInventory() {
    return requiresInventory;
}
    
}
