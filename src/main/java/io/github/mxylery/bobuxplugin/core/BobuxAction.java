package io.github.mxylery.bobuxplugin.core;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;

public abstract class BobuxAction implements Runnable {
    
protected Entity[] entityList;
protected boolean requiresCondition;

public abstract void run();
public abstract void adjustFlat(int adjustment);
public abstract void adjustPerc(int adjustment);

public void initializeEntityList(Entity[] entityList) {
    this.entityList = entityList;
}

public boolean requiresCondition() {
    return requiresCondition;
}
    
}
