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
    super.requiresEntity = true;
}

public DamageEntity(double damage) {
    this.damage = damage;
    this.entity = null;
    super.requiresEntity = true;
}

public void setDamage(double damage) {
    this.damage = damage;
}

public void setEntity(Damageable entity) {
    this.entity = entity;
}

public void run() {
    entity.damage(damage);
}

}
