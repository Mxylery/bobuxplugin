package io.github.mxylery.bobuxplugin.core.ability_types;

import io.github.mxylery.bobuxplugin.core.BobuxAbility;

//In the playerabilitymanager, these abilities will recursively call useAbility at later dates specified by the rep cycle until the condition is removed.
public class AbilityPassive {
    
    private int repCycle;
    private BobuxAbility ability;

    public AbilityPassive() {
        repCycle = 0;
    }

}
