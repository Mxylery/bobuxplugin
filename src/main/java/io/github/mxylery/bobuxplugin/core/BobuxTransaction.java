package io.github.mxylery.bobuxplugin.core;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import io.github.mxylery.bobuxplugin.guis.bounty.BobuxBounty;
import io.github.mxylery.bobuxplugin.guis.questboard.BobuxBeginnerQuest;
import io.github.mxylery.bobuxplugin.items.BobuxItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public class BobuxTransaction {
    
    private int cost;
    private Player player;
    private ItemStack stack;
    PlayerInventory inventory;
    private int[] slotNums = new int[36];
    private Object object;
    private boolean wentThrough;

    public BobuxTransaction(Player player, int cost, ItemStack stack) {
        this.player = player;
        this.cost = cost;
        this.stack = stack;
        inventory = player.getInventory();
        removeTotalBBX();
    }

    //maybe use this class for bounties.
    public BobuxTransaction(Player player, Object object) {
        this.player = player;
        this.object = object;
        inventory = player.getInventory();
        removeTotalItems();
    }

    public boolean wentThrough() {
        return wentThrough;
    }

    private boolean checkTotalItems() {
        if (object instanceof BobuxBounty) {
            BobuxBounty bounty = (BobuxBounty) object;
            int itemTotal0 = 0;
            int itemTotal1 = 0;
            int itemTotal2 = 0;
            ItemStack bounty0 = bounty.getStacks()[0];
            ItemStack bounty1 = bounty.getStacks()[1];
            ItemStack bounty2 = bounty.getStacks()[2];
            int size = -1;
            for (int i = 0; i < 36; i++) {
                ItemStack currStack = inventory.getItem(i);
                if (currStack != null) {
                    if (currStack.getType().equals(bounty0.getType())) {
                        itemTotal0 += currStack.getAmount();
                        size++;
                        slotNums[size] = i;
                    } else if (currStack.getType().equals(bounty1.getType())){
                        itemTotal1 += currStack.getAmount();
                        size++;
                        slotNums[size] = i;
                    } else if (currStack.getType().equals(bounty2.getType())){
                        itemTotal2 += currStack.getAmount();
                        size++;
                        slotNums[size] = i;
                    }
                }
            }
            if (size == -1) {
                player.sendMessage("You need more items to complete this bounty!");
                player.playSound(player, Sound.BLOCK_BELL_USE, 0.5f, 1f);
                return false;
            } 
            if (itemTotal0 >= bounty0.getAmount() && itemTotal1 >= bounty1.getAmount() && itemTotal2 >= bounty2.getAmount()) {
                int[] newSlots = new int[size+1];
                System.arraycopy(slotNums, 0, newSlots, 0, size+1);
                slotNums = newSlots;
                return true;
            } else {
                player.sendMessage("You need more items to complete this bounty!");
                player.playSound(player, Sound.BLOCK_BELL_USE, 0.5f, 1f);
                
                return false;
            }
        } else if (object instanceof BobuxBeginnerQuest) {
            BobuxBeginnerQuest quest = (BobuxBeginnerQuest) object;
            int itemTotal0 = 0;
            int itemTotal1 = 0;
            int itemTotal2 = 0;
            BobuxItem quest0 = quest.getDrop(0);
            BobuxItem quest1 = quest.getDrop(1);
            BobuxItem quest2 = quest.getDrop(2);
            int size = -1;
            for (int i = 0; i < 36; i++) {
                ItemStack currStack = inventory.getItem(i);
                if (currStack != null) {
                    if (BobuxUtils.checkWithoutDuraAmnt(currStack, quest0)) {
                        itemTotal0 += currStack.getAmount();
                        size++;
                        slotNums[size] = i;
                    } else if (BobuxUtils.checkWithoutDuraAmnt(currStack, quest1)){
                        itemTotal1 += currStack.getAmount();
                        size++;
                        slotNums[size] = i;
                    } else if (BobuxUtils.checkWithoutDuraAmnt(currStack, quest2)) {
                        itemTotal2 += currStack.getAmount();
                        size++;
                        slotNums[size] = i;
                    }
                }
            }
            //If there are no matching items detected in the inventory
            if (size == -1) {
                player.sendMessage("You are missing mob drops for this quest!");
                player.playSound(player, Sound.BLOCK_BELL_USE, 0.5f, 1f);
                return false;
            } 
            //If there is a matching amount of items detected in the inventory
            if (itemTotal0 >= quest.getDropAmount(0) && itemTotal1 >= quest.getDropAmount(1) && itemTotal2 >= quest.getDropAmount(2)) {
                int[] newSlots = new int[size+1];
                System.arraycopy(slotNums, 0, newSlots, 0, size+1);
                slotNums = newSlots;
                return true;
            } else {
                player.sendMessage("You are missing mob drops for this quest!");
                player.playSound(player, Sound.BLOCK_BELL_USE, 0.5f, 1f);
                wentThrough = false;
                return false;
            }
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
                bbxTotal += 8*stack.getAmount();
                size++;
                slotNums[size] = i;
            } else if (BobuxUtils.checkWithoutDuraAmnt(stack, BobuxItemInterface.bobuxCube)){
                bbxTotal += 64*stack.getAmount();
                size++;
                slotNums[size] = i;
            } else if (BobuxUtils.checkWithoutDuraAmnt(stack, BobuxItemInterface.bobuxTesseract)){
                bbxTotal += 512*stack.getAmount();
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
                if (stack.getType().equals(comparedStack.getType())) {
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

    private int[] sortItemTypes(ItemStack[] stackList, BobuxItem bobuxItem) {
        int[] bbxStorage = new int[stackList.length];
        int index = -1;
        for (int i = 0; i < slotNums.length; i++) {
            ItemStack comparedStack = inventory.getItem(slotNums[i]);
            if (comparedStack != null) {
                if (BobuxUtils.checkWithoutDuraAmnt(comparedStack, bobuxItem)) {
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

    private int[] sortBBXTypes(ItemStack[] stackList, int bobuxType) {
        int[] bbxStorage = new int[slotNums.length];
        int index = -1;
        switch (bobuxType) {
            case 0:
            for (int i = 0; i < slotNums.length; i++) {
                ItemStack comparedStack = inventory.getItem(slotNums[i]);
                if (comparedStack != null) {
                    if (BobuxUtils.checkWithoutDuraAmnt(stackList[i], BobuxItemInterface.bobux)) {
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
                    if (BobuxUtils.checkWithoutDuraAmnt(stackList[i], BobuxItemInterface.bobuxSquare)) {
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
                    if (BobuxUtils.checkWithoutDuraAmnt(stackList[i], BobuxItemInterface.bobuxCube)) {
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
                    if (BobuxUtils.checkWithoutDuraAmnt(stackList[i], BobuxItemInterface.bobuxTesseract)) {
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
        if (object == null) {
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
            while (cost <= -64) {
                inventory.addItem(BobuxItemInterface.bobuxCube.getStack());
                cost += 64;
            }
            while (cost <= -8) {
                inventory.addItem(BobuxItemInterface.bobuxSquare.getStack());
                cost += 8;
            }
            while (cost <= -1) {
                inventory.addItem(BobuxItemInterface.bobux.getStack());
                cost += 1;
            }
        }
    }

    private void removeTotalItems() {
        if (checkTotalItems()) {
            if (object instanceof BobuxBounty) {
                BobuxBounty bounty = (BobuxBounty) object;
                if (!bounty.getState()) {
                    cost = -bounty.getCompensation();
                    removeBountyStacks(bounty); 
                }
            } else if (object instanceof BobuxBeginnerQuest) {
                BobuxBeginnerQuest quest = (BobuxBeginnerQuest) object;
                if (!quest.getState()) {
                    cost = -quest.getCompensation();
                    removeQuestStacks(quest);
                }
            }
        } 
    }

    private void removeQuestStacks(BobuxBeginnerQuest quest) {
        if (!quest.getState()) {
            ItemStack[] tempStack = new ItemStack[slotNums.length];
            for (int i = 0; i < slotNums.length; i++) {
                tempStack[i] = inventory.getItem(slotNums[i]);
            }
            BobuxItem quest0 = quest.getDrop(0);
            BobuxItem quest1 = quest.getDrop(1);
            BobuxItem quest2 = quest.getDrop(2);

            int[] itemStack0 = sortItemTypes(tempStack, quest0);
            int[] itemStack1 = sortItemTypes(tempStack, quest1);
            int[] itemStack2 = sortItemTypes(tempStack, quest2);

            int itemAmnt0 = quest.getDropAmount(0);
            int itemAmnt1 = quest.getDropAmount(1);
            int itemAmnt2 = quest.getDropAmount(2);

            removeXItemStacks(itemAmnt0, itemStack0);
            removeXItemStacks(itemAmnt1, itemStack1);
            removeXItemStacks(itemAmnt2, itemStack2);
            bobuxCompensator();
            player.playSound(player, Sound.ITEM_BOOK_PAGE_TURN, 0.5f, 1f);
            quest.setState(true);
            wentThrough = true;
        } else {
            player.sendMessage("This quest has already been completed!");
            player.playSound(player, Sound.BLOCK_BELL_USE, 0.5f, 1f);
            wentThrough = false;
        }
    }

    private void removeBountyStacks(BobuxBounty bounty) {
        if (!bounty.getState()) {
            ItemStack[] tempStack = new ItemStack[slotNums.length];
            for (int i = 0; i < slotNums.length; i++) {
                tempStack[i] = inventory.getItem(slotNums[i]);
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

            removeXItemStacks(itemAmnt0, itemStack0);
            removeXItemStacks(itemAmnt1, itemStack1);
            removeXItemStacks(itemAmnt2, itemStack2);
            bobuxCompensator();
            player.playSound(player, Sound.ITEM_BOOK_PAGE_TURN, 0.5f, 1f);
            bounty.setState(true);
            wentThrough = true;
        } else {
            player.sendMessage("This bounty has already been completed!");
            player.playSound(player, Sound.BLOCK_BELL_USE, 0.5f, 1f);
            wentThrough = false;
        }
    }

    private void removeXItemStacks(int amount, int[] stackIndexList) {
        ItemStack intermStack;
        while (amount > 0) {
            intermStack = inventory.getItem(stackIndexList[0]);
            int prevAmnt = intermStack.getAmount();
            intermStack.setAmount(prevAmnt - 1);
            amount--;
            prevAmnt--;
            if (prevAmnt == 0) {
                for (int i = 0; i < stackIndexList.length-1; i++) {
                    stackIndexList[i] = stackIndexList[i+1];
                }
            } else {
                inventory.setItem(stackIndexList[0], intermStack);
            }                
        }
    }

    private void removeTotalBBX() {
        if (validate()) {
            ItemStack[] tempStack = new ItemStack[slotNums.length];
            for (int i = 0; i < slotNums.length; i++) {
                tempStack[i] = inventory.getItem(slotNums[i]);
            }
            int[] bbxStack = sortBBXTypes(tempStack, 0);
            int[] squareStack = sortBBXTypes(tempStack,1);
            int[] cubeStack = sortBBXTypes(tempStack, 2);
            int[] tesseractStack = sortBBXTypes(tempStack, 3);
            boolean tessRanOut = false;
            boolean cubeRanOut = false;
            boolean squareRanOut = false;
            //This variable is used as an intermediate between the previous and next stacks.
            ItemStack intermStack;
            //This goes thru the inventory removing larger moneys first
            while (cost > 0) {
                //Checks the tesseracts
                if (tesseractStack != null && !tessRanOut) {
                    intermStack = inventory.getItem(tesseractStack[0]);
                    int prevAmnt = intermStack.getAmount();
                    intermStack.setAmount(prevAmnt - 1);
                    prevAmnt--;
                    cost -= 512;
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
                    } else if (cubeStack != null && tessRanOut && !cubeRanOut) {
                        intermStack = inventory.getItem(cubeStack[0]);
                        int prevAmnt = intermStack.getAmount();
                        intermStack.setAmount(prevAmnt - 1);
                        prevAmnt--;
                        cost -= 64;
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
                    } else if (squareStack != null && cubeRanOut && !squareRanOut) {
                        intermStack = inventory.getItem(squareStack[0]);
                        int prevAmnt = intermStack.getAmount();
                        intermStack.setAmount(prevAmnt - 1);
                        prevAmnt--;
                        cost -= 8;
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
            if (!stack.getType().equals(Material.AIR)) {
                inventory.addItem(stack);
            }
            wentThrough = true;
        } else {
            player.sendMessage("You do not have enough bobux to go through with this transaction.");
            player.playSound(player, Sound.BLOCK_BELL_USE,0.1f,0.5f);
            wentThrough = false;
        } 
    }

}
