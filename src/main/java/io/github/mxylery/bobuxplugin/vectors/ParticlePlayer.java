package io.github.mxylery.bobuxplugin.vectors;

import org.bukkit.Location;
import org.bukkit.util.Vector;

//This class will use the MatrixNormalizer and BobuxParticleShapes enums to generate various particles.
public class ParticlePlayer {

    public static void spawnParticles(ParticleSequence incomingParticleSequence,  Location location, Vector direction) {
        incomingParticleSequence.setDirection(direction);
        incomingParticleSequence.setLocation(location);
        incomingParticleSequence.run();
    }
}
