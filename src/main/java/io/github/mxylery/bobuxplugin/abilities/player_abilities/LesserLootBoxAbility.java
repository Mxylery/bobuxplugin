package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.abilities.ability_types.PhaseAbility;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.item.DeleteItem;
import io.github.mxylery.bobuxplugin.actions.misc.DelayedAction;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.guis.misc.LesserLootboxGUI;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class LesserLootBoxAbility extends PhaseAbility {

    public LesserLootBoxAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        Player player = (Player) user;

        switch (phase) {
            case 0:
            break;
            case 1:
            break;
            case 2:
            break;
            case 3:
            break;
            default:
            break;
        }

        if (phase == 0) {
            componentHead = new AbilityComponent
            (new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.5f), user.getLocation());
            componentHead.addComponent(new AbilityComponent
            (new DelayedAction(new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.75f),3), user.getLocation()));
            componentHead.addComponent(new AbilityComponent
            (new DelayedAction(new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f),6), user.getLocation()));

            triggerPhase(player, BobuxItemInterface.lesserLootbox, 9, 1);
        } else if (phase == 1) {
            componentHead = new AbilityComponent
            (new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.75f), user.getLocation());
            componentHead.addComponent(new AbilityComponent
            (new DelayedAction(new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f),3), user.getLocation()));
            componentHead.addComponent(new AbilityComponent
            (new DelayedAction(new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.25f),6), user.getLocation()));

            triggerPhase(player, BobuxItemInterface.lesserLootbox, 9, 3);
        } else {

            componentHead = new AbilityComponent(new DeleteItem(BobuxItemInterface.lesserLootbox.getStack(), 1), player.getInventory());

            phase = 0;
            new LesserLootboxGUI(Bukkit.createInventory(player, 27), player, BobuxTimer.getPlugin());
        }
        return true;
    }

}
