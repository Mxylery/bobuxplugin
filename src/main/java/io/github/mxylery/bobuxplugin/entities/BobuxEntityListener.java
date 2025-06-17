package io.github.mxylery.bobuxplugin.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.EntitiesUnloadEvent;

import io.github.mxylery.bobuxplugin.BobuxPlugin;

public class BobuxEntityListener implements Listener {

    private BobuxPlugin plugin;
    private static ArrayList<BobuxEntity> bobuxEntityList;
    private static BobuxSpawnChances spawnChances;

    public BobuxEntityListener(BobuxPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        bobuxEntityList = new ArrayList<BobuxEntity>();
        spawnChances = new BobuxSpawnChances(plugin, bobuxEntityList);
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
        spawnChances.attemptToSpawn(e.getChunk(), block.getBiome(), block.getLocation());
    }

    @EventHandler
    public void onEntityUnload(EntitiesUnloadEvent e) {
        List<Entity> entityList = e.getEntities();
        if (entityList != null) {
            for (int i = 0; i < entityList.size(); i++) {
                if (getBobuxEntity(entityList.get(i)) != null) {
                    Entity entity = entityList.get(i);
                    BobuxEntity bobuxEntity = getBobuxEntity(entity);
                    bobuxEntityList.remove(bobuxEntity);
                    entity.remove();
                    System.out.println("Bobux Entity Removed");
                }
            }
        }
    }


    public static ArrayList<BobuxEntity> getBobuxEntityList() {
        return bobuxEntityList;
    }

}
