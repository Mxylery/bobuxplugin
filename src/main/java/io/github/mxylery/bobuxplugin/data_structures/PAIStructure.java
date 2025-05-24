package io.github.mxylery.bobuxplugin.data_structures;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.core.PlayerAbilityInstance;

//Basically just a bag, maybe replace with doubly linked list implementation at some point
public class PAIStructure {
    
    //In the sentinel node, next and previous are first and last nodes respectively
    private PAIStructure sentinel;
    private PAIStructure next;
    private PAIStructure previous;
    private Player owner;
    private PlayerAbilityInstance PAI;
    private int length;

    public PAIStructure(PlayerAbilityInstance PAI) {
        this.PAI = PAI;
        this.owner = PAI.getPlayer();
        length = 0;
    }

    public PAIStructure() {
        length = 0;
    }

    /**
     * This method is used to clear memory of each PAIStructure whenever their length reaches above 50 to make sure 
     */
    private void clearMemory() {
        int cull = length;
        PAIStructure tempHead = this;
        while(tempHead.previous != this) {
            tempHead = tempHead.previous;
            if (BobuxTimer.getTicksPassed() - tempHead.PAI.getTick() > 1200) {
                break;
            }
            cull++;
        }
        this.next = tempHead;
        length -= cull;
        System.out.print("Culled " + cull + " PAIs.");
    }

    /**
     * Returns the owner of the Player Ability Structure.
     * @return Owner of the PAIStructure.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the Player Ability Structure. This should only be used for sentinel nodes.
     * @param player The player set to be this PAIStructure's owner.
     */
    public void setOwner(Player player) {
        owner = player;
    }

    /**
     * Sets the given node after the sentinel node.
     * @param PAI Node to be set.
     */
    public void addPAIFirst(PlayerAbilityInstance PAI) {
        if (length == 0) {
            PAIStructure newElement = new PAIStructure(PAI);
            this.sentinel = this;
            this.next = newElement;
            this.previous = newElement;
            newElement.next = sentinel;
            newElement.previous = sentinel;
            length++;
        } else if (length >= 1) {
            PAIStructure newFirst = new PAIStructure(PAI);
            PAIStructure prevFirst = this.next;
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
     * @param PAI Node to be set.
     */
    public void addPAILast(PlayerAbilityInstance PAI) {
        if (length == 0) {
            PAIStructure newElement = new PAIStructure(PAI);
            this.sentinel = this;
            this.next = newElement;
            this.previous = newElement;
            newElement.next = sentinel;
            newElement.previous = sentinel;
            length++;
        } else if (length >= 1) {
            PAIStructure newLast = new PAIStructure(PAI);
            PAIStructure prevLast = this.previous;
            this.previous = newLast;
            prevLast.next = newLast;
            newLast.next = this;
            newLast.previous = prevLast;
            length++;
        } 
    }

    /*
     * This method seeks to remove the oldest instance of a desired ability within a time frame of a player
     */
    public boolean removeAbilityInstance(BobuxAbility ability, long timeFrame) {
        PAIStructure tempHead = this;
        while (tempHead.previous != this) {
            tempHead = tempHead.previous;
            if (tempHead.PAI.getAbility().getName().equals(ability.getName())) {
                if (tempHead.PAI.getTick() - BobuxTimer.getTicksPassed() < timeFrame) {
                    PAIStructure previous = tempHead.previous;
                    PAIStructure after = tempHead.next;
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
        PAIStructure tempHead = this;
        while (tempHead.previous != this) {
            tempHead = tempHead.previous;
            //Checks if the ability of the checked PAI is the desired one
            if (tempHead.PAI.getAbility().getName().equals(ability.getName())) {

                Player otherPlayer = tempHead.PAI.getPlayer();
                Location location1 = owner.getLocation();
                Location location2 = otherPlayer.getLocation();

                //If their distance is less than the radius
                if (BobuxUtils.getLocationDifferenceMagnitude(location1, location2) < radius) {
                    if (BobuxTimer.getTicksPassed() - tempHead.PAI.getTick() < timeFrame) {
                        PAIStructure previous = tempHead.previous;
                        PAIStructure after = tempHead.next;
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
     * Check if player has performed an ability in the past (timeFrame) ticks
     * @param ability
     * @param timeFrame
     * @param currentTime
     * @param player
     * @return The remaining cooldown before using or -1 if not found
     */
    public long checkForAbilityCD(BobuxAbility ability, long timeFrame, Player player) {
        if (length == 0) {
            return -1;
        }
        PAIStructure tempHead = this;
        while(tempHead.previous != this && BobuxTimer.getTicksPassed() - tempHead.previous.PAI.getTick() < timeFrame) {
            tempHead = tempHead.previous;
            //Checks if the ability of the checked PAI is the desired one
            if (tempHead.PAI.getAbility().equals(ability)) {
                if (tempHead.PAI.getPlayer().equals(player)) {
                    if (BobuxTimer.getTicksPassed() - tempHead.PAI.getTick() < timeFrame) {
                        System.out.println(BobuxTimer.getTicksPassed() - tempHead.PAI.getTick());
                        return BobuxTimer.getTicksPassed() - tempHead.PAI.getTick();
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
