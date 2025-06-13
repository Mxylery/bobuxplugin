package io.github.mxylery.bobuxplugin.abilities.mob_abilities;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.abilities.projectile_abilities.BigChickenEggHit;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.actions.spawn.SpawnEgg;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;

public class BigChickenAbilityOne extends AbilityOneTime {

    public BigChickenAbilityOne(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {

        Vector differenceVector = BobuxUtils.getLocationDifference(user.getLocation(), singleTarget.getLocation());

        Location elevatedChickenLoc = new Location(user.getWorld(), user.getLocation().getX(), user.getLocation().getY() + 1, user.getLocation().getZ());

        Entity[][] targetList = {{null}};
        Vector[] vectorList = {differenceVector};
        Location[] locationList = {elevatedChickenLoc};
        Inventory[] inventoryList = {null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        BobuxAction[] actionList = {new SpawnEgg(1.0, true, null, new BigChickenEggHit("Big Chicken Egg Hit Abil", true, 0))};
        
        super.actionList = actionList;
        return true;
    }

}
