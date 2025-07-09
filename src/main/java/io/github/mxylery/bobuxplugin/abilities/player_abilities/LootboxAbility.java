package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.PhaseAbility;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.item.DeleteItem;
import io.github.mxylery.bobuxplugin.actions.misc.DelayedAction;
import io.github.mxylery.bobuxplugin.guis.misc.LootboxGUI;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class LootboxAbility extends PhaseAbility {

    public LootboxAbility() {
        super("Lootbox Ability", true, 100);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        Player player = (Player) user;

        if (triggeredNormally) {
            phase = 0;
            componentHead = new AbilityComponent
            (new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.5f), user.getLocation());
            componentHead.addComponent(new AbilityComponent
            (new DelayedAction(new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.75f),3), user.getLocation()));
            componentHead.addComponent(new AbilityComponent
            (new DelayedAction(new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f),6), user.getLocation()));

            triggerPhase(player, BobuxItemInterface.lootbox, 9, 1);
        } else if (phase == 1 && !triggeredNormally) {
            componentHead = new AbilityComponent
            (new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 0.75f), user.getLocation());
            componentHead.addComponent(new AbilityComponent
            (new DelayedAction(new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f),3), user.getLocation()));
            componentHead.addComponent(new AbilityComponent
            (new DelayedAction(new PlaySound(Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.25f),6), user.getLocation()));

            triggerPhase(player, BobuxItemInterface.lootbox, 9, 2);
        } else if (phase == 2 && !triggeredNormally) {
            componentHead = new AbilityComponent(new DeleteItem(BobuxItemInterface.lootbox.getStack(), 1), player.getInventory());
            Firework firework = player.getWorld().spawn(player.getLocation(), Firework.class);
            FireworkMeta fireworkMeta = firework.getFireworkMeta();
            FireworkEffect fireworkEffect = FireworkEffect.builder().flicker(false).trail(false).withColor(Color.WHITE).build();
            fireworkMeta.addEffect(fireworkEffect);
            firework.detonate();

            phase = 0;
            new LootboxGUI().openGUI(player);
        } 
        return true;
    }

}
