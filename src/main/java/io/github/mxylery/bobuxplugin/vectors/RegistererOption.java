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
        public double length;
        public double radius;
        public int limit;
        public Vector direction;

        public RegistererOption(RegistererType registerType, double length, double radius, int limit, Vector direction) {
            this.registerType = registerType;
            this.length = length;
            this.radius = radius;
            this.limit = limit;
            this.direction = direction;
    }
}