package io.github.mxylery.bobuxplugin.actions.spawn;

import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;

public class SpawnEntity extends BobuxAction {
    
    private EntityType entityType;
    private boolean isBaby;

    public SpawnEntity(EntityType entityType, boolean isBaby) {
        this.entityType = entityType;
        this.isBaby = isBaby;
        super.requiresLocation = true;
    }   

    public void run() {
        Entity entity = location.getWorld().spawnEntity(location, entityType);
        if (entity instanceof Ageable && isBaby) {
            Ageable ageable = (Ageable) entity;
            ageable.setBaby();
        }
    }
}
