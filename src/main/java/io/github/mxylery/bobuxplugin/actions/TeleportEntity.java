package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.Location;

import io.github.mxylery.bobuxplugin.core.BobuxAction;

public class TeleportEntity extends BobuxAction {
    
    public TeleportEntity(boolean requiresCondition) {
        super.requiresCondition = requiresCondition;
        super.requiresEntity = true;
        super.requiresVector = true;
        super.requiresLocation = true;
    }

    public void adjustPerc(double adjustment) {

    }

    public void adjustFlat(double adjustment) {

    }

    public void run() {
        for (int i = 0; i < super.entityList.length; i++) {
            Location tempLoc = new Location(entityList[i].getWorld(), location.getX(), location.getY(), location.getZ());
            tempLoc.setDirection(vector);
            entityList[i].teleport(tempLoc);
        }
    }
}
