package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.core.BobuxAction;

public class AddVelocity extends BobuxAction {
    
    private double magnitude;

    //Additive decides if it sets (false) or adds (true)
    public AddVelocity(double magnitude) {
        this.magnitude = magnitude;
        super.requiresEntity = true;
        super.requiresVector = true;
    }

    @Override
    public void run() {
        vector.multiply(magnitude*0.1);
        for (int i = 0; i < super.entityList.length; i++) {
            if (entityList[i] instanceof LivingEntity) {
                Vector currentVelocity = entityList[i].getVelocity();
                currentVelocity.add(vector);
            }
        }
    }

}
