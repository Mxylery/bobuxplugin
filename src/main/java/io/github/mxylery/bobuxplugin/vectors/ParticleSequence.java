package io.github.mxylery.bobuxplugin.vectors;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.World;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.core.BobuxUtils;

public class ParticleSequence implements Runnable {
    
    private World world;
    private Location location;
    private Particle particle;
    private Vector[] directionMatrix;
    private double length;
    private double amount;
    private double speed;
    private ParticleSequenceOptions option;
    private ParticleSequenceOrientations orientation;
    private double inRadius;
    private double outRadius;
    private DustOptions dustOption;

    //all the options for the particle player
    public enum ParticleSequenceOptions {
        LINE,
        SPIRAL,
        RING,
        SPHERE,
        RECTANGLE,
        CYLINDER,
        EXPLOSION
    }

    public enum ParticleSequenceOrientations {
        NORMAL,
        LEFT,
        RIGHT,
        DOWN,
        UP
    }

    public void setLocation(Location location) {
        this.location = location;
        this.world = location.getWorld();
    }

    public void setDirection(Vector direction) {
        directionMatrix = BobuxUtils.getNormalizedMatrix(direction, orientation);
    }

    /**
     * This constructor is used for non-animated line sequences.
     * @param option Particle sequence desired (line, ring, etc)
     * @param particle Type of particle desired
     * @param amount Amount of particles per block (measured in lines drawn)
     * @param speed Speed of the particle (weather it will move a lot or not, also depends on the particle)
     * @param dustOption DustOption if using redstone dust particles
     */
    public ParticleSequence(ParticleSequenceOrientations orientation, Particle particle, double length, double amount, double speed, DustOptions dustOption) {
        this.option = ParticleSequenceOptions.LINE;
        this.orientation = orientation;
        this.particle = particle;
        this.amount = amount;
        this.speed = speed;
        this.length = length;
        this.dustOption = dustOption;
    }

    /**
     * This constructor is used for non-line non-animated particle sequences. 
     * @param option Particle sequence desired (line, ring, etc)
     * @param particle Type of particle desired
     * @param amount Amount of particles per block (measured in unit lengths approx)
     * @param speed Speed of the particle (weather it will move a lot or not, also depends on the particle)
     * @param inRadius The inner radius of a sphere/ring/cylinder, and also used for the torsion of a spiral.
     * @param outRadius The outer radius of a sphere/ring/cylinder, and also used for the radius of a spiral.
     * @param dustOption DustOption if using redstone dust particles
     */
    public ParticleSequence(ParticleSequenceOptions option, ParticleSequenceOrientations orientation, Particle particle, double amount, double speed, double inRadius, double outRadius, double length, DustOptions dustOption) {
        this.option = option;
        this.orientation = orientation;
        this.particle = particle;
        this.amount = amount;
        this.outRadius = outRadius;
        this.inRadius = inRadius;
        this.length = length;
        this.dustOption = dustOption;
    }

    public void run() {
        //Left-hand: x is index, z is middle, y is thumb
        switch(option) {
            case LINE: drawLine(location);
            break;
            case SPIRAL: drawSpiral();
            break;
            case SPHERE: 
            break;
            case RING: drawRing(inRadius, outRadius);
            break;
            case RECTANGLE:
            break;
            case EXPLOSION: drawExplosion();
            break;
            default:
            break;
        }
    }

    private void drawLine(Location thisLoc) {
        //d/dt<ax,by,cz> = <a,b,c>
        Location ogLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
        for (double i = 0; i < length; i += 1/amount) {
            Location locAdjustment = new Location(world, directionMatrix[0].getX()/amount, directionMatrix[0].getY()/amount, directionMatrix[0].getZ()/amount);
            ogLoc.add(locAdjustment);
            world.spawnParticle(particle,ogLoc,1,0,0,0,speed, dustOption);
        }
    }

    //To draw a hollow ring, make the inner radius = outer radius.
    private void drawRing(double innerRadius, double outerRadius) {
        if (innerRadius >= outerRadius) {
            Location ogLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
            Vector[] ogDirectionMatrix = {directionMatrix[0], directionMatrix[1], directionMatrix[2]};
            for (double i = 0; i < 2*Math.PI; i += 1/amount) {
                //could also be Z, either one. both perpendicular
                Location yLoc = new Location(world, 
                ogDirectionMatrix[1].getX()*outerRadius*Math.cos(i), 
                ogDirectionMatrix[1].getY()*outerRadius*Math.cos(i), 
                ogDirectionMatrix[1].getZ()*outerRadius*Math.cos(i));
                Location zLoc = new Location(world, 
                ogDirectionMatrix[2].getX()*outerRadius*Math.sin(i), 
                ogDirectionMatrix[2].getY()*outerRadius*Math.sin(i), 
                ogDirectionMatrix[2].getZ()*outerRadius*Math.sin(i));
                double xAdjustment = ogLoc.getX() + yLoc.getX() + zLoc.getX();
                double yAdjustment = ogLoc.getY() + yLoc.getY() + zLoc.getY();
                double zAdjustment = ogLoc.getZ() + yLoc.getZ() + zLoc.getZ();
                world.spawnParticle(particle, xAdjustment, yAdjustment, zAdjustment, 
                1, 0, 0, 0, speed, dustOption);
            }
        } else {
            for (double i = outRadius; i > inRadius; i -= 1/amount) {
                drawRing(i, i);
            }
        } 
    }

    private void drawSpiral() {
        //d/dt(<t, sin(t), cos(t)>) = <1, cos(t), -sin(t)>
        Location ogLoc = new Location(location.getWorld(), location.getX(), location.getY(), location.getZ());
        Vector[] ogDirectionMatrix = {directionMatrix[0], directionMatrix[1], directionMatrix[2]};
        for (double i = 0; i < length; i += 1/amount) {
            Location xLoc = new Location(world, 
            ogDirectionMatrix[0].getX()*i, 
            ogDirectionMatrix[0].getY()*i, 
            ogDirectionMatrix[0].getZ()*i);
            Location yLoc = new Location(world, 
            ogDirectionMatrix[1].getX()*outRadius*Math.cos(i*inRadius), 
            ogDirectionMatrix[1].getY()*outRadius*Math.cos(i*inRadius), 
            ogDirectionMatrix[1].getZ()*outRadius*Math.cos(i*inRadius));
            Location zLoc = new Location(world, 
            -ogDirectionMatrix[2].getX()*outRadius*Math.sin(i*inRadius), 
            -ogDirectionMatrix[2].getY()*outRadius*Math.sin(i*inRadius), 
            -ogDirectionMatrix[2].getZ()*outRadius*Math.sin(i*inRadius));
            double xAdjustment = ogLoc.getX() + xLoc.getX() + yLoc.getX() + zLoc.getX();
            double yAdjustment = ogLoc.getY() + xLoc.getY() + yLoc.getY() + zLoc.getY();
            double zAdjustment = ogLoc.getZ() + xLoc.getZ() + yLoc.getZ() + zLoc.getZ();
            world.spawnParticle(particle,  xAdjustment, yAdjustment, zAdjustment, 0, 0, 0, speed, dustOption);
        }
    }

    private void drawExplosion() {
        location.getWorld().spawnParticle(particle, location, (int) amount, length, length, length, dustOption);
    }

    private void drawSphere() {

    }

    private void drawRectangleHollow() {

    }

    private void drawRectangleFull() {

    }

}
