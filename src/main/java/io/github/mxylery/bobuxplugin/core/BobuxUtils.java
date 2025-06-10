package io.github.mxylery.bobuxplugin.core;

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
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;

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
				if (!player.isDead()) {
					playerList.add(player);				
				}
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

	public static int[] checkTotalItems(Inventory inventory, ItemStack stack) {
        int size = -1;
		int[] slotNums = new int[36];
        for (int i = 0; i < 36; i++) {
            ItemStack currStack = inventory.getItem(i);
            if (currStack != null) {
                if (BobuxUtils.checkWithoutDuraAmnt(currStack, stack)) {
                    size++;
                    slotNums[size] = i;
                } 
            }
        }
		System.out.println("Size: " + size);
        if (size != -1) {
            int[] newSlots = new int[size+1];
            System.arraycopy(slotNums, 0, newSlots, 0, size+1);
            return newSlots;
        } else {
			return null;
		}
    }

	public static void removeTotalItems(Inventory inventory, ItemStack stack, int amount) {
		int[] stackIndexList = checkTotalItems(inventory, stack);
		if (stackIndexList != null) {
			int itemAmnt = amount;
			ItemStack intermStack;
			while (itemAmnt > 0) {
                intermStack = inventory.getItem(stackIndexList[0]);
                int prevAmnt = intermStack.getAmount();
                intermStack.setAmount(prevAmnt - 1);
                prevAmnt--;
                itemAmnt--;
                if (prevAmnt == 0) {
                    for (int i = 0; i < stackIndexList.length-1; i++) {
                        stackIndexList[i] = stackIndexList[i+1];
                    }
                } else {
                    inventory.setItem(stackIndexList[0], intermStack);
                }                
            }
		} else {

		}
	}

	public static Location offsetLocation(Location location, Vector direction, double offset, double verticalOffset) {
		Location newLocation = new Location(location.getWorld(), location.getX(), location.getY() + verticalOffset, location.getZ());
		Vector newVector = new Vector(direction.getX(), direction.getY(), direction.getZ());
		newVector.normalize();
		newLocation.add(newVector.getX()*offset, newVector.getY()*offset, newVector.getZ()*offset);
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

	//do this wehne back
	public static boolean checkWithoutDuraAmnt(ItemStack item1, ItemStack item2) {
		ItemStack tempStack1 = new ItemStack(item1);
		tempStack1.setAmount(1);
		ItemMeta tempMeta1 = tempStack1.getItemMeta();
		ItemStack tempStack2 = new ItemStack(item2);
		tempStack2.setAmount(1);
		ItemMeta tempMeta2 = tempStack2.getItemMeta();
		System.out.println("ItemMeta 1: " + tempMeta1.toString());
		System.out.println("ItemMeta 2: " + tempMeta2.toString());
		if (tempMeta1 instanceof Damageable && tempMeta2 instanceof Damageable) {
			Damageable damageable1 = (Damageable) tempMeta1;
			damageable1.setDamage(0);
			Damageable damageable2 = (Damageable) tempMeta2;
			damageable1.setDamage(0);
			if (damageable1.equals(damageable2)) {
				return true;
			}
			return false;
		}
		if (tempMeta1.equals(tempMeta2)) {
			return true;
		}
		return false;
	}

	//Don't forget to make it so that like when you like when when you like when if its when if its - then unnegative because then its like the other way or like negative it after the normalize
	public static Vector[] getNormalizedMatrix(Vector vector, ParticleSequenceOrientations orientation) {
		Vector[] directionMatrix = new Vector[3];
		Vector tempVector = new Vector(vector.getX(), vector.getY(), vector.getZ());
		tempVector.normalize();
		if (Math.abs(vector.getX()) > Math.abs(vector.getZ())) {
			Vector newXVector = new Vector(0.0,0.0,1.0);
			Vector newYVector = tempVector.getCrossProduct(newXVector);
        	newYVector.normalize();
        	Vector newZVector = tempVector.getCrossProduct(newYVector);
        	newZVector.normalize();
			directionMatrix[0] = tempVector;
			directionMatrix[1] = newYVector;
			directionMatrix[2] = newZVector;
		} else {
			Vector newXVector = new Vector(1.0,0.0,0.0);
			Vector newYVector = newXVector.getCrossProduct(tempVector);
        	newYVector.normalize();
        	Vector newZVector = newYVector.getCrossProduct(tempVector);
        	newZVector.normalize();
			directionMatrix[0] = tempVector;
			directionMatrix[1] = newYVector;
			directionMatrix[2] = newZVector;
		}
		Vector oldX;
		Vector oldY;
		Vector oldZ;
        switch(orientation) {
            case NORMAL: 
            break;
            case DOWN:
            oldX = directionMatrix[0];
            oldY = directionMatrix[1];
            oldZ = directionMatrix[2];
            directionMatrix[0] = oldY;
            directionMatrix[0].multiply(-1);
            directionMatrix[1] = oldX;
            directionMatrix[2] = oldZ;
            break;
            case UP: 
			oldX = directionMatrix[0];
            oldY = directionMatrix[1];
            oldZ = directionMatrix[2];
            directionMatrix[0] = oldY;
            directionMatrix[1] = oldX;
			directionMatrix[1].multiply(-1);
            directionMatrix[2] = oldZ;
			break;
			case RIGHT:
            oldX = directionMatrix[0];
            oldY = directionMatrix[1];
            oldZ = directionMatrix[2];
            directionMatrix[0] = oldZ;
            directionMatrix[1] = oldY;
            directionMatrix[2] = oldX;
			directionMatrix[2].multiply(-1);
            break;
			case LEFT:
            oldX = directionMatrix[0];
            oldY = directionMatrix[1];
            oldZ = directionMatrix[2];
            directionMatrix[0] = oldZ;
			directionMatrix[0].multiply(-1);
            directionMatrix[1] = oldY;
            directionMatrix[2] = oldX;
            default:
            break;
        }
        return directionMatrix;
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

	

	public static int calculateTotalBBX(Inventory inventory) {
        int bbxTotal = 0;
        for (int i = 0; i < 36; i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack != null) {
                if (BobuxUtils.checkWithoutDuraAmnt(stack, BobuxItemInterface.bobux)) {
                bbxTotal += stack.getAmount();
            } else if (BobuxUtils.checkWithoutDuraAmnt(stack, BobuxItemInterface.bobuxSquare)){
                bbxTotal += 8*stack.getAmount();
            } else if (BobuxUtils.checkWithoutDuraAmnt(stack, BobuxItemInterface.bobuxCube)){
                bbxTotal += 64*stack.getAmount();
            } else if (BobuxUtils.checkWithoutDuraAmnt(stack, BobuxItemInterface.bobuxTesseract)){
                bbxTotal += 512*stack.getAmount();
            	}
        	}
    	}
        return bbxTotal;
    }


}
