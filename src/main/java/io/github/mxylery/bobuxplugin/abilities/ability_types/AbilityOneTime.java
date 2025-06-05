package io.github.mxylery.bobuxplugin.abilities.ability_types;


import org.bukkit.entity.Entity;
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

    /**
     * This is the method where the creator will make the ability: Four things must be initialized (as of 0.4.1):
     * 1. Entity List
     * 2. Vector List
     * 3. Location List
     * 4. Inventory List
     */
    protected boolean assignVariables() {
        return false;
    }

    public BobuxAction[] getActionList() {
        return actionList;
    }

    //If the ability was retriggered by the initial trigger: ignoreCD = on, until a retrigger triggers a non retrigger trigger.
    protected void retrigger(BobuxRegisterer registerer, Player player, BobuxItem bobuxitem, int delay) {
        BukkitScheduler scheduler = BobuxTimer.getScheduler();
        if (PlayerAbilityManager.verifyItemCD(player, this, muteCD)) {
                ignoreCD = true;
        }
        if (PlayerAbilityManager.verifyItemCD(user, bobuxitem.getAbility(), true) || ignoreCD) {
            Runnable runnable = new Runnable(){
            public void run() {
                if (BobuxUtils.checkWithoutDuraAmnt(player.getInventory().getItemInMainHand(), bobuxitem) && registerer.getEntityList() != null) {
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
