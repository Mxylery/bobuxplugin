package io.github.mxylery.bobuxplugin.actions;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;

public class PullTowardsPoint extends BobuxAction {

    private double radius;
    private double strength;
    private double bounce;

    public PullTowardsPoint(double radius, double strength, double bounce, boolean requiresCondition) {
        this.radius = radius;
        this.strength = strength;
        this.bounce = bounce;
        super.requiresCondition = requiresCondition;
        super.requiresEntity = true;
        super.requiresLocation = true;
    }

    @Override
    public void run() {
        for (int i = 0; i < super.entityList.length; i++) {
            if (entityList[i] instanceof LivingEntity) {
                Vector vector = BobuxUtils.getLocationDifference(super.entityList[i].getLocation(), super.location);
                double euclDist = BobuxUtils.getLocationDifferenceMagnitude(entityList[i].getLocation(), super.location);
                double sendStrength = Math.sqrt(Math.max(radius*radius - euclDist*euclDist,0));
                ChangeVelocity velocityAction = new ChangeVelocity(sendStrength, false);
                Entity[] entityAsArray = {entityList[i]};
                vector.add(new Vector(0,bounce,0));
                velocityAction.initializeEntityList(entityAsArray);
                velocityAction.initializeVector(vector);
                velocityAction.run();
            }
        }
    }

    @Override
    public void adjustFlat(double adjustment) {

    }

    @Override
    public void adjustPerc(double adjustment) {

    }
    
}
