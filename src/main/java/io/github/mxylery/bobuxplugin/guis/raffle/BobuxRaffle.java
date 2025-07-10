package io.github.mxylery.bobuxplugin.guis.raffle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.BobuxTransaction;
import io.github.mxylery.bobuxplugin.items.BobuxAttributeSet;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.player.BobuxPlayerStats;
import io.github.mxylery.bobuxplugin.player.TempAttribute;

public class BobuxRaffle implements Serializable {
    
    private boolean isCompleted;
    private HashMap<UUID, Integer> playerContributionMap = new HashMap<UUID, Integer>();
    private TempAttribute tempAttribute;
    private int total;
    private int goal;
    private ItemStack raffleStack;
    private transient Player winner;
    private static transient HashMap<UUID, ItemStack> offlinePlayerStacks = new HashMap<UUID, ItemStack>();
    private static transient HashMap<UUID, TempAttribute> offlinePlayerAttributes = new HashMap<UUID, TempAttribute>();
    private static transient HashMap<UUID, Integer> offlinePlayerDonations = new HashMap<UUID, Integer>();
    
    public BobuxRaffle() {
        double goalRng = Math.random();
        if (goalRng < 0.6) {
            goal = 250;
        } else if (goalRng < 0.9) {
            goal = 750;
        } else {
            goal = 2500;
        }
        chooseRaffleStack();
        chooseTempAttribute();
        isCompleted = false;
    }

    private void chooseTempAttribute() {
        int attributeRng = (int) (Math.random()*3);
        double timeRng = Math.random();
        long length;
        if (timeRng < 0.33) {
            length = 24000;
        } else if (timeRng < 0.67) {
            length = 48000;
        } else {
            length = 72000;
        }
        BobuxAttributeSet attributeSet;
        switch(attributeRng) {
            case 0: 
            switch (goal) {
                case 250: attributeSet = new BobuxAttributeSet(Attribute.MOVEMENT_SPEED, 0.2, Operation.ADD_SCALAR);
                break;
                case 750: attributeSet = new BobuxAttributeSet(Attribute.MOVEMENT_SPEED, 0.4, Operation.ADD_SCALAR);
                break;
                case 2500: attributeSet = new BobuxAttributeSet(Attribute.MOVEMENT_SPEED, 0.6, Operation.ADD_SCALAR);
                break;
                default: attributeSet = new BobuxAttributeSet(Attribute.MOVEMENT_SPEED, 0.2, Operation.ADD_SCALAR);
            }
            break;
            case 1: 
            switch (goal) {
                case 250: attributeSet = new BobuxAttributeSet(Attribute.ATTACK_DAMAGE, 0.1, Operation.ADD_SCALAR);
                break;
                case 750: attributeSet = new BobuxAttributeSet(Attribute.ATTACK_DAMAGE, 0.2, Operation.ADD_SCALAR);
                break;
                case 2500: attributeSet = new BobuxAttributeSet(Attribute.ATTACK_DAMAGE, 0.3, Operation.ADD_SCALAR);
                break;
                default: attributeSet = new BobuxAttributeSet(Attribute.ATTACK_DAMAGE, 0.1, Operation.ADD_SCALAR);
            }
            break;
            case 2: 
            switch (goal) {
                case 250: attributeSet = new BobuxAttributeSet(Attribute.STEP_HEIGHT, 0.5, Operation.ADD_NUMBER);
                break;
                case 750: attributeSet = new BobuxAttributeSet(Attribute.STEP_HEIGHT, 1.0, Operation.ADD_NUMBER);
                break;
                case 2500: attributeSet = new BobuxAttributeSet(Attribute.STEP_HEIGHT, 1.5, Operation.ADD_NUMBER);
                break;
                default: attributeSet = new BobuxAttributeSet(Attribute.STEP_HEIGHT, 0.5, Operation.ADD_NUMBER);
            }
            break;
            default: attributeSet = new BobuxAttributeSet(Attribute.ATTACK_DAMAGE, 0.1, Operation.ADD_SCALAR);
            break;
        }
        tempAttribute = new TempAttribute(attributeSet, length);
    }

    private void chooseRaffleStack() {
        int raffleStackRng = (int) Math.random()*5;
        switch (raffleStackRng) {
            case 0: raffleStack = new ItemStack(Material.DIAMOND);
            switch (goal) {
                case 250: raffleStack.setAmount(5);
                break;
                case 750: raffleStack.setAmount(16);
                break;
                case 2500: raffleStack.setAmount(64);
                break;
            }
            break;
            case 1: raffleStack = new ItemStack(Material.NETHERITE_INGOT);
            switch (goal) {
                case 250: raffleStack.setAmount(1);
                break;
                case 750: raffleStack.setAmount(4);
                break;
                case 2500: raffleStack.setAmount(15);
                break;
            }
            break;
            case 2: raffleStack = new ItemStack(Material.NETHERITE_INGOT);
            switch (goal) {
                case 250: raffleStack.setAmount(1);
                break;
                case 750: raffleStack.setAmount(3);
                break;
                case 2500: raffleStack.setAmount(10);
                break;
            }
            break;
            case 3: raffleStack = new ItemStack(Material.NETHERITE_INGOT);
            switch (goal) {
                case 250: raffleStack.setAmount(1);
                break;
                case 750: raffleStack.setAmount(3);
                break;
                case 2500: raffleStack.setAmount(10);
                break;
            }
            break;
            case 4: raffleStack = new ItemStack(Material.NETHERITE_INGOT);
            switch (goal) {
                case 250: raffleStack.setAmount(1);
                break;
                case 750: raffleStack.setAmount(3);
                break;
                case 2500: raffleStack.setAmount(10);
                break;
            }
            break;
            default:
            break;
        }
    }

