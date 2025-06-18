package io.github.mxylery.bobuxplugin.actions.misc;

import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;

public class DelayedAction extends BobuxAction {
    private BobuxAction action;
    private long delay;

    //Weights have to add up to one
    public DelayedAction(BobuxAction action, long delay) {
        this.action = action;
        this.delay = delay;
        super.requiresEntity = action.requiresEntities();
        super.requiresVector = action.requiresVector();
        super.requiresLocation = action.requiresLocation();
        super.requiresInventory = action.requiresInventory();
    }

    public void run() {
        action.initializeEntityList(super.entityList);
        action.initializeVector(super.vector);
        action.initializeLocation(super.location);
        action.initializeInventory(super.inventory);
        action.runLater(delay);
    }
}
