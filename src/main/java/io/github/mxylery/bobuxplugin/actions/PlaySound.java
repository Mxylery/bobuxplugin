package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.Location;
import org.bukkit.Sound;

import io.github.mxylery.bobuxplugin.core.BobuxAction;

public class PlaySound extends BobuxAction {
    
    private Sound sound;
    private Location location;

    public PlaySound(Sound sound, boolean requiresCondition) {
        this.sound = sound;
        super.requiresCondition = requiresCondition;
        super.requiresEntity = true;
        super.requiresLocation = true;
    }

    public void adjustFlat(double adjustment) {
        
    }

    public void adjustPerc(double adjustment) {

    }

    public void run() {
        location = entityList[0].getLocation();

    }
}
