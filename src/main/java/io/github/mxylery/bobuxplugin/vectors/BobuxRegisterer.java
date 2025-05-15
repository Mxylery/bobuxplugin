package io.github.mxylery.bobuxplugin.vectors;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

public class BobuxRegisterer {
    
    public Entity[] getEntitiesSphere(Entity entity, double radius) {
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

    //Angle in degrees
    public Entity[] getEntitiesCone(Entity entity, double angle, double length, Vector direction) {
        if (angle >= 180) {
            return getEntitiesRectangle(entity, length, length, length, 0, direction);
        } else {
            ArrayList<Entity> entityList = (ArrayList<Entity>) entity.getNearbyEntities(length, length, length);
            direction.normalize();
            Entity[] intermediateList = new Entity[entityList.size()];
            int size = -1;
            //Rotating the vector around any axis should give the same max distance
            Vector maxVectorDirection = direction.rotateAroundX(angle/2);
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

    public Entity[] getEntitiesLine() {
        return null;
    }

    public Entity[] getEntitiesRectangle(Entity entity, double length, double width, double height, double offset, Vector direction) {
        return null;
    }


}
