package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityPassive;
import io.github.mxylery.bobuxplugin.actions.ChangeVelocity;
import io.github.mxylery.bobuxplugin.core.BobuxAction;

public class KungFuGlovesPassive extends AbilityPassive {
    public KungFuGlovesPassive(String name, boolean muteCD, long cooldown, long period) {
        super(name, muteCD, cooldown, period);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        if (verifyPassivePeriod()) {
            Entity[][] targetList = {{user},{otherEntity}};
            Vector[] vectorList = {new Vector(0,1,0), new Vector(0,1,0)};
            Location[] locationList = {null, null};
            Inventory[] inventoryList = {null, null};

            super.targetList = targetList;
            super.vectorList = vectorList;
            super.locationList = locationList;
            super.inventoryList = inventoryList;

            BobuxAction[] actionList = 
            {new ChangeVelocity(5), new ChangeVelocity(0.5)};
        
            super.actionList = actionList;
            return true;        
        } else {
            return false;
        }
    }
}
