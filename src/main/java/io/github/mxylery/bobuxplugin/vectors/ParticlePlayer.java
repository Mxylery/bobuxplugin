package io.github.mxylery.bobuxplugin.vectors;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.util.Vector;

//This class will use the MatrixNormalizer and BobuxParticleShapes enums to generate various particles.
public class ParticlePlayer {

    public static void spawnParticles(ParticleSequence[] incomingParticleSequence, int[] delayList, Location[] location, Vector[] direction) {
        if (delayList == null) {
            for (int i = 0; i < incomingParticleSequence.length; i++) {
                incomingParticleSequence[i].setDirection(direction[i]);
                incomingParticleSequence[i].setLocation(location[i]);
                incomingParticleSequence[i].run();
            }
        } else {
            for (int i = 0; i < incomingParticleSequence.length; i++) {
                if (i == 0) {
                    incomingParticleSequence[i].setDirection(direction[i]);
                    incomingParticleSequence[i].setLocation(location[i]);
                    incomingParticleSequence[i].run();
                } else {
                    incomingParticleSequence[i].setDirection(direction[i]);
                    incomingParticleSequence[i].setLocation(location[i]);
                    incomingParticleSequence[i].runLater(delayList[i-1]);
                }
            }
        }
    }
}
