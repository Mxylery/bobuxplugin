package io.github.mxylery.bobuxplugin.actions.velocity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;

public class ConstantAcceleration extends BobuxAction implements Listener {
    
    private double magnitude;
    private final long max;
    private long counter;

    //Additive decides if it sets (false) or adds (true)
    public ConstantAcceleration(double magnitude, long time) {
        this.magnitude = magnitude;
        counter = time;
        max = time;
        super.requiresEntity = true;
        super.requiresVector = true;
    }

    @Override
    public void run() {
        if (counter == max) {
            vector.multiply(magnitude*0.1);
        }
        for (int i = 0; i < super.entityList.length; i++) {
            if (entityList[i] instanceof LivingEntity) {
                Vector prevVelo = new Vector(entityList[i].getVelocity().getX() + vector.getX(), 
                entityList[i].getVelocity().getY() + vector.getY(), 
                entityList[i].getVelocity().getZ() + vector.getZ());
                entityList[i].setVelocity(prevVelo);
            }
        }
        counter--;
        if (counter > 0) {
            this.runLater(1);
        }
    }
        
}
