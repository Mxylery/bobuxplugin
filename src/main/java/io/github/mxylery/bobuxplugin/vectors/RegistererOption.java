package io.github.mxylery.bobuxplugin.vectors;

import org.bukkit.util.Vector;

/**
 * Used for the construction of bobux entity registerers
 */
public class RegistererOption {

        /**
         * What type of registerer it will be, if any.
         */
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

        public RegistererOption(RegistererType registerType, double length, double radius, int limit, Vector vector) {
            this.registerType = registerType;
            this.length = length;
            this.radius = radius;
            this.limit = limit;
            this.direction = vector;
    }

}