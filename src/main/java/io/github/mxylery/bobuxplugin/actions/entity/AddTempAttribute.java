package io.github.mxylery.bobuxplugin.actions.entity;

import java.util.UUID;

import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.player.BobuxPlayerStats;
import io.github.mxylery.bobuxplugin.player.TempAttribute;

/**
 * First example of a bobux action: all of the abilities will use them 
 * in conjunction to allow for as much expression as the creator wants 
 * and with relative ease
 */
public class AddTempAttribute extends BobuxAction {
    
private TempAttribute attribute;

//This action needs an entity to damage and a number to damage for
public AddTempAttribute(TempAttribute attribute) {
    super.requiresEntity = true;
}

public void run() {
    //Filter through all of the non-damageable entities
    for (int i = 0; i < super.entityList.length; i++) {
        if (super.entityList[i] instanceof Player) {
            Player player = (Player) super.entityList[i];
            UUID uuid = player.getUniqueId();
            BobuxPlayerStats stats = BobuxPlugin.getPlayerStatMap().get(uuid);
            stats.addAttribute(attribute);
        }
    }
    
}

}
