package io.github.mxylery.bobuxplugin.core;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.items.BobuxItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public final class BobuxGiver implements Listener {

    private final BobuxPlugin plugin;

    public BobuxGiver(BobuxPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

    @EventHandler
	public void onKill(EntityDeathEvent e) {
		
		double rngNum = Math.random();
		Location dropLoc = e.getEntity().getLocation();
		ItemStack bobux = BobuxItemInterface.bobux.getStack();
		ItemStack bobuxSquare = BobuxItemInterface.bobuxSquare.getStack();
		String mobString = e.getEntityType().toString();
		double dayModifier;
		
		if (e.getEntity().getKiller() instanceof Player) {
			dropLoc.add(0.5, 0.5, 0.5);
			Entity entity = e.getEntity();
			switch (mobString) {
			case "HUSK":
			case "ZOMBIE": 
			case "SPIDER":
			case "SKELETON":
			case "CREEPER": 
			BobuxUtils.dropBobux(0.25, entity, bobux, 3);
			BobuxUtils.dropBobux(0.05, entity, bobuxSquare, 0);
			break;
			case "VINDICATOR":
			case "ENDERMAN":
			case "WITHER_SKELETON":
			case "BLAZE": 
			BobuxUtils.dropBobux(0.3, entity, bobux, 5);
			BobuxUtils.dropBobux(0.1, entity, bobuxSquare, 1);
			break;
			case "ENDER_DRAGON": 
			case "WITHER": 
			BobuxUtils.dropBobux(1, entity, bobux, 24);
			BobuxUtils.dropBobux(1, entity, bobuxSquare, 4);
			default: if (rngNum <= 0.1) {
				e.getEntity().getWorld().dropItem(dropLoc, bobux);
			}
			break;
		    }
	    }
    }
}

