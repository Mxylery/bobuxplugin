package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.item.ReplaceItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class BobuxBrewAbility extends AbilityOneTime {

    public BobuxBrewAbility() {
        super("Bobux Brew Ability", false, 600);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        Player player = (Player) user;

        componentHead = new AbilityComponent(new ReplaceItem(BobuxItemInterface.bobuxBrew.getStack(), BobuxItemInterface.bobuxBrewRemnants.getStack()), player.getInventory());

        return true;
    }

}
