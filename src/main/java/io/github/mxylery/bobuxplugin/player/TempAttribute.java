package io.github.mxylery.bobuxplugin.player;

import java.io.Serializable;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.items.BobuxAttributeSet;

//This will be used to save and load temporary attributes for players. Make sure to make another serializable class with hashmaps that save and load these whenever they quit/server shuts down and shit
//HashMap for UUIDs and 
public class TempAttribute implements Serializable {
    
    private transient Attribute attribute;
    private AttributeModifier modifier;
    private String attributeName;
    private long timeLeft;

    public TempAttribute(BobuxAttributeSet bobuxAttributeSet, Player player, long duration) {
        this.attribute = bobuxAttributeSet.getAttribute();
        this.modifier = bobuxAttributeSet.getModifier();
        this.timeLeft = duration;
        setAttributeType();
    }

    private void setAttributeType() {
        if (attribute.equals(Attribute.ARMOR)) {
            attributeName = "ARMOR";
        } else if (attribute.equals(Attribute.ARMOR_TOUGHNESS)) {
            attributeName = "ARMOR_TOUGHNESS";
        } else if (attribute.equals(Attribute.ATTACK_DAMAGE)) {
            attributeName = "ATTACK_DAMAGE";
        } else if (attribute.equals(Attribute.ATTACK_KNOCKBACK)) {
            attributeName = "ATTACK_KNOCKBACK";
        } else if (attribute.equals(Attribute.ATTACK_SPEED)) {
            attributeName = "ATTACK_SPEED"; 
        } else if (attribute.equals(Attribute.BLOCK_BREAK_SPEED)) {
            attributeName = "BLOCK_BREAK_SPEED";
        } else if (attribute.equals(Attribute.BLOCK_INTERACTION_RANGE)) {
            attributeName = "BLOCK_INTERACTION_RANGE";
        } else if (attribute.equals(Attribute.BURNING_TIME)) {
            attributeName = "BURNING_TIME";
        } else if (attribute.equals(Attribute.ENTITY_INTERACTION_RANGE)) {
            attributeName = "ENTITY_INTERACTION_RANGE";
        } else if (attribute.equals(Attribute.EXPLOSION_KNOCKBACK_RESISTANCE)) {
            attributeName = "EXPLOSION_KNOCKBACK_RESISTANCE";
        } else if (attribute.equals(Attribute.FALL_DAMAGE_MULTIPLIER)) {
            attributeName = "FALL_DAMAGE_MULTIPLIER";
        } else if (attribute.equals(Attribute.FLYING_SPEED)) {
            attributeName = "FLYING_SPEED";
        } else if (attribute.equals(Attribute.FOLLOW_RANGE)) {
            attributeName = "FOLLOW_RANGE";
        } else if (attribute.equals(Attribute.GRAVITY)) {
            attributeName = "GRAVITY";
        } else if (attribute.equals(Attribute.JUMP_STRENGTH)) {
            attributeName = "JUMP_STRENGTH";
        } else if (attribute.equals(Attribute.KNOCKBACK_RESISTANCE)) {
            attributeName = "KNOCKBACK_RESISTANCE";
        } else if (attribute.equals(Attribute.LUCK)) {
            attributeName = "LUCK";
        } else if (attribute.equals(Attribute.MAX_ABSORPTION)) {
            attributeName = "MAX_ABSORPTION";
        } else if (attribute.equals(Attribute.MAX_HEALTH)) {
            attributeName = "MAX_HEALTH";
        } else if (attribute.equals(Attribute.MINING_EFFICIENCY)) {
            attributeName = "MINING_EFFICIENCY";
        } else if (attribute.equals(Attribute.MOVEMENT_EFFICIENCY)) {
            attributeName = "MAX_ABSORPTION";
        } else if (attribute.equals(Attribute.MOVEMENT_SPEED)) {
            attributeName = "MOVEMENT_SPEED";
        } else if (attribute.equals(Attribute.OXYGEN_BONUS)) {
            attributeName = "OXYGEN_BONUS";
        } else if (attribute.equals(Attribute.SAFE_FALL_DISTANCE)) {
            attributeName = "SAFE_FALL_DISTANCE";
        } else if (attribute.equals(Attribute.SCALE)) {
            attributeName = "SCALE";
        } 
    }

