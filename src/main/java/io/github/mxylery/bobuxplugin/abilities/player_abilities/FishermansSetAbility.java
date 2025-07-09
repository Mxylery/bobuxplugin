package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.item.GiveItem;
import io.github.mxylery.bobuxplugin.actions.spawn.SpawnEntity;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class FishermansSetAbility extends AbilityOneTime {

    public FishermansSetAbility() {
        super("Fisherman Set Ability", true, 20);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        double rng = Math.random();

        Player player = (Player) user;

        if (rng < 0.0005) {
            componentHead = new AbilityComponent(
            new GiveItem(BobuxItemInterface.greaterLootbox.getStack(), 1), player.getInventory());
        } else if (rng < 0.002) {
            componentHead = new AbilityComponent(
            new GiveItem(BobuxItemInterface.lootbox.getStack(), 1), player.getInventory());
        } else if (rng < 0.01) {
            componentHead = new AbilityComponent(
            new GiveItem(BobuxItemInterface.lesserLootbox.getStack(), 1), player.getInventory());
        } else {
            return false;
        }
        return true;
    }

}
