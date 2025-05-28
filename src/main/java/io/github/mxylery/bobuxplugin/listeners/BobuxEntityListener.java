package io.github.mxylery.bobuxplugin.listeners;

import java.util.ArrayList;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.entities.BobuxEntity;
import io.github.mxylery.bobuxplugin.entities.BobuxSpawnChances;

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

    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent e) {
        Chunk chunk = e.getChunk();
        Location location = new Location(chunk.getWorld(), chunk.getX()+8, 192, chunk.getZ()+8);
        ArrayList<Entity> entityList = (ArrayList<Entity>) location.getWorld().getNearbyEntities(location, 8, 192, 8);
        for (int i = 0; i < entityList.size(); i++) {
            if (getBobuxEntity(entityList.get(i)) != null) {
                Entity entity = entityList.get(i);
                BobuxEntity bobuxEntity = getBobuxEntity(entity);
                bobuxEntityList.remove(bobuxEntity);
                entity.remove();
            }
        }
    }


    public static ArrayList<BobuxEntity> getBobuxEntityList() {
        return bobuxEntityList;
    }

}
