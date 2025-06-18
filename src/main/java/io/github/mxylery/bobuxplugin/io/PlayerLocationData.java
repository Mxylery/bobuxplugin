package io.github.mxylery.bobuxplugin.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import io.github.mxylery.bobuxplugin.BobuxPlugin;

public class PlayerLocationData implements Serializable {
    private static transient final long serialVersionUUID = -1681012206529286330L;

    public final HashMap<UUID, Location> playerLocMap;

    // Can be used for saving
    public PlayerLocationData(HashMap<UUID, Location> map) {
        this.playerLocMap = map;
    }
    // Can be used for loading
    public PlayerLocationData(PlayerLocationData loadedData) {
        this.playerLocMap = loadedData.playerLocMap;
    }

    public boolean saveData() {
        try {
            String filePath = new File(".").getCanonicalPath().toString();
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath + "\\plugins\\bobux\\playerLocs.data")));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } 
    }

    public static PlayerLocationData loadData() {
        try {
            String filePath = new File(".").getCanonicalPath().toString();
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath + "\\plugins\\bobux\\playerLocs.data")));
            PlayerLocationData data = (PlayerLocationData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveDataToFile() {
        HashMap<UUID, Location> playerLocMap = new HashMap<UUID, Location>();
        ArrayList<Player> playerList = new ArrayList<Player>();
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (player.getWorld().equals(BobuxPlugin.getOverworld())) {
                playerList.add(player);
            }
        }

        playerList.forEach(player -> playerLocMap.put(player.getUniqueId(), player.getLocation()));

        for (Player player : playerList) {
            if (player.isDead()) {
                Location respawnLoc = player.getRespawnLocation();
                if (respawnLoc != null) {
                    playerLocMap.put(player.getUniqueId(), BobuxPlugin.getOverworld().getSpawnLocation());
                } else {
                    playerLocMap.put(player.getUniqueId(), respawnLoc);
                }
            }
        }

        new PlayerLocationData(playerLocMap).saveData();
        Bukkit.getServer().getLogger().log(Level.INFO, "Data saved");
    }

    public static void loadDataToGame() {
        // Load the data from disc using our loadData method.
        PlayerLocationData data = new PlayerLocationData(loadData());
        BobuxPlugin.setPlayerLocMap(data.playerLocMap);
        Bukkit.getServer().getLogger().log(Level.INFO, "Data loaded");
    }
}
