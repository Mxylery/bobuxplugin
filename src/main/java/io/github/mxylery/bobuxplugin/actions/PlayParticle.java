package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.vectors.ParticlePlayer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;

public class PlayParticle extends BobuxAction {
    
    private ParticleSequence particleSequence;
    
    public PlayParticle(ParticleSequence particleSequence, boolean requiresCondition) {
        this.particleSequence = particleSequence;
        super.requiresCondition = requiresCondition;
        super.requiresVector = true;
        super.requiresLocation = true;
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
        ParticlePlayer.spawnParticles(particleSequence, super.location, super.vector);
    }

}
