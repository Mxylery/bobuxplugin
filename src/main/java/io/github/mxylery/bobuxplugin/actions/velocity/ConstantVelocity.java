package io.github.mxylery.bobuxplugin.actions.velocity;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;

public class ConstantVelocity extends BobuxAction {
    
    private double magnitude;
    private final long max;
    private long counter;

    //Additive decides if it sets (false) or adds (true)
    public ConstantVelocity(double magnitude, long time) {
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
            entityList[i].setVelocity(vector);
        }
        counter--;
        if (counter > 0) {
            this.runLater(1);
        }
    }

}
