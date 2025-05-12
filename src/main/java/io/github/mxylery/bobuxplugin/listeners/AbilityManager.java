package io.github.mxylery.bobuxplugin.listeners;

import java.util.HashMap;

import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import io.github.mxylery.bobuxplugin.*;
import io.github.mxylery.bobuxplugin.core.*;
import io.github.mxylery.bobuxplugin.data_structures.*;

//This is where all cooldowns get registered to players and whatnot. Also take into account mobs, can have seperate methods or even a seperate class
public class AbilityManager implements Listener {
    
    //PAI = Player Ability Instance, ../core/PlayerAbilityInstance.java
    private final BobuxPlugin plugin;
    private HashMap cooldownPAIMap = new HashMap<Player, PAIStructure>();

    public AbilityManager(BobuxPlugin plugin) {
        this.plugin = plugin;
    }

    private void useAbility(BobuxAbility ability) {

    }
}
