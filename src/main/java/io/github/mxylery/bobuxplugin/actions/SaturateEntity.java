package io.github.mxylery.bobuxplugin.actions;

import io.github.mxylery.bobuxplugin.core.BobuxAction;
import org.bukkit.entity.*;

/**
 * First example of a bobux action: all of the abilities will use them 
 * in conjunction to allow for as much expression as the creator wants 
 * and with relative ease
 */
public class SaturateEntity extends BobuxAction {
    
private float saturate;

//This action needs an entity to saturate and a number to saturate for
public SaturateEntity(float hunger, float saturate) {
    this.saturate = saturate;
    super.requiresEntity = true;
}

public void adjustFlat(double increase) {
    this.saturate += increase;
}

public void adjustPerc(double increase) {
    this.saturate = this.saturate*( (float) increase/100 + 1);
}

public void run() {
    int j = -1;
    HumanEntity[] saturateArray = new HumanEntity[super.entityList.length];
    //Filter through all of the non-saturateable entities
    for (int i = 0; i < super.entityList.length; i++) {
        if (super.entityList[i] instanceof HumanEntity) {
            saturateArray[j + 1] = (HumanEntity) super.entityList[i];
            j++;
        }
    }
    for (int i = 0; i < j + 1; i++) {
        System.out.println("Saturate by: " + saturate);
        saturateArray[i].setSaturation(saturate);
        saturateArray[i].setFoodLevel((int) saturate);
    }
    
}

}
