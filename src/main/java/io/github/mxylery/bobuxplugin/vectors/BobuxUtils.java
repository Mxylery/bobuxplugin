package io.github.mxylery.bobuxplugin.vectors;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.items.BobuxItem;

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
				Player player = (Player) entityList.get(i);
				playerList.add(player);
			}
		}
		return playerList;
	}

	//This is to excluse a center player
	public static ArrayList<Player> getNearbyPlayers(Player player, double radius) {

		Location loc = player.getLocation();
		ArrayList<Entity> entityList = new ArrayList<Entity>();
		entityList = (ArrayList<Entity>) loc.getWorld().getNearbyEntities(loc, radius, radius, radius);
		ArrayList<Player> playerList = new ArrayList<Player>();

		for (int i = 0; i < entityList.size(); i++) {
			if (entityList.get(i) instanceof Player && !entityList.get(i).equals(player)) {
				Player newPlayer = (Player) entityList.get(i);
				playerList.add(newPlayer);
			}
		}
		return playerList;
	}

	public static boolean checkWithoutDuraAmnt(ItemStack item, BobuxItem bobuxitem) {
		item.setAmount(1);
		if (item instanceof Damageable) {
			Damageable damageable = (Damageable) item;
			damageable.setDamage(damageable.getMaxDamage());
			if (damageable == bobuxitem.getStack()) {
			return true;
		}
		return false;
		}
		if (item == bobuxitem.getStack()) {
			return true;
		}
		return false;
	}
}
