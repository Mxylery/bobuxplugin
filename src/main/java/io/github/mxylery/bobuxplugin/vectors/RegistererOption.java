package io.github.mxylery.bobuxplugin.vectors;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class RegistererOption {

        public enum RegistererType {
        LINE,
        SPHERE,
        NONE
        }

        public RegistererType registerType;
        public Location location;
        public double length;
        public double radius;
        public int limit;
        public Vector direction;
        public boolean particleVecConst;

        public RegistererOption(RegistererType registerType, Location location, double length, double radius, int limit, Vector direction) {
            this.registerType = registerType;
            this.location = location;
            this.length = length;
            this.radius = radius;
            this.limit = limit;
            this.direction = direction;
    }
}