package io.github.mxylery.bobuxplugin.actions;

import io.github.mxylery.bobuxplugin.core.BobuxAction;
import org.bukkit.entity.*;

/**
 * First example of a bobux action: all of the abilities will use them 
 * in conjunction to allow for as much expression as the creator wants 
 * and with relative ease
 */
public class DamageEntity extends BobuxAction {
    
private double damage;

//This action needs an entity to damage and a number to damage for
public DamageEntity(double damage, boolean requiresCondition) {
    this.damage = damage;
    super.requiresCondition = requiresCondition;
    super.requiresEntity = true;
    super.requiresVector = false;
}

public void adjustFlat(double increase) {
    this.damage += increase;
}

public void adjustPerc(double increase) {
    this.damage = this.damage*(increase/100 + 1);
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
    if (j != -1) {
        for (int i = 0; i < j + 1; i++) {
            damageArray[i].damage(damage);
        }
    }
}

}
