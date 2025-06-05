package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.DeleteItem;
import io.github.mxylery.bobuxplugin.actions.SpawnPearl;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class StraightPearlAbility extends AbilityOneTime {

    public StraightPearlAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Player player = (Player) user;

        Entity[][] targetList = {{user},null};
        Vector[] vectorList = {user.getLocation().getDirection(),null};
        Location[] locationList = {user.getLocation(),null};
        Inventory[] inventoryList = {null, player.getInventory()};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        BobuxAction[] actionList = {new SpawnPearl(1, false, null, null), new DeleteItem(BobuxItemInterface.straightPearl.getStack(), 1)};
        
        super.actionList = actionList;
        return true;
    }

}
