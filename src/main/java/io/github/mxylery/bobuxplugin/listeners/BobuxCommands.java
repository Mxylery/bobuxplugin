package io.github.mxylery.bobuxplugin.listeners;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.BobuxPlugin;

public class BobuxCommands implements CommandExecutor {
    
private final BobuxPlugin plugin;

public BobuxCommands(BobuxPlugin plugin) {
    this.plugin = plugin;
}

public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
    
    if (sender instanceof Player) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("bobuxgive")) {
        
        } else if (command.getName().equalsIgnoreCase("bobuxitemgive")) {
            switch (args[0]) {
                case "testing_item": player.getInventory().addItem(BobuxItemInterface.testingItem.getStack());
            }
        }
    }
    
    
    return false;
}

}
