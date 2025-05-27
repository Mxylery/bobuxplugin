package io.github.mxylery.bobuxplugin.core;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.core.BobuxDay.DayType;
import io.github.mxylery.bobuxplugin.listeners.BobuxGUIGenerator;

/**
 * This class keeps track of the ticks of the server and reports to the
 * ability manager to update it with the current tick. Also manages the server-wide timed events
 */
public class BobuxTimer implements Runnable {
    
    private static long ticksPassed;
    private static Server server;
    private static Plugin bobuxPlugin;
    private static long time;
    private static World world;

    public BobuxTimer(Server pluginServer, Plugin plugin) {
        ticksPassed = 0;
        server = pluginServer;
        bobuxPlugin = plugin;
        //for some reason there needs to be an initialized BobuxItemInterface to use the static method BobuxItemInterface.randomizeMarketItems
        //even though static methods by definition literally should be used statically, but it completely breaks everything if not so.
    }

    private static void refresh() {
        //Try to overlap as many possibilities as possible (least amount of if checks)
        if (ticksPassed % 1200 == 0) {
            server.broadcastMessage("The market has been reset! ");
            BobuxGUIGenerator.randomizeMarketItems();
            if (ticksPassed % 2400 == 0) {
                server.broadcastMessage("New bounties are now available...");
                BobuxGUIGenerator.randomizeBounties();
            }
        } 
        if (world == null) {

        } else if (world.getTime() == 0) {
            rollDay();
        }
    }

    private static void rollDay() {
        BobuxDay.rollDay();
    }

    public void run() {
        BobuxTimer.refresh();
        ticksPassed++;
    }

    public static void setWorld() {

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

    public static long getTime() {
        return world.getTime();
    }

    public static Plugin getPlugin() {
        return bobuxPlugin;
    }

}
