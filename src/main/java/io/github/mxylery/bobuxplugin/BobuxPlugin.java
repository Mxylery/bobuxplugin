package io.github.mxylery.bobuxplugin;

import java.io.File;
import java.rmi.server.UID;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.abilities.PlayerAbilityListener;
import io.github.mxylery.bobuxplugin.core.BobuxCommands;
import io.github.mxylery.bobuxplugin.core.BobuxGiver;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.entities.BobuxEntityListener;
import io.github.mxylery.bobuxplugin.guis.BobuxGUIGenerator;

public final class BobuxPlugin extends JavaPlugin implements Listener {

    private static BukkitScheduler scheduler;
    private static Server server;
    private static World hubWorld;
    private static World overworld;
    private static HashMap<UUID, Location> playerLocMap;

    @Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");

        server = this.getServer();
        scheduler = this.getServer().getScheduler();
        hubWorld = Bukkit.getWorld("bobuxhub");
        WorldCreator creator = new WorldCreator("world");
        creator.createWorld();
        overworld = Bukkit.getWorld("world");
        server.getPluginManager().registerEvents(this, this);
        BobuxTimer bobuxTimer = new BobuxTimer(this.getServer(), this);
        scheduler.runTaskTimer(this, bobuxTimer, 0, 1);

        new BobuxGiver(this);
        new PlayerAbilityListener(this);
        new BobuxGUIGenerator(this);
        new BobuxEntityListener(this);
        
        this.getCommand("bobuxgive").setExecutor(new BobuxCommands(this));
        this.getCommand("bobuxmenu").setExecutor(new BobuxCommands(this));
        this.getCommand("bobuxinfo").setExecutor(new BobuxCommands(this));
        this.getCommand("bobuxspawn").setExecutor(new BobuxCommands(this));
        this.getCommand("bobuxhub").setExecutor(new BobuxCommands(this));

	}

	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}

    //For bobuxhub stuff...
    @EventHandler
    public void onSignRightClick(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getPlayer().getWorld().equals(hubWorld)) {
            if (e.getClickedBlock().getBlockData().getMaterial().equals(Material.OAK_SIGN)) {
                Player player = e.getPlayer();
                Location lastPlayerLoc = overworld.getSpawnLocation();
                player.teleport(lastPlayerLoc);
            }
        }
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

}