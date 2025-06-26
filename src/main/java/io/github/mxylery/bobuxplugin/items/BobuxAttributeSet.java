package io.github.mxylery.bobuxplugin.items;

import java.io.Serializable;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;

import io.github.mxylery.bobuxplugin.core.BobuxTimer;

public class BobuxAttributeSet implements Serializable {
    
    private Attribute attribute;
    private AttributeModifier modifier;
    private double amount;
    private AttributeModifier.Operation operation;
    private EquipmentSlotGroup equipmentSlotGroup;
    private int attributeID;
    public static int attributeIDCount = 0;

    public BobuxAttributeSet(Attribute attribute, double amount, AttributeModifier.Operation operation, EquipmentSlotGroup equipmentSlotGroup) {
        this.attribute = attribute;
        this.amount = amount;
        this.operation = operation;
        this.equipmentSlotGroup = equipmentSlotGroup;
        this.modifier = new AttributeModifier(new NamespacedKey(BobuxTimer.getPlugin(), "" + attributeIDCount), amount, operation, equipmentSlotGroup);
        attributeID = attributeIDCount;
        attributeIDCount++;
    }

    public BobuxAttributeSet(Attribute attribute, double amount, AttributeModifier.Operation operation) {
        this.attribute = attribute;
        this.amount = amount;
        this.operation = operation;
        this.modifier = new AttributeModifier(new NamespacedKey(BobuxTimer.getPlugin(), "" + attributeIDCount), amount, operation, EquipmentSlotGroup.ANY);
        attributeID = attributeIDCount;
        attributeIDCount++;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public double getAmount() {
        return amount;
    }

    public AttributeModifier.Operation getOperation() {
        return operation;
    }

    public EquipmentSlotGroup getEquipmentSlotGroup() {
        return equipmentSlotGroup;
    }

    public AttributeModifier getModifier() {
        return modifier;
    }

}
