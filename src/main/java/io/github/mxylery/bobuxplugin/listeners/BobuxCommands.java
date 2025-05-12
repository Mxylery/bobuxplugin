package io.github.mxylery.bobuxplugin.listeners;

import org.bukkit.command.*;
import io.github.mxylery.bobuxplugin.BobuxPlugin;

public class BobuxCommands implements CommandExecutor {
    
private final BobuxPlugin plugin;

public BobuxCommands(BobuxPlugin plugin) {
    this.plugin = plugin;
}

public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
    if (command.getName().equalsIgnoreCase("bobuxgive")) {

        

    }
    
    return false;
}

}
