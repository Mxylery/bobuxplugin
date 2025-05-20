package io.github.mxylery.bobuxplugin.vectors;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.items.BobuxItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class BobuxUtils {
    
    public static Vector getLocationDifference(Location loc1, Location loc2) {
		
		Vector result = new Vector(loc2.getX() - loc1.getX(), loc2.getY() - loc1.getY(), loc2.getZ() - loc1.getZ());
		
		return result;
		
	}

    public static Double getLocationDifferenceMagnitude(Location loc1, Location loc2) {
		
		double diffX = loc2.getX() - loc1.getX();
		double diffY = loc2.getY() - loc1.getY();
		double diffZ = loc2.getZ() - loc1.getZ();

		double result = Math.sqrt(diffX*diffX + diffY*diffY + diffZ*diffZ);
		
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

	public static Location offsetLocation(Location location, Vector direction, double offset, double verticalOffset) {
		Location newLocation = new Location(location.getWorld(), location.getX(), location.getY() + verticalOffset, location.getZ());
		newLocation.add(direction.getX()*offset, direction.getY()*offset, direction.getZ()*offset);
		return newLocation;
	}

	public static boolean checkWithoutDuraAmnt(ItemStack item, BobuxItem bobuxitem) {
		ItemStack tempStack = new ItemStack(item);
		tempStack.setAmount(1);
		ItemMeta tempMeta = tempStack.getItemMeta();
		if (tempMeta instanceof Damageable && bobuxitem.getStack().getItemMeta() instanceof Damageable) {
			Damageable damageable = (Damageable) tempMeta;
			damageable.setDamage(0);
			tempStack.setItemMeta(damageable);
			if (damageable.equals((Damageable) bobuxitem.getStack().getItemMeta())) {
			return true;
		}
		return false;
		}
		if (tempStack.equals(bobuxitem.getStack())) {
			return true;
		}
		return false;
	}

	public static boolean checkWithoutDuraAmnt(ItemStack item, ItemStack item2) {
		ItemStack tempStack = new ItemStack(item);
		tempStack.setAmount(1);
		ItemMeta tempMeta = tempStack.getItemMeta();
		if (tempMeta instanceof Damageable && item2.getItemMeta() instanceof Damageable) {
			Damageable damageable = (Damageable) tempMeta;
			damageable.setDamage(0);
			tempStack.setItemMeta(damageable);
			if (damageable.equals((Damageable) item2.getItemMeta())) {
			return true;
		}
		return false;
		}
		if (tempStack.equals(item2)) {
			return true;
		}
		return false;
	}

	//Don't forget to make it so that like when you like when when you like when if its when if its - then unnegative because then its like the other way or like negative it after the normalize
	public static Vector[] getNormalizedMatrix(Vector vector) {
		Vector[] matrix = new Vector[3];
		Vector tempVector = new Vector(vector.getX(), vector.getY(), vector.getZ());
		tempVector.normalize();
		if (Math.abs(vector.getX()) > Math.abs(vector.getZ())) {
			Vector newXVector = new Vector(0.0,0.0,1.0);
			Vector newYVector = tempVector.getCrossProduct(newXVector);
        	newYVector.normalize();
        	Vector newZVector = tempVector.getCrossProduct(newYVector);
        	newZVector.normalize();
			matrix[0] = tempVector;
			matrix[1] = newYVector;
			matrix[2] = newZVector;
		} else {
			Vector newXVector = new Vector(1.0,0.0,0.0);
			Vector newYVector = newXVector.getCrossProduct(tempVector);
        	newYVector.normalize();
        	Vector newZVector = newYVector.getCrossProduct(tempVector);
        	newZVector.normalize();
			matrix[0] = tempVector;
			matrix[1] = newYVector;
			matrix[2] = newZVector;
		}
        return matrix;
    }

	public static Entity[] getEntitiesHemisphere(Entity entity, double radius) {
		return null;
	}

	//this is for ordering mobs based off of eucl dist from another entity, used in the line method two methods down
	//TODO
	public static void entityMerge(Entity[] array, int l, int r, Location location) {
		
		int length = array.length;
		int m = (r-l)/2;
		Entity[] newArray1 = new Entity[length/2];
		for (int i = 0; i < m; i++) {
			newArray1[i] = array[i];
		}
		Entity[] newArray2 = new Entity[(length+1)/2];
		for (int i = 0; i < (2*m+1)/2; i++) {
			newArray1[i] = array[i+m];
		}

		entityMerge(newArray1, 0, m, location);
		entityMerge(newArray2, m+1, r, location);

	}

	public static Entity[] entityMergeSort(Entity[] array, Location location) {
		int l = 0;
		int r = array.length;

		entityMerge(array, l, r, location);
		return array;
	}

	public static Entity[] getEntitiesLine(Location location, double length, double radius, int limit, Vector direction) {
		ArrayList<Entity> firstList = (ArrayList<Entity>) location.getWorld().getNearbyEntities(location, length, length, length);
		Vector tempDirection = new Vector(direction.getX(), direction.getY(), direction.getZ());
		tempDirection.normalize();
		ArrayList<Entity> finalList = new ArrayList<Entity>();
		for (int i = 0; i < firstList.size(); i++) {
			Entity currentEntity = firstList.get(i);
			double tempVecLength = getLocationDifferenceMagnitude(currentEntity.getLocation(), location);
			Location tempLoc = new Location
			(location.getWorld(), 
			location.getX() + tempDirection.getX()*tempVecLength,
			location.getY() + tempDirection.getY()*tempVecLength,
			location.getZ() + tempDirection.getZ()*tempVecLength);
			double euclDist = getLocationDifferenceMagnitude(currentEntity.getLocation(), tempLoc);
			if (euclDist < radius) {
				finalList.add(currentEntity);
			}
		}
		if (finalList.size() > 1) {
			if (limit == 0) {
				Entity[] returnList = new Entity[finalList.size()];
				for (int i = 0; i < returnList.length; i++) {
					returnList[i] = finalList.get(i);
				}
				return returnList;
			} else {
				int biggestNum = Math.min(finalList.size(), limit);
				Entity[] returnList = new Entity[biggestNum];
				for (int i = 0; i < biggestNum; i++) {
					returnList[i] = finalList.get(i);
				}
				return returnList;
			}
		} else {
			return null;
		}
	}

	   //Angle in degrees
    public static Entity[] getEntitiesCone(Entity entity, double angle, double length, Vector direction) {
        if (angle >= 180) {
            return null;
        } else {
            ArrayList<Entity> entityList = (ArrayList<Entity>) entity.getNearbyEntities(length, length, length);
            direction.normalize();
            Entity[] intermediateList = new Entity[entityList.size()];
            int size = -1;
			Vector crossedVector = direction.crossProduct(new Vector(1,0,0));
            //Rotating around perpendicular axis (would be like rotating normally)
            Vector maxVectorDirection = direction.rotateAroundAxis(crossedVector, angle/2);
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
                maxLocation.getY(), //This makes the Y direction not count, so the cone only cares for width/height
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

	public static Entity[] getEntitiesSphere(Entity user, double radius) {
        ArrayList<Entity> entityList = (ArrayList<Entity>) user.getNearbyEntities(radius, radius, radius);
		Location entityLoc = user.getLocation();
        int size = -1;
        Entity[] intermList = new Entity[entityList.size()];
        for (int i = 0; i < entityList.size(); i++) {
            //If euclidean distances less than radius
			Location entityListLoc = entityList.get(i).getLocation();
            if (BobuxUtils.getLocationDifferenceMagnitude(entityLoc, entityListLoc) < radius) {
                size++;
                intermList[size] = entityList.get(i);
            }
        }
		if (size == -1) {
			return null;
		}
        Entity[] finalList = new Entity[size+1];
        System.arraycopy(intermList, 0, finalList, 0, size+1);
        return finalList;
	}


	//doesnt work for now dont use
	public static Entity[] getEntitiesSphere(Player player, Location location, double radius, double offset, Vector direction) {

		direction.multiply(offset);
		location.add(direction);

		ArrayList<Entity> entityList = (ArrayList<Entity>) location.getWorld().getNearbyEntities(location, radius+offset, radius+offset, radius+offset);
        int size = -1;
        Entity[] intermList = new Entity[entityList.size()];
        for (int i = 0; i < entityList.size(); i++) {
            //If euclidean distances less than radius
            if (BobuxUtils.getLocationDifferenceMagnitude(location, entityList.get(i).getLocation()) < radius) {
                size++;
                intermList[size] = entityList.get(i);
            }
        }
        Entity[] finalList = new Entity[size+1];
        System.arraycopy(intermList, 0, finalList, 0, size+1);
		if (size > 0) {
			return finalList;
		} else {
			return null;
		}
	}

	public static int calculateTotalBBX(Inventory inventory) {
        int bbxTotal = 0;
        for (int i = 0; i < 36; i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack != null) {
                if (BobuxUtils.checkWithoutDuraAmnt(stack, BobuxItemInterface.bobux)) {
                bbxTotal += stack.getAmount();
            } else if (BobuxUtils.checkWithoutDuraAmnt(stack, BobuxItemInterface.bobuxSquare)){
                bbxTotal += 4*stack.getAmount();
            } else if (BobuxUtils.checkWithoutDuraAmnt(stack, BobuxItemInterface.bobuxCube)){
                bbxTotal += 16*stack.getAmount();
            } else if (BobuxUtils.checkWithoutDuraAmnt(stack, BobuxItemInterface.bobuxTesseract)){
                bbxTotal += 64*stack.getAmount();
            	}
        	}
    	}
        return bbxTotal;
    }

	//work on later
	public static Entity[] getEntitiesEllipse(Location location, double length, double width, double height, double offset, Vector direction, Entity ignoredEntity) {
		Vector[] matrix = getNormalizedMatrix(direction);

		Vector lengthVector = matrix[0];
		Vector heightVector = matrix[1];
		Vector widthVector = matrix[2];

		Location newLocation = new Location
		(location.getWorld(), 
		location.getX() + lengthVector.length()*offset, 
		location.getY() + heightVector.length()*offset,
		location.getZ() + widthVector.length()*offset);

		double biggestVal = Math.max(length, Math.max(height, width));
		ArrayList<Entity> entityList = (ArrayList<Entity>) ignoredEntity.getNearbyEntities(biggestVal, biggestVal, biggestVal);

		//x^2 + y^2 + z^2 = magnitude (of max); make equation for ax^2 + by^2 + cz^2 = n (depending on ratios), convert x, y, z to normalized coords.
		//Maybe iterate along each axis. maybe express each variable as a ratio of the other ones (like ax^2 = n - by^2 - cz^2?), maybe just need one variable to base off
		//in this case ^^^
		for (int i = 0; i < entityList.size(); i++) {
			Location entityLocation = entityList.get(i).getLocation();
			double euclideanDist = BobuxUtils.getLocationDifferenceMagnitude(newLocation, entityLocation);
			Location normalizedLoc = BobuxUtils.getLocationDifference(newLocation, entityLocation).toLocation(newLocation.getWorld());


		}
		return null;
	}


}
