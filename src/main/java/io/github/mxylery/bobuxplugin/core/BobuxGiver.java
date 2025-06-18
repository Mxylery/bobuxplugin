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

	private double adjustedChance(double chance, double mod) {
		if (mod != 0) {
			double opposite = 1 - chance;
			opposite = (opposite * (mod - 1))/mod;
			return chance + opposite;
		}
		return 0;
	}

    @EventHandler
	public void onKill(EntityDeathEvent e) {

		Location dropLoc = e.getEntity().getLocation();
		ItemStack bobux = BobuxItemInterface.bobux.getStack();
		ItemStack bobuxSquare = BobuxItemInterface.bobuxSquare.getStack();
		String mobString = e.getEntityType().toString();
		double modifier = BobuxDay.getBobuxModifier();
		
		if (e.getEntity().getKiller() instanceof Player) {
			dropLoc.add(0.5, 0.5, 0.5);
			Entity entity = e.getEntity();
			switch (mobString) {
			case "HUSK":
			case "ZOMBIE": 
			case "SPIDER":
			case "SKELETON":
			case "CREEPER": BobuxUtils.dropBobux(adjustedChance(0.25, modifier), entity, bobux, 2); BobuxUtils.dropBobux(0.05, entity, bobuxSquare, 0);
			break;
			case "VINDICATOR":
			case "ENDERMAN":
			case "WITHER_SKELETON":
			case "BLAZE": BobuxUtils.dropBobux(adjustedChance(0.3, modifier), entity, bobux, 5); BobuxUtils.dropBobux(0.1, entity, bobuxSquare, 1);
			break;
			case "ENDER_DRAGON": 
			case "WITHER": BobuxUtils.dropBobux(1, entity, bobux, 24); BobuxUtils.dropBobux(1, entity, bobuxSquare, 4);
			break;
			default: BobuxUtils.dropBobux(adjustedChance(0.2, modifier), entity, bobux, 0);
			break;
		    }
	    }
    }
}

