package io.github.mxylery.bobuxplugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import io.github.mxylery.bobuxplugin.BobuxPlugin;

public class ItemPassiveManager implements Listener {
    
    private BobuxPlugin plugin;

    public ItemPassiveManager(BobuxPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler //To update all of the passives if the player is wearing/holding anything
    public void onLogin(PlayerLoginEvent e) {
        
    }
}
