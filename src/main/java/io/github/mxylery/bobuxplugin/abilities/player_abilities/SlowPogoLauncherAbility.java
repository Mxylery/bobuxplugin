package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.abilities.projectile_abilities.PogoLauncherProjectileAbility;
import io.github.mxylery.bobuxplugin.actions.velocity.ConstantVelocity;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.entities.entities.projectiles.BobuxSnowball;

public class SlowPogoLauncherAbility extends AbilityOneTime {

    public SlowPogoLauncherAbility() {
        super("Pogo Launcher Ability", false, 60);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        Player player = (Player) user;

        BobuxSnowball snowball = new BobuxSnowball(BobuxUtils.offsetLocation(user.getLocation(), player.getLocation().getDirection(), 1, 1), player.getLocation().getDirection(), 0, false, null, new PogoLauncherProjectileAbility());

        componentHead = new AbilityComponent
        (new ConstantVelocity(1.5, 100), snowball.getEntity(), user.getLocation().getDirection());

        return true;
    }

}
