package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.item.GiveItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class FishermansPoleAbility extends AbilityOneTime {

    public FishermansPoleAbility() {
        super("Fishermans Pole Ability", true, 20);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        double rng = Math.random();
        int amount;

        Player player = (Player) user;

        if (rng < 0.0025) {
            amount = 1;
            componentHead = new AbilityComponent(
            new GiveItem(BobuxItemInterface.bobuxCube.getStack(), amount), player.getInventory());
        } else if (rng < 0.02) {
            amount = (int) (1 + Math.random()*2);
            componentHead = new AbilityComponent(
            new GiveItem(BobuxItemInterface.bobuxSquare.getStack(), amount), player.getInventory());
        } else if (rng < 0.12) {
            amount = (int) (1 + Math.random()*5);
            componentHead = new AbilityComponent(
            new GiveItem(BobuxItemInterface.bobux.getStack(), amount), player.getInventory());
        } else {
            return false;
        }

        return true;
    }

}
