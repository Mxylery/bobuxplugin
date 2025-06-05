package io.github.mxylery.bobuxplugin.actions;

import io.github.mxylery.bobuxplugin.core.BobuxAction;

import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;

/**
 * First example of a bobux action: all of the abilities will use them 
 * in conjunction to allow for as much expression as the creator wants 
 * and with relative ease
 */
public class DamageEntity extends BobuxAction {
    
private double damage;

//This action needs an entity to damage and a number to damage for
public DamageEntity(double damage) {
    this.damage = damage;
    super.requiresEntity = true;
}

public void run() {
    //Filter through all of the non-damageable entities
    for (int i = 0; i < super.entityList.length; i++) {
        if (super.entityList[i] instanceof Attributable) {
            Attributable attributable = (Attributable) super.entityList[i];
            double multiplier = (1 - attributable.getAttribute(Attribute.ARMOR).getValue()*0.04);
            if (super.entityList[i] instanceof Damageable) {
                Damageable damageable = (Damageable) super.entityList[i];
                damageable.damage(damage*multiplier);
            }
        }
    }
    
}

}
