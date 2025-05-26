package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.EffectGive;
import io.github.mxylery.bobuxplugin.actions.PlaySound;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
public class HurriedStopwatchAbility extends AbilityOneTime {

    public HurriedStopwatchAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Entity[][] targetList = {{user},null};
        Vector[] vectorList = {null,null};
        Location[] locationList = {null,user.getLocation()};
        Inventory[] inventoryList = {null,null};

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        BobuxAction[] actionList = 
        {new EffectGive(PotionEffectType.SPEED, 100, 2, false),
        new PlaySound(Sound.BLOCK_END_PORTAL_FRAME_FILL, 0.5f, 0.5f, false)};
        
        super.actionList = actionList;
        return true;
    }

}
