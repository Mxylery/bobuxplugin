package io.github.mxylery.bobuxplugin.guis.bounty;

import org.bukkit.inventory.ItemStack;

public class BobuxBounty {

    private ItemStack[] stacks = new ItemStack[3];
    private boolean completed;
    private int compensation;
    private int type;
    
    public BobuxBounty(int type) {
        this.completed = false;
        this.compensation = 0;
        this.type = type;
        switch (type) {
            case 0: generateJunkBounty();
            break;
            case 1: generateGoodsBounty();
            break;
            case 2: generateRarityBounty();
            break;
        }
    }

    public ItemStack[] getStacks() {
        return stacks;
    }

    /**
     * Returns whether the grant is completed or not.
     * @return the completion state
     */
    public boolean getState() {
        return completed;
    }

    public void setState(boolean completed) {
        this.completed = completed;
    }

    private void generateJunkBounty() {
        Bounty bounty;
        int[] rngTrack = new int[3];
        for (int i = 0; i < 3; i++) {
            int rng = (int) (Math.random()*Bounty.junkCount);
            for (int j = 0; j < i; j++) {
                if (rng == rngTrack[j]) {
                    rng = (int) (Math.random()*Bounty.junkCount);
                    j = -1;
                }
            }
            switch (rng) {
                case 0: bounty = Bounty.ANDESITE;
                break;
                case 1: bounty = Bounty.COBBLESTONE;
                break;
                case 2: bounty = Bounty.BONE;
                break;
                case 3: bounty = Bounty.DIRT;
                break;
                case 4: bounty = Bounty.DIORITE;
                break;
                case 5: bounty = Bounty.GRAVEL;
                break;
                case 6: bounty = Bounty.KELP;
                break;
                case 7: bounty = Bounty.MUTTON;
                break;
                case 8: bounty = Bounty.ROTTEN_FLESH;
                break;
                case 9: bounty = Bounty.SAND;
                break;
                case 10: bounty = Bounty.SPIDER_EYE;
                break;
                case 11: bounty = Bounty.STRING;
                break;
                default: bounty = Bounty.DIRT;
                break;
            }
            int stackAmnt = (int) (Math.random()*(bounty.max - bounty.min) + bounty.min);
            stacks[i] = new ItemStack(bounty.stack);
            stacks[i].setAmount(stackAmnt);
            rngTrack[i] = rng;
            double tempCompensation = 0;
            for (int j = 0; j < stackAmnt; j++) {
                tempCompensation += bounty.bobux;
            }
            compensation += (int) tempCompensation;
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
                case 2: bounty = Bounty.COAL;
                break;
                case 3: bounty = Bounty.COD;
                break;
                case 4: bounty = Bounty.FLINT;
                break;
                case 5: bounty = Bounty.GLASS;
                break;
                case 6: bounty = Bounty.IRON_INGOT;
                break;
                case 7: bounty = Bounty.LAPIS_LAZULI;
                break;
                case 8: bounty = Bounty.LEATHER;
                break;
                case 9: bounty = Bounty.OAK_LOG;
                break;
                case 10: bounty = Bounty.POTATO;
                break;
                case 11: bounty = Bounty.PUFFERFISH;
                break;
                case 12: bounty = Bounty.REDSTONE_BLOCK;
                break;
                case 13: bounty = Bounty.RED_MUSHROOM;
                break;
                case 14: bounty = Bounty.STEAK;
                break;
                case 15: bounty = Bounty.SUGAR_CANE;
                break;
                case 16: bounty = Bounty.WHEAT;
                break;
                case 17: bounty = Bounty.WOOL;
                break;
                case 18: bounty = Bounty.BIRCH_LOG;
                break;
                case 19: bounty = Bounty.DARK_OAK_LOG;
                break;
                case 20: bounty = Bounty.SALMON;
                break;
                case 21: bounty = Bounty.SEEDS;
                break;
                default: bounty = Bounty.WOOL;
                break;
            }
            int stackAmnt = (int) (Math.random()*(bounty.max - bounty.min) + bounty.min);
            stacks[i] = new ItemStack(bounty.stack);
            stacks[i].setAmount(stackAmnt);
            rngTrack[i] = rng;
            double tempCompensation = 0;
            for (int j = 0; j < stackAmnt; j++) {
                tempCompensation += bounty.bobux;
            }
            compensation += (int) tempCompensation;
        }
    }

    private void generateRarityBounty() {
        Bounty bounty;
        int[] rngTrack = new int[3];
        for (int i = 0; i < 3; i++) {
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
                case 2: bounty = Bounty.GOLD_INGOT;
                break;
                case 3: bounty = Bounty.HEART_OF_THE_SEA;
                break;
                case 4: bounty = Bounty.NAUTILUS_SHELL;
                break;
                case 5: bounty = Bounty.NETHERITE_INGOT;
                break;
                case 6: bounty = Bounty.SHULKER_SHELL;
                break;
                default: bounty = Bounty.DIAMOND;
                break;
            }
            int stackAmnt = (int) (Math.random()*(bounty.max - bounty.min) + bounty.min);
            stacks[i] = new ItemStack(bounty.stack);
            stacks[i].setAmount(stackAmnt);
            rngTrack[i] = rng;
            double tempCompensation = 0;
            for (int j = 0; j < stackAmnt; j++) {
                tempCompensation += bounty.bobux;
            }
            compensation += (int) tempCompensation;
        }
    }

    public int getCompensation() {
        return compensation;
    }

    //0 = junk, 1 = goods, 2 = rarities
    public int getType() {
        return type;
    }
}
