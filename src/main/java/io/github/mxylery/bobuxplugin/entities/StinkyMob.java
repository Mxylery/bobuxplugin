package io.github.mxylery.bobuxplugin.entities;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.abilities.mob_abilities.StinkyMobAbilityOne;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.listeners.BobuxEntityListener;

//Zombie
public class StinkyMob extends BobuxMob {
    
    public StinkyMob(BobuxPlugin plugin, BobuxEntityListener listener, Location location) {
        super(plugin, listener, location);
    }

    protected void setUpEntity() {
        ItemStack bootStack = new ItemStack(Material.IRON_BOOTS);
        ItemStack chestplateStack = new ItemStack(Material.IRON_CHESTPLATE);
        Zombie zombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        zombie.getEquipment().setBoots(bootStack);
        zombie.getEquipment().setChestplate(chestplateStack);
        zombie.setCustomName("Stinky Mob");
        BobuxAbility[] abilityList = {new StinkyMobAbilityOne("Stinky Mob Ability", isDead, 100)};
        super.abilityList = abilityList;
        super.entityType = EntityType.ZOMBIE;
        super.entity = zombie;
    }
}
