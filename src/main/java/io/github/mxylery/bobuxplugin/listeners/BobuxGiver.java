package io.github.mxylery.bobuxplugin.listeners;

import org.bukkit.event.Listener;
import io.github.mxylery.bobuxplugin.BobuxPlugin;

public final class BobuxGiver implements Listener {

    private final BobuxPlugin plugin;

    public BobuxGiver(BobuxPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    

}