package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.DeleteItem;
import io.github.mxylery.bobuxplugin.actions.EffectGive;
import io.github.mxylery.bobuxplugin.actions.SpawnArrow;
import io.github.mxylery.bobuxplugin.core.BobuxAction;

public class MegaLongBowAbility extends AbilityOneTime {

    public MegaLongBowAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Player player = (Player) user;

        Entity[][] targetList = {null,null,{user}};
        Vector[] vectorList = {user.getLocation().getDirection(),null,null};
        Location[] locationList = {user.getLocation(),null,null};
        Inventory[] inventoryList = {null, player.getInventory(), null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        BobuxAction[] actionList = {new SpawnArrow(1, true, null, null), 
            new DeleteItem(new ItemStack(Material.ARROW), 1), 
            new EffectGive(PotionEffectType.JUMP_BOOST, 40, 3)};
        
        super.actionList = actionList;
        return true;
    }

}
