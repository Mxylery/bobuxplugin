package io.github.mxylery.bobuxplugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.abilities.PlayerAbilityListener;
import io.github.mxylery.bobuxplugin.core.BobuxCommands;
import io.github.mxylery.bobuxplugin.core.BobuxGiver;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.entities.BobuxEntity;
import io.github.mxylery.bobuxplugin.entities.BobuxEntityListener;
import io.github.mxylery.bobuxplugin.entities.BobuxHostile;
import io.github.mxylery.bobuxplugin.guis.BobuxGUIGenerator;
import io.github.mxylery.bobuxplugin.guis.core.MainGUI;
import io.github.mxylery.bobuxplugin.guis.raffle.BobuxRaffle;
import io.github.mxylery.bobuxplugin.io.BobuxStatsData;
import io.github.mxylery.bobuxplugin.io.PlayerLocationData;
import io.github.mxylery.bobuxplugin.io.RaffleData;
import io.github.mxylery.bobuxplugin.player.BobuxPlayerStats;

public final class BobuxPlugin extends JavaPlugin implements Listener {

    private static BukkitScheduler scheduler;
    private static Server server;
    private static World hubWorld;
    private static World overworld;
    private static HashMap<UUID, Location> playerLocMap;
    private static HashMap<UUID, BobuxPlayerStats> playerStatMap;
    private static BobuxRaffle currentRaffle;
    private static MainGUI mainGUI;

    @Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
        playerLocMap = new HashMap<UUID, Location>();
        playerStatMap = new HashMap<UUID, BobuxPlayerStats>();

        server = this.getServer();
        scheduler = this.getServer().getScheduler();
        hubWorld = Bukkit.getWorld("bobuxhub");
        WorldCreator creator = new WorldCreator("world");
        creator.createWorld();
        overworld = Bukkit.getWorld("world");
        server.getPluginManager().registerEvents(this, this);

        PlayerLocationData.loadDataToGame();
        BobuxStatsData.loadDataToGame();

        BobuxTimer bobuxTimer = new BobuxTimer(this.getServer(), this);
        scheduler.runTaskTimer(this, bobuxTimer, 0, 1);
        scheduler.runTaskLater(this, new Runnable(){
            public void run() {
                RaffleData.loadDataToGame();
            }
        }, 20);

        mainGUI = new MainGUI();

        new BobuxGiver(this);
        new PlayerAbilityListener(this);
        new BobuxGUIGenerator(this);
        new BobuxEntityListener(this);
        
        this.getCommand("bobuxgive").setExecutor(new BobuxCommands(this));
        this.getCommand("bobuxmenu").setExecutor(new BobuxCommands(this));
        this.getCommand("bobuxinfo").setExecutor(new BobuxCommands(this));
        this.getCommand("bobuxspawn").setExecutor(new BobuxCommands(this));
        this.getCommand("bobuxhub").setExecutor(new BobuxCommands(this));
        this.getCommand("bobuxconvert").setExecutor(new BobuxCommands(this));
        this.getCommand("bobuxattribute").setExecutor(new BobuxCommands(this));

	}

	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
        PlayerLocationData.saveDataToFile();
        BobuxStatsData.saveDataToFile();
        RaffleData.saveDataToFile();
        ArrayList<BobuxEntity> entityList = BobuxEntityListener.getBobuxEntityList();
        if (entityList != null) {
        for (int i = 0; i < entityList.size(); i++) {
            if (entityList.get(i).getEntity() != null) {
                Entity entity = entityList.get(i).getEntity();
                entity.remove();
                if (entityList.get(i) instanceof BobuxHostile) {
                    BobuxHostile hostile = (BobuxHostile) entityList.get(i);
                    hostile.removeInvisZombie();
                }
            }
        }
        }
	}

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.setGameMode(GameMode.ADVENTURE);
        e.getPlayer().teleport(hubWorld.getSpawnLocation());
        if (!playerStatMap.containsKey(player.getUniqueId())) {
            BobuxPlayerStats stats = new BobuxPlayerStats(player);
            playerStatMap.put(player.getUniqueId(), stats);
        }
    }

    //For bobuxhub stuff...
    @EventHandler
    public void onSignRightClick(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.LEFT_CLICK_BLOCK) && e.getPlayer().getWorld().equals(hubWorld)) {
            if (e.getClickedBlock().getBlockData().getMaterial().equals(Material.OAK_SIGN)) {
                Player player = e.getPlayer();
                Location lastPlayerLoc = playerLocMap.get(player.getUniqueId());
                if (lastPlayerLoc == null) {
                    lastPlayerLoc = overworld.getSpawnLocation();
                    playerLocMap.put(player.getUniqueId(), lastPlayerLoc);
                }
                player.setGameMode(GameMode.SURVIVAL);
                player.teleport(lastPlayerLoc);
            }
        }
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent e) {
        if (!e.getPlayer().getWorld().equals(hubWorld)) {
            PlayerLocationData.saveDataToFile();
            PlayerLocationData.loadDataToGame();
            BobuxStatsData.saveDataToFile();
            BobuxStatsData.loadDataToGame();
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (!e.getEntity().getWorld().equals(hubWorld)) {
            Player player = e.getEntity();
            Location respawnLoc = player.getRespawnLocation();
            if (respawnLoc != null) {
                playerLocMap.put(player.getUniqueId(), overworld.getSpawnLocation());
                PlayerLocationData.saveDataToFile();
                PlayerLocationData.loadDataToGame();
            } else {
                playerLocMap.put(player.getUniqueId(), respawnLoc);
                PlayerLocationData.saveDataToFile();
                PlayerLocationData.loadDataToGame();
            }
        }
    }

    @EventHandler
    public void onSpawn(PlayerRespawnEvent e) {
        scheduler.runTaskLater(this, new Runnable() {
            public void run() {
                e.getPlayer().teleport(hubWorld.getSpawnLocation());
                e.getPlayer().setGameMode(GameMode.ADVENTURE);
            }
        }, 1);
    }
    
    public static BukkitScheduler getScheduler() {
        return scheduler;
    }

    public static World getOverworld() {
        return overworld;
    }

    public static World getBobuxHub() {
        return hubWorld;
    }

    public static void setPlayerLocMap(HashMap<UUID, Location> map) {
        playerLocMap = map;
    }

    public static void setPlayerStatMap(HashMap<UUID, BobuxPlayerStats> list) {
        playerStatMap = list;
    }

    public static HashMap<UUID, BobuxPlayerStats> getPlayerStatMap() {
        return playerStatMap;
    }

    public static BobuxPlayerStats getPlayerStats(Player player) {
        return playerStatMap.get(player.getUniqueId());
    }

    public static BobuxRaffle getRaffle() {
        return currentRaffle;
    }

    public static void setRaffle(BobuxRaffle raffle) {
        currentRaffle = raffle;
    }

    public static MainGUI getMainGUI() {
        return mainGUI;
    }

    public static HashMap<UUID, Location> getPlayerLocMap() {
        return playerLocMap;
    }

}