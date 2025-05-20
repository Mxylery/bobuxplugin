package io.github.mxylery.bobuxplugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.ability_types.AbilityPassive;

public class ItemPassiveManager implements Listener {
    
    private BobuxPlugin plugin;

    public ItemPassiveManager(BobuxPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private void checkForCD(AbilityPassive passive) {
        
    }

    @EventHandler //To update all of the passives if the player is wearing/holding anything
    public void onLogin(PlayerLoginEvent e) {
        
    }

    @EventHandler
    public void onInvInteract(InventoryClickEvent e) {

    }

    @EventHandler 
    public void onHotbarSwap(PlayerItemHeldEvent e) {

    }


}