    private void chooseAttributeType() {
        switch(attributeName) {
            case "ARMOR": attribute = Attribute.ARMOR;
            break;
            case "ARMOR_TOUGHNESS": attribute = Attribute.ARMOR_TOUGHNESS;
            break;
            case "ATTACK_DAMAGE": attribute = Attribute.ATTACK_DAMAGE;
            break;
            case "ATTACK_KNOCKBACK": attribute = Attribute.ATTACK_KNOCKBACK;
            break;
            case "ATTACK_SPEED": attribute = Attribute.ATTACK_SPEED;
            break;
            case "BLOCK_BREAK_SPEED": attribute = Attribute.BLOCK_BREAK_SPEED;
            break;
            case "BLOCK_INTERACTION_RANGE": attribute = Attribute.BLOCK_INTERACTION_RANGE;
            break;
            case "BURNING_TIME": attribute = Attribute.BURNING_TIME;
            break;
            case "ENTITY_INTERACTION_RANGE": attribute = Attribute.ENTITY_INTERACTION_RANGE;
            break;
            case "EXPLOSION_KNOCKBACK_RESISTANCE": attribute = Attribute.EXPLOSION_KNOCKBACK_RESISTANCE;
            break;
            case "FALL_DAMAGE_MULTIPLIER": attribute = Attribute.FALL_DAMAGE_MULTIPLIER;
            break;
            case "FLYING_SPEED": attribute = Attribute.FLYING_SPEED;
            break;
            case "FOLLOW_RANGE": attribute = Attribute.FOLLOW_RANGE;
            break;
            case "GRAVITY": attribute = Attribute.GRAVITY;
            break;
            case "JUMP_STRENGTH": attribute = Attribute.JUMP_STRENGTH;
            break;
            case "KNOCKBACK_RESISTANCE": attribute = Attribute.KNOCKBACK_RESISTANCE;
            break;
            case "LUCK": attribute = Attribute.LUCK;
            break;
            case "MAX_ABSORPTION": attribute = Attribute.MAX_ABSORPTION;
            break;
            case "MAX_HEALTH": attribute = Attribute.MAX_HEALTH;
            break;
            case "MINING_EFFICIENCY": attribute = Attribute.MINING_EFFICIENCY;
            break;
            case "MOVEMENT_EFFICIENCY": attribute = Attribute.MOVEMENT_EFFICIENCY;
            break;
            case "OXYGEN_BONUS": attribute = Attribute.OXYGEN_BONUS;
            break;
            case "SAFE_FALL_DISTANCE": attribute = Attribute.SAFE_FALL_DISTANCE;
            break;
            case "SCALE": attribute = Attribute.SCALE;
            break;
            case "SNEAKING_SPEED": attribute = Attribute.SNEAKING_SPEED;
            break;
            case "SPAWN_REINFORCEMENTS": attribute = Attribute.SPAWN_REINFORCEMENTS;
            break;
            case "STEP_HEIGHT": attribute = Attribute.STEP_HEIGHT;
            break;
            case "SUBMERGED_MINING_SPEED": attribute = Attribute.SUBMERGED_MINING_SPEED;
            break;
            case "SWEEPING_DAMAGE_RATIO": attribute = Attribute.SWEEPING_DAMAGE_RATIO;
            break;
            case "TEMPT_RANGE": attribute = Attribute.TEMPT_RANGE;
            break;
            case "WATER_MOVEMENT_EFFICIENCY": attribute = Attribute.WATER_MOVEMENT_EFFICIENCY;
            break;
        }
    }

    public long getTimeLeft() {
        return timeLeft;
    }

    public Attribute getAttribute() {
        if (attribute == null) {
            chooseAttributeType();
        }
        return attribute;
    }

    public AttributeModifier getModifier() {
        return modifier;
    }

    public void tick() {
        timeLeft--;
    }

}
