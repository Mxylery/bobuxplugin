package io.github.mxylery.bobuxplugin.core;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public abstract class BobuxAction implements Runnable {

protected Vector vector;
protected Entity[] entityList;
protected boolean requiresCondition;
protected boolean requiresEntity;
protected boolean requiresVector;


public abstract void run();
public abstract void adjustFlat(double adjustment);
public abstract void adjustPerc(double adjustment);

public void initializeEntityList(Entity[] entityList) {
    this.entityList = entityList;
}

public void initializeVector(Vector vector) {
    this.vector = vector;
}

public boolean requiresEntities() {
    return requiresEntity;
}

public boolean requiresVector() {
    return requiresVector;
}

public boolean requiresCondition() {
    return requiresCondition;
}
    
}
