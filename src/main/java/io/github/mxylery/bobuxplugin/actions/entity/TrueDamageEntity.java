package io.github.mxylery.bobuxplugin.actions.entity;

import org.bukkit.entity.*;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;

/**
 * First example of a bobux action: all of the abilities will use them 
 * in conjunction to allow for as much expression as the creator wants 
 * and with relative ease
 */
public class TrueDamageEntity extends BobuxAction {
    
private double damage;

//This action needs an entity to damage and a number to damage for
public TrueDamageEntity(double damage) {
    this.damage = damage;
    super.requiresEntity = true;
}

public void run() {
    //Filter through all of the non-damageable entities
    for (int i = 0; i < super.entityList.length; i++) {
        if (super.entityList[i] instanceof Damageable) {
            Damageable damageable = (Damageable) super.entityList[i];
            damageable.damage(damage);
        }
    }
    
}

}
