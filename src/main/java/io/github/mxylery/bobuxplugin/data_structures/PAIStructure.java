package io.github.mxylery.bobuxplugin.data_structures;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.PlayerAbilityInstance;

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
    }

    /*
     * This method seeks to remove the oldest instance of a desired ability within a time frame
     */
    public PlayerAbilityInstance checkForAbilityInstanceDelete(BobuxAbility ability, long timeFrame, long currentTime) {
        
        String abilityName = ability.getName();
        
        for (int i = 0; i < PAIarray.length; i++) {
            //Checks if the ability of the checked PAI is the desired one
            if (PAIarray[i].getAbility().getName().equals(abilityName)) {
                //If the difference of the currentTime and the ability instance is
                //lesser than the time difference desired, return this instance
                if (PAIarray[i].getTick() - currentTime < timeFrame) {
                    PlayerAbilityInstance temp = PAIarray[i];
                    for (int j = i; j < PAIarray.length; j++) {
                        PAIarray[j] = PAIarray[j+1];
                    }
                    return temp;
                }
            } 
        }
        return null;
    }

}
