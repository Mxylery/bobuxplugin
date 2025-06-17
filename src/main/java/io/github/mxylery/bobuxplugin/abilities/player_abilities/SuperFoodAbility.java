package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.actions.spawn.SpawnEntity;

public class SuperFoodAbility extends AbilityOneTime {

    public SuperFoodAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        
        Entity[][] targetList = {{null},{null}};
        Vector[] vectorList = {null,null};
        Location[] locationList = {singleTarget.getLocation(), singleTarget.getLocation()};
        Inventory[] inventoryList = {null, null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        BobuxAction[] actionList = 
        {new SpawnEntity(singleTarget.getType(), true), new SpawnEntity(singleTarget.getType(), true)};
        
        super.actionList = actionList;
        return true;
    }

}
