package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.ChangeVelocity;
import io.github.mxylery.bobuxplugin.actions.DeleteSelf;
import io.github.mxylery.bobuxplugin.core.BobuxAction;

public class BouncingItemAbility extends AbilityOneTime {

    public BouncingItemAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Entity[][] targetList = {{user}, {user}};
        Vector[] vectorList = {user.getLocation().getDirection(), null};
        Location[] locationList = {null, null};
        Inventory[] inventoryList = {null, null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        BobuxAction[] actionList = 
        {new ChangeVelocity(10, false), 
        new DeleteSelf(EquipmentSlot.HAND, 1, false)};
        
        super.actionList = actionList;
        return true;
    }

}
