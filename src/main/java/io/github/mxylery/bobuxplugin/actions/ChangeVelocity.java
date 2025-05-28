package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.entity.LivingEntity;

import io.github.mxylery.bobuxplugin.core.BobuxAction;

public class ChangeVelocity extends BobuxAction {
    
    private double magnitude;

    //Additive decides if it sets (false) or adds (true)
    public ChangeVelocity(double magnitude, boolean requiresCondition) {
        this.magnitude = magnitude;
        super.requiresCondition = requiresCondition;
        super.requiresEntity = true;
        super.requiresVector = true;
    }

    public void adjustFlat(double adjustment) {
        this.magnitude += adjustment;
    }

    public void adjustPerc(double adjustment) {
        this.magnitude = this.magnitude*(adjustment/100 + 1);
    }

    @Override
    public void run() {
        vector.multiply(magnitude*0.1);
        for (int i = 0; i < super.entityList.length; i++) {
            if (entityList[i] instanceof LivingEntity) {
                entityList[i].setVelocity(vector);
            }
        }
    }

}
