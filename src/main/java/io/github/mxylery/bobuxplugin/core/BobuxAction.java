package io.github.mxylery.bobuxplugin.core;

import org.bukkit.entity.Entity;

public abstract class BobuxAction implements Runnable {
    
protected Entity triggerer;
protected boolean requiresCondition;

@Override
public void run() {

}

public void setTriggerer(Entity triggerer) {
    this.triggerer = triggerer;
}

public boolean requiresCondition() {
    return requiresCondition;
}
    
}
