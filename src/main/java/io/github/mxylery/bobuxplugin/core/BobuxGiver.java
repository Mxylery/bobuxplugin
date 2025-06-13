package io.github.mxylery.bobuxplugin.core;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
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
			switch (mobString) {
			case "HUSK":
			case "ZOMBIE": 
			case "SPIDER":
			case "SKELETON":
			case "CREEPER": if (rngNum <= 0.25) {
				e.getEntity().getWorld().dropItem(dropLoc, bobux);
				if (rngNum <= 0.05) {
					e.getEntity().getWorld().dropItem(dropLoc, bobuxSquare);
				}
			} 
			break;
			case "VINDICATOR":
			case "ENDERMAN":
			case "WITHER_SKELETON":
			case "BLAZE": if (rngNum <= 0.5) {
				e.getEntity().getWorld().dropItem(dropLoc, bobux);
				if (rngNum <= 0.25) {
					e.getEntity().getWorld().dropItem(dropLoc, bobuxSquare);
				}
			} 
			break;
			case "ENDER_DRAGON": 
			case "WITHER": 
			e.getEntity().getWorld().dropItem(dropLoc, bobux);
			default: if (rngNum <= 0.1) {
				e.getEntity().getWorld().dropItem(dropLoc, bobux);
			}
			break;
		    }
	    }
    }
}

