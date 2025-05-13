package io.github.mxylery.bobuxplugin.data_structures;

import org.bukkit.Location;
import org.bukkit.entity.Player;

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
        System.out.println("Clearing memory with index " + index);
        if (index != 0) {
            int cull = 0;
            for (int i = index; i > 0; i--) {
                //If it has been more than one minute, remove the instances. 
                //No ability should rely on an instance ran more than a minute from its activation.
                if (BobuxTimer.getTicksPassed() - PAIarray[i].getTick() > 1200) {
                    cull = i;
                    break;
                }
            }
            for (int i = 0; i + cull < index; i++) {
                PAIarray[i] = PAIarray[i+cull];
            }
            index =- cull;
            }
        
    }

    private void addMemory() {
        int arrayLength = PAIarray.length;
        PlayerAbilityInstance[] newPAIarray = new PlayerAbilityInstance[arrayLength*2];
        System.arraycopy(PAIarray, 0, newPAIarray, 0, arrayLength);
        PAIarray = newPAIarray;
    }

    public void addPAI(PlayerAbilityInstance PAI) {
        System.out.println("Added a PAI");
        if (index != 0) {
            this.clearMemory();
        }
        if (index + 1 == PAIarray.length) {
            this.addMemory();
        }
        PAIarray[index] = PAI;
        index++;
    }

    /*
     * This method seeks to remove the oldest instance of a desired ability within a time frame of a player
     */
    public boolean removeAbilityInstance(BobuxAbility ability, long timeFrame, Player player) {
        
        for (int i = 0; i < index; i++) {
            //Checks if the ability of the checked PAI is the desired one
            if (PAIarray[i].getAbility().equals(ability)) {
                if (PAIarray[i].getPlayer().equals(player)) {
                    if (PAIarray[i].getTick() - BobuxTimer.getTicksPassed() < timeFrame) {
                        for (int j = i; j < PAIarray.length; j++) {
                            PAIarray[j] = PAIarray[j+1];
                        }
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
     * This is used for when a condition is for other users  of the same ability in a certain radius and time frame
     */
    public boolean removeAbilityInstance(BobuxAbility ability, long timeFrame, double radius, Player player) {
        

        for (int i = 0; i < index + 1; i++) {
            //Checks if the ability of the checked PAI is the desired one
            if (PAIarray[i].getAbility().equals(ability)) {

                Player otherPlayer = PAIarray[i].getPlayer();
                Location location1 = player.getLocation();
                Location location2 = otherPlayer.getLocation();

                //If their distance is less than the radius
                if (BobuxUtils.getLocationDifferenceMagnitude(location1, location2) < radius) {
                    if (PAIarray[i].getTick() - BobuxTimer.getTicksPassed() < timeFrame) {
                        for (int j = i; j < PAIarray.length; j++) {
                            PAIarray[j] = PAIarray[j+1];
                        }
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
        
        for (int i = 0; i < index + 1; i++) {
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
