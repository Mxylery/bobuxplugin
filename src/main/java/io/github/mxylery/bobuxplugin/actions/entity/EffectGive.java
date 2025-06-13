package io.github.mxylery.bobuxplugin.actions.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;

public class EffectGive extends BobuxAction {

    private PotionEffectType potionEffect;
    private int length;
    private int strength;

    //The effect strength is the strength + 1 (if you want level 1 effect, make the strength 0)
    public EffectGive(PotionEffectType potionEffect, int length, int strength) {
        this.potionEffect = potionEffect;
        this.length = length;
        this.strength = strength;
        super.requiresEntity = true;
    }

    public void adjustFlat(double adjustment) {
        strength += adjustment;
    }

    public void adjustPerc(double adjustment) {
        length *= adjustment/100 + 1;
    }

    public void run() {
        PotionEffect effect = new PotionEffect(potionEffect, length, strength);
        for (int i = 0; i < super.entityList.length; i++) {
            if (entityList[i] instanceof LivingEntity) {
                LivingEntity effectReceiver = (LivingEntity) entityList[i];
                effectReceiver.addPotionEffect(effect);
            }
        }
    }
}
