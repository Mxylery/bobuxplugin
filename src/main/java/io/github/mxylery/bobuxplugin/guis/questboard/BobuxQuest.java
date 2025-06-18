package io.github.mxylery.bobuxplugin.guis.questboard;

import io.github.mxylery.bobuxplugin.items.BobuxItem;

public abstract class BobuxQuest {

    protected Quest[] questArray;
    protected BobuxItem[] drops;
    protected int[] dropAmounts;
    protected boolean completed;
    protected int compensation;
    protected int questAmount;
    
    public BobuxQuest(int questAmount) {
        this.completed = false;
        this.questAmount = questAmount;
        this.questArray = new Quest[questAmount];
        this.dropAmounts = new int[questAmount];
        this.drops = new BobuxItem[questAmount];
        generateQuests();
    }

    public Quest[] getQuests() {
        return questArray;
    }

    public BobuxItem getDrop(int index) {
        return drops[index];
    }

    public int getDropAmount(int index) {
        return dropAmounts[index];
    }

    protected void generateDrops() {
        BobuxItem[] dropArray = new BobuxItem[questAmount];
        for (int i = 0; i < questAmount; i++) {
            dropArray[i] = questArray[i].getDrop();
            dropAmounts[i] = (int) (Math.random()*(questArray[i].max - questArray[i].min) + questArray[i].min);
        }
        drops = dropArray;
    }

    public boolean getState() {
        return completed;
    }

    public void setState(boolean completed) {
        this.completed = completed;
    }

    protected abstract void generateQuests();

    public int getCompensation() {
        return compensation;
    }
}
