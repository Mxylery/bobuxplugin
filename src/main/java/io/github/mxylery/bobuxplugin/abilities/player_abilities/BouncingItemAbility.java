package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Sound;
import org.bukkit.inventory.EquipmentSlot;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.item.DeleteHeldItem;
import io.github.mxylery.bobuxplugin.actions.velocity.ChangeVelocity;

public class BouncingItemAbility extends AbilityOneTime {

    public BouncingItemAbility() {
        super("Bouncing Item Ability", false, 20);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        componentHead = new AbilityComponent(new ChangeVelocity(10), user, user.getLocation().getDirection());
        componentHead.addComponent(new AbilityComponent
        (new DeleteHeldItem(EquipmentSlot.HAND, 1), user));
        componentHead.addComponent(new AbilityComponent
        (new PlaySound(Sound.BLOCK_SLIME_BLOCK_BREAK, 1.0f, 1.0f), user.getLocation()));

        return true;
    }

}
