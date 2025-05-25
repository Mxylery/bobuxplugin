package io.github.mxylery.bobuxplugin.actions;

import io.github.mxylery.bobuxplugin.core.BobuxAction;

public class RandomAction extends BobuxAction {
    
    private BobuxAction[][] randomList;
    private double[] weights;

    //Weights have to add up to one
    public RandomAction(BobuxAction[][] randomList, double[] weights, boolean requiresCondition) {
        this.randomList = randomList;
        this.weights = weights;
        super.requiresCondition = requiresCondition;
        super.requiresEntity = true;
        super.requiresVector = true;
        super.requiresLocation = true;
        super.requiresInventory = true;
    }

    public void adjustFlat(double adjustment) {

    }

    public void adjustPerc(double adjustment) {

    }

    public void run() {
        //Each index represents
        double sumCheck = 0;
        double[] ranges = new double[weights.length+1];
        ranges[0] = 0;
        for (int i = 0; i < weights.length; i++) {
            sumCheck += weights[i];
            ranges[i+1] = weights[i] + ranges[i];
        }
        if (sumCheck == 1) {
            double randomNumber = Math.random();
            int selectedElement = 0;
            for (int i = 0; i < ranges.length; i++) {
                if (randomNumber < ranges[i+1]) {
                    selectedElement = i;
                    break;
                }
            }
            for (int i = 0; i < randomList[selectedElement].length; i++) {
                randomList[selectedElement][i].initializeEntityList(super.entityList);
                randomList[selectedElement][i].initializeVector(super.vector);
                randomList[selectedElement][i].initializeInventory(super.inventory);
                randomList[selectedElement][i].initializeLocation(super.location);
                randomList[selectedElement][i].run();
            }
        }
    }
}
