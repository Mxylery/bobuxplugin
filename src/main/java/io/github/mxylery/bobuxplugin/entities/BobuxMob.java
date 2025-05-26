package io.github.mxylery.bobuxplugin.entities;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.AbilityInstance;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.data_structures.AbilityInstanceStructure;
import io.github.mxylery.bobuxplugin.listeners.BobuxEntityListener;

public abstract class BobuxMob extends BobuxLivingEntity {

    protected double attackDamage;
    protected boolean isDead;
    protected BobuxAbility[] abilityList;
    protected AbilityInstanceStructure abilityStructure;

    public BobuxMob(BobuxPlugin plugin, BobuxEntityListener listener, Location location) {
        super(plugin, listener, location);
        abilityStructure = new AbilityInstanceStructure();
    }

    //The roles are switched here; the user is the entity and the player is the victim.
    public void useAbility(int index) {
        abilityList[index].setOtherEntity(entity);
        if (abilityList[index].setActionList() && abilityStructure.checkForAbilityCD(abilityList[index], abilityList[index].getCooldown(), entity) == -1) {
            abilityList[index].use();
            AbilityInstance abilityInstance = new AbilityInstance(entity, BobuxTimer.getTicksPassed(), abilityList[index]);
            abilityStructure.addabilityInstanceLast(abilityInstance);
        }
    }

    public void useAbility(int index, Player player) {
        abilityList[index].setUser(player);
        abilityList[index].setOtherEntity(entity);
        if (abilityList[index].setActionList() && abilityStructure.checkForAbilityCD(abilityList[index], abilityList[index].getCooldown(), entity) == -1) {
            abilityList[index].use();
            AbilityInstance abilityInstance = new AbilityInstance(entity, BobuxTimer.getTicksPassed(), abilityList[index]);
            abilityStructure.addabilityInstanceLast(abilityInstance);
        }
    }

    public void setTarget(LivingEntity target) {
        Mob mob = (Mob) entity;
        mob.setTarget(target);
    }

    public AbilityInstanceStructure getAbilityHistory() {
        return abilityStructure;
    }

    public BobuxAbility getAbility(int index) {
        return abilityList[index];
    }
    
}
