package io.github.mxylery.bobuxplugin.core;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.conditions.PlayerAbilityInstanceCondition;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;

/* This will be used for the ability manager and to 
* give each bobux item a given ability (defining them 
* in their class def, not a random other managing class)
*/

//NAMING CONVENTION: CAPITALIZE THE NAME OF THE ABILITY THE EXACT SAME WAY AS THE NAME OF THE ITEM THAT IT IS THE ABILITY FOR.
//IF THERE ARE SEVERAL, NUMBER THEM.

//Will requires up to 5 things: User, Entity List, Vector List, Location List, Inventory List
public abstract class BobuxAbility {
    
    protected int actionListLength;
    protected BobuxAction[] actionList;
    protected PlayerAbilityInstanceCondition[] conditions;
    protected Player user;
    protected long cooldown;
    protected Inventory[] inventoryList;
    protected Location[] locationList;
    protected Vector[] vectorList;
    protected Entity[][] targetList;
    protected BobuxRegisterer[] registererList;
    protected String name;
    protected boolean muteCD;
    protected Entity otherEntity;

    public BobuxAbility(String name, boolean muteCD, long cooldown) {
        this.name = name;
        this.muteCD = muteCD;
        this.cooldown = cooldown;
    }

    protected abstract boolean assignVariables();
    public abstract void adjustFlat();
    public abstract void adjustPerc();

    public boolean setActionList() {
        if (assignVariables()) {
            for (int i = 0; i < actionList.length; i++) {
            if (actionList[i].requiresEntity && targetList[i] != null) {
                actionList[i].initializeEntityList(targetList[i]);
            } else if (actionList[i].requiresEntity) {
                return false;
            }
            if (actionList[i].requiresVector && vectorList[i] != null) {
                actionList[i].initializeVector(vectorList[i]);
            } else if (actionList[i].requiresVector) {
                return false;
            }
            if (actionList[i].requiresLocation && locationList[i] != null) {
                actionList[i].initializeLocation(locationList[i]);
            } else if (actionList[i].requiresLocation) {
                return false;
            }
            if (actionList[i].requiresInventory && inventoryList[i] != null) {
                actionList[i].initializeInventory(inventoryList[i]);
            } else if (actionList[i].requiresInventory) {
                return false;
            }
            }   return true;   
        } else {
            return false;
        }
    }

    public void use() {
        for (int i = 0; i < actionList.length; i++) {
            actionList[i].run();
        }
    }

    public long getCooldown() {
        return cooldown;
    }

    public String getName() {
        return name;
    }

    public boolean isMuted() {
        return muteCD;
    }

    public PlayerAbilityInstanceCondition[] getConditionList() {
        return conditions;
    }

    public void setUser(Player user) {
        this.user = user;
    }

    public void setOtherEntity(Entity entity) {
        this.otherEntity = entity;
    }

}
