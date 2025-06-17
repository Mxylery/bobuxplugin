package io.github.mxylery.bobuxplugin.core;

import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.BobuxPlugin;

//this will do things on the bobuxgiver stats and bobuxmob spawning
//I also rlly wanna do day events
public class BobuxDay {
    
    private static World world = BobuxPlugin.getOverworld();
    private static DayType day;
    private static Server server = BobuxTimer.getServer();
    private static double bobuxModifier = 1.0;
    private static double spawnModifier = 1.0;
    private static double shopModifier = 1.0;
    private static boolean eventHappened = false;

    public enum DayType {
        NORMAL,
        BLOOD,
        SUSPICIOUS,
        HAPPY,
        AVARICIOUS,
        DANGEROUS,
        ADVENTUROUS
    }

    public static void defaultDay() {
        day = DayType.NORMAL;
    }

    public static void rollDay() {
        double rng = Math.random();
        Object[] objectList = (Object[]) server.getOnlinePlayers().toArray();
        Player[] playerList = new Player[objectList.length];
        for (int i = 0; i < playerList.length; i++) {
            playerList[i] = (Player) objectList[i];
        }
        if (rng < 0.4) {
            day = DayType.NORMAL;
            server.broadcastMessage("§oToday seems to be a normal day.\n");
            for (int i = 0; i < playerList.length; i++) {
                playerList[i].playSound(playerList[i], Sound.BLOCK_BELL_USE, 1.0f, 1.0f);
            }
            bobuxModifier = 1.0;
            spawnModifier = 1.0;
            shopModifier = 1.0;
        } else if (rng < 0.6) {
            day = DayType.HAPPY;
            server.broadcastMessage("§oAs the sun rises, you feel delighted.\n");
            for (int i = 0; i < playerList.length; i++) {
                playerList[i].playSound(playerList[i], Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1.0f);
            }
            bobuxModifier = 1.2;
            spawnModifier = 0.8;
            shopModifier = 1.0;            
        } else if (rng < 0.8) {
            day = DayType.AVARICIOUS;
            server.broadcastMessage("§oThere seems to be a lot of activity in the marketplace today...\n");
            for (int i = 0; i < playerList.length; i++) {
                playerList[i].playSound(playerList[i], Sound.ENTITY_VILLAGER_AMBIENT, 1.0f, 1.0f);
            }
            bobuxModifier = 1.5;
            spawnModifier = 1.0;
            shopModifier = 0.9;           
        } else if (rng < 0.95) {
            day = DayType.SUSPICIOUS;
            server.broadcastMessage("Unease settles over you as dusk fades away...\n");
            for (int i = 0; i < playerList.length; i++) {
                playerList[i].playSound(playerList[i], Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1.0f);
            }
        } else {
            day = DayType.BLOOD;
            server.broadcastMessage("Hide.\n");
            for (int i = 0; i < playerList.length; i++) {
                playerList[i].playSound(playerList[i], Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1.0f);
            }
            bobuxModifier = 1.0;
            spawnModifier = 1.0;
            shopModifier = 1.0;
        }
    }

    public static DayType getDay() {
        return day;
    }

    public static double getSpawnModifier() {
        return spawnModifier;
    }

    public static double getBobuxModifier() {
        return bobuxModifier;
    }

    public static double getShopModifier() {
        return shopModifier;
    }

}
