package io.github.mxylery.bobuxplugin.actions.entity;

import org.bukkit.entity.*;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;

/**
 * Requires >2 entities, first is the leash holder and any other is leashed to the leash holder.
 */
public class AttachLead extends BobuxAction {

//This action needs an entity to damage and a number to damage for
public AttachLead() {
    super.requiresEntity = true;
}

public void run() {
    LivingEntity leasher = (LivingEntity) super.entityList[0];
    for (int i = 1; i < super.entityList.length; i++) {
        LivingEntity leashed = (LivingEntity) super.entityList[i];
        leashed.setLeashHolder(leasher);
    }
    
}

}
