package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.Location;
import org.bukkit.Sound;

import io.github.mxylery.bobuxplugin.core.BobuxAction;

public class PlaySound extends BobuxAction {
    
    private Sound sound;
    private float volume;
    private float pitch;

    public PlaySound(Sound sound, float volume, float pitch, boolean requiresCondition) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
        super.requiresCondition = requiresCondition;
        super.requiresEntity = true;
        super.requiresLocation = true;
    }

    public void adjustFlat(double adjustment) {
        
    }

    public void adjustPerc(double adjustment) {

    }

    public void run() {
        location.getWorld().playSound(location,sound,volume,pitch);
    }
}
