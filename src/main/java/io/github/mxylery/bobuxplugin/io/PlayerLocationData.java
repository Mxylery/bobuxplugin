package io.github.mxylery.bobuxplugin.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import jakarta.json.JsonWriter;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.BobuxPlugin;

public class PlayerLocationData {

    public final HashMap<UUID, Location> playerLocMap;

    // Can be used for saving
    public PlayerLocationData(HashMap<UUID, Location> map) {
        this.playerLocMap = map;
    }
    // Can be used for loading
    public PlayerLocationData(PlayerLocationData loadedData) throws FileNotFoundException {
        this.playerLocMap = loadedData.playerLocMap;

    }

    public static void saveDataToFile() {
        HashMap<UUID, Location> playerLocMap = BobuxPlugin.getPlayerLocMap();
        try {
            String filePath = new File(".").getCanonicalPath().toString();
            JsonWriter writer = Json.createWriter(new FileWriter(filePath + "\\plugins\\bobux\\playerLocs.txt"));
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            Object[] playerSet = Bukkit.getServer().getOnlinePlayers().toArray();
            for (Object object : playerSet) {
                if (object instanceof Player) {
                    Player player = (Player) object;
                    UUID uuid = player.getUniqueId();
                    Location location = playerLocMap.get(uuid);
                    if (player.getWorld().equals(BobuxPlugin.getOverworld())) {
                        if (!player.isDead()) {
                            Location playerLoc = player.getLocation();
                            JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                                .add("uuid", player.getUniqueId().toString())
                                .add("x", playerLoc.getX())
                                .add("y", playerLoc.getY())
                                .add("z", playerLoc.getZ());
                            arrayBuilder.add(objectBuilder);
                        } else if (player.getRespawnLocation() != null) {
                            Location respawnLoc = player.getRespawnLocation();
                            JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                                .add("uuid", player.getUniqueId().toString())
                                .add("x", respawnLoc.getX())
                                .add("y", respawnLoc.getY())
                                .add("z", respawnLoc.getZ());
                            arrayBuilder.add(objectBuilder);
                        } else {
                            World overworld = BobuxPlugin.getOverworld();
                            JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                                .add("uuid", player.getUniqueId().toString())
                                .add("x", overworld.getSpawnLocation().getX())
                                .add("y", overworld.getSpawnLocation().getY())
                                .add("z", overworld.getSpawnLocation().getZ());
                            arrayBuilder.add(objectBuilder);
                        }
                    } 
                }
            }
            //Gets all players that were offline
            Set<UUID> offlineSet = playerLocMap.keySet();
            for (UUID uuid : offlineSet) {
                if (!Bukkit.getServer().getOnlinePlayers().contains(Bukkit.getPlayer(uuid))) {
                    Location location = playerLocMap.get(uuid);
                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                        .add("uuid", uuid.toString())
                        .add("x", location.getX())
                        .add("y", location.getY())
                        .add("z", location.getZ());
                    arrayBuilder.add(objectBuilder);
                }
            }
            writer.writeArray(arrayBuilder.build());
            writer.close();
            Bukkit.getServer().getLogger().log(Level.INFO, "Data saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadDataToGame() {
        HashMap<UUID, Location> playerLocMap = new HashMap<UUID, Location>();
        try {
            String filePath = new File(".").getCanonicalPath().toString();

            JsonReader reader = Json.createReader(new FileReader(filePath + "\\plugins\\bobux\\playerLocs.txt"));
            JsonArray fullArray = reader.readArray();

            Iterator<JsonValue> iterator = fullArray.iterator();

            while(iterator.hasNext()) {
                JsonValue value = iterator.next();
                if (value instanceof JsonObject) {
                    JsonObject object = (JsonObject) value;
                    Location location = new Location(BobuxPlugin.getOverworld(), object.getInt("x"), object.getInt("y"), object.getInt("z"));
                    String uuidString = object.getString("uuid");
                    UUID uuid = UUID.fromString(uuidString);
                    playerLocMap.put(uuid, location);
                }
            }

            reader.close();
            BobuxPlugin.setPlayerLocMap(playerLocMap);
            Bukkit.getServer().getLogger().log(Level.INFO, "Data loaded");
        } catch (IOException e) {
            BobuxPlugin.setPlayerLocMap(playerLocMap);
            e.printStackTrace();
        }
    }
}
