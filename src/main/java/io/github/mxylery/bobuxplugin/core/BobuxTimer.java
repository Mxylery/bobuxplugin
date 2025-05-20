package io.github.mxylery.bobuxplugin.core;

import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.listeners.BobuxGUIGenerator;

/**
 * This class keeps track of the ticks of the server and reports to the
 * ability manager to update it with the current tick. Also manages the server-wide timed events
 */
public class BobuxTimer implements Runnable {
    
    private static long ticksPassed;
    private static Server server;
    private static Plugin bobuxPlugin;
    private static BobuxGUIGenerator stupid;

    public BobuxTimer(Server pluginServer, Plugin plugin) {
        ticksPassed = 0;
        server = pluginServer;
        bobuxPlugin = plugin;
        //for some reason there needs to be an initialized BobuxItemInterface to use the static method BobuxItemInterface.randomizeMarketItems
        //even though static methods by definition literally should be used statically, but it completely breaks everything if not so.
        this.stupid = new BobuxGUIGenerator();
    }

    private static void refresh() {
        if (ticksPassed % 300 == 0) {
            server.broadcastMessage("The market has been reset! ");
            stupid.randomizeMarketItems();
        }
    }

    public void run() {
        BobuxTimer.refresh();
        ticksPassed++;
    }

    public static long getTicksPassed() {
        return ticksPassed;
    }

    public static Server getServer() {
        return server;
    }

    public static BukkitScheduler getScheduler() {
        return server.getScheduler();
    }

    public static Plugin getPlugin() {
        return bobuxPlugin;
    }

}
