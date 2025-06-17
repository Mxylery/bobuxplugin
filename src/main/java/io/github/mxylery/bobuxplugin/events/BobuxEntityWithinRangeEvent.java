package io.github.mxylery.bobuxplugin.events;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import io.github.mxylery.bobuxplugin.entities.BobuxEntity;

public class BobuxEntityWithinRangeEvent extends Event {
    
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private List<Entity> entityList;
    private Entity entity;

    public BobuxEntityWithinRangeEvent(Entity entity, List<Entity> entityList) {
        this.entityList = entityList;
        this.entity = entity;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public List<Entity> getEntitiesInRange() {
        return entityList;
    }

    public Entity getEntity() {
        return entity;
    }

}
