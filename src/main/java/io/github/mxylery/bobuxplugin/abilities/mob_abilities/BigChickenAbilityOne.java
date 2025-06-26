package io.github.mxylery.bobuxplugin.abilities.mob_abilities;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.abilities.projectile_abilities.BigChickenEggHit;
import io.github.mxylery.bobuxplugin.actions.spawn.SpawnEgg;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;

public class BigChickenAbilityOne extends AbilityOneTime {

    public BigChickenAbilityOne(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        Vector differenceVector = BobuxUtils.getLocationDifference(user.getLocation(), singleTarget.getLocation());
        Location elevatedChickenLoc = new Location(user.getWorld(), user.getLocation().getX(), user.getLocation().getY() + 1, user.getLocation().getZ());

        componentHead = new AbilityComponent
        (new SpawnEgg(1.0, true, null, new BigChickenEggHit("Big Chicken Egg Hit Abil", true, 0)), differenceVector, elevatedChickenLoc);
        
        return true;
    }

}
