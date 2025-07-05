package io.github.mxylery.bobuxplugin.player;

import java.io.Serializable;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.items.BobuxAttributeSet;

//This will be used to save and load temporary attributes for players. Make sure to make another serializable class with hashmaps that save and load these whenever they quit/server shuts down and shit
//HashMap for UUIDs and 
public class TempAttribute implements Serializable {
    
    private transient Attribute attribute;
    private AttributeModifier modifier;
    private String attributeName;
    private long timeLeft;

    public TempAttribute(BobuxAttributeSet bobuxAttributeSet, long duration) {
        this.attribute = bobuxAttributeSet.getAttribute();
        this.modifier = bobuxAttributeSet.getModifier();
        this.timeLeft = duration;
        this.attributeName = setAttributeType();
    }

    private String setAttributeType() {
        if (attribute.equals(Attribute.ARMOR)) {
            return "ARMOR";
        } else if (attribute.equals(Attribute.ARMOR_TOUGHNESS)) {
            return "ARMOR_TOUGHNESS";
        } else if (attribute.equals(Attribute.ATTACK_DAMAGE)) {
            return "ATTACK_DAMAGE";
        } else if (attribute.equals(Attribute.ATTACK_KNOCKBACK)) {
            return "ATTACK_KNOCKBACK";
        } else if (attribute.equals(Attribute.ATTACK_SPEED)) {
            return "ATTACK_SPEED"; 
        } else if (attribute.equals(Attribute.BLOCK_BREAK_SPEED)) {
            return "BLOCK_BREAK_SPEED";
        } else if (attribute.equals(Attribute.BLOCK_INTERACTION_RANGE)) {
            return "BLOCK_INTERACTION_RANGE";
        } else if (attribute.equals(Attribute.BURNING_TIME)) {
            return "BURNING_TIME";
        } else if (attribute.equals(Attribute.ENTITY_INTERACTION_RANGE)) {
            return "ENTITY_INTERACTION_RANGE";
        } else if (attribute.equals(Attribute.EXPLOSION_KNOCKBACK_RESISTANCE)) {
            return "EXPLOSION_KNOCKBACK_RESISTANCE";
        } else if (attribute.equals(Attribute.FALL_DAMAGE_MULTIPLIER)) {
            return "FALL_DAMAGE_MULTIPLIER";
        } else if (attribute.equals(Attribute.FLYING_SPEED)) {
            return "FLYING_SPEED";
        } else if (attribute.equals(Attribute.FOLLOW_RANGE)) {
            return "FOLLOW_RANGE";
        } else if (attribute.equals(Attribute.GRAVITY)) {
            return "GRAVITY";
        } else if (attribute.equals(Attribute.JUMP_STRENGTH)) {
            return "JUMP_STRENGTH";
        } else if (attribute.equals(Attribute.KNOCKBACK_RESISTANCE)) {
            return "KNOCKBACK_RESISTANCE";
        } else if (attribute.equals(Attribute.LUCK)) {
            return "LUCK";
        } else if (attribute.equals(Attribute.MAX_ABSORPTION)) {
            return "MAX_ABSORPTION";
        } else if (attribute.equals(Attribute.MAX_HEALTH)) {
            return "MAX_HEALTH";
        } else if (attribute.equals(Attribute.MINING_EFFICIENCY)) {
            return "MINING_EFFICIENCY";
        } else if (attribute.equals(Attribute.MOVEMENT_EFFICIENCY)) {
            return "MAX_ABSORPTION";
        } else if (attribute.equals(Attribute.MOVEMENT_SPEED)) {
            return "MOVEMENT_SPEED";
        } else if (attribute.equals(Attribute.OXYGEN_BONUS)) {
            return "OXYGEN_BONUS";
        } else if (attribute.equals(Attribute.SAFE_FALL_DISTANCE)) {
            return "SAFE_FALL_DISTANCE";
        } else if (attribute.equals(Attribute.SCALE)) {
            return "SCALE";
        } else {
            return "MOVEMENT_SPEED";
        }
    }

    private void chooseAttributeFromString() {
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
            case "MOVEMENT_SPEED": attribute = Attribute.MOVEMENT_SPEED;
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
            chooseAttributeFromString();
        }
        return attribute;
    }

    public AttributeModifier getModifier() {
        return modifier;
    }

    public void tick() {
        timeLeft--;
    }

    public String toString() {
        String[] parsedUnderscores = attributeName.split("_");
        String finalString = "";
        for (String string : parsedUnderscores) {
            String toAppend = string.substring(0,1) + string.substring(1,string.length()).toLowerCase() + " ";
            finalString = finalString + toAppend;
        }
        long seconds = timeLeft/20;
        int minutes = 0;
        while (seconds >= 60) {
            seconds -= 60;
            minutes++;
        }
        String operationString;
        if (modifier.getOperation() == Operation.ADD_SCALAR) {
            operationString = String.format("%+f%%", modifier.getAmount());
        } else {
            operationString = String.format("%+f", modifier.getAmount());
        }
        finalString = String.format("%s%s (%02d:%02d)", finalString, operationString, minutes, seconds);
        return finalString;
    }

}
