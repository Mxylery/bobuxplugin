package io.github.mxylery.bobuxplugin;

import java.io.File;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.abilities.PlayerAbilityListener;
import io.github.mxylery.bobuxplugin.core.BobuxCommands;
import io.github.mxylery.bobuxplugin.core.BobuxGiver;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.entities.BobuxEntityListener;
import io.github.mxylery.bobuxplugin.guis.BobuxGUIGenerator;

public final class BobuxPlugin extends JavaPlugin implements Listener {

    private static File[] worldFolder;
    private static BukkitScheduler scheduler;
    private static Server server;
    private static World hubWorld;
    private static World overworld;

    @Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
        worldFolder = this.getServer().getWorldContainer().listFiles();

        server = this.getServer();
        scheduler = this.getServer().getScheduler();
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

	}

	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}

    //For bobuxhub stuff...
    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        Location spawnLoc;
        if (hubWorld == null) {
            hubWorld = server.getWorld("bobux_hub");
        } 
        spawnLoc = hubWorld.getSpawnLocation();
        e.getPlayer().teleport(spawnLoc);
        e.getPlayer().setGameMode(GameMode.ADVENTURE);
    }

    @EventHandler
    public void onWorld(PlayerInteractEvent e) {
        
    }
    
    public static BukkitScheduler getScheduler() {
        return scheduler;
    }

}