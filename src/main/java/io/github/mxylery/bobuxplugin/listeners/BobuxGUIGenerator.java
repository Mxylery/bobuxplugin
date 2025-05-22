package io.github.mxylery.bobuxplugin.listeners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxBounty;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
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

    public static HashMap<Player,BobuxBounty[]> playerBountyMap = new HashMap<Player,BobuxBounty[]>();
    public static BobuxItem[] marketMenu = new BobuxItem[3];
    public static final int marketItemTotal = 10;
    //As of 0.2.3, generates 3 items randomly.
    public static void randomizeMarketItems() {
        int[] noDupe = new int[3];
        for (int i = 0; i < 3; i++) {
            int rng = (int) (Math.random()*marketItemTotal);
            for (int j = 0; j < i; j++) {
                if (rng == noDupe[j]) {
                    rng = (int) (Math.random()*marketItemTotal);
                    j = -1;
                }
            }
            switch(rng) {
                case 0: marketMenu[i] = BobuxItemInterface.bounceBoots;
                break;
                case 1: marketMenu[i] = BobuxItemInterface.hurriedStopwatch;
                break;
                case 2: marketMenu[i] = BobuxItemInterface.harmfulSubstance;
                break;
                case 3: marketMenu[i] = BobuxItemInterface.cleaver;
                break;
                case 4: marketMenu[i] = BobuxItemInterface.bouncingItem;
                break;
                case 5: marketMenu[i] = BobuxItemInterface.lineSpawner;
                break;
                case 6: marketMenu[i] = BobuxItemInterface.railgun;
                break;
                case 7: marketMenu[i] = BobuxItemInterface.theHotStick;
                break;
                case 8: marketMenu[i] = BobuxItemInterface.BW5;
                break;
                case 9: marketMenu[i] = BobuxItemInterface.BW5Ammo;
                break;
                case 10:
                break;
                case 11:
                break;
                case 12:
                break;
            }
            noDupe[i] = rng;
        }
    }

    public static void randomizeBounties() {
        Object[] objectList = (Object[]) server.getOnlinePlayers().toArray();
        Player[] playerList = new Player[objectList.length];
        for (int i = 0; i < playerList.length; i++) {
            playerList[i] = (Player) objectList[i];
        }
        for (int i = 0; i < playerList.length; i++) {
            BobuxBounty[] bountyList = new BobuxBounty[3];
            bountyList[0] = new BobuxBounty();
            bountyList[1] = new BobuxBounty();
            bountyList[2] = new BobuxBounty();
            playerBountyMap.put(playerList[i], bountyList);
        }
    }

    public static void randomizeBounty(Player player) {
        BobuxBounty[] bountyList = new BobuxBounty[3];
        bountyList[0] = new BobuxBounty();
        bountyList[1] = new BobuxBounty();
        bountyList[2] = new BobuxBounty();
        playerBountyMap.put(player, bountyList);
    }

    @EventHandler
    public void onLogin (PlayerLoginEvent e) {
        OfflinePlayer[] playerList =  server.getOfflinePlayers();
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
