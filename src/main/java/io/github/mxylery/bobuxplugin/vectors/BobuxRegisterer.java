package io.github.mxylery.bobuxplugin.vectors;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.vectors.RegistererOption.RegistererType;

public class BobuxRegisterer {

    private Player player;
    private RegistererOption option;
    private Entity[] entityList;

    //TODO ADD A BOOLEAN THAT DETERMINES IF THE RESGISTERER MUTATES EACH VALUE (e.g if the vector is a constant vector; don't change it; otherwise, use the player vector or a rotation of it);
    //TODO THIS WILL MAKE MOST CASES FINE.

    //ALSO 5/22 REMOVE THE ENTITY LIST AND REMOVE THE OPTION PARAM FOR ANY NON-ENTITY USING REGISTERERS

    public BobuxRegisterer(RegistererOption option, Player player) {
        this.player = player;
        this.option = option;
        this.entityList = null;
        updateTargeting();
    }

    public BobuxRegisterer(Entity[] entityList, Player player) {
        this.player = player;
        this.option = option;
        this.entityList = entityList;
        updateTargeting();
    }

    public RegistererOption getOption() {
        return option;
    }

    public Entity[] getEntityList() {
        return entityList;
    }

    public void updateTargeting() {
        if (option != null) {
            switch (option.registerType) {
                case LINE:
                Location playerLocElevated = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ());
                entityList = getEntitiesLine(playerLocElevated, option.length, option.radius, option.limit, player.getEyeLocation().getDirection());
                break;
                case SPHERE:
                playerLocElevated = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ());
                if (entityList != null) {
                    entityList = getEntitiesSphere(player, entityList[0].getLocation(), option.radius, option.length, player.getEyeLocation().getDirection());                
                } else {
                    playerLocElevated = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ());
                    entityList = getEntitiesSphere(player, playerLocElevated, option.radius, option.length, player.getEyeLocation().getDirection());
                }
                break;
                case NONE:
                break;
            }
        }
    }
    

    private Entity[] getEntitiesLine(Location location, double length, double radius, int limit, Vector direction) {
		ArrayList<Entity> firstList = (ArrayList<Entity>) location.getWorld().getNearbyEntities(location, length, length, length);
        firstList.remove(player);
		Vector tempDirection = new Vector(direction.getX(), direction.getY(), direction.getZ());
		tempDirection.normalize();
		ArrayList<Entity> finalList = new ArrayList<Entity>();
		for (int i = 0; i < firstList.size(); i++) {
			Entity currentEntity = firstList.get(i);
			double tempVecLength = BobuxUtils.getLocationDifferenceMagnitude(currentEntity.getLocation(), location);
			Location tempLoc = new Location
			(location.getWorld(), 
			location.getX() + tempDirection.getX()*tempVecLength,
			location.getY() + tempDirection.getY()*tempVecLength,
			location.getZ() + tempDirection.getZ()*tempVecLength);
			double euclDist = BobuxUtils.getLocationDifferenceMagnitude(currentEntity.getLocation(), tempLoc);
			if (euclDist < radius) {
				finalList.add(currentEntity);
			}
		}
		if (finalList.size() >= 1) {
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
    private Entity[] getEntitiesCone(Entity entity, double angle, double length, Vector direction) {
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

	//doesnt work for now dont use
	private Entity[] getEntitiesSphere(Player player, Location loc, double radius, double offset, Vector direction) {

        Vector tempDirection = new Vector(direction.getX(), direction.getY(), direction.getZ());
        Location tempLocation = new Location(player.getWorld(), loc.getX(), loc.getY(), loc.getZ());

        System.out.println("Location: " + tempLocation);
        System.out.println("Direction: " + tempDirection);

		tempDirection.multiply(offset);
		tempLocation.add(tempDirection);

		ArrayList<Entity> entityList = (ArrayList<Entity>) tempLocation.getWorld().getNearbyEntities(tempLocation, radius+offset, radius+offset, radius+offset);
        int size = -1;
        Entity[] intermList = new Entity[entityList.size()];
        for (int i = 0; i < entityList.size(); i++) {
            //If euclidean distances less than radius
            if (BobuxUtils.getLocationDifferenceMagnitude(tempLocation, entityList.get(i).getLocation()) < radius) {
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

    public static Entity[] getEntitiesLine(Player paramPlayer, Location location, double length, double radius, int limit, Vector direction, boolean junk) {
        ArrayList<Entity> firstList = (ArrayList<Entity>) location.getWorld().getNearbyEntities(location, length, length, length);
        firstList.remove(paramPlayer);
		Vector tempDirection = new Vector(direction.getX(), direction.getY(), direction.getZ());
		tempDirection.normalize();
		ArrayList<Entity> finalList = new ArrayList<Entity>();
		for (int i = 0; i < firstList.size(); i++) {
			Entity currentEntity = firstList.get(i);
			double tempVecLength = BobuxUtils.getLocationDifferenceMagnitude(currentEntity.getLocation(), location);
			Location tempLoc = new Location
			(location.getWorld(), 
			location.getX() + tempDirection.getX()*tempVecLength,
			location.getY() + tempDirection.getY()*tempVecLength,
			location.getZ() + tempDirection.getZ()*tempVecLength);
			double euclDist = BobuxUtils.getLocationDifferenceMagnitude(currentEntity.getLocation(), tempLoc);
			if (euclDist < radius) {
				finalList.add(currentEntity);
			}
		}
		if (finalList.size() >= 1) {
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

}
