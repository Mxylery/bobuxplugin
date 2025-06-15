package io.github.mxylery.bobuxplugin.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
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

    public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream("playerLocs.data")));
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
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream("playerLocs.data")));
            PlayerLocationData data = (PlayerLocationData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

     public static void saveDataToFile(Player player) {
        HashMap<UUID, Location> playerLocMap = new HashMap<UUID, Location>();
        
        // Bukkit.getServer().getOnlinePlayers().forEach(player -> playerLocMap.put(player.getUniqueId(), player.getLocation()));    

        new PlayerLocationData(playerLocMap).saveData("playerLocs.data");
        Bukkit.getServer().getLogger().log(Level.INFO, "Data Saved");
    }

    public static void loadDataToGame() {
        // Load the data from disc using our loadData method.
        PlayerLocationData data = new PlayerLocationData(loadData());
        Bukkit.getServer().getLogger().log(Level.INFO, "Data loaded");
        
    }
}
