package io.github.mxylery.bobuxplugin.listeners;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import io.github.mxylery.bobuxplugin.core.BobuxBounty;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.items.BobuxItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class BobuxGUIGenerator implements Listener {
    
    private static Server server = BobuxTimer.getServer();

    public BobuxGUIGenerator() {

    }

    
    public static HashMap<Player,BobuxBounty> playerBountyMap = new HashMap<Player,BobuxBounty>();
    public static BobuxItem[] marketMenu = new BobuxItem[3];
    public static final int marketItemTotal = 7;
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
                case 7:
                break;
                case 8:
                break;
                case 9:
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
        Player[] playerList = (Player[]) server.getOnlinePlayers().toArray();
        for (int i = 0; i < playerList.length; i++) {
           
        }
    }

    @EventHandler
    public void onLogin (PlayerLoginEvent e) {

    }

}
