package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.vectors.ParticlePlayer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;

public class PlayParticle extends BobuxAction {
    
    private Vector[] vectorArray;
    private Location[] locationArray;
    private ParticleSequence[] particleSequenceArray;
    private int[] delays;
    
    public PlayParticle(ParticleSequence[] particleSequenceArray, int[] delays, boolean requiresCondition) {
        this.particleSequenceArray = particleSequenceArray;
        this.delays = delays;
        super.requiresCondition = requiresCondition;
    }

    public void adjustPerc(double adjustment) {

    }

    public void adjustFlat(double adjustment) {
        
    }

    public void setLocArray(Location[] locationArray) {
        this.locationArray = locationArray;
    }

    public void setVecArray(Vector[] vectorArray) {
        this.vectorArray = vectorArray;
    }

    public ParticleSequence[] getParticleSequence() {
        return particleSequenceArray;
    }

    public void setLoc() {

    }

    public void run() {
        ParticlePlayer.spawnParticles(particleSequenceArray, delays, locationArray, vectorArray);
    }

}
