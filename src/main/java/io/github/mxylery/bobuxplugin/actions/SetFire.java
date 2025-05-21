package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.entity.Damageable;

import io.github.mxylery.bobuxplugin.core.BobuxAction;

public class SetFire extends BobuxAction {
    
    private int ticks;

    public SetFire(int ticks, boolean requiresCondition) {
        this.ticks = ticks;
        super.requiresCondition = requiresCondition;
        super.requiresEntity = true;
    }

    public void adjustPerc(double adjustment) {

    }

    public void adjustFlat(double adjustment) {

    }
    
    public void run() {
        int j = -1;
        Damageable[] damageArray = new Damageable[super.entityList.length];
        //Filter through all of the non-damageable entities
        for (int i = 0; i < super.entityList.length; i++) {
            if (super.entityList[i] instanceof Damageable) {
                damageArray[j + 1] = (Damageable) super.entityList[i];
                j++;
            }
        }
        for (int i = 0; i < j + 1; i++) {
            damageArray[i].setFireTicks(ticks);
        }
    }

}
