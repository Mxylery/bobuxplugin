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
    public static BobuxItem[] marketMenu = new BobuxItem[5];
    public static int menuSize = 3;
    public static final int marketItemTotal = 14;
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
                case 5: marketMenu[i] = BobuxItemInterface.lineSpawner;
                break;
                case 6: marketMenu[i] = BobuxItemInterface.railgun;
                break;
                case 7: marketMenu[i] = BobuxItemInterface.theHotStick;
                break;
                case 8: marketMenu[i] = BobuxItemInterface.kungFuGloves;
                break;
                case 9: marketMenu[i] = BobuxItemInterface.fruitcakeAndCookies;
                break;
                case 10: marketMenu[i] = BobuxItemInterface.bobuxinator;
                break;
                case 11: marketMenu[i] = BobuxItemInterface.bobuxBrew;
                break;
                case 12: marketMenu[i] = BobuxItemInterface.straightPearl;
                break;
                case 13: marketMenu[i] = BobuxItemInterface.megaLongBow;
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
