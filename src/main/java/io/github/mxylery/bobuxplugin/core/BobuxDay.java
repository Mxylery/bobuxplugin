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
    private static double goodSpawnModifier = 1.0;
    private static double badSpawnModifier = 1.0;
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
        if (rng < 0.5) {
            day = DayType.NORMAL;
            server.broadcastMessage("§oToday seems to be a normal day.");
            for (int i = 0; i < playerList.length; i++) {
                playerList[i].playSound(playerList[i], Sound.BLOCK_BELL_USE, 1.0f, 1.0f);
            }
            bobuxModifier = 1.0;
            goodSpawnModifier = 1.0;
            badSpawnModifier = 1.0;
        } else if (rng < 0.6) {
            day = DayType.HAPPY;
            server.broadcastMessage("§oAs the sun rises, you feel delighted.");
            for (int i = 0; i < playerList.length; i++) {
                playerList[i].playSound(playerList[i], Sound.BLOCK_NOTE_BLOCK_CHIME, 1.0f, 1.0f);
            }
            bobuxModifier = 1.2;      
            goodSpawnModifier = 1.5;
            badSpawnModifier = 0.8;   
        } else if (rng < 0.7) {
            day = DayType.AVARICIOUS;
            server.broadcastMessage("§oThere seems to be a lot of activity in the marketplace today...");
            for (int i = 0; i < playerList.length; i++) {
                playerList[i].playSound(playerList[i], Sound.ENTITY_VILLAGER_AMBIENT, 1.0f, 1.0f);
            }
            bobuxModifier = 1.5; 
            goodSpawnModifier = 0.5;
            badSpawnModifier = 0.5; 
        } else if (rng < 0.8) {
            day = DayType.SUSPICIOUS;
            server.broadcastMessage("§oUnease settles over you as dusk fades away...");
            for (int i = 0; i < playerList.length; i++) {
                playerList[i].playSound(playerList[i], Sound.BLOCK_DEEPSLATE_PLACE, 1.0f, 1.0f);
            }
            bobuxModifier = 1.0;
            goodSpawnModifier = 0.5;
            badSpawnModifier = 0.5 + Math.random();
        } else if (rng < 0.95) {
            day = DayType.ADVENTUROUS;
            server.broadcastMessage("§oAdventure awaits!");
            for (int i = 0; i < playerList.length; i++) {
                playerList[i].playSound(playerList[i], Sound.ITEM_ARMOR_EQUIP_CHAIN, 1.0f, 1.0f);
            }
            bobuxModifier = 1.5;
            goodSpawnModifier = 1.5;
            badSpawnModifier = 1.5;
        } else {
            day = DayType.BLOOD;
            server.broadcastMessage("Hide.");
            for (int i = 0; i < playerList.length; i++) {
                playerList[i].playSound(playerList[i], Sound.AMBIENT_CAVE, 1.0f, 1.0f);
            }
            bobuxModifier = 1.0;
        }
    }

    public static DayType getDay() {
        return day;
    }

    public static double getBobuxModifier() {
        return bobuxModifier;
    }

    public static double getGoodSpawnModifier() {
        return goodSpawnModifier;
    }

    public static double getBadSpawnModifier() {
        return badSpawnModifier;
    }

}
