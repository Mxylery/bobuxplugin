package io.github.mxylery.bobuxplugin.listeners;

import java.util.HashMap;

import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;
import io.github.mxylery.bobuxplugin.*;
import io.github.mxylery.bobuxplugin.core.*;
import io.github.mxylery.bobuxplugin.core.ability_types.*;
import io.github.mxylery.bobuxplugin.data_structures.*;

//This is where all cooldowns get registered to players and whatnot. Also take into account mobs, can have seperate methods or even a seperate class
public class PlayerAbilityManager implements Listener {
    
    //PAI = Player Ability Instance, ../core/PlayerAbilityInstance.java
    private final BobuxPlugin plugin;
    private static HashMap<Player, PAIStructure> cooldownPAIMap = new HashMap<Player, PAIStructure>();

    public PlayerAbilityManager(BobuxPlugin plugin) {
        this.plugin = plugin;
    }

    //Used in all of the listener methods below.
    private static void useAbility(BobuxAbility ability, Player player) {
        if (ability instanceof AbilityOneTime) {
            if (!cooldownPAIMap.containsKey(player)) {
                PAIStructure newStruct = new PAIStructure();
                cooldownPAIMap.put(player, newStruct);
            }

            //Initializes the PAI Structure holding all of the ability instances
            PAIStructure abilityInstanceHistory = cooldownPAIMap.get(player);
            BobuxAction[] actionList = ability.getActionList();

            //Runs each action of the ability
            for (int i = 0; i < actionList.length; i++) {
                actionList[i].run();
            }

            PlayerAbilityInstance abilityInstance = new PlayerAbilityInstance(player, BobuxTimer.getTicksPassed(), ability);
            abilityInstanceHistory.addPAI(abilityInstance);
            cooldownPAIMap.put(player, abilityInstanceHistory);

        } else if (ability instanceof AbilityMultiStep) {

        } else {

        }
    }


    public void playerListener(PlayerInteractEvent e) {

    }

}
