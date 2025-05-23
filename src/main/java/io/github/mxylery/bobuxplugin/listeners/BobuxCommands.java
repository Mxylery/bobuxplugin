package io.github.mxylery.bobuxplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
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
                case "hurried_stopwatch": player.getInventory().addItem(BobuxItemInterface.hurriedStopwatch.getStack());
                break;
                case "bobux": player.getInventory().addItem(BobuxItemInterface.bobux.getStack());
                break;
                case "bobux_square": player.getInventory().addItem(BobuxItemInterface.bobuxSquare.getStack());
                break;
                case "bobux_cube": player.getInventory().addItem(BobuxItemInterface.bobuxCube.getStack());
                break;
                case "bobux_tesseract": player.getInventory().addItem(BobuxItemInterface.bobuxTesseract.getStack());
                break;
                case "cleaver": player.getInventory().addItem(BobuxItemInterface.cleaver.getStack());
                break;
                case "line_spawner": player.getInventory().addItem(BobuxItemInterface.lineSpawner.getStack());
                break;
                case "railgun": player.getInventory().addItem(BobuxItemInterface.railgun.getStack());
                break;
                case "the_hot_stick": player.getInventory().addItem(BobuxItemInterface.theHotStick.getStack());
                break;
                case "bw5": player.getInventory().addItem(BobuxItemInterface.BW5.getStack());
                break;
                case "bw5_ammo": player.getInventory().addItem(BobuxItemInterface.BW5Ammo.getStack());
                break;
                case "kung_fu_gloves": player.getInventory().addItem(BobuxItemInterface.kungFuGloves.getStack());
                default:
                break;
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("bobuxmenu")) {
            MainGUI mainGUI = new MainGUI(Bukkit.createInventory(player, 27), player, plugin);
            return true;
        } else if (command.getName().equalsIgnoreCase("bobuxinfo")) {
            long ticksLeft = 1200 - BobuxTimer.getTicksPassed() % 1200;
            long seconds = ticksLeft/20;
            player.sendMessage("Next market refresh:" + seconds + " s");
            ticksLeft = 2400 - BobuxTimer.getTicksPassed() % 2400;
            seconds = ticksLeft/20;
            player.sendMessage("Next bounty refresh:" + seconds + " s");
            return true;
        } else {
            return false;
        }
    }
    return false;
}

}
