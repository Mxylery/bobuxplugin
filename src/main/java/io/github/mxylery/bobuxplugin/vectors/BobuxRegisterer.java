package io.github.mxylery.bobuxplugin.vectors;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.core.BobuxUtils;

public class BobuxRegisterer<T> {

    private Player player;
    private RegistererOption option;
    private ArrayList<Entity> entityList;
    private Entity otherEntity;
    private Class<T> theClass;

    //TODO ADD A BOOLEAN THAT DETERMINES IF THE RESGISTERER MUTATES EACH VALUE (e.g if the vector is a constant vector; don't change it; otherwise, use the player vector or a rotation of it);
    //TODO THIS WILL MAKE MOST CASES FINE.

    //ALSO 5/22 REMOVE THE ENTITY LIST AND REMOVE THE OPTION PARAM FOR ANY NON-ENTITY USING REGISTERERS

    //Can i ditch the class<t> in constructor since by declaring bobuxregisterer<t>, im declaring the class type on runtime which then makes Class<T> into whatever the bobuxregisterer<T> was made into and it's defined as is?
    public BobuxRegisterer(RegistererOption option, Player player, Class<T> theClass) {
        this.player = player;
        this.option = option;
        this.entityList = null;
        this.otherEntity = null;
        this.theClass = theClass;
        updateTargeting();
    }

    public BobuxRegisterer(RegistererOption option, Entity entity, Player player, Class<T> theClass) {
        this.player = player;
        this.option = option;
        this.entityList = null;
        this.otherEntity = entity;
        this.theClass = theClass;
        updateTargeting();
    }

    public RegistererOption getOption() {
        return option;
    }

    public Entity[] getEntityList() {
        if (entityList != null) {
            if (entityList.size() != 0) {
                Entity[] entityArray = new Entity[entityList.size()];
                for (int i = 0; i < entityList.size(); i++) {
                    entityArray[i] = entityList.get(i);
                }
                return entityArray;            
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void updateTargeting() {
        if (option != null) {
            Location elevatedPlayerLoc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ());
            switch (option.registerType) {
                case LINE:
                if (otherEntity != null) {
                    entityList = getEntitiesLine(player, otherEntity.getLocation(), option.length, option.radius, option.limit, player.getEyeLocation().getDirection());
                } else {
                    entityList = getEntitiesLine(player, elevatedPlayerLoc, option.length, option.radius, option.limit, player.getEyeLocation().getDirection());
                }
                break;
                case SPHERE:
                if (otherEntity != null) {
                    entityList = getEntitiesSphere(otherEntity.getLocation(), option.radius, option.length, player.getEyeLocation().getDirection());                
                } else {
                    entityList = getEntitiesSphere(elevatedPlayerLoc, option.radius, option.length, player.getEyeLocation().getDirection());
                }
                break;
                case NONE:
                break;
            }
        }
    }

    private ArrayList<Entity> getEntitiesLine(Entity entityToRemove, Location location, double length, double radius, int limit, Vector direction) {
		ArrayList<Entity> firstList = (ArrayList<Entity>) location.getWorld().getNearbyEntities(location, length, length, length);
        firstList.remove(entityToRemove);
		Vector tempDirection = new Vector(direction.getX(), direction.getY(), direction.getZ());
		tempDirection.normalize();
        ArrayList<Entity> finalList = new ArrayList<Entity>();
		for (int i = 0; i < firstList.size(); i++) {
			Entity currentEntity = firstList.get(i);
            if (!theClass.isInstance(currentEntity)) {
                System.out.println("False");
                continue;
            }
            Vector LocToEntityVector = BobuxUtils.getLocationDifference(location, currentEntity.getLocation());
            double angleFactor = Math.cos(tempDirection.angle(LocToEntityVector));
            if (angleFactor == 0) { 
                continue;
            }
			double tempVecLength = BobuxUtils.getLocationDifferenceMagnitude(currentEntity.getLocation(), location);
            Location adjustedLocation = new Location(currentEntity.getWorld(), 
            location.getX() + LocToEntityVector.getX()/angleFactor,
            location.getY() + LocToEntityVector.getY()/angleFactor,
            location.getZ() + LocToEntityVector.getZ()/angleFactor);
			Location tempLoc = new Location
			(location.getWorld(), 
			location.getX() + tempDirection.getX()*tempVecLength,
			location.getY() + tempDirection.getY()*tempVecLength - 0.25,
			location.getZ() + tempDirection.getZ()*tempVecLength);
			double euclDist = BobuxUtils.getLocationDifferenceMagnitude(adjustedLocation, tempLoc);
			if (euclDist < radius) {
				finalList.add(currentEntity);
			}
		}
        if (finalList.size() == 0) {
            return null;
        }
		if (limit == 0) {
			return finalList;
		} else {
            while (finalList.size() > limit) {
                finalList.remove(finalList.size() - 1);
            }
			return finalList;
		}
	}

	private ArrayList<Entity> getEntitiesSphere(Location loc, double radius, double offset, Vector direction) {

        Vector tempDirection = new Vector(direction.getX(), direction.getY(), direction.getZ());
        Location tempLocation = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());

		tempDirection.multiply(offset);
		tempLocation.add(tempDirection);

		ArrayList<Entity> entityList = (ArrayList<Entity>) tempLocation.getWorld().getNearbyEntities(tempLocation, radius+offset, radius+offset, radius+offset);
        for (int i = 0; i < entityList.size(); i++) {
            //If euclidean distances less than radius
            Entity currentEntity = entityList.get(i);
            if (!theClass.getClass().equals(currentEntity.getClass())) {
                entityList.remove(i);
                continue;
            }
            if (BobuxUtils.getLocationDifferenceMagnitude(tempLocation, entityList.get(i).getLocation()) > radius) {
                entityList.remove(currentEntity);
            }
        }
		if (entityList.size() > 0) {
			return entityList;
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
