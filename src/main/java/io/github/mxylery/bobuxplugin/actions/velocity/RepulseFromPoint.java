package io.github.mxylery.bobuxplugin.actions.velocity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;

public class RepulseFromPoint extends BobuxAction {

    private double radius;
    private double strength;
    private double bounce;

    public RepulseFromPoint(double radius, double strength, double bounce) {
        this.radius = radius;
        this.strength = strength;
        this.bounce = bounce;
        super.requiresEntity = true;
        super.requiresLocation = true;
    }

    @Override
    public void run() {
        for (int i = 0; i < super.entityList.length; i++) {
            if (entityList[i] instanceof LivingEntity) {
                Vector vector = BobuxUtils.getLocationDifference(super.location, super.entityList[i].getLocation());
                double euclDist = BobuxUtils.getLocationDifferenceMagnitude(entityList[i].getLocation(), super.location);
                double sendStrength = strength*0.1*Math.sqrt(Math.max(radius*radius - euclDist*euclDist,0));
                ChangeVelocity velocityAction = new ChangeVelocity(sendStrength);
                Entity[] entityAsArray = {entityList[i]};
                vector.add(new Vector(0,bounce,0));
                velocityAction.initializeEntityList(entityAsArray);
                velocityAction.initializeVector(vector);
                velocityAction.run();
            }
        }
    }
}
