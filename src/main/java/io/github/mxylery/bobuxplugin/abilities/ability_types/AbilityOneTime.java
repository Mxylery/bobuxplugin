package io.github.mxylery.bobuxplugin.abilities.ability_types;


import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.core.*;
import io.github.mxylery.bobuxplugin.items.BobuxItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.vectors.BobuxRegisterer;

public abstract class AbilityOneTime extends BobuxAbility {

    protected boolean ignoreCD;

    public AbilityOneTime(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
    }

    public void adjustPerc() {

    }

    public void adjustFlat() {

    }

    protected boolean assignVariables() {
        return false;
    }

    public BobuxAction[] getActionList() {
        return actionList;
    }

    //If the ability was retriggered by the initial trigger: ignoreCD = on, until a retrigger triggers a non retrigger trigger.
    protected void retrigger(BobuxRegisterer registerer, Player user, BobuxItem bobuxitem, int delay) {
        BukkitScheduler scheduler = BobuxTimer.getScheduler();
        if (PlayerAbilityManager.verifyItemCD(user, this, muteCD)) {
                ignoreCD = true;
        }
        if (PlayerAbilityManager.verifyItemCD(user, bobuxitem.getAbility(), true) || ignoreCD) {
            Runnable runnable = new Runnable(){
            public void run() {
                if (BobuxUtils.checkWithoutDuraAmnt(user.getInventory().getItemInMainHand(), bobuxitem) && registerer.getEntityList() != null) {
                    registerer.updateTargeting();
                    BobuxAbility ability = BobuxItemInterface.kungFuGloves.getAbility();
                    ability.setUser(user);
                    ability.setActionList();
                    ability.use();
                }
            }
        };    
        scheduler.runTaskLater(BobuxTimer.getPlugin(), runnable, delay);    
        }
    }
}
