package io.github.mxylery.bobuxplugin.abilities.ability_types;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.BobuxAbility;

public abstract class AbilityOneTime extends BobuxAbility {

    protected boolean ignoreCD;

    public AbilityOneTime(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    /**
     * This is the method where the creator will make the ability: Four things must be initialized (as of 0.4.1):
     * 1. Entity List
     * 2. Vector List
     * 3. Location List
     * 4. Inventory List
     */
    public boolean assignVariables() {
        return false;
    }

    public AbilityComponent getComponentHead() {
        return componentHead;
    }
}
