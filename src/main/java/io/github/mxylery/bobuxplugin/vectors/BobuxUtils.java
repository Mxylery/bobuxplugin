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
		
		return Math.abs(result);
		
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
		ItemStack tempStack = new ItemStack(item);
		tempStack.setAmount(1);
		if (tempStack instanceof Damageable && bobuxitem.getStack() instanceof Damageable) {
			Damageable damageable = (Damageable) item;
			damageable.setDamage(damageable.getMaxDamage());
			if (damageable.equals((Damageable) bobuxitem.getStack())) {
			return true;
		}
		return false;
		}
		if (tempStack.equals(bobuxitem.getStack())) {
			return true;
		}
		return false;
	}

	public static Vector[] getNormalizedMatrix(Vector vector) {
        //The normalized matrix will be as if the player was facing straight up.
        Vector normalXVector = new Vector(1.0,0.0,0.0);
		vector.normalize();
        Vector newYVector = vector.crossProduct(normalXVector);
        newYVector.normalize();
        Vector newZVector = vector.crossProduct(newYVector);
        newZVector.normalize();
        Vector[] matrix = {vector, newYVector, newZVector};
        return matrix;
    }

	public static Entity[] getEntitiesSphere(Entity entity, double radius) {
        ArrayList<Entity> entityList = (ArrayList<Entity>) entity.getNearbyEntities(radius, radius, radius);
        int size = -1;
        Entity[] intermList = new Entity[entityList.size()];
        for (int i = 0; i < entityList.size(); i++) {
            //If euclidean distances less than radius
            if (BobuxUtils.getLocationDifferenceMagnitude(entity.getLocation(), entityList.get(i).getLocation()) < radius) {
                size++;
                intermList[size] = entityList.get(i);
            }
        }
        Entity[] finalList = new Entity[size+1];
        System.arraycopy(intermList, 0, finalList, 0, size+1);
        return finalList;
    }

	public static Entity[] getEntitiesHemisphere(Entity entity, double radius) {
		return null;
	}

	   //Angle in degrees
    public static Entity[] getEntitiesCone(Entity entity, double angle, double length, Vector direction, Player player) {
        if (angle >= 180) {
            return null;
        } else {
            ArrayList<Entity> entityList = (ArrayList<Entity>) entity.getNearbyEntities(length, length, length);
            direction.normalize();
            Entity[] intermediateList = new Entity[entityList.size()];
            int size = -1;
            //Rotating the vector around any axis should give the same max distance
            Vector maxVectorDirection = direction.rotateAroundAxis(direction, angle/2);
            //Makes up for the length loss during the rotation
            maxVectorDirection.multiply(1/Math.cos(angle/2));
            for (int i = 0; i < entityList.size(); i++) {
                double distance = BobuxUtils.getLocationDifferenceMagnitude(entity.getLocation(), entityList.get(i).getLocation());
                double entityX = entity.getLocation().getX();
                double entityY = entity.getLocation().getY();
                double entityZ = entity.getLocation().getZ();
                Location maxLocation = new Location (entity.getWorld(), 
                maxVectorDirection.getX()*distance + entityX,
                maxVectorDirection.getY()*distance + entityY,
                maxVectorDirection.getZ()*distance + entityZ);
                Location directionLocation = new Location (entity.getWorld(), 
                direction.getX()*distance + entityX,
                direction.getY()*distance + entityY,
                direction.getZ()*distance + entityZ);
                if (BobuxUtils.getLocationDifferenceMagnitude(entityList.get(i).getLocation(), maxLocation) <
                BobuxUtils.getLocationDifferenceMagnitude(maxLocation, directionLocation)) {
                    size++;
                    intermediateList[size] = entityList.get(i);
                }
            }
            Entity[] finalList = new Entity[size+1];
            System.arraycopy(intermediateList, 0, finalList, 0, size+1);
            return finalList;
        }
    }

	 

    public static Entity[] getEntitiesLine() {
        return null;
    }

    public static Entity[] getEntitiesRectangle(Entity entity, double length, double width, double height, double offset, Location location, Vector direction, Player player) {
		
		Vector[] matrix = getNormalizedMatrix(direction);

		Vector lengthVector = matrix[0];
		Vector heightVector = matrix[1];
		Vector widthVector = matrix[2];

		//workout the matrix stuff soon, shouldnt be too bad
		Location newLocation = new Location
		(entity.getWorld(), 
		location.getX() + lengthVector.length()*offset, 
		location.getY() + heightVector.length()*offset,
		location.getZ() + widthVector.length()*offset);

		Entity[] entityList = (Entity[]) entity.getWorld().getNearbyEntities
		(newLocation, 
		length*lengthVector.length(), 
		height*heightVector.length(), 
		width*widthVector.length()).toArray();

		if (player != null) {
			for (int i = 0; i < entityList.length; i++) {

			}
		}
		return null;
    }

}