    private void distributePrizes() {
        Set<UUID> playerList = playerContributionMap.keySet();
        for (UUID uuid : playerList) {
            Player player = Bukkit.getPlayer(uuid);
            int remainingContribution = playerContributionMap.get(uuid);
            if (remainingContribution > goal/10) {
                BobuxPlayerStats stats = BobuxPlugin.getPlayerStats(player);
                stats.addAttribute(tempAttribute);
            }
            if (player.isOnline()) {
                while (remainingContribution > 0) {
                    while (remainingContribution > 500) {
                        remainingContribution -= 500;
                        player.getInventory().addItem(BobuxItemInterface.greaterLootbox.getStack());
                        player.getInventory().addItem(BobuxItemInterface.lootbox.getStack());
                        player.getInventory().addItem(BobuxItemInterface.lesserLootbox.getStack());
                    }
                    while (remainingContribution > 100) {
                        remainingContribution -= 100;
                        player.getInventory().addItem(BobuxItemInterface.lootbox.getStack());
                        player.getInventory().addItem(BobuxItemInterface.lesserLootbox.getStack());
                    }
                    while (remainingContribution > 50) {
                        remainingContribution -= 50;
                        player.getInventory().addItem(BobuxItemInterface.lesserLootbox.getStack());
                    }
                    remainingContribution = 0;
                }
            } else {
                offlinePlayerDonations.put(uuid, remainingContribution);
            }
        }
    }

    //Every time a donation is given to the raffle, it updates the total and the amount that person contributed.
    public void updateRaffle(Player player, int bobuxToAdd) {
        UUID uuid = player.getUniqueId();
        if (!playerContributionMap.containsKey(uuid)) {
            if (new BobuxTransaction(player, bobuxToAdd, new ItemStack(Material.AIR)).wentThrough()) {
                int bobuxToPut = Math.min(bobuxToAdd, goal - total);
                playerContributionMap.put(uuid, bobuxToPut);
                total += bobuxToAdd;
            }
        } else {
            int previousBobuxAmnt = playerContributionMap.get(uuid);
            if (new BobuxTransaction(player, bobuxToAdd, new ItemStack(Material.AIR)).wentThrough()) {
                int bobuxToPut = Math.min(bobuxToAdd, goal - total);
                playerContributionMap.put(uuid, previousBobuxAmnt + bobuxToPut);
                total += bobuxToAdd;
            }
        }
        if (total == goal) {
            chooseWinner();
        }
    }

    private void chooseWinner() {
        isCompleted = true;
        int goalMoney = (int) Math.random()*goal;
        int temp = 0;
        Set<UUID> raffleeSet = playerContributionMap.keySet();
        for (UUID uuid : raffleeSet) {
            temp += playerContributionMap.get(uuid);
            if (temp > goalMoney) {
                winner = Bukkit.getPlayer(uuid);
                BobuxTimer.getServer().broadcastMessage("Congratulations to " + winner.getName() + " for winning the Bobux Raffle!");
                if (winner.isOnline()) {
                    winner.getInventory().addItem(raffleStack);
                } else {
                    offlinePlayerAttributes.put(uuid, tempAttribute);
                    offlinePlayerStacks.put(uuid, raffleStack);
                }
                break;
            }
        }
        distributePrizes();
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public int getGoal() {
        return goal;
    }

    public int getCurrentTotal() {
        return total;
    }

    public HashMap<UUID, TempAttribute> getOfflineAttributeMap() {
        return offlinePlayerAttributes;
    }

    public HashMap<UUID, ItemStack> getOfflineStacks() {
        return offlinePlayerStacks;
    }

    public HashMap<UUID, Integer> getOfflineDonations() {
        return offlinePlayerDonations;
    }

    public void setOfflineStacks(HashMap<UUID, ItemStack> stackMap) {
        offlinePlayerStacks = stackMap;
    }

    public void setOfflineAttributeMap(HashMap<UUID, TempAttribute> attributeMap) {
        offlinePlayerAttributes = attributeMap;
    }

    public void setOfflineDonations(HashMap<UUID, Integer> playerDonations) {
        offlinePlayerDonations = playerDonations;
    }

    public ItemStack getWinningStack() {
        return raffleStack;
    }

    public TempAttribute getParticipationAttribute() {
        return tempAttribute;
    }
    
}
