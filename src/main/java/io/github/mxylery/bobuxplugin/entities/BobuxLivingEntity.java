package io.github.mxylery.bobuxplugin.entities;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.items.BobuxAttributeSet;
import io.github.mxylery.bobuxplugin.listeners.BobuxEntityListener;

public abstract class BobuxLivingEntity extends BobuxEntity {

    protected int maxHealth;
    protected boolean isDead;
    protected ItemStack[] dropTable;
    protected double[] dropWeights;
    protected int[][] dropRanges;
    protected BobuxAttributeSet[] attributeSet;

    public BobuxLivingEntity(BobuxPlugin plugin, Location location) {
        super(plugin, location);
        isDead = false;
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity() == entity) {
            HandlerList.unregisterAll(this);
            this.rollLootTable(entity.getLocation());
            BobuxEntityListener.getBobuxEntityList().remove(this);
        }
    }

    public void rollLootTable(Location location) {
        Location dropLoc = new Location(location.getWorld(), location.getX() + 0.5, location.getY() + 0.5, location.getZ() + 0.5);
        if (dropTable == null || dropWeights == null || dropRanges == null) {

        } else {
            for (int i = 0; i < dropTable.length; i++) {
                Double rng = Math.random();
                if (rng < dropWeights[i]) {
                    int amount = (int) (dropRanges[i][0] + Math.random()*(dropRanges[i][0] - dropRanges[i][1]));
                    for (int j = 0; j < amount; j++) {
                        location.getWorld().dropItem(dropLoc, dropTable[i]);
                    }
                }
            }
        }
    }

    protected void applyAttributes() {
        if (attributeSet != null) {
            String tempName = this.getName();
            String[] noSpaces = tempName.split(" ", 0);
            String attributeString = "";
            LivingEntity livingEntity = (LivingEntity) entity;
            for (int j = 0; j < noSpaces.length; j++) {
                attributeString = attributeString + noSpaces[j];
            }
            for (int i = 0; i < attributeSet.length; i++) {
                String tempAttributeString = attributeString + i;
                livingEntity.getAttribute(attributeSet[i].getAttribute()).addModifier(new AttributeModifier
                (new NamespacedKey(BobuxTimer.getPlugin(), tempAttributeString), attributeSet[i].getAmount(), attributeSet[i].getOperation(), attributeSet[i].getEquipmentSlotGroup()));
            }
            if (maxHealth != 0) {
                int hpToAdd = (int) (maxHealth - livingEntity.getHealth());
                livingEntity.getAttribute(Attribute.MAX_HEALTH).addModifier(new AttributeModifier
                (new NamespacedKey(BobuxTimer.getPlugin(), attributeString + "health"), hpToAdd, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY));
                livingEntity.setHealth(maxHealth);
                entity = livingEntity;
            }
            livingEntity.setCustomName(name);
        }
    }

    public boolean isDead() {
        return entity.isDead();
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }
    
}
