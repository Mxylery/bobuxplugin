package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.ReplaceItem;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class BobuxBrewAbility extends AbilityOneTime {

    public BobuxBrewAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Entity[][] targetList = {{null}};
        Vector[] vectorList = {null};
        Location[] locationList = {null};
        Inventory[] inventoryList = {user.getInventory()};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        BobuxAction[] actionList = 
        {new ReplaceItem(BobuxItemInterface.bobuxBrew.getStack(), BobuxItemInterface.bobuxBrewRemnants.getStack())};
        
        super.actionList = actionList;
        return true;
    }

}
