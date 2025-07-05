package io.github.mxylery.bobuxplugin.abilities.ability_types;

import java.util.HashMap;

import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.BobuxAbility;
import io.github.mxylery.bobuxplugin.abilities.PlayerAbilityManager;
import io.github.mxylery.bobuxplugin.data_structures.AbilityInstanceStructure;

//In the playerabilitymanager, these abilities will recursively call useAbility at later dates specified by the rep cycle until the condition is removed.
//Period and cooldown is swapped; if you want to make a cooldown, use the period to set it (the kung fu gloves have a 1 second cooldown for its left click, or a 20 tick period).
public class AbilityPassive extends BobuxAbility {

    protected long period;

    public AbilityPassive(String name, boolean muteCD, long cooldown, long period) {
        super(name, muteCD, cooldown);
        this.period = period;
    }

    public boolean assignVariables() {
        return false;
    }

    public AbilityComponent getComponentHead() {
        return componentHead;
    }

    protected boolean verifyPassivePeriod() {
        HashMap<Player,AbilityInstanceStructure> theMap = PlayerAbilityManager.getAbilityHistoryMap();
        if (theMap.containsKey(user)) {
            AbilityInstanceStructure playerStructure = theMap.get(user);
            if (playerStructure.checkForAbilityCD(this, period, user) == -1) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
