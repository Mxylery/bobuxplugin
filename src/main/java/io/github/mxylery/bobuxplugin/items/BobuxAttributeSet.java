package io.github.mxylery.bobuxplugin.items;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;

public class BobuxAttributeSet {
    
    private Attribute attribute;
    private double amount;
    private AttributeModifier.Operation operation;
    private EquipmentSlotGroup equipmentSlotGroup;

    public BobuxAttributeSet(Attribute attribute, double amount, AttributeModifier.Operation operation, EquipmentSlotGroup equipmentSlotGroup) {
        this.attribute = attribute;
        this.amount = amount;
        this.operation = operation;
        this.equipmentSlotGroup = equipmentSlotGroup;
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

}
