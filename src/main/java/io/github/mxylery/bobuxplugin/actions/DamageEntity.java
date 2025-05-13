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
private Damageable entity;

//This action needs an entity to damage and a number to damage for
public DamageEntity(double damage, Damageable entity) {
    this.damage = damage;
    this.entity = entity;
}

public DamageEntity(double damage) {
    this.damage = damage;
    this.entity = (Damageable) super.triggerer;
}

public void setDamage(double damage) {
    this.damage = damage;
}

public void run() {
    if (entity == null) {
        entity = (Damageable) super.triggerer;
    }
    entity.damage(damage);
}

}
