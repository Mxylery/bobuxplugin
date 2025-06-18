package io.github.mxylery.bobuxplugin.events;

import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import io.github.mxylery.bobuxplugin.core.BobuxTransaction;
import io.github.mxylery.bobuxplugin.entities.BobuxEntity;

public class BobuxTransactionEvent extends Event {
    
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private BobuxTransaction transaction;

    public BobuxTransactionEvent(BobuxTransaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }

    public BobuxTransaction getTransaction() {
        return transaction;
    }

}
