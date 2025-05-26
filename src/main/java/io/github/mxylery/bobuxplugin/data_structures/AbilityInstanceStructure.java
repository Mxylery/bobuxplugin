package io.github.mxylery.bobuxplugin.data_structures;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.core.AbilityInstance;

//Basically just a bag, maybe replace with doubly linked list implementation at some point
public class AbilityInstanceStructure {
    
    //In the sentinel node, next and previous are first and last nodes respectively
    private AbilityInstanceStructure sentinel;
    private AbilityInstanceStructure next;
    private AbilityInstanceStructure previous;
    private Entity owner;
    private AbilityInstance abilityInstance;
    private int length;

    public AbilityInstanceStructure(AbilityInstance abilityInstance) {
        this.abilityInstance = abilityInstance;
        this.owner = abilityInstance.getEntity();
        length = 0;
    }

    public AbilityInstanceStructure() {
        length = 0;
    }

    /**
     * This method is used to clear memory of each AbilityInstanceStructure whenever their length reaches above 50 to make sure 
     */
    private void clearMemory() {
        int cull = length;
        AbilityInstanceStructure tempHead = this;
        while(tempHead.previous != this) {
            tempHead = tempHead.previous;
            if (BobuxTimer.getTicksPassed() - tempHead.abilityInstance.getTick() > 1200) {
                break;
            }
            cull++;
        }
        this.next = tempHead;
        length -= cull;
        System.out.print("Culled " + cull + " AbilityInstances.");
    }

    /**
     * Returns the owner of the Entity Ability Structure.
     * @return Owner of the AbilityInstanceStructure.
     */
    public Entity getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the Entity Ability Structure. This should only be used for sentinel nodes.
     * @param Entity The Entity set to be this AbilityInstanceStructure's owner.
     */
    public void setOwner(Entity Entity) {
        owner = Entity;
    }

    /**
     * Sets the given node after the sentinel node.
     * @param AbilityInstance Node to be set.
     */
    public void addAbilityInstanceFirst(AbilityInstance abilityInstance) {
        if (length == 0) {
            AbilityInstanceStructure newElement = new AbilityInstanceStructure(abilityInstance);
            this.sentinel = this;
            this.next = newElement;
            this.previous = newElement;
            newElement.next = sentinel;
            newElement.previous = sentinel;
            length++;
        } else if (length >= 1) {
            AbilityInstanceStructure newFirst = new AbilityInstanceStructure(abilityInstance);
            AbilityInstanceStructure prevFirst = this.next;
            this.next = newFirst;
            prevFirst.previous = newFirst;
            newFirst.previous = this;
            newFirst.next = prevFirst;
            length++;
        } 
        if (length > 100) {
            clearMemory();
        }
    }

    /**
     * Sets the given node before the sentinel node.
     * @param abilityInstance Node to be set.
     */
    public void addabilityInstanceLast(AbilityInstance abilityInstance) {
        if (length == 0) {
            AbilityInstanceStructure newElement = new AbilityInstanceStructure(abilityInstance);
            this.sentinel = this;
            this.next = newElement;
            this.previous = newElement;
            newElement.next = sentinel;
            newElement.previous = sentinel;
            length++;
        } else if (length >= 1) {
            AbilityInstanceStructure newLast = new AbilityInstanceStructure(abilityInstance);
            AbilityInstanceStructure prevLast = this.previous;
            this.previous = newLast;
            prevLast.next = newLast;
            newLast.next = this;
            newLast.previous = prevLast;
            length++;
        } 
    }

    /*
     * This method seeks to remove the oldest instance of a desired ability within a time frame of a Entity
     */
    public boolean removeAbilityInstance(BobuxAbility ability, long timeFrame) {
        AbilityInstanceStructure tempHead = this;
        while (tempHead.previous != this) {
            tempHead = tempHead.previous;
            if (tempHead.abilityInstance.getAbility().getName().equals(ability.getName())) {
                if (tempHead.abilityInstance.getTick() - BobuxTimer.getTicksPassed() < timeFrame) {
                    AbilityInstanceStructure previous = tempHead.previous;
                    AbilityInstanceStructure after = tempHead.next;
                    previous.next = after;
                    after.previous = previous;
                    return true;
                }
            }
            //If the difference of the currentTime and the ability instance is
            //lesser than the time difference desired, return this instance
        } 
        return false;
    }

    /**
     * This is used for when a condition is for other users  of the same ability in a certain radius and time frame
     */
    public boolean removeAbilityInstance(BobuxAbility ability, long timeFrame, double radius) {
        AbilityInstanceStructure tempHead = this;
        while (tempHead.previous != this) {
            tempHead = tempHead.previous;
            //Checks if the ability of the checked abilityInstance is the desired one
            if (tempHead.abilityInstance.getAbility().getName().equals(ability.getName())) {

                Entity otherEntity = tempHead.abilityInstance.getEntity();
                Location location1 = owner.getLocation();
                Location location2 = otherEntity.getLocation();

                //If their distance is less than the radius
                if (BobuxUtils.getLocationDifferenceMagnitude(location1, location2) < radius) {
                    if (BobuxTimer.getTicksPassed() - tempHead.abilityInstance.getTick() < timeFrame) {
                        AbilityInstanceStructure previous = tempHead.previous;
                        AbilityInstanceStructure after = tempHead.next;
                        previous.next = after;
                        after.previous = previous;
                        return true;
                    }
                }
                //If the difference of the currentTime and the ability instance is
                //lesser than the time difference desired, return this instance
            } 
        }
        return false;
    }

    /**
     * Check if Entity has performed an ability in the past (timeFrame) ticks
     * @param ability
     * @param timeFrame
     * @param currentTime
     * @param Entity
     * @return The remaining cooldown before using or -1 if not found
     */
    public long checkForAbilityCD(BobuxAbility ability, long timeFrame, Entity Entity) {
        if (length == 0) {
            return -1;
        }
        AbilityInstanceStructure tempHead = this;
        while(tempHead.previous != this && BobuxTimer.getTicksPassed() - tempHead.previous.abilityInstance.getTick() < timeFrame) {
            tempHead = tempHead.previous;
            //Checks if the ability of the checked abilityInstance is the desired one
            if (tempHead.abilityInstance.getAbility().equals(ability)) {
                if (tempHead.abilityInstance.getEntity().equals(Entity)) {
                    if (BobuxTimer.getTicksPassed() - tempHead.abilityInstance.getTick() < timeFrame) {
                        return BobuxTimer.getTicksPassed() - tempHead.abilityInstance.getTick();
                    } 
                }  
            } 
        }
        return -1;
    }
    
    public int getLength() {
        return length;
    }

}
