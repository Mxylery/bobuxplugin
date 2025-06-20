package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.abilities.ability_types.PhaseAbility;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.item.DeleteItem;
import io.github.mxylery.bobuxplugin.actions.misc.DelayedAction;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class LesserLootBoxAbility extends PhaseAbility {

    public LesserLootBoxAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    protected boolean assignVariables() {
        Player player = (Player) user;

        Entity[][] targetList;
        Vector[] vectorList;
        Location[] locationList;
        Inventory[] inventoryList;

        if (phase == 0) {
            targetList = new Entity[3][3];
            vectorList = new Vector[3];
            locationList = new Location[3];
            inventoryList = new Inventory[3];
            for (int i = 0; i < 3; i++) {
                locationList[i] = user.getLocation();
            }

            BobuxAction[] actionList = {new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.5f),
            new DelayedAction(new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.7f),3),
            new DelayedAction(new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.9f),3)
            };
            super.actionList = actionList;

            phase = 1;
            triggerPhase(player, BobuxItemInterface.lesserLootbox, 9);
        } else if (phase == 1) {
            targetList = new Entity[3][3];
            vectorList = new Vector[3];
            locationList = new Location[3];
            inventoryList = new Inventory[3];
            for (int i = 0; i < 3; i++) {
                locationList[i] = user.getLocation();
            }

            BobuxAction[] actionList = {new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.7f),
            new DelayedAction(new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.9f),3),
            new DelayedAction(new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.1f),3)
            };
            super.actionList = actionList;

            phase = 2;
            triggerPhase(player, BobuxItemInterface.lesserLootbox, 9);
        } else if (phase == 2) {
            targetList = new Entity[3][3];
            vectorList = new Vector[3];
            locationList = new Location[3];
            inventoryList = new Inventory[3];
            for (int i = 0; i < 3; i++) {
                locationList[i] = user.getLocation();
            }

            BobuxAction[] actionList = {new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.9f),
            new DelayedAction(new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.1f),3),
            new DelayedAction(new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.3f),3)
            };
            super.actionList = actionList;

            phase = 3;
            triggerPhase(player, BobuxItemInterface.lesserLootbox, 9);
        } else {
            targetList = new Entity[1][1];
            vectorList = new Vector[1];
            locationList = new Location[1];
            inventoryList = new Inventory[1];
            inventoryList[0] = player.getInventory();

            BobuxAction[] actionList = {new DeleteItem(BobuxItemInterface.lesserLootbox.getStack(), 1)};
            super.actionList = actionList;

            phase = 0;
            super.ignoreCD = false;
        }

        super.targetList = targetList;
        super.vectorList = vectorList;
        super.locationList = locationList;
        super.inventoryList = inventoryList;

        return true;
    }

}
