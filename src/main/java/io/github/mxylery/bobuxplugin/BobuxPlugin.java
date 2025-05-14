package io.github.mxylery.bobuxplugin;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.listeners.*;

public final class BobuxPlugin extends JavaPlugin {

    private BobuxTimer bobuxTimer = new BobuxTimer(this.getServer());

    @Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");

        new BobuxGiver(this);
        new PlayerAbilityManager(this);
        

        this.getCommand("bobuxgive").setExecutor(new BobuxCommands(this));
        this.getCommand("bobuxitemgive").setExecutor(new BobuxCommands(this));

        bobuxTimer.runTaskTimer(this, 0, 1);
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