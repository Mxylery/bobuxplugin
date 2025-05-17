package io.github.mxylery.bobuxplugin.vectors;

import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

//This class will use the MatrixNormalizer and BobuxParticleShapes enums to generate various particles.
public class ParticlePlayer implements Runnable {
    
    //all the options for the particle player
    public enum ParticlePlayerOptions {
        LINE,
        SPIRAL,
        CIRCLE,
        SPHERE,
        RECTANGLE
    }

    @Override
    public void run() {
        
    }

    public void setParticleSequence(ParticlePlayerOptions option) {

    }

    public void drawHelix(World world, Vector vector, double length, double radius, Particle particle) {

    }

}
