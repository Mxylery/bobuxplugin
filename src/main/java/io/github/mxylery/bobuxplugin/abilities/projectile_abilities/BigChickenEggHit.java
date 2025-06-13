package io.github.mxylery.bobuxplugin.abilities.projectile_abilities;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.actions.entity.DamageEntity;

public class BigChickenEggHit extends AbilityOneTime {

    public BigChickenEggHit(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {

        Entity[][] targetList = {{singleTarget}};
        Vector[] vectorList = {null};
        Location[] locationList = {null};
        Inventory[] inventoryList = {null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        BobuxAction[] actionList = {new DamageEntity(5)};
        
        super.actionList = actionList;
        return true;
    }

}

