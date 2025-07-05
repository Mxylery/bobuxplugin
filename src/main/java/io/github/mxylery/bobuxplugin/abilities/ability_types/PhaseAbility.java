package io.github.mxylery.bobuxplugin.abilities.ability_types;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.abilities.AbilityComponent;
import io.github.mxylery.bobuxplugin.abilities.BobuxAbility;
import io.github.mxylery.bobuxplugin.abilities.PlayerAbilityManager;
import io.github.mxylery.bobuxplugin.core.*;
import io.github.mxylery.bobuxplugin.items.BobuxItem;

//The first phase is the initial trigger
public abstract class PhaseAbility extends BobuxAbility {

    protected int phase;
    protected int phaseCount;

    public PhaseAbility(String name, boolean muteCD, long cooldown) {
        super(name, muteCD, cooldown);
        this.phase = 0;
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

    //If the ability was retriggered by the initial trigger: triggeredNormally = false, until a retrigger triggers a non retrigger trigger.
    protected void triggerPhase(Player player, BobuxItem bobuxitem, int delay, int phase) {
        this.phase = phase;
        BukkitScheduler scheduler = BobuxTimer.getScheduler();
        if (!triggeredNormally || PlayerAbilityManager.verifyItemCD(player, bobuxitem.getAbility(), true)) {
            if (phase != 0) {
                Runnable runnable = new Runnable(){
                public void run() {
                    if (BobuxUtils.checkWithoutDuraAmnt(player.getInventory().getItemInMainHand(), bobuxitem)) {
                        BobuxAbility ability = bobuxitem.getAbility();
                        ability.setTriggeredNormally(false);
                        ability.setUser(user);
                        ability.assignVariables();
                        ability.use();
                    }
                }
            };    
            scheduler.runTaskLater(BobuxTimer.getPlugin(), runnable, delay);    
            }
        }
    }
}
