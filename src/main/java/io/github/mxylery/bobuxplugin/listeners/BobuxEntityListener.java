package io.github.mxylery.bobuxplugin.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.Plugin;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.entities.BobuxEntity;
import io.github.mxylery.bobuxplugin.entities.BobuxLivingEntity;
import io.github.mxylery.bobuxplugin.entities.BobuxMob;
import io.github.mxylery.bobuxplugin.entities.BobuxSpawnChances;
import io.github.mxylery.bobuxplugin.entities.mobs.StinkyMob;

public class BobuxEntityListener implements Listener {

    private BobuxPlugin plugin;
    private static ArrayList<BobuxEntity> bobuxEntityList;
    private static BobuxSpawnChances spawnChances;

    public BobuxEntityListener(BobuxPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.bobuxEntityList = new ArrayList<BobuxEntity>();
        this.spawnChances = new BobuxSpawnChances(plugin, bobuxEntityList);
    }

    public static BobuxEntity getBobuxEntity(Entity entity) {
        for (int i = 0; i < bobuxEntityList.size(); i++) {
            if (entity.equals(bobuxEntityList.get(i).getEntity())) {
                return bobuxEntityList.get(i);
            }
        }
        return null;
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent e) {
        Block block = e.getChunk().getBlock(8,8,8);
        spawnChances.attemptToSpawn(block.getBiome(), block.getLocation());
    }

    public static void spawnEntity() {

    }

    public static ArrayList<BobuxEntity> getBobuxEntityList() {
        return bobuxEntityList;
    }

}
