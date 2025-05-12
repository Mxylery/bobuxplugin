package io.github.mxylery.bobuxplugin.data_structures;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.PlayerAbilityInstance;
import io.github.mxylery.bobuxplugin.vectors.BobuxUtils;

//Basically just a bag
public class PAIStructure {
    
    private PlayerAbilityInstance[] PAIarray;
    private int index;

    public PAIStructure() {
        PAIarray = new PlayerAbilityInstance[10];
        index = 0;
    }

    private void clearMemory() {
        int lastIndex = PAIarray.length - 1;
        int cull = 0;
        for (int i = lastIndex; i > 0; i--) {
            //If it has been more than one minute, remove the instances. 
            //No ability should rely on an instance ran more than a minute from its activation.
            if (PAIarray[lastIndex].getTick() - PAIarray[i].getTick() > 1200) {
                for (int j = i; j > 0; j--) {
                    PAIarray[j] = PAIarray[j+1];
                    cull++;
                }
                index =- cull;
                break;
            }
        }
    }

    private void addMemory() {
        int arrayLength = PAIarray.length;
        PlayerAbilityInstance[] newPAIarray = new PlayerAbilityInstance[arrayLength*2];
        System.arraycopy(PAIarray, 0, newPAIarray, 0, arrayLength);
        PAIarray = newPAIarray;
    }

    public void addPAI(PlayerAbilityInstance PAI) {
        if (index + 1 == PAIarray.length) {
            this.addMemory();
        }
        PAIarray[index] = PAI;
        index++;
        this.clearMemory();
    }

    /*
     * This method seeks to remove the oldest instance of a desired ability within a time frame of a player
     */
    public PlayerAbilityInstance removeAbilityInstance(BobuxAbility ability, long timeFrame, Player player) {
        
        for (int i = 0; i < PAIarray.length; i++) {
            //Checks if the ability of the checked PAI is the desired one
            if (PAIarray[i].getAbility().equals(ability)) {
                if (PAIarray[i].getPlayer().equals(player)) {
                    if (PAIarray[i].getTick() - BobuxTimer.getTicksPassed() < timeFrame) {
                        PlayerAbilityInstance temp = PAIarray[i];
                        for (int j = i; j < PAIarray.length; j++) {
                            PAIarray[j] = PAIarray[j+1];
                        }
                        return temp;
                    }
                }
                //If the difference of the currentTime and the ability instance is
                //lesser than the time difference desired, return this instance
            } 
        }
        return null;
    }

    /**
     * This is used for when a condition is for other users  of the same ability in a certain radius and time frame
     */
    public PlayerAbilityInstance removeAbilityInstance(BobuxAbility ability, long timeFrame, double radius, Player player) {
        
        for (int i = 0; i < PAIarray.length; i++) {
            //Checks if the ability of the checked PAI is the desired one
            if (PAIarray[i].getAbility().equals(ability)) {

                Player otherPlayer = PAIarray[i].getPlayer();
                Location location1 = player.getLocation();
                Location location2 = otherPlayer.getLocation();

                //If their distance is less than the radius
                if (BobuxUtils.getLocationDifferenceMagnitude(location1, location2) < radius) {
                    if (PAIarray[i].getTick() - BobuxTimer.getTicksPassed() < timeFrame) {
                        PlayerAbilityInstance temp = PAIarray[i];
                        for (int j = i; j < PAIarray.length; j++) {
                            PAIarray[j] = PAIarray[j+1];
                        }
                        return temp;
                    }
                }
                //If the difference of the currentTime and the ability instance is
                //lesser than the time difference desired, return this instance
            } 
        }
        return null;
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
        
            for (int i = 0; i < PAIarray.length; i++) {
                //Checks if the ability of the checked PAI is the desired one
                if (PAIarray[i].getAbility().equals(ability)) {
                    if (PAIarray[i].getPlayer().equals(player)) {
                        if (BobuxTimer.getTicksPassed() - PAIarray[i].getTick() < timeFrame) {
                            return BobuxTimer.getTicksPassed() - PAIarray[i].getTick();
                        } else {
                            break;
                        }
                    }  
                //If the difference of the currentTime and the ability instance is
                //lesser than the time difference desired, return this instance
                } 
            }
        return -1;
    }
    

    public int getIndex() {
        return index;
    }

}
