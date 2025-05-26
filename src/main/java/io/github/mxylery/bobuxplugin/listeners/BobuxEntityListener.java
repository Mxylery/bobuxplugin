package io.github.mxylery.bobuxplugin.listeners;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.Plugin;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.entities.BobuxEntity;
import io.github.mxylery.bobuxplugin.entities.BobuxMob;
import io.github.mxylery.bobuxplugin.entities.StinkyMob;

public class BobuxEntityListener implements Listener {

    private BobuxPlugin plugin;
    private ArrayList<BobuxEntity> bobuxEntityList;

    public BobuxEntityListener(BobuxPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        bobuxEntityList = new ArrayList<BobuxEntity>();
    }

    private BobuxEntity isBobuxMob(Entity entity) {
        for (int i = 0; i < bobuxEntityList.size(); i++) {
            if (entity.equals(bobuxEntityList.get(i).getEntity())) {
                return bobuxEntityList.get(i);
            }
        }
        return null;
    }

    public void spawnMob() {

    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent e) {
        if (e.getEntity() instanceof Zombie) {
            double randomChance = Math.random();
            if (randomChance < 0.5) {
                Location zombieLoc = e.getEntity().getLocation();
                StinkyMob stinkyMob = new StinkyMob(plugin, this, zombieLoc);
                bobuxEntityList.add(stinkyMob);
                e.setCancelled(true);
            }
        }
    }

}
