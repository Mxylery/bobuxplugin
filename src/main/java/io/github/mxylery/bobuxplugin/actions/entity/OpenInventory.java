package io.github.mxylery.bobuxplugin.actions.entity;

import org.bukkit.entity.*;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.guis.BobuxGUI;

/**
 * Requires >2 entities, first is the leash holder and any other is leashed to the leash holder.
 */
public class OpenInventory extends BobuxAction {

    private BobuxGUI gui;

    //This action needs an entity to damage and a number to damage for
    public OpenInventory(BobuxGUI gui) {
        this.gui = gui;
        super.requiresEntity = true;
    }

    public void run() { 
        
    }

}
