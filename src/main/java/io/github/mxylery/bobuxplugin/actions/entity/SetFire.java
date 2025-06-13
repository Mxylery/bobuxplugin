package io.github.mxylery.bobuxplugin.actions.entity;

import org.bukkit.entity.Damageable;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;

public class SetFire extends BobuxAction {
    
    private int ticks;

    public SetFire(int ticks) {
        this.ticks = ticks;
        super.requiresEntity = true;
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
