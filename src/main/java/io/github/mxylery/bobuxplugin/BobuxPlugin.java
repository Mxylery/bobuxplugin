package io.github.mxylery.bobuxplugin;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.listeners.*;

public final class BobuxPlugin extends JavaPlugin {

    private BukkitScheduler scheduler;

    @Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");

        new BobuxGiver(this);
        new PlayerAbilityManager(this);
        new BobuxGUIGenerator(this);
        
        this.getCommand("bobuxgive").setExecutor(new BobuxCommands(this));
        this.getCommand("bobuxmenu").setExecutor(new BobuxCommands(this));
        this.getCommand("bobuxinfo").setExecutor(new BobuxCommands(this));

        scheduler = this.getServer().getScheduler();
        BobuxTimer bobuxTimer = new BobuxTimer(this.getServer(), this);
        scheduler.runTaskTimer(this, bobuxTimer, 0, 1);
        
	}

	@Override 
    /* 
     * Write something that saves bobux after server close,
     * or maybe just implement SQL throughout so it doesn't 
     * need to close?
     */
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}
    

}