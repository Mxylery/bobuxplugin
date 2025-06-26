package io.github.mxylery.bobuxplugin.abilities.player_abilities;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.ability_types.AbilityOneTime;
import io.github.mxylery.bobuxplugin.actions.aesthetic.PlaySound;
import io.github.mxylery.bobuxplugin.actions.item.DeleteHeldItem;
import io.github.mxylery.bobuxplugin.actions.velocity.ChangeVelocity;
import io.github.mxylery.bobuxplugin.actions.velocity.RepulseFromPoint;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class FlockingFeatherAbility extends AbilityOneTime {

    public FlockingFeatherAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    //Assuming the player is a user
    public boolean assignVariables() {

        RegistererOption option = new RegistererOption(RegistererType.SPHERE, 0, 8, 0, user.getLocation().getDirection());
        BobuxRegisterer<Mob> registerer = new BobuxRegisterer<Mob>(option, user, Mob.class);
        Vector blastBack = new Vector(-user.getLocation().getDirection().getX(), -user.getLocation().getDirection().getY(), -user.getLocation().getDirection().getZ());

        Entity[] userAsArray = {user};

        componentHead = new AbilityComponent
        (new ChangeVelocity(10), userAsArray, blastBack);
        componentHead.addComponent(new AbilityComponent
        (new DeleteHeldItem(EquipmentSlot.HAND, 1), userAsArray));
        componentHead.addComponent(new AbilityComponent
        (new PlaySound(Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.0f), user.getLocation()));
        if (registerer.getEntityList() != null) {
            componentHead.addComponent(new AbilityComponent
            (new RepulseFromPoint(8, 10, 2), registerer.getEntityList(), user.getLocation()));
        }
        return true;
    }

}
