package io.github.mxylery.bobuxplugin.entities.mobs;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.abilities.mob_abilities.StinkyMobAbilityOne;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.entities.BobuxMob;
import io.github.mxylery.bobuxplugin.items.BobuxAttributeSet;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.listeners.BobuxEntityListener;
import io.github.mxylery.bobuxplugin.listeners.MobAbilityManager;

public class BigChicken extends BobuxMob {
    
    private Zombie invisZombie;

    public BigChicken(BobuxPlugin plugin, Location location) {
        super(plugin, location);
    }

    protected void setUpEntity() {
        Chicken chicken = (Chicken) location.getWorld().spawnEntity(location, EntityType.CHICKEN);

        BobuxAttributeSet[] attributeSet = {new BobuxAttributeSet(Attribute.SCALE, 3.0, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.ANY)};

        ItemStack[] dropTable = {new ItemStack(Material.CHICKEN), new ItemStack(Material.FEATHER), BobuxItemInterface.bouncingItem.getStack()};
        double[] dropWeights = {1, 0.5, 0.25};
        int[][] dropRanges = {{3,8}, {2,6}, {1,2}};

        invisZombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);
        invisZombie.setInvisible(true);
        invisZombie.setInvulnerable(true);
        invisZombie.setSilent(true);
        invisZombie.setCustomName("invis zombie");
        invisZombie.setCustomNameVisible(false);

        super.maxHealth = 40;
        super.dropTable = dropTable;
        super.dropWeights = dropWeights;
        super.dropRanges = dropRanges;
        super.attributeSet = attributeSet;
        super.abilityList = abilityList;
        super.name = "Big Chicken";
        super.entity = chicken;
        applyAttributes();
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getEntity().equals(entity) && e.getDamageSource().getCausingEntity() instanceof Player) {
            Player player = (Player) e.getDamageSource().getCausingEntity();
            double damageTaken = e.getDamage();
            invisZombie.setTarget(player);
        }
    }

    @Override
    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity().equals(super.entity)) {
            invisZombie.setInvulnerable(false);
            invisZombie.setHealth(0);
            rollLootTable(super.entity.getLocation());
            BobuxEntityListener.getBobuxEntityList().remove(this);
            HandlerList.unregisterAll(this);
        }
    }
}
