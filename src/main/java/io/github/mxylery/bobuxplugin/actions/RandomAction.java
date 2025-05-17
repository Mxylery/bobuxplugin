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
        for (int i = 0; i < randomList.length; i++) {
            for (int j = 0; j < randomList[i].length; j++) {
                if (randomList[i][j].requiresEntities()) {
                super.requiresEntity = true;
                }
                if (randomList[i][j].requiresVector()) {
                super.requiresVector = true;
                }
                if (randomList[i][j].requiresLocation()) {
                super.requiresLocation = true;
                }
            }
        }
    }

    public void adjustFlat(double adjustment) {

    }

    public void adjustPerc(double adjustment) {

    }

    public void run() {
        //Each index represents
        double[] ranges = new double[randomList.length+1];
        double sumCheck = 0;
        for (int i = 0; i < weights.length; i++) {
            sumCheck += weights[i];
            if (i == weights.length - 2) {
                ranges[i+1] = randomList.length;
            } else {
                ranges[i+1] = weights[i]*randomList.length;
            }
        }
        if (sumCheck == 1) {
            double randomNumber = Math.random()*randomList.length;
            int selectedElement = 0;
            for (int i = 0; i < weights.length; i++) {
                if (randomNumber < ranges[i+1]) {
                    selectedElement = i;
                }
            }
            for (int i = 0; i < randomList[selectedElement].length; i++) {
                randomList[selectedElement][i].initializeEntityList(super.entityList);
                randomList[selectedElement][i].initializeVector(super.vector);
                randomList[selectedElement][i].run();
            }
        }
    }
}
