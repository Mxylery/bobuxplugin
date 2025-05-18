package io.github.mxylery.bobuxplugin.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
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
	public void onMine(BlockBreakEvent e) {
		
		/*Initializes the RNG number, the player that broke the block, where the item will spawn
		the Bobux item stack and the string of the block broken.*/
			
		double rngNum = Math.random();
		Player player = e.getPlayer();
		Location dropLoc = e.getBlock().getLocation();
		ItemStack bobux = BobuxItemInterface.bobux.getStack();
		String blockString = e.getBlock().getBlockData().getMaterial().toString();
			
		//Checks all possible blocks that would give Bobux and gives them a chance to drop it
			switch (blockString) {
			//0.1% Chance
				case "STONE": if (rngNum <= 0.001) {
					dropLoc.add(0.5, 0.5, 0.5);
					player.getWorld().dropItem(dropLoc, bobux);
				}
					break;
				//0.25% Chance
					case "ANDESITE":
					case "DIORITE":
					case "GRANITE": if (rngNum <= 0.0025) {
						dropLoc.add(0.5, 0.5, 0.5);
						player.getWorld().dropItem(dropLoc, bobux);
					}
					break;
				//2.5% Chance
					case "BAKED_POTATO":
					case "BEETROOTS":
					case "SUGAR_CANE":
					case "CARROTS": if (rngNum <= 0.025) {
						dropLoc.add(0.5, 0.5, 0.5);
						player.getWorld().dropItem(dropLoc, bobux);
					}
					break;
					//5% Chance
					case "AMETHYST_BLOCK":
					case "GLOWSTONE": if (rngNum <= 0.05) {
						dropLoc.add(0.5, 0.5, 0.5);
						player.getWorld().dropItem(dropLoc, bobux);
					}
					break;
					//10% Chance
					case "COAL_ORE":
					case "REDSTONE_ORE":
					case "NETHER_QUARTZ_ORE":
					case "IRON_ORE": if (rngNum <= 0.1) {
						dropLoc.add(0.5, 0.5, 0.5);
						player.getWorld().dropItem(dropLoc, bobux);
					}
					break;
					//25% Chance
					case "GOLD_ORE":
					case "LAPIS_ORE": if (rngNum <= 0.25) {
						dropLoc.add(0.5, 0.5, 0.5);
						player.getWorld().dropItem(dropLoc, bobux);
					}
					break;
					//100% Chance
					case "ANCIENT_DEBRIS":
					case "DIAMOND_ORE":
					case "DEEPSLATE_DIAMOND_ORE": dropLoc.add(0.5, 0.5, 0.5);
						player.getWorld().dropItem(dropLoc, bobux);
					break;
					default:
			    }
			
	}

    @EventHandler
	public void onKill(EntityDeathEvent e) {
		
		double rngNum = Math.random();
		Location dropLoc = e.getEntity().getLocation();
		ItemStack bobux = BobuxItemInterface.bobux.getStack();
		String mobString = e.getEntityType().toString();
		
		if (e.getEntity().getKiller() instanceof Player) {
			switch (mobString) {
			//5% Chance
			case "COW":
			case "CHICKEN": 
			case "PIG":
			case "SHEEP": if (rngNum <= 0.05) {
				dropLoc.add(0.5, 0.5, 0.5);
				e.getEntity().getWorld().dropItem(dropLoc, bobux);
			}
			break;
			//10% Chance
			case "ZOMBIE": 
			case "SPIDER":
			case "SKELETON":
			case "CREEPER": if (rngNum <= 0.1) {
				dropLoc.add(0.5, 0.5, 0.5);
				e.getEntity().getWorld().dropItem(dropLoc, bobux);
			}
			break;
			//25% Chance
			case "ENDERMAN":
			case "WITHER_SKELETON":
			case "BLAZE": if (rngNum <= 0.25) {
				dropLoc.add(0.5, 0.5, 0.5);
				e.getEntity().getWorld().dropItem(dropLoc, bobux);
			}
			break;
			//100% Chance
			case "ENDER_DRAGON": 
			case "WITHER": dropLoc.add(0.5, 0.5, 0.5);
			e.getEntity().getWorld().dropItem(dropLoc, bobux);
			default:
		    }
	    }
    }
}

