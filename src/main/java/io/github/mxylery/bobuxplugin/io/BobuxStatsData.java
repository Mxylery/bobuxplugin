package io.github.mxylery.bobuxplugin.io;

import java.io.File;
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
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.player.BobuxPlayerStats;

public class BobuxStatsData implements Serializable {
    private static transient final long serialVersionUUID = -1681012206529286329L;

    public final HashMap<UUID, BobuxPlayerStats> statMap;

    // Can be used for saving
    public BobuxStatsData(HashMap<UUID, BobuxPlayerStats> statMap) {
        this.statMap = statMap;
    }
    // Can be used for loading
    public BobuxStatsData(BobuxStatsData loadedData) {
        this.statMap = loadedData.statMap;
    }

    public boolean saveData() {
        try {
            String filePath = new File(".").getCanonicalPath().toString();
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath + "\\plugins\\bobux\\playerStats.data")));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } 
    }

    public static BobuxStatsData loadData() {
        try {
            String filePath = new File(".").getCanonicalPath().toString();
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath + "\\plugins\\bobux\\playerStats.data")));
            BobuxStatsData data = (BobuxStatsData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveDataToFile() {
        HashMap<UUID, BobuxPlayerStats> statMap = BobuxPlugin.getPlayerStatMap();
        new BobuxStatsData(statMap).saveData();
        Bukkit.getServer().getLogger().log(Level.INFO, "Data saved");
    }

    public static void loadDataToGame() {
        // Load the data from disc using our loadData method.
        BobuxStatsData data = new BobuxStatsData(loadData());
        BobuxPlugin.setPlayerStatMap(data.statMap);
        Bukkit.getServer().getLogger().log(Level.INFO, "Data loaded");
    }
}
