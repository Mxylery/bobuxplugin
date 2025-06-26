package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.item.DeleteItem;
import io.github.mxylery.bobuxplugin.actions.velocity.ConstantVelocity;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.entities.entities.projectiles.BobuxPearl;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class StraightPearlAbility extends AbilityOneTime {

    public StraightPearlAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        Player player = (Player) user;

        BobuxPearl pearl = new BobuxPearl(BobuxUtils.offsetLocation(user.getLocation(), player.getLocation().getDirection(), 1, 1), player.getLocation().getDirection(), 1, false, null, null);
        pearl.setOwner(player);

        componentHead = new AbilityComponent
        (new ConstantVelocity(1, 80), pearl.getEntity(), user.getLocation().getDirection());
        componentHead.addComponent(new AbilityComponent
        (new DeleteItem(BobuxItemInterface.straightPearl.getStack(), 1), player.getInventory()));

        return true;
    }

}
