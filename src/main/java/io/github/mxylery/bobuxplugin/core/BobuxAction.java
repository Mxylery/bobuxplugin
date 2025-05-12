package io.github.mxylery.bobuxplugin.core;

import org.bukkit.entity.Entity;

import io.github.mxylery.bobuxplugin.conditions.ActionCondition;

public abstract class BobuxAction implements Runnable {
    
protected Entity triggerer;
protected boolean requiresEntity;

@Override
public void run() {

}

public void setTriggerer(Entity triggerer) {
    this.triggerer = triggerer;
}
    
}
