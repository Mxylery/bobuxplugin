package io.github.mxylery.bobuxplugin.data_structures;

import java.util.HashMap;

import org.bukkit.inventory.ItemStack;

public class GUIStructure {
    
    private HashMap<Integer, ItemStack> slotStackMap;
    private int maxSlot;

    public GUIStructure(int maxSlot) {
        this.slotStackMap = new HashMap<Integer, ItemStack>();
        this.maxSlot = maxSlot;
    }

    public void removeSlot(int slot) {
        slotStackMap.remove(slot);
    }

    public void addSlot(int slot, ItemStack stack) {
        slotStackMap.put(slot, stack);
    }

    public int[] getIndices() {
        int[] indices = new int[maxSlot];
        for (int i = 0; i < maxSlot; i++) {
            if (slotStackMap.containsKey(i)) {
                indices[i] = i;
            }
        }
        return indices;
    }

    public ItemStack getStack(int slot) {
        return slotStackMap.get(slot);
    }

    public boolean hasSlot(int slot) {
        return slotStackMap.containsKey(slot);
    }

}
