package io.github.mxylery.bobuxplugin.vectors;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class BobuxRegisterer {

    private RegistererOption option;
    private Player player;
    private Entity[] entityList;
    private Vector vector;
    private Location location;
    private Vector particleDir;
    private Location particleLoc;
    private Vector[] particleDirArray;
    private Location[] particleLocArray;
    private boolean particleDirConst;

    //TODO ADD A BOOLEAN THAT DETERMINES IF THE RESGISTERER MUTATES EACH VALUE (e.g if the vector is a constant vector; don't change it; otherwise, use the player vector or a rotation of it);
    //TODO THIS WILL MAKE MOST CASES FINE.

    public BobuxRegisterer(RegistererOption option, Player player, Entity[] entityList) {
        this.option = option;
        this.player = player;
        this.entityList = entityList;
        this.vector = null;
        this.location = null;
        this.particleDir = null;
        this.particleLoc = null;
        this.particleDirArray = null;
        this.particleLocArray = null;
    }

    public BobuxRegisterer(RegistererOption option, Player player, Entity[] entityList, Vector vector) {
        this.option = option;
        this.player = player;
        this.entityList = entityList;
        this.vector = vector;
        this.location = null;
        this.particleDir = null;
        this.particleLoc = null;
        this.particleDirArray = null;
        this.particleLocArray = null;
    }
    
    public BobuxRegisterer(RegistererOption option, Player player, Entity[] entityList, Location location) {
        this.option = option;
        this.player = player;
        this.entityList = entityList;
        this.vector = null;
        this.location = location;
        this.particleDir = null;
        this.particleLoc = null;
        this.particleDirArray = null;
        this.particleLocArray = null;
    }

    public BobuxRegisterer(RegistererOption option, Player player, Vector vector, Location location) {
        this.option = option;
        this.player = player;
        this.entityList = null;
        this.vector = vector;
        this.location = location;
        this.particleDir = null;
        this.particleLoc = null;
        this.particleDirArray = null;
        this.particleLocArray = null;
    }

    public BobuxRegisterer(RegistererOption option, Player player, Entity[] entityList, Vector vector, Location location) {
        this.option = option;
        this.player = player;
        this.entityList = entityList;
        this.vector = vector;
        this.location = location;
        this.particleDir = null;
        this.particleLoc = null;
        this.particleDirArray = null;
        this.particleLocArray = null;
    }

    public BobuxRegisterer(RegistererOption option, Player player, Entity[] entityList, Vector vector, Location location, Vector particleDir, Location particleLoc, boolean particleDirConst) {
        this.option = option;
        this.player = player;
        this.entityList = entityList;
        this.vector = vector;
        this.location = location;
        this.particleDir = particleDir;
        this.particleLoc = particleLoc;
        this.particleDirArray = null;
        this.particleLocArray = null;
        this.particleDirConst = particleDirConst;
    }

    public BobuxRegisterer(RegistererOption option, Player player, Entity[] entityList, Vector vector, Location location, Vector[] particleDirArray, Location[] particleLocArray) {
        this.option = option;
        this.player = player;
        this.entityList = entityList;
        this.vector = vector;
        this.location = location;
        this.particleDir = null;
        this.particleLoc = null;
        this.particleDirArray = particleDirArray;
        this.particleLocArray = particleLocArray;
    }

    public Entity[] getEntityList() {
        return entityList;
    }

    public Vector getVector() {
        return vector;
    }

    public Location getLocation() {
        return location;
    }

    public Vector getParticleVector() {
        return particleDir;
    }

    public Location getParticleLocation() {
        return particleLoc;
    }

    public void updateSettings() {
        switch(option.registerType) {
            case LINE: 
            if (entityList != null) {
                Location playerLoc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ());
                entityList = getEntitiesLine(playerLoc, option.length, option.radius, option.limit, player.getEyeLocation().getDirection());
            }
            if (vector != null) {

            }
            if (location != null) {
                location = player.getLocation();
            }
            if (particleDir != null) {
                if (!particleDirConst) {
                    particleDir = player.getEyeLocation().getDirection();
                }
            }
            if (particleLoc != null) {
                Location playerLoc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ());
                particleLoc = playerLoc;
            }
            break;
            case SPHERE:
            break;
            default:
            if (entityList != null) {
                Location playerLoc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ());
                entityList = getEntitiesLine(playerLoc, option.length, option.radius, option.limit, player.getEyeLocation().getDirection());
            }
            if (vector != null) {

            }
            if (location != null) {
                location = player.getLocation();
            }
            if (particleDir != null) {
                if (!particleDirConst) {
                    particleDir = player.getEyeLocation().getDirection();
                }
            }
            if (particleLoc != null) {
                Location playerLoc = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 1, player.getLocation().getZ());
                particleLoc = playerLoc;
            }
            break;
        }
    }

    public static Entity[] getEntitiesLine(Location location, double length, double radius, int limit, Vector direction) {
        System.out.println("X: " + location.getX() + "Y: " + location.getY() + "Z: " + location.getZ());
		ArrayList<Entity> firstList = (ArrayList<Entity>) location.getWorld().getNearbyEntities(location, length, length, length);
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
        System.out.println(finalList.size());
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
    private static Entity[] getEntitiesCone(Entity entity, double angle, double length, Vector direction) {
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
}
