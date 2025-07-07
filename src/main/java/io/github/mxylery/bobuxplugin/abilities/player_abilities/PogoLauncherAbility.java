package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.abilities.projectile_abilities.PogoLauncherProjectileAbility;
import io.github.mxylery.bobuxplugin.actions.item.DeleteItem;
import io.github.mxylery.bobuxplugin.actions.spawn.SpawnSnowball;
import io.github.mxylery.bobuxplugin.actions.velocity.ConstantVelocity;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.entities.entities.projectiles.BobuxSnowball;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class PogoLauncherAbility extends AbilityOneTime {

    public PogoLauncherAbility() {
        super("Pogo Launcher Ability", false, 60);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        Player player = (Player) user;

        componentHead = new AbilityComponent
        (new SpawnSnowball(1.0, true, null, new PogoLauncherProjectileAbility()), user.getLocation().getDirection(), user.getLocation());

        return true;
    }

}
