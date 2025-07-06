package io.github.mxylery.bobuxplugin.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.guis.BobuxGUIGenerator;
import io.github.mxylery.bobuxplugin.guis.raffle.BobuxRaffle;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.player.BobuxPlayerStats;
import io.github.mxylery.bobuxplugin.player.TempAttribute;

/**
 * This class keeps track of the ticks of the server and reports to the
 * ability manager to update it with the current tick. Also manages the server-wide timed events
 */
public class BobuxTimer implements Runnable {
    
    private static long ticksPassed;
    private static Server server;
    private static BobuxPlugin bobuxPlugin;
    private static World world = BobuxPlugin.getOverworld();
    private static int numberOfDays = -1;
    private static HashMap<UUID, BobuxPlayerStats> playerStatMap;

    public BobuxTimer(Server pluginServer, BobuxPlugin plugin) {
        ticksPassed = 0;
        server = pluginServer;
        bobuxPlugin = plugin;
        //for some reason there needs to be an initialized BobuxItemInterface to use the static method BobuxItemInterface.randomizeMarketItems
        //even though static methods by definition literally should be used statically, but it completely breaks everything if not so.
        BobuxDay.rollDay();
        BobuxGUIGenerator.randomizeQuests();
        BobuxGUIGenerator.randomizeMarketItems();
        playerStatMap = BobuxPlugin.getPlayerStatMap();
    }

    private static void refresh() {
        if (world == null) {

        } else if (world.getTime() == 0) {
            BobuxDay.rollDay();
            server.broadcastMessage("The day §6market §fis now open.");
            BobuxGUIGenerator.randomizeMarketItems();
            server.broadcastMessage("New §cbounties §fare now available...");
            BobuxGUIGenerator.randomizeBounties();
            server.broadcastMessage("New §cquests §fare now available...");
            BobuxGUIGenerator.randomizeQuests();
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.getInventory().addItem(BobuxItemInterface.bobuxSquare.getStack());
            }
            double raffleRng = Math.random();
            if (raffleRng < 0.5 && BobuxPlugin.getRaffle().isCompleted()) {
                BobuxRaffle raffle = new BobuxRaffle();
                BobuxPlugin.setRaffle(raffle);
            }
            numberOfDays++;
        } else if (world.getTime() == 13000) {
            server.broadcastMessage("The night §6market §fis now open.");
            BobuxGUIGenerator.randomizeMarketItems();
        } else {

        }
        updateAttributes();
    }

    private static void updateAttributes() {
        List<Player> playerList = world.getPlayers();
        for (Player player : playerList) {
            BobuxPlayerStats stats = playerStatMap.get(player.getUniqueId());
            stats.tick();
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

    public static long getTime() {
        return world.getTime();
    }

    public static boolean isDay() {
        return world.getTime() < 13000;
    }

    public static BobuxPlugin getPlugin() {
        return bobuxPlugin;
    }

    public static int getDaysPassed() {
        return numberOfDays;
    }

}
