package io.github.mxylery.bobuxplugin.abilities.ability_types;


import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.abilities.BobuxAbility;
import io.github.mxylery.bobuxplugin.abilities.PlayerAbilityManager;
import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.core.*;
import io.github.mxylery.bobuxplugin.items.BobuxItem;

//The first phase is the initial trigger
public abstract class PhaseAbility extends BobuxAbility {

    protected boolean ignoreCD;
    protected int phase;
    protected int phaseCount;
    protected int[] phaseTickMap;

    public PhaseAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
        this.phase = 0;
        this.phaseTickMap = new int[phaseCount];
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

    private void ignoreCDTrigger(int phase, BobuxItem bobuxitem) {
        if (phase != 0 && PlayerAbilityManager.verifyItemCD(user, bobuxitem.getAbility(), false)) {
            ignoreCD = true;
        } else {
            ignoreCD = false;
        }
    }

    //If the ability was retriggered by the initial trigger: ignoreCD = on, until a retrigger triggers a non retrigger trigger.
    protected void triggerPhase(Player player, BobuxItem bobuxitem, int delay) {
        BukkitScheduler scheduler = BobuxTimer.getScheduler();
        ignoreCDTrigger(phase, bobuxitem);
        if (PlayerAbilityManager.verifyItemCD(user, bobuxitem.getAbility(), true) || ignoreCD) {
            Runnable runnable = new Runnable(){
            public void run() {
                if (BobuxUtils.checkWithoutDuraAmnt(player.getInventory().getItemInMainHand(), bobuxitem)) {
                    BobuxAbility ability = bobuxitem.getAbility();
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
