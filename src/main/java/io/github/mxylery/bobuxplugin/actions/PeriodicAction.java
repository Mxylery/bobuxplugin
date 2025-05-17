package io.github.mxylery.bobuxplugin.actions;

import io.github.mxylery.bobuxplugin.core.BobuxAction;

//no need for passives if we got this...
public class PeriodicAction extends BobuxAction {

    private BobuxAction[] periodicList;
    private long delay;
    
    public PeriodicAction(BobuxAction[] actionList, long delay, boolean requiresCondition) {
        this.periodicList = actionList;
        this.delay = delay;
        super.requiresCondition = requiresCondition;
        for (int i = 0; i < periodicList.length; i++) {
            if (periodicList[i].requiresEntities()) {
                super.requiresEntity = true;
            }
            if (periodicList[i].requiresVector()) {
                super.requiresVector = true;
            }
            if (periodicList[i].requiresLocation()) {
                super.requiresLocation = true;
            }
        }
    }

    public void adjustPerc(double adjustment) {
        
    }

    public void adjustFlat(double adjustment) {

    }

    public void run() {
        
    }
}


