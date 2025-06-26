package io.github.mxylery.bobuxplugin.abilities;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;

public class AbilityComponent {
    
    private BobuxAction action;
    private Entity[] entityList;
    private Vector vector;
    private Location location;
    private Inventory inventory;
    private AbilityComponent nextComponent;

    public AbilityComponent(BobuxAction action, Entity[] entityList, Vector vector, Location location, Inventory inventory) {
        this.action = action;
        this.entityList = entityList;
        this.vector = vector;
        this.location = location;
        this.inventory = inventory;
        action.initializeEntityList(entityList);
        action.initializeVector(vector);
        action.initializeLocation(location);
        action.initializeInventory(inventory);
    }

    public AbilityComponent(BobuxAction action, Entity[] entityList) {
        this.action = action;
        this.entityList = entityList;
        action.initializeEntityList(entityList);
    }

    public AbilityComponent(BobuxAction action, Entity entity) {
        this.action = action;
        Entity[] list = {entity};
        this.entityList = list;
        action.initializeEntityList(entityList);
    }

    public AbilityComponent(BobuxAction action, Vector vector) {
        this.action = action;
        this.vector = vector;
        action.initializeVector(vector);
    }

    public AbilityComponent(BobuxAction action, Location location) {
        this.action = action;
        this.location = location;
        action.initializeLocation(location);
    }

    public AbilityComponent(BobuxAction action, Inventory inventory) {
        this.action = action;
        this.inventory = inventory;
        action.initializeInventory(inventory);
    }

    public AbilityComponent(BobuxAction action, Entity[] entityList, Vector vector) {
        this.action = action;
        this.entityList = entityList;
        this.vector = vector;
        action.initializeEntityList(entityList);
        action.initializeVector(vector);
    }

    public AbilityComponent(BobuxAction action, Entity entity, Vector vector) {
        this.action = action;
        Entity[] list = {entity};
        this.entityList = list;
        action.initializeEntityList(entityList);
        action.initializeVector(vector);
    }

    public AbilityComponent(BobuxAction action, Entity[] entityList, Location location) {
        this.action = action;
        this.entityList = entityList;
        this.location = location;
        action.initializeEntityList(entityList);
        action.initializeLocation(location);
    }

    public AbilityComponent(BobuxAction action, Entity[] entityList, Vector vector, Location location) {
        this.action = action;
        this.entityList = entityList;
        this.vector = vector;
        this.location = location;
        action.initializeEntityList(entityList);
        action.initializeVector(vector);
        action.initializeLocation(location);
    }

    public AbilityComponent(BobuxAction action, Entity entity, Vector vector, Location location) {
        this.action = action;
        Entity[] list = {entity};
        this.entityList = list;
        this.vector = vector;
        this.location = location;
        action.initializeEntityList(entityList);
        action.initializeVector(vector);
        action.initializeLocation(location);
    }

    public AbilityComponent(BobuxAction action, Vector vector, Location location) {
        this.action = action;
        this.vector = vector;
        this.location = location;
        action.initializeVector(vector);
        action.initializeLocation(location);
    }

    public AbilityComponent(BobuxAction action, Vector vector, Location location, Inventory inventory) {
        this.action = action;
        this.vector = vector;
        this.location = location;
        this.inventory = inventory;
        action.initializeVector(vector);
        action.initializeLocation(location);
        action.initializeInventory(inventory);
    }

    public AbilityComponent(BobuxAction action, Location location, Inventory inventory) {
        this.action = action;
        this.location = location;
        this.inventory = inventory;
        action.initializeLocation(location);
        action.initializeInventory(inventory);
    }

    public void addComponent(AbilityComponent componentToAdd) {
        if (nextComponent == null) {
            nextComponent = componentToAdd;
        } else {
            AbilityComponent temp = nextComponent;
            nextComponent = componentToAdd;
            componentToAdd.nextComponent = temp;
        }
    }

    public AbilityComponent getNextComponent() {
        return nextComponent;
    }

    public void useAction() {
        action.run();
    }


}
