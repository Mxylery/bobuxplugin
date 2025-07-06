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
import io.github.mxylery.bobuxplugin.guis.raffle.BobuxRaffle;
import io.github.mxylery.bobuxplugin.player.BobuxPlayerStats;

public class RaffleData implements Serializable {
    private static transient final long serialVersionUUID = -1681012206529286329L;

    public final BobuxRaffle raffle;

    // Can be used for saving
    public RaffleData(BobuxRaffle raffle) {
        this.raffle = raffle;
    }
    // Can be used for loading
    public RaffleData(RaffleData loadedData) {
        this.raffle = loadedData.raffle;
    }

    public boolean saveData() {
        try {
            String filePath = new File(".").getCanonicalPath().toString();
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath + "\\plugins\\bobux\\raffle.data")));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } 
    }

    public static RaffleData loadData() {
        try {
            String filePath = new File(".").getCanonicalPath().toString();
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath + "\\plugins\\bobux\\raffle.data")));
            RaffleData data = (RaffleData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            BobuxPlugin.setRaffle(new BobuxRaffle());
            return null;
        }
    }

    public static void saveDataToFile() {
        BobuxRaffle raffle = BobuxPlugin.getRaffle();
        new RaffleData(raffle).saveData();
        Bukkit.getServer().getLogger().log(Level.INFO, "Data saved");
    }

    public static void loadDataToGame() {
        // Load the data from disc using our loadData method.
        RaffleData data = new RaffleData(loadData());
        if (data.raffle == null) {
            BobuxPlugin.setRaffle(new BobuxRaffle());
        } else {
            BobuxPlugin.setRaffle(data.raffle);
        }
        Bukkit.getServer().getLogger().log(Level.INFO, "Data loaded");
    }
}
