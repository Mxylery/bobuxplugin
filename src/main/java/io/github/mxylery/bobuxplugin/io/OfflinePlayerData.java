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
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.guis.raffle.BobuxRaffle;
import io.github.mxylery.bobuxplugin.player.BobuxPlayerStats;
import io.github.mxylery.bobuxplugin.player.TempAttribute;

public class OfflinePlayerData implements Serializable {
    private static transient final long serialVersionUUID = -1681012206529286329L;

    public final HashMap<UUID, ItemStack> offlinePlayerStacks;
    public final HashMap<UUID, TempAttribute> offlinePlayerAttributes;

    // Can be used for saving
    public OfflinePlayerData(HashMap<UUID, ItemStack> offlinePlayerStacks, HashMap<UUID, TempAttribute> offlinePlayerAttributes) {
        this.offlinePlayerStacks = offlinePlayerStacks;
        this.offlinePlayerAttributes = offlinePlayerAttributes;
    }
    // Can be used for loading
    public OfflinePlayerData(OfflinePlayerData loadedData) {
        this.offlinePlayerStacks = loadedData.offlinePlayerStacks;
        this.offlinePlayerAttributes = loadedData.offlinePlayerAttributes;
    }

    public boolean saveData() {
        try {
            String filePath = new File(".").getCanonicalPath().toString();
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath + "\\plugins\\bobux\\offline.data")));
            out.writeObject(this);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } 
    }

    public static OfflinePlayerData loadData() {
        try {
            String filePath = new File(".").getCanonicalPath().toString();
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath + "\\plugins\\bobux\\offline.data")));
            OfflinePlayerData data = (OfflinePlayerData) in.readObject();
            in.close();
            return data;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveDataToFile() {
        HashMap<UUID, ItemStack> stacks = BobuxPlugin.getRaffle().getOfflineStacks();
        HashMap<UUID, TempAttribute> attributes = BobuxPlugin.getRaffle().getOfflineAttributeMap();
        new OfflinePlayerData(stacks, attributes).saveData();
        Bukkit.getServer().getLogger().log(Level.INFO, "Data saved");
    }

    public static void loadDataToGame() {
        // Load the data from disc using our loadData method.
        OfflinePlayerData data = new OfflinePlayerData(loadData());
        BobuxRaffle raffle = BobuxPlugin.getRaffle();
        raffle.setOfflineAttributeMap(data.offlinePlayerAttributes);
        raffle.setOfflineStacks(data.offlinePlayerStacks);
        Bukkit.getServer().getLogger().log(Level.INFO, "Data loaded");
    }
}
