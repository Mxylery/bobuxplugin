package io.github.mxylery.bobuxplugin.actions;

import io.github.mxylery.bobuxplugin.core.BobuxAction;
import org.bukkit.entity.*;

/**
 * Requires >2 entities, first is the leash holder and any other is leashed to the leash holder.
 */
public class AttachLead extends BobuxAction {

//This action needs an entity to damage and a number to damage for
public AttachLead() {
    super.requiresEntity = true;
}

public void run() {
    //Filter through all of the non-damageable entities
    LivingEntity leasher = (LivingEntity) super.entityList[0];
    for (int i = 1; i < super.entityList.length; i++) {
        LivingEntity leashed = (LivingEntity) super.entityList[i];
        leashed.setLeashHolder(leasher);
    }
    
}

}
