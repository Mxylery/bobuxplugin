package io.github.mxylery.bobuxplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.guis.MainGUI;
import io.github.mxylery.bobuxplugin.items.BobuxItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class BobuxCommands implements CommandExecutor {
    
private final BobuxPlugin plugin;

public BobuxCommands(BobuxPlugin plugin) {
    this.plugin = plugin;
}

@Override
public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
    
    Player player = (Player) sender;
    player.getServer().broadcastMessage(command.getName());
    if (sender instanceof Player) {
        if (command.getName().equalsIgnoreCase("bobuxgive")) {
            switch (args[0]) {
                case "testing_item": player.getInventory().addItem(BobuxItemInterface.testingItem.getStack());
                break;
                case "bouncing_item": player.getInventory().addItem(BobuxItemInterface.bouncingItem.getStack());
                break;
                case "bouncing_boots": player.getInventory().addItem(BobuxItemInterface.bounceBoots.getStack());
                break;
                case "harmful_substance": player.getInventory().addItem(BobuxItemInterface.harmfulSubstance.getStack());
                break;
                case "hurried_stopwatch": player.getInventory().addItem(BobuxItemInterface.hurriedStopwatch.getStack());
                break;
                case "bobux": player.getInventory().addItem(BobuxItemInterface.bobux.getStack());
                break;
                case "cleaver": player.getInventory().addItem(BobuxItemInterface.cleaver.getStack());
                break;
                default:
                break;
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("bobuxmenu")) {
            MainGUI mainGUI = new MainGUI(Bukkit.createInventory(player, 27), player, plugin);
            return true;
        } else {

        }
    }
    return false;
}

}
