package io.github.mxylery.bobuxplugin.actions.aesthetic;


import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.vectors.ParticlePlayer;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence;

public class PlayParticle extends BobuxAction {
    
    private ParticleSequence particleSequence;
    
    public PlayParticle(ParticleSequence particleSequence) {
        this.particleSequence = particleSequence;
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
