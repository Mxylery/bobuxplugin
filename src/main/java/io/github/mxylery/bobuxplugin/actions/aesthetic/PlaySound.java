package io.github.mxylery.bobuxplugin.actions.aesthetic;

import org.bukkit.Sound;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;

public class PlaySound extends BobuxAction {
    
    private Sound sound;
    private float volume;
    private float pitch;

    public PlaySound(Sound sound, float volume, float pitch) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
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
