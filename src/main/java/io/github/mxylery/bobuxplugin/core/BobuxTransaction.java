package io.github.mxylery.bobuxplugin.core;

import java.util.HashMap;

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
    private ItemStack[] bounties;


    public BobuxTransaction(Player player, int cost, ItemStack stack) {
        this.player = player;
        this.cost = cost;
        this.stack = stack;
        inventory = player.getInventory();
        removeTotalBBX();
    }

    //maybe use this class for bounties?
    public BobuxTransaction(Player player, int compensation, ItemStack[] bounties) {
        this.player = player;
        cost = compensation;
        this.bounties = bounties;
        inventory = player.getInventory();
        removeTotalBBX();
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

    //The method returns an array exclusively of the indices of the bobux type asked for (3 = tesseract, ... , 0 = bobux)
    private int[] sortBBXTypes(ItemStack[] stackList, int type) {
        int[] bbxStorage = new int[stackList.length];
        int index = -1;
        switch(type) {
            case 0: 
            for (int i = 0; i < slotNums.length; i++) {
                ItemStack comparedStack = inventory.getItem(slotNums[i]);
                if (comparedStack != null) {
                    if (BobuxUtils.checkWithoutDuraAmnt(comparedStack, BobuxItemInterface.bobux)) {
                        index++;
                        bbxStorage[index] = slotNums[i];
                    }
                }
            }
            break;
            case 1:
            for (int i = 0; i < slotNums.length; i++) {
                ItemStack comparedStack = inventory.getItem(slotNums[i]);
                if (comparedStack != null) {
                    if (BobuxUtils.checkWithoutDuraAmnt(comparedStack, BobuxItemInterface.bobuxSquare)) {
                        index++;
                        bbxStorage[index] = slotNums[i];
                    }
                }
            }
            break;
            case 2:
            for (int i = 0; i < slotNums.length; i++) {
                ItemStack comparedStack = inventory.getItem(slotNums[i]);
                if (comparedStack != null) {
                    if (BobuxUtils.checkWithoutDuraAmnt(comparedStack, BobuxItemInterface.bobuxCube)) {
                        index++;
                        bbxStorage[index] = slotNums[i];
                    }                
                }

            }
            break;
            case 3:
            for (int i = 0; i < slotNums.length; i++) {
                ItemStack comparedStack = inventory.getItem(slotNums[i]);
                if (comparedStack != null) {
                    if (BobuxUtils.checkWithoutDuraAmnt(comparedStack, BobuxItemInterface.bobuxTesseract)) {
                        index++;
                        bbxStorage[index] = slotNums[i];
                    }                
                }
            }
            break;
        }
        if (index == -1) {
            return null;
        } 
        int[] finalArray = new int[index+1];
        System.arraycopy(bbxStorage, 0, finalArray, 0, index+1);
        return intBubbleSort(finalArray);
    }

    private boolean validate() {
        if (calculateTotalBBX() >= cost) {
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

    private void removeTotalBBX() {
        if (validate()) {
            ItemStack[] tempStack = new ItemStack[slotNums.length];
            for (int i = 0; i < slotNums.length; i++) {
                tempStack[i] = inventory.getItem(i);
            }
            int[] bbxStack = sortBBXTypes(tempStack, 0);
            int[] squareStack = sortBBXTypes(tempStack, 1);
            int[] cubeStack = sortBBXTypes(tempStack, 2);
            int[] tesseractStack = sortBBXTypes(tempStack, 3);
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
                        }
                        inventory.setItem(bbxStack[0], intermStack);
                    } 
                }
            inventory.addItem(stack);
        } else {
            player.sendMessage("You do not have enough bobux to go through with this transaction.");
        }
    }

}
