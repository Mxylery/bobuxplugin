package io.github.mxylery.bobuxplugin.vectors;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class BobuxUtils {
    
    public static Vector getLocationDifference(Location loc1, Location loc2) {
		
		Vector result = new Vector(loc2.getX() - loc1.getX(), loc2.getY() - loc1.getY(), loc2.getZ() - loc1.getZ());
		
		return result;
		
	}

    public static Double getLocationDifferenceMagnitude(Location loc1, Location loc2) {
		
		double result = Math.sqrt(loc2.getX() - loc1.getX() + loc2.getY() - loc1.getY() + loc2.getZ() - loc1.getZ());
		
		return result;
		
	}

	/**
	 * Retrieves all nearby players from a location (in a box) into an array list
	 */
	public static ArrayList<Player> getNearbyPlayers(Location loc, double radius) {

		ArrayList<Entity> entityList = new ArrayList<Entity>();
		entityList = (ArrayList<Entity>) loc.getWorld().getNearbyEntities(loc, radius, radius, radius);
		ArrayList<Player> playerList = new ArrayList<Player>();

		for (int i = 0; i < entityList.size(); i++) {
			if (entityList.get(i) instanceof Player) {
				Player player = (Player) playerList.get(i);
				playerList.add(player);
			}
		}

		return playerList;
	}
}
