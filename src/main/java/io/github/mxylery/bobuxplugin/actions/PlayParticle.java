package io.github.mxylery.bobuxplugin.actions;

import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.vectors.ParticlePlayer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;

public class PlayParticle extends BobuxAction {
    
    private ParticleSequence[] particleSequenceArray;
    private ParticleSequence particleSequence;
    private int[] delays;

    public PlayParticle(ParticleSequence particleSequence, boolean requiresCondition) {
        this.particleSequence = particleSequence;
        this.particleSequenceArray = null;
        super.requiresCondition = requiresCondition;
    }
    
    public PlayParticle(ParticleSequence[] particleSequenceArray, int[] delays, boolean requiresCondition) {
        this.particleSequenceArray = particleSequenceArray;
        this.delays = delays;
        super.requiresCondition = requiresCondition;
    }

    public void adjustPerc(double adjustment) {

    }

    public void adjustFlat(double adjustment) {
        
    }

    public void run() {
        if (particleSequenceArray == null) {
            ParticlePlayer.spawnParticles(particleSequence, super.location, super.vector);
        } else {
            ParticlePlayer.spawnParticles(particleSequenceArray, delays, location, vector);
        }
    }

}
