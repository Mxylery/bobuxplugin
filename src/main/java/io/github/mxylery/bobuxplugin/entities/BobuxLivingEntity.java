package io.github.mxylery.bobuxplugin.entities;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.data_structures.AbilityInstanceStructure;
import io.github.mxylery.bobuxplugin.items.BobuxAttributeSet;

public abstract class BobuxLivingEntity extends BobuxEntity {

    protected LivingEntity livingEntity;
    protected int maxHealth;
    protected boolean isDead;
    protected ItemStack[] dropTable;
    protected double[] dropWeights;
    protected int[][] dropRanges;
    protected BobuxAttributeSet[] attributeSet;
    protected boolean isHostile;

    public BobuxLivingEntity(Location location) {
        super(location);
        abilityStructure = new AbilityInstanceStructure();
        isDead = false;
        this.livingEntity = (LivingEntity) super.entity;
    }

    /**
     * Constructor used for Bobux Living Entities who are hostile and whose entities don't attack normally. abilityList[0] should be the hit ability.
     * @param location Location where the living entity will spawn
     * @param knockback Knockback taken on hit
     * @param damage Damage taken on hit
     */
    public BobuxLivingEntity(Location location, double knockback, double damage, double range) {
        super(location);
        abilityStructure = new AbilityInstanceStructure();
        isDead = false;
        this.livingEntity = (LivingEntity) super.entity;
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity().equals(super.entity)) {
            rollLootTable(super.entity.getLocation());
            BobuxEntityListener.getBobuxEntityList().remove(this);
            HandlerList.unregisterAll(this);
        }
    }

    //Min is dropRanges[i][0], max is dropRanges[i][1]
    public void rollLootTable(Location location) {
        Location dropLoc = new Location(location.getWorld(), location.getX() + 0.5, location.getY() + 0.5, location.getZ() + 0.5);
        if (dropTable == null || dropWeights == null || dropRanges == null) {
        } else {
            for (int i = 0; i < dropTable.length; i++) {
                Double rng = Math.random();
                if (rng < dropWeights[i]) {
                    int amount = (int) (dropRanges[i][0] + (int) (Math.random()*(dropRanges[i][1] - dropRanges[i][0])));
                    for (int j = 0; j < amount; j++) {
                        dropLoc.getWorld().dropItem(dropLoc, dropTable[i]);
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
                if (maxHealth < livingEntity.getHealth()) {
                    livingEntity.damage(-hpToAdd);
                } else {
                    livingEntity.setHealth(maxHealth);
                }
                entity = livingEntity;
            }
            livingEntity.setCustomName(name);
        }
    }

    public void setTarget(LivingEntity target) {
        Mob mob = (Mob) entity;
        mob.setTarget(target);
    }

    public boolean isDead() {
        return entity.isDead();
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public boolean isHostile() {
        return isHostile;
    }
    
}
