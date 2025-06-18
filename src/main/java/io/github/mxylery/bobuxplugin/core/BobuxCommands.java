package io.github.mxylery.bobuxplugin.core;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.entities.BobuxEntityListener;
import io.github.mxylery.bobuxplugin.entities.entities.BobuxInvisArmorStand;
import io.github.mxylery.bobuxplugin.entities.livingentities.hostiles.BigChicken;
import io.github.mxylery.bobuxplugin.entities.mobs.CulturalCultist;
import io.github.mxylery.bobuxplugin.entities.mobs.JumpySkeleton;
import io.github.mxylery.bobuxplugin.entities.mobs.Sandbagger;
import io.github.mxylery.bobuxplugin.entities.mobs.ScoutZombie;
import io.github.mxylery.bobuxplugin.entities.mobs.StinkyMob;
import io.github.mxylery.bobuxplugin.guis.core.MainGUI;
import io.github.mxylery.bobuxplugin.io.PlayerLocationData;
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
                break;
                case "fruitcake_and_cookies": player.getInventory().addItem(BobuxItemInterface.fruitcakeAndCookies.getStack());
                break;
                case "bobuxinator": player.getInventory().addItem(BobuxItemInterface.bobuxinator.getStack());
                break;
                case "bobux_brew": player.getInventory().addItem(BobuxItemInterface.bobuxBrew.getStack());
                break;
                case "bobux_brew_remnants": player.getInventory().addItem(BobuxItemInterface.bobuxBrewRemnants.getStack());
                break;
                case "straight_pearl": player.getInventory().addItem(BobuxItemInterface.straightPearl.getStack());
                break;
                case "mega_long_bow": player.getInventory().addItem(BobuxItemInterface.megaLongBow.getStack());
                break;
                case "freaky_carrot": player.getInventory().addItem(BobuxItemInterface.freakyCarrot.getStack());
                break;
                case "stinky_pants": player.getInventory().addItem(BobuxItemInterface.stinkyPants.getStack());
                break;
                case "freaky_wheat": player.getInventory().addItem(BobuxItemInterface.freakyWheat.getStack());
                break;
                case "freaky_seeds": player.getInventory().addItem(BobuxItemInterface.freakySeeds.getStack());
                break;
                default:
                break;
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("bobuxmenu")) {
            MainGUI mainGUI = new MainGUI(Bukkit.createInventory(player, 27), player, plugin);
            return true;
        } else if (command.getName().equalsIgnoreCase("bobuxinfo")) {
            //Market info
            long ticksLeft = 12000 - BobuxTimer.getTime() % 12000;
            long seconds = ticksLeft/20;
            int minutes = 0;
            while (seconds > 60) {
                minutes++;
                seconds -= 60;
            }
            player.sendMessage("Next market refresh: " + minutes + "m, " + seconds + "s");

            //Bounty info
            ticksLeft = 24000 - BobuxTimer.getTime() % 24000;
            seconds = ticksLeft/20;
            minutes = 0;
            while (seconds > 60) {
                minutes++;
                seconds -= 60;
            }
            player.sendMessage("Next bounty refresh: " + minutes + "m, " + seconds + "s");
            player.sendMessage("Current Day Type: " + BobuxDay.getDay().toString());
            return true;
        } else if (command.getName().equalsIgnoreCase("bobuxspawn")) {
            switch (args[0]) {
                case "stinky_mob": StinkyMob stinkyMob = new StinkyMob(player.getLocation());
                BobuxEntityListener.getBobuxEntityList().add(stinkyMob);
                break;
                case "scout_zombie": ScoutZombie scoutZombie = new ScoutZombie(player.getLocation());
                BobuxEntityListener.getBobuxEntityList().add(scoutZombie);
                break;
                case "sandbagger": Sandbagger sandbagger = new Sandbagger(player.getLocation());
                BobuxEntityListener.getBobuxEntityList().add(sandbagger);
                break;
                case "big_chicken": BigChicken bigChicken = new BigChicken(player.getLocation());
                BobuxEntityListener.getBobuxEntityList().add(bigChicken);
                break;
                case "cultural_cultist": CulturalCultist culturalCultist = new CulturalCultist(player.getLocation());
                BobuxEntityListener.getBobuxEntityList().add(culturalCultist);
                break;
                case "jumpy_skeleton": JumpySkeleton jumpySkeleton = new JumpySkeleton(player.getLocation());
                BobuxEntityListener.getBobuxEntityList().add(jumpySkeleton);
                break;
                case "invisible_armor_stand": 
                if (args[1] != null) {
                    BobuxInvisArmorStand invisStand = new BobuxInvisArmorStand(player.getLocation(), Integer.parseInt(args[1]), null, null);
                    BobuxEntityListener.getBobuxEntityList().add(invisStand);
                }
                break;
            }
        } else if (command.getName().equalsIgnoreCase("bobuxhub")) {
            if (player.getWorld().equals(BobuxPlugin.getOverworld())) {
                PlayerLocationData.saveDataToFile();
                PlayerLocationData.loadDataToGame();
                player.teleport(BobuxPlugin.getBobuxHub().getSpawnLocation());
            } else {
                player.sendMessage("You're already in the Bobux Hub!");
            }
        } else if (command.getName().equalsIgnoreCase("bobuxconvert")) {
             
        } else {
            return false;
        }
    }
    return false;
}

}
