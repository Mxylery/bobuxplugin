package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.vectors.ParticlePlayer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;

public class PlayParticle extends BobuxAction {
    
    private Vector vector;
    private Location location;
    private ParticleSequence particleSequence;
    
    public PlayParticle(ParticleSequence particleSequence, boolean requiresCondition) {
        this.particleSequence = particleSequence;
        super.requiresCondition = requiresCondition;
    }

    public void adjustPerc(double adjustment) {

    }

    public void adjustFlat(double adjustment) {
        
    }

    public ParticleSequence getParticleSequence() {
        return particleSequence;
    }

    public void setLoc() {

    }

    public void run() {
        ParticlePlayer.spawnParticles(particleSequence, location, vector);
    }

}
