package io.github.mxylery.bobuxplugin.vectors;

import org.bukkit.Location;
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
}
