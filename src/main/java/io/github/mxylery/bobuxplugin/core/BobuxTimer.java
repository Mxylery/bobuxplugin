package io.github.mxylery.bobuxplugin.core;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.listeners.BobuxGUIGenerator;

/**
 * This class keeps track of the ticks of the server and reports to the
 * ability manager to update it with the current tick. Also manages the server-wide timed events
 */
public class BobuxTimer implements Runnable {
    
    private static long ticksPassed;
    private static Server server;
    private static BobuxPlugin bobuxPlugin;
    private static World world;
    private static boolean daySet = false;
    private static int numberOfDays = -1;
    private static long startTick = -1;

    public BobuxTimer(Server pluginServer, BobuxPlugin plugin) {
        ticksPassed = 0;
        server = pluginServer;
        bobuxPlugin = plugin;
        //for some reason there needs to be an initialized BobuxItemInterface to use the static method BobuxItemInterface.randomizeMarketItems
        //even though static methods by definition literally should be used statically, but it completely breaks everything if not so.
    }

    private static void refresh() {
        if (world == null) {

        } else if (world.getTime() == 0) {
            if (startTick == -1) {
                startTick = ticksPassed;
            }
            rollDay();
            server.broadcastMessage("The day market is now open.");
            BobuxGUIGenerator.randomizeMarketItems();
            server.broadcastMessage("New bounties are now available...");
            BobuxGUIGenerator.randomizeBounties();
            numberOfDays++;
        } else if (world.getTime() == 12000) {
            server.broadcastMessage("The night market is now open.");
            BobuxGUIGenerator.randomizeMarketItems();
        }
    }

    private static void rollDay() {
        BobuxDay.rollDay();
    }

    public void run() {
        BobuxTimer.refresh();
        ticksPassed++;
    }

    public static void setWorld(World overworld) {
        world = overworld;
        daySet = true;
    }

    public static boolean isDaySet() {
        return daySet;
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
        if (world != null) {
            return world.getTime();
        } else {
            return 0;
        }
    }

    public static boolean isDay() {
        if (world != null) {
            return world.getTime() < 13000;
        } else {
            return false;
        }
    }

    public static BobuxPlugin getPlugin() {
        return bobuxPlugin;
    }

    public static long getStartTick() {
        return startTick;
    }

    public static int getDaysPassed() {
        return numberOfDays;
    }

}
