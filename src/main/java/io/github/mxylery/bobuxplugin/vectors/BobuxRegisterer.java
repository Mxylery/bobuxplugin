package io.github.mxylery.bobuxplugin.vectors;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.World;
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
    private World world;
    private Vector vector;
    
    public BobuxRegisterer(RegistererOption option, Player player, Class<T> theClass) {
        this.player = player;
        this.option = option;
        this.entityList = null;
        this.otherEntity = null;
        this.theClass = theClass;
        this.world = player.getWorld();
        this.vector = null;
        updateTargeting();
    }

    public BobuxRegisterer(RegistererOption option, Entity entity, Player player, Class<T> theClass) {
        this.player = player;
        this.option = option;
        this.entityList = null;
        this.otherEntity = entity;
        this.theClass = theClass;
        this.world = player.getWorld();
        this.vector = null;
        updateTargeting();
    }

    public BobuxRegisterer(RegistererOption option, Entity entity, Vector vector, Class<T> theClass) {
        this.player = null;
        this.option = option;
        this.entityList = null;
        this.otherEntity = entity;
        this.theClass = theClass;
        this.world = entity.getWorld();
        this.vector = vector;
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
            switch (option.registerType) {
                case LINE:
                if (otherEntity != null && player == null) {
                    entityList = getEntitiesLine(otherEntity, otherEntity.getLocation(), option.length, option.radius, option.limit, vector);
                } else  {
                    Location elevatedPlayerLoc = new Location(world, player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ());
                    entityList = getEntitiesLine(player, elevatedPlayerLoc, option.length, option.radius, option.limit, player.getEyeLocation().getDirection());
                }
                break;
                case SPHERE:
                if (otherEntity != null && player == null) {
                    entityList = getEntitiesSphere(otherEntity, otherEntity.getLocation(), option.radius, option.length, vector);                
                } else {
                    Location elevatedPlayerLoc = new Location(world, player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ());
                    entityList = getEntitiesSphere(player, elevatedPlayerLoc, option.radius, option.length, player.getEyeLocation().getDirection());
                }
                break;
                case NONE:
                break;
            }
        }
    }

    //Removes entity from the list
    private ArrayList<Entity> getEntitiesLine(Entity entityToRemove, Location location, double length, double radius, int limit, Vector direction) {
		ArrayList<Entity> firstList = (ArrayList<Entity>) location.getWorld().getNearbyEntities(location, length, length, length);
        firstList.remove(entityToRemove);
		Vector tempDirection = new Vector(direction.getX(), direction.getY(), direction.getZ());
		tempDirection.normalize();
        ArrayList<Entity> finalList = new ArrayList<Entity>();
		for (int i = 0; i < firstList.size(); i++) {
			Entity currentEntity = firstList.get(i);
            if (!theClass.isInstance(currentEntity)) {
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

    //Removes the entity from the list;
	private ArrayList<Entity> getEntitiesSphere(Entity entity, Location loc, double radius, double offset, Vector direction) {

        Vector tempDirection = new Vector(direction.getX(), direction.getY(), direction.getZ());
        Location tempLocation = new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
        ArrayList<Entity> firstList = (ArrayList<Entity>) tempLocation.getWorld().getNearbyEntities(tempLocation, radius+offset, radius+offset, radius+offset);
        ArrayList<Entity> finalList = new ArrayList<Entity>();

		tempDirection.multiply(offset);
		tempLocation.add(tempDirection);
		
        for (int i = 0; i < firstList.size(); i++) {
            //If euclidean distances less than radius
            Entity currentEntity = firstList.get(i);
            if (!theClass.isInstance(currentEntity) || currentEntity.equals(entity)) {
                continue;
            }
            if (BobuxUtils.getLocationDifferenceMagnitude(tempLocation, firstList.get(i).getLocation()) < radius) {
                finalList.add(currentEntity);
                continue;
            }
        }
		if (finalList.size() == 0) {
			return null;
		} else {
			return finalList;
		}
	}


}
