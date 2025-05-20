package io.github.mxylery.bobuxplugin.core;

import java.util.HashMap;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;
import io.github.mxylery.bobuxplugin.vectors.BobuxUtils;

public class BobuxTransaction {
    
    private int cost;
    private Player player;
    private ItemStack stack;
    PlayerInventory inventory;
    private int[] slotNums = new int[36];
    private BobuxBounty bounty;


    public BobuxTransaction(Player player, int cost, ItemStack stack) {
        this.player = player;
        this.cost = cost;
        this.stack = stack;
        inventory = player.getInventory();
        removeTotalBBX();
    }

    //maybe use this class for bounties.
    public BobuxTransaction(Player player, int compensation, BobuxBounty bounty) {
        this.player = player;
        cost = -compensation;
        this.bounty = bounty;
        inventory = player.getInventory();
        removeTotalItems();
    }

    private boolean checkTotalItems() {
        int itemTotal0 = 0;
        int itemTotal1 = 0;
        int itemTotal2 = 0;
        ItemStack bounty0 = bounty.getStacks()[0];
        ItemStack bounty1 = bounty.getStacks()[1];
        ItemStack bounty2 = bounty.getStacks()[2];
        int size = -1;
        for (int i = 0; i < 36; i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack != null) {
                if (BobuxUtils.checkWithoutDuraAmnt(stack, bounty0)) {
                itemTotal0 += stack.getAmount();
                size++;
                slotNums[size] = i;
            } else if (BobuxUtils.checkWithoutDuraAmnt(stack, bounty1)){
                itemTotal1 += stack.getAmount();
                size++;
                slotNums[size] = i;
            } else if (BobuxUtils.checkWithoutDuraAmnt(stack, bounty2)){
                itemTotal2 += stack.getAmount();
                size++;
                slotNums[size] = i;
                }
            }
        }
        if (size != -1) {
            return false;
        } 
        if (itemTotal0 >= bounty0.getAmount() && itemTotal1 >= bounty1.getAmount() && itemTotal2 >= bounty2.getAmount()) {
            int[] newSlots = new int[size+1];
            System.arraycopy(slotNums, 0, newSlots, 0, size+1);
            slotNums = newSlots;
            return true;
        } else {
            return false;
        }
    }

    private int calculateTotalBBX() {
        int bbxTotal = 0;
        int size = -1;
        for (int i = 0; i < 36; i++) {
            ItemStack stack = inventory.getItem(i);
            if (stack != null) {
                if (BobuxUtils.checkWithoutDuraAmnt(stack, BobuxItemInterface.bobux)) {
                bbxTotal += stack.getAmount();
                size++;
                slotNums[size] = i;
            } else if (BobuxUtils.checkWithoutDuraAmnt(stack, BobuxItemInterface.bobuxSquare)){
                bbxTotal += 4*stack.getAmount();
                size++;
                slotNums[size] = i;
            } else if (BobuxUtils.checkWithoutDuraAmnt(stack, BobuxItemInterface.bobuxCube)){
                bbxTotal += 16*stack.getAmount();
                size++;
                slotNums[size] = i;
            } else if (BobuxUtils.checkWithoutDuraAmnt(stack, BobuxItemInterface.bobuxTesseract)){
                bbxTotal += 64*stack.getAmount();
                size++;
                slotNums[size] = i;
            }
        }
    }
        if (size != -1) {
            int[] newSlots = new int[size+1];
            System.arraycopy(slotNums, 0, newSlots, 0, size+1);
            slotNums = newSlots;
        }
        return bbxTotal;
    }

    private int[] sortItemTypes(ItemStack[] stackList, ItemStack stack) {
        int[] bbxStorage = new int[stackList.length];
        int index = -1;
        for (int i = 0; i < slotNums.length; i++) {
            ItemStack comparedStack = inventory.getItem(slotNums[i]);
            if (comparedStack != null) {
                if (BobuxUtils.checkWithoutDuraAmnt(comparedStack, stack)) {
                    index++;
                    bbxStorage[index] = slotNums[i];
                }
            }
        }
        if (index == -1) {
            return null;
        } 
        int[] finalArray = new int[index+1];
        System.arraycopy(bbxStorage, 0, finalArray, 0, index+1);
        return intBubbleSort(finalArray);
    }

    private boolean validate() {
        if (bounty == null) {
            if (calculateTotalBBX() >= cost) {
                return true;
            } else {
                return false;
            } 
        } else if (checkTotalItems()) {
                return true;
            } else {
                return false;
        }
    }

    //this method will typically not even have to sort at all, but when it does the input size is so small that there would be no benefit
    private int[] intBubbleSort(int[] array) {
        if (array.length <= 1) {
            return array;
        } else {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length - 1; j++) {
                    int amountOne = inventory.getItem(array[j]).getAmount();
                    int amountTwo = inventory.getItem(array[j+1]).getAmount();
                    if (amountOne < amountTwo) {
                        int temp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = temp;
                    }
                }
            }
        }
        return array;
    }

    private void bobuxCompensator() {
        while (cost < 0) {
            while (cost <= -16) {
                inventory.addItem(BobuxItemInterface.bobuxCube.getStack());
                cost += 16;
            }
            while (cost <= -4) {
                inventory.addItem(BobuxItemInterface.bobuxSquare.getStack());
                cost += 4;
            }
            inventory.addItem(BobuxItemInterface.bobux.getStack());
            cost += 1;
        }
    }

    private void removeTotalItems() {
        if (checkTotalItems()) {
            ItemStack[] tempStack = new ItemStack[slotNums.length];
            for (int i = 0; i < slotNums.length; i++) {
                tempStack[i] = inventory.getItem(i);
            }
            ItemStack bounty0 = bounty.getStacks()[0];
            ItemStack bounty1 = bounty.getStacks()[1];
            ItemStack bounty2 = bounty.getStacks()[2];

            int[] itemStack0 = sortItemTypes(tempStack, bounty0);
            int[] itemStack1 = sortItemTypes(tempStack, bounty1);
            int[] itemStack2 = sortItemTypes(tempStack, bounty2);

            int itemAmnt0 = bounty0.getAmount();
            int itemAmnt1 = bounty1.getAmount();
            int itemAmnt2 = bounty2.getAmount();

            ItemStack intermStack;

            while (itemAmnt0 > 0) {
                intermStack = inventory.getItem(itemStack0[0]);
                int prevAmnt = intermStack.getAmount();
                intermStack.setAmount(prevAmnt - 1);
                prevAmnt--;
                if (prevAmnt == 0) {
                    for (int i = 0; i < itemStack0.length-1; i++) {
                        itemStack0[i] = itemStack0[i+1];
                    }
                } else {
                    inventory.setItem(itemStack0[0], intermStack);
                }                
            }
            while (itemAmnt1 > 0) {
                intermStack = inventory.getItem(itemStack1[0]);
                int prevAmnt = intermStack.getAmount();
                intermStack.setAmount(prevAmnt - 1);
                prevAmnt--;
                if (prevAmnt == 0) {
                    for (int i = 0; i < itemStack1.length-1; i++) {
                        itemStack1[i] = itemStack1[i+1];
                    }
                } else {
                    inventory.setItem(itemStack1[0], intermStack);
                }                
            }
            while (itemAmnt2 > 0) {
                intermStack = inventory.getItem(itemStack2[0]);
                int prevAmnt = intermStack.getAmount();
                intermStack.setAmount(prevAmnt - 1);
                prevAmnt--;
                if (prevAmnt == 0) {
                    for (int i = 0; i < itemStack2.length-1; i++) {
                        itemStack0[i] = itemStack2[i+1];
                    }
                } else {
                    inventory.setItem(itemStack2[0], intermStack);
                }                
            }
            bobuxCompensator();
            player.playSound(player, Sound.ITEM_BOOK_PAGE_TURN, 0.1f, 1f);
            bounty.setState(true);
        } else {
            player.sendMessage("You do not have enough items to go through with this transaction.");
            player.playSound(player, Sound.BLOCK_BELL_USE,0.1f,0.5f);
        }
    }

    private void removeTotalBBX() {
        if (validate()) {
            ItemStack[] tempStack = new ItemStack[slotNums.length];
            for (int i = 0; i < slotNums.length; i++) {
                tempStack[i] = inventory.getItem(i);
            }
            int[] bbxStack = sortItemTypes(tempStack, BobuxItemInterface.bobux.getStack());
            int[] squareStack = sortItemTypes(tempStack, BobuxItemInterface.bobuxSquare.getStack());
            int[] cubeStack = sortItemTypes(tempStack, BobuxItemInterface.bobuxCube.getStack());
            int[] tesseractStack = sortItemTypes(tempStack, BobuxItemInterface.bobuxTesseract.getStack());
            boolean tessRanOut = false;
            boolean cubeRanOut = false;
            boolean squareRanOut = false;
            //This variable is used as an intermediate between the previous and next stacks.
            ItemStack intermStack;
            //This goes thru the inventory removing larger moneys first
            //Rewrite this using only the booleans ranOuts, you can just do if (xStack == null) then xranout = true
            while (cost > 0) {
                //Checks the tesseracts
                if (tesseractStack != null) {
                    intermStack = inventory.getItem(tesseractStack[0]);
                    int prevAmnt = intermStack.getAmount();
                    intermStack.setAmount(prevAmnt - 1);
                    prevAmnt--;
                    cost -= 64;
                        if (prevAmnt == 0) {
                            for (int i = 0; i < tesseractStack.length-1; i++) {
                                tesseractStack[i] = tesseractStack[i+1];
                            }
                            if (inventory.getItem(tesseractStack[0]) == null || tesseractStack.length == 1) {
                                tessRanOut = true;
                                bobuxCompensator();
                            } 
                        } else {
                            inventory.setItem(tesseractStack[0], intermStack);
                            bobuxCompensator();
                        }
                        //Checks the cubes
                    } else if (tesseractStack == null && !tessRanOut) {
                        tessRanOut = true;
                    } else if (cubeStack != null && tessRanOut) {
                        intermStack = inventory.getItem(cubeStack[0]);
                        int prevAmnt = intermStack.getAmount();
                        intermStack.setAmount(prevAmnt - 1);
                        prevAmnt--;
                        cost -= 16;
                        if (prevAmnt == 0) {
                            for (int i = 0; i < cubeStack.length-1; i++) {
                                cubeStack[i] = cubeStack[i+1];
                            }
                            if (inventory.getItem(cubeStack[0]) == null || cubeStack.length == 1) {
                                cubeRanOut = true;
                                bobuxCompensator();
                            } 
                        } else {
                            inventory.setItem(cubeStack[0], intermStack);
                            bobuxCompensator();
                        }

                    } else if (cubeStack == null && !cubeRanOut) {
                        cubeRanOut = true;
                        //Checks the squares
                    } else if (squareStack != null && cubeRanOut) {
                        intermStack = inventory.getItem(squareStack[0]);
                        int prevAmnt = intermStack.getAmount();
                        intermStack.setAmount(prevAmnt - 1);
                        prevAmnt--;
                        cost -= 4;
                        if (prevAmnt == 0) {
                            for (int i = 0; i < squareStack.length-1; i++) {
                                squareStack[i] = squareStack[i+1];
                            }
                            if (inventory.getItem(squareStack[0]) == null || squareStack.length == 1) {
                                squareRanOut = true;
                                bobuxCompensator();
                            } 
                        } else {
                            inventory.setItem(squareStack[0], intermStack);
                            bobuxCompensator();
                        }
                    } else if (squareStack == null && !squareRanOut) {
                        squareRanOut = true;
                        //Checks normal bobux
                    } else if (bbxStack != null && squareRanOut) {
                        intermStack = inventory.getItem(bbxStack[0]);
                        int prevAmnt = intermStack.getAmount();
                        intermStack.setAmount(prevAmnt - 1);
                        prevAmnt--;
                        cost--;
                        if (prevAmnt == 0) {
                            for (int i = 0; i < bbxStack.length-1; i++) {
                                bbxStack[i] = bbxStack[i+1];
                            }
                        } else {
                            inventory.setItem(bbxStack[0], intermStack);
                        }
                    } 
                }
            player.playSound(player, Sound.BLOCK_AMETHYST_BLOCK_CHIME,0.1f,1f);
            inventory.addItem(stack);
        } else {
            player.sendMessage("You do not have enough bobux to go through with this transaction.");
            player.playSound(player, Sound.BLOCK_BELL_USE,0.1f,0.5f);
        } 
    }

}
