package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.actions.item.DeleteHeldItem;
import io.github.mxylery.bobuxplugin.actions.velocity.ChangeVelocity;

public class BouncingItemAbility extends AbilityOneTime {

    public BouncingItemAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Entity[][] targetList = {{user}, {user}};
        Vector[] vectorList = {user.getLocation().getDirection(), null};
        Location[] locationList = new Location[2];
        Inventory[] inventoryList = new Inventory[2];

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        BobuxAction[] actionList = 
        {new ChangeVelocity(10), 
        new DeleteHeldItem(EquipmentSlot.HAND, 1)};
        
        super.actionList = actionList;
        return true;
    }

}
