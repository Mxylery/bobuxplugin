package io.github.mxylery.bobuxplugin.guis;

import java.util.HashMap;

import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxDay;
import io.github.mxylery.bobuxplugin.core.BobuxDay.DayType;
import io.github.mxylery.bobuxplugin.guis.bounty.BobuxBounty;
import io.github.mxylery.bobuxplugin.guis.questboard.BobuxBeginnerQuest;
import io.github.mxylery.bobuxplugin.guis.questboard.BobuxQuest;
import io.github.mxylery.bobuxplugin.items.BobuxItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class BobuxGUIGenerator implements Listener {
    
    private static Server server;
    private BobuxPlugin plugin;

    public BobuxGUIGenerator(BobuxPlugin plugin) {
        this.plugin = plugin;
        server = plugin.getServer();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static BobuxItem[] marketMenu = new BobuxItem[5];
    public static int menuSize = 3;
    public static final int marketItemTotal = 32;
    public static void randomizeMarketItems() {
        if (BobuxDay.getDay() != DayType.AVARICIOUS) {
            menuSize = 3;
        } else {
            menuSize = 5;
        }
        int[] noDupe = new int[menuSize];
        for (int i = 0; i < menuSize; i++) {
            int rng = (int) (Math.random()*marketItemTotal);
            for (int j = 0; j < i; j++) {
                if (rng == noDupe[j]) {
                    rng = (int) (Math.random()*marketItemTotal);
                    j = -1;
                }
            }
            switch(rng) {
                case 0: marketMenu[i] = BobuxItemInterface.BW5Ammo;
                break;
                case 1: marketMenu[i] = BobuxItemInterface.BW5; 
                break;
                case 2: marketMenu[i] = BobuxItemInterface.hurriedStopwatch;
                break;
                case 3: marketMenu[i] = BobuxItemInterface.cleaver;
                break;
                case 4: marketMenu[i] = BobuxItemInterface.bouncingItem;
                break;
                case 5: marketMenu[i] = BobuxItemInterface.railgun;
                break;
                case 6: marketMenu[i] = BobuxItemInterface.theHotStick;
                break;
                case 7: marketMenu[i] = BobuxItemInterface.kungFuGloves;
                break;
                case 8: marketMenu[i] = BobuxItemInterface.fruitcakeAndCookies;
                break;
                case 9: marketMenu[i] = BobuxItemInterface.bobuxinator;
                break;
                case 10: marketMenu[i] = BobuxItemInterface.bobuxBrew;
                break;
                case 11: marketMenu[i] = BobuxItemInterface.straightPearl;
                break;
                case 12: marketMenu[i] = BobuxItemInterface.megaLongBow;
                break;
                case 13: marketMenu[i] = BobuxItemInterface.freakyWheat;
                break;
                case 14: marketMenu[i] = BobuxItemInterface.freakyCarrot;
                break;
                case 15: marketMenu[i] = BobuxItemInterface.freakySeeds;
                break;
                case 16: marketMenu[i] = BobuxItemInterface.lesserLootbox;
                break;
                case 17: marketMenu[i] = BobuxItemInterface.bardCap;
                break;
                case 18: marketMenu[i] = BobuxItemInterface.bardVest;
                break;
                case 19: marketMenu[i] = BobuxItemInterface.bardLeggings;
                break;
                case 20: marketMenu[i] = BobuxItemInterface.bardVest;
                break;
                case 21: marketMenu[i] = BobuxItemInterface.miniPick;
                break;
                case 22: marketMenu[i] = BobuxItemInterface.flockingFeather;
                break;
                case 23: marketMenu[i] = BobuxItemInterface.peaceTreaty;
                break;
                case 24: marketMenu[i] = BobuxItemInterface.hypeTrain;
                break;
                case 25: marketMenu[i] = BobuxItemInterface.slowPogo;
                break;
                case 26: marketMenu[i] = BobuxItemInterface.pogoLauncher;
                break;
                case 27: marketMenu[i] = BobuxItemInterface.fishermanBoots;
                break;
                case 28: marketMenu[i] = BobuxItemInterface.fishermanHat;
                break;
                case 29: marketMenu[i] = BobuxItemInterface.fishermanPants;
                break;
                case 30: marketMenu[i] = BobuxItemInterface.fishermanVest;
                break;
                case 31: marketMenu[i] = BobuxItemInterface.fishermansPole;
                break;
            }
            noDupe[i] = rng;
        }
    }

    public static HashMap<Player,BobuxBounty[]> playerBountyMap = new HashMap<Player,BobuxBounty[]>();

    public static void randomizeBounties() {
        Object[] objectList = (Object[]) server.getOnlinePlayers().toArray();
        Player[] playerList = new Player[objectList.length];
        for (int i = 0; i < playerList.length; i++) {
            playerList[i] = (Player) objectList[i];
        }
        for (int i = 0; i < playerList.length; i++) {
            BobuxBounty[] bountyList = new BobuxBounty[6];
            bountyList[0] = new BobuxBounty(0);
            bountyList[1] = new BobuxBounty(0);
            bountyList[2] = new BobuxBounty(0);
            bountyList[3] = new BobuxBounty(1);
            bountyList[4] = new BobuxBounty(1);
            bountyList[5] = new BobuxBounty(2);
            playerBountyMap.put(playerList[i], bountyList);
        }
    }

    public static void randomizeBounty(Player player) {
        BobuxBounty[] bountyList = new BobuxBounty[6];
        bountyList[0] = new BobuxBounty(0);
        bountyList[1] = new BobuxBounty(0);
        bountyList[2] = new BobuxBounty(0);
        bountyList[3] = new BobuxBounty(1);
        bountyList[4] = new BobuxBounty(1);
        bountyList[5] = new BobuxBounty(2);
        playerBountyMap.put(player, bountyList);
    }

    public static BobuxQuest[] questMenu = new BobuxQuest[3];

    public static void randomizeQuests() {
        questMenu[0] = new BobuxBeginnerQuest(3);
        questMenu[1] = new BobuxBeginnerQuest(3);
        questMenu[2] = new BobuxBeginnerQuest(3);
    }

    public static void randomizeQuest(int index, int amount) {
        questMenu[index] = new BobuxBeginnerQuest(amount);
    }

    @EventHandler
    public void onLogin (PlayerLoginEvent e) {
        OfflinePlayer[] playerList = server.getOfflinePlayers();
        boolean wasOnline = false;
        for (int i = 0; i < playerList.length; i++) {
            if (playerList[i].getUniqueId() == e.getPlayer().getUniqueId()) {
                wasOnline = true;
            }
        }
        if (!wasOnline) {
            System.out.println("Randomized a bounty!" );
            randomizeBounty(e.getPlayer());
        }
    }
}
