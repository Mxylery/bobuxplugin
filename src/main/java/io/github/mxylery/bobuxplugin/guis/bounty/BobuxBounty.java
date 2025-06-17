package io.github.mxylery.bobuxplugin.guis.bounty;

import org.bukkit.inventory.ItemStack;

public class BobuxBounty {

    private ItemStack[] stacks = new ItemStack[3];
    private boolean completed;
    private int compensation;
    
    public BobuxBounty() {
        this.completed = false;
        this.compensation = 0;
        generateBounty();
    }

    public ItemStack[] getStacks() {
        return stacks;
    }

    public boolean getState() {
        return completed;
    }

    public void setState(boolean completed) {
        this.completed = completed;
    }

    private void generateJunkBounty() {
        Bounty bounty;
        int[] rngTrack = new int[2];
        for (int i = 0; i < 2; i++) {
            int rng = (int) (Math.random()*Bounty.junkCount);
            for (int j = 0; j < i; j++) {
                if (rng == rngTrack[j]) {
                    rng = (int) (Math.random()*Bounty.junkCount);
                    j = -1;
                }
            }
            switch (rng) {
                case 0: bounty = Bounty.COBBLESTONE;
                break;
                case 1: bounty = Bounty.DIRT;
                break;
                case 2: bounty = Bounty.MUTTON;
                break;
                case 3: bounty = Bounty.ROTTEN_FLESH;
                break;
                case 4: bounty = Bounty.SPIDER_EYE;
                break;
                default: bounty = Bounty.DIRT;
                break;
            }
            int stackAmnt = (int) (Math.random()*bounty.max + bounty.min);
            stacks[i] = new ItemStack(bounty.stack);
            stacks[i].setAmount(stackAmnt);
            rngTrack[i] = rng;
            compensation += bounty.bobux;
        }
    }

    private void generateBounty() {
        Bounty bounty;
        int[] rngTrack = new int[3];
        for (int i = 0; i < 3; i++) {
            int rng = (int) (Math.random()*Bounty.typeCount);
            for (int j = 0; j < i; j++) {
                if (rng == rngTrack[j]) {
                    rng = (int) (Math.random()*Bounty.typeCount);
                    j = -1;
                }
            }
            switch (rng) {
                case 0: bounty = Bounty.BROWN_MUSHROOM;
                break;
                case 1: bounty = Bounty.CARROT;
                break;
                case 2: bounty = Bounty.DIAMOND;
                break;
                case 3: bounty = Bounty.ENDER_PEARL;
                break;
                case 4: bounty = Bounty.GLASS;
                break;
                case 5: bounty = Bounty.GOLD_INGOT;
                break;
                case 6: bounty = Bounty.HEART_OF_THE_SEA;
                break;
                case 7: bounty = Bounty.LAPIS_LAZULI;
                break;
                case 8: bounty = Bounty.LEATHER;
                break;
                case 9: bounty = Bounty.MUTTON;
                break;
                case 10: bounty = Bounty.NAUTILUS_SHELL;
                break;
                case 11: bounty = Bounty.NETHERITE_INGOT;
                break;
                case 12: bounty = Bounty.OAK_LOG;
                break;
                case 13: bounty = Bounty.POTATO;
                break;
                case 14: bounty = Bounty.REDSTONE_BLOCK;
                break;
                case 15: bounty = Bounty.RED_MUSHROOM;
                break;
                case 16: bounty = Bounty.ROTTEN_FLESH;
                break;
                case 17: bounty = Bounty.STEAK;
                break;
                case 18: bounty = Bounty.WHEAT;
                break;
                case 19: bounty = Bounty.WOOL;
                break;
                default: bounty = Bounty.WOOL;
                break;
            }
            int stackAmnt = (int) (Math.random()*bounty.max + bounty.min);
            stacks[i] = new ItemStack(bounty.stack);
            stacks[i].setAmount(stackAmnt);
            rngTrack[i] = rng;
            compensation += bounty.bobux;
        }
    }

    private void generateGoodsBounty() {
        Bounty bounty;
        int[] rngTrack = new int[3];
        for (int i = 0; i < 3; i++) {
            int rng = (int) (Math.random()*Bounty.goodsCount);
            for (int j = 0; j < i; j++) {
                if (rng == rngTrack[j]) {
                    rng = (int) (Math.random()*Bounty.goodsCount);
                    j = -1;
                }
            }
            switch (rng) {
                case 0: bounty = Bounty.BROWN_MUSHROOM;
                break;
                case 1: bounty = Bounty.CARROT;
                break;
                case 2: bounty = Bounty.GLASS;
                break;
                case 3: bounty = Bounty.IRON_INGOT;
                break;
                case 4: bounty = Bounty.GOLD_INGOT;
                break;
                case 5: bounty = Bounty.LAPIS_LAZULI;
                break;
                case 6: bounty = Bounty.LEATHER;
                break;
                case 7: bounty = Bounty.OAK_LOG;
                break;
                case 8: bounty = Bounty.POTATO;
                break;
                case 9: bounty = Bounty.REDSTONE_BLOCK;
                break;
                case 10: bounty = Bounty.RED_MUSHROOM;
                break;
                case 11: bounty = Bounty.STEAK;
                break;
                case 12: bounty = Bounty.WHEAT;
                break;
                case 13: bounty = Bounty.WOOL;
                break;
                case 14: bounty = Bounty.BIRCH_LOG;
                break;
                case 15: bounty = Bounty.DARK_OAK_LOG;
                break;
                case 16: bounty = Bounty.SEEDS;
                break;
                default: bounty = Bounty.WOOL;
                break;
            }
            int stackAmnt = (int) (Math.random()*bounty.max + bounty.min);
            stacks[i] = new ItemStack(bounty.stack);
            stacks[i].setAmount(stackAmnt);
            rngTrack[i] = rng;
            compensation += bounty.bobux;
        }
    }

    private void generateRarityBounty() {
        Bounty bounty;
        int[] rngTrack = new int[2];
        for (int i = 0; i < 2; i++) {
            int rng = (int) (Math.random()*Bounty.raritiesCount);
            for (int j = 0; j < i; j++) {
                if (rng == rngTrack[j]) {
                    rng = (int) (Math.random()*Bounty.raritiesCount);
                    j = -1;
                }
            }
            switch (rng) {
                case 0: bounty = Bounty.DIAMOND;
                break;
                case 1: bounty = Bounty.ENDER_PEARL;
                break;
                case 2: bounty = Bounty.HEART_OF_THE_SEA;
                break;
                case 3: bounty = Bounty.NAUTILUS_SHELL;
                break;
                case 4: bounty = Bounty.NETHERITE_INGOT;
                break;
                default: bounty = Bounty.DIAMOND;
                break;
            }
            int stackAmnt = (int) (Math.random()*bounty.max + bounty.min);
            stacks[i] = new ItemStack(bounty.stack);
            stacks[i].setAmount(stackAmnt);
            rngTrack[i] = rng;
            compensation += bounty.bobux;
        }
    }

    public int getCompensation() {
        return compensation;
    }
}
