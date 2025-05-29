package io.github.mxylery.bobuxplugin.entities;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.AbilityInstance;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.data_structures.AbilityInstanceStructure;
import io.github.mxylery.bobuxplugin.listeners.BobuxEntityListener;

public abstract class BobuxEntity implements Listener {
    
    protected BobuxPlugin plugin;
    protected Entity entity;
    protected Location location;
    protected String name;
    protected BobuxAbility[] abilityList;
    protected AbilityInstanceStructure abilityStructure;
    protected long lifetime;

    public BobuxEntity(BobuxPlugin plugin, Location location) {
        this.location = location;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        setUpEntity();
    }

        public BobuxEntity(BobuxPlugin plugin, Location location, long lifetime) {
        this.location = location;
        this.lifetime = lifetime;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        setUpEntity();
    }

    protected abstract void setUpEntity();

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

    public AbilityInstanceStructure getAbilityHistory() {
        return abilityStructure;
    }

    public BobuxAbility getAbility(int index) {
        return abilityList[index];
    }

    public Location getLocation() {
        location = entity.getLocation();
        return entity.getLocation();
    }

    public Entity getEntity() {
        return entity;
    }

    public EntityType getEntityType() {
        return entity.getType();
    }

    public String getName() {
        return name;
    }

}
