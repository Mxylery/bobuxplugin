package io.github.mxylery.bobuxplugin.core.ability_types;

import io.github.mxylery.bobuxplugin.conditions.PlayerAbilityInstanceCondition;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxAction;

//In the playerabilitymanager, these abilities will recursively call useAbility at later dates specified by the rep cycle until the condition is removed.
public class AbilityPassive extends BobuxAbility {

    public AbilityPassive(long cooldown, PlayerAbilityInstanceCondition[] conditions, BobuxAction[] actionList, String name, boolean muteCD) {
        super(cooldown, conditions, name, muteCD);
    }



}
