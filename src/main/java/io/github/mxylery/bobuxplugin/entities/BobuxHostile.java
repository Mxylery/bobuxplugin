package io.github.mxylery.bobuxplugin.entities;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDismountEvent;
import org.bukkit.event.player.PlayerInputEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.mxylery.bobuxplugin.abilities.AbilityInstance;
import io.github.mxylery.bobuxplugin.abilities.MobAbilityManager;
import io.github.mxylery.bobuxplugin.abilities.ability_types.MobHitAbility;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.data_structures.AbilityInstanceStructure;

public abstract class BobuxHostile extends BobuxLivingEntity {
    
    protected MobHitAbility hitAbility;
    protected Zombie invisZombie;
    protected double range;

    /**
     * This spawns a Bobux Hostile at the specified location, with a melee attack with the given values
     * @param location Location where hostile will spawn
     * @param knockback Knockback dealt by melee attack
     * @param damage Damage dealt by melee attack
     * @param range Range of melee attack
     */
    public BobuxHostile(Location location, double knockback, double damage, double range) {
        super(location);
        abilityStructure = new AbilityInstanceStructure();
        isDead = false;
        this.range = range;
        this.livingEntity = (LivingEntity) super.entity;
        this.hitAbility = new MobHitAbility("Hit", true, knockback, damage, range);
        spawnInvisZombie(this.livingEntity);
    }

    protected void spawnInvisZombie(Entity entity) {
        Zombie invisZombie = (Zombie) location.getWorld().spawnEntity(entity.getLocation(), EntityType.ZOMBIE);
        invisZombie.setCustomName("Invis Zombie");
        invisZombie.setInvulnerable(true);
        invisZombie.setInvisible(true);
        invisZombie.setSilent(true);
        invisZombie.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100000, 0));
        this.invisZombie = invisZombie;
        entity.addPassenger(invisZombie);
    }

    public MobHitAbility getHitAbility() {
        return hitAbility;
    }

    @EventHandler
    public void onZombieHit(EntityDamageByEntityEvent e) {
        if (e.getDamager().equals(invisZombie)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onUnmount(EntityDismountEvent e) {
        if (e.getEntity().equals(invisZombie)) {
            invisZombie.remove();
            entity.remove();
            BobuxEntityListener.getBobuxEntityList().remove(this);
        }
    }

    @Override
    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (e.getEntity().equals(super.entity)) {
            invisZombie.setInvulnerable(false);
            invisZombie.setHealth(0);
            invisZombie.damage(1000);
            rollLootTable(super.entity.getLocation());
            BobuxEntityListener.getBobuxEntityList().remove(this);
            HandlerList.unregisterAll(this);
        }
    }

    /**
     * Method for using a Bobux Hostile's ability with a single target involved. Takes in the index of the ability to use of the ability list (initialized in setUpEntity()), and the target to intake.
     * @param index
     */
    @Override
    public void useAbility(int index, Entity target) {
        if (index == -1) {
            hitAbility.setUser(entity);
            hitAbility.setTarget(target);
            if (hitAbility.setActionList() && abilityStructure.checkForAbilityCD(hitAbility, hitAbility.getCooldown(), entity) == -1) {
                hitAbility.use();
                AbilityInstance abilityInstance = new AbilityInstance(entity, BobuxTimer.getTicksPassed(), hitAbility);
                abilityStructure.addAbilityInstanceLast(abilityInstance);
            }
        } else {
            abilityList[index].setUser(entity);
            abilityList[index].setTarget(target);
            if (abilityList[index].setActionList() && abilityStructure.checkForAbilityCD(abilityList[index], abilityList[index].getCooldown(), entity) == -1) {
                abilityList[index].use();
                AbilityInstance abilityInstance = new AbilityInstance(entity, BobuxTimer.getTicksPassed(), abilityList[index]);
                abilityStructure.addAbilityInstanceLast(abilityInstance);
            }
        }

    }
    
    /**
     * Since the playerinputevent listener is taken, whatever that would be needs to go here instead
     */
    protected void normalAction() {

    }

    /**
     * Since the entityhitbyentityaction listener is taken, whatever that would be needs to go here instead
     */
    protected void entityHitByEntityAction() {

    }

    /**
     * Only used for mobs which don't naturally attack players
     * @param e 
     */
    @EventHandler
    public void meleeAttack(PlayerInputEvent e) {
        ArrayList<Player> playerList = BobuxUtils.getNearbyPlayers(entity.getLocation(), range);
        if (playerList.size() != 0) {
            Player player = playerList.get(0);
            MobAbilityManager.verifyAbilityCD(this, -1, player);
        }
        normalAction();
    }
}
