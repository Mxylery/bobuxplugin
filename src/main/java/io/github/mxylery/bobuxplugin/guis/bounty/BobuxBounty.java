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

    public int getCompensation() {
        return compensation;
    }
}
