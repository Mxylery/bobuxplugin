package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.entity.EffectGive;
import io.github.mxylery.bobuxplugin.actions.item.DeleteItem;
import io.github.mxylery.bobuxplugin.actions.spawn.SpawnArrow;

public class MegaLongBowAbility extends AbilityOneTime {

    public MegaLongBowAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    public boolean assignVariables() {
        Player player = (Player) user;

        componentHead = new AbilityComponent(
        new SpawnArrow(4, true, null, null), user.getLocation().getDirection(), user.getLocation());
        componentHead.addComponent
        (new AbilityComponent(new DeleteItem(new ItemStack(Material.ARROW), 1), player.getInventory()));
        componentHead.addComponent
        (new AbilityComponent(new EffectGive(PotionEffectType.JUMP_BOOST, 40, 3), user));

        return true;
    }

}
