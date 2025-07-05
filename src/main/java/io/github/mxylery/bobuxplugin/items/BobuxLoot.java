package io.github.mxylery.bobuxplugin.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

public class BobuxLoot {

    private final static int LESSER_LOOT_AMNT = 16;
    
    public static ItemStack[] generateLesserStack(int amount) {
        int[] noDupe = new int[amount];
        ItemStack[] finalArray = new ItemStack[amount];
        for (int i = 0; i < amount; i++) {
            int rng = (int) (Math.random()*LESSER_LOOT_AMNT);
            for (int j = 0; j < i; j++) {
                if (rng == noDupe[j]) {
                    rng = (int) (Math.random()*LESSER_LOOT_AMNT);
                    j = -1;
                }
            }
            switch(rng) {
                case 0: finalArray[i] = BobuxItemInterface.bouncingItem.getStack();
                finalArray[i].setAmount(8);
                break;
                case 1: finalArray[i] = new ItemStack(Material.IRON_INGOT, 4);
                break;
                case 2: int logRng = (int) (Math.random()*9);
                Material material;
                switch (logRng) {
                    case 0: material = Material.OAK_LOG;
                    break;
                    case 1: material = Material.BIRCH_LOG;
                    break;
                    case 2: material = Material.ACACIA_LOG;
                    break;
                    case 3: material = Material.DARK_OAK_LOG;
                    break;
                    case 4: material = Material.JUNGLE_LOG;
                    break;
                    case 5: material = Material.CHERRY_LOG;
                    break;
                    case 6: material = Material.SPRUCE_LOG;
                    break;
                    case 7: material = Material.MANGROVE_LOG;
                    break;
                    case 8: material = Material.PALE_OAK_LOG;
                    break;
                    default: material = Material.OAK_LOG;
                    break;
                }
                finalArray[i] = new ItemStack(material, 64);
                break; 
                case 3: finalArray[i] = BobuxItemInterface.freakyCarrot.getStack();
                finalArray[i].setAmount(4);
                break; 
                case 4: finalArray[i] = BobuxItemInterface.freakySeeds.getStack();
                finalArray[i].setAmount(4);
                break;
                case 5: finalArray[i] = BobuxItemInterface.freakyWheat.getStack();
                finalArray[i].setAmount(4);
                break;
                case 6: finalArray[i] = new ItemStack(Material.DIAMOND);
                break;
                case 7: finalArray[i] = new ItemStack(Material.COOKED_BEEF);
                finalArray[i].setAmount(16);
                break;
                case 8: finalArray[i] = BobuxItemInterface.abcBlood.getStack();
                finalArray[i].setAmount(3);
                break;
                case 9: finalArray[i] = BobuxItemInterface.stinkyResidue.getStack();
                finalArray[i].setAmount(3);
                break;
                case 10: finalArray[i] = BobuxItemInterface.BW5Ammo.getStack();
                finalArray[i].setAmount(4);
                break;
                case 11: finalArray[i] = BobuxItemInterface.bobux.getStack();
                finalArray[i].setAmount(4);
                break;
                case 12: finalArray[i] = BobuxItemInterface.bobuxSquare.getStack();
                finalArray[i].setAmount(2);
                break;
                case 13: finalArray[i] = new ItemStack(Material.QUARTZ);
                finalArray[i].setAmount(64);
                break;
                case 14: finalArray[i] = new ItemStack(Material.ENDER_EYE);
                finalArray[i].setAmount(2);
                break;
                case 15: finalArray[i] = new ItemStack(Material.ENCHANTED_BOOK);
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) finalArray[i].getItemMeta();
                meta.addStoredEnchant(Enchantment.SHARPNESS, 1, true);
                meta.addStoredEnchant(Enchantment.EFFICIENCY, 1, true);
                meta.addStoredEnchant(Enchantment.PROTECTION, 1, true);
                finalArray[i].setItemMeta(meta);
                break;
                default:
                break;
            }
            noDupe[i] = rng;
        }
        return finalArray;
    }

    public static ItemStack[] generateNormalStack(int amount) {
        int[] noDupe = new int[amount];
        ItemStack[] finalArray = new ItemStack[amount];
        for (int i = 0; i < amount; i++) {
            int rng = (int) (Math.random()*LESSER_LOOT_AMNT);
            for (int j = 0; j < i; j++) {
                if (rng == noDupe[j]) {
                    rng = (int) (Math.random()*LESSER_LOOT_AMNT);
                    j = -1;
                }
            }
            switch(rng) {
                case 0: finalArray[i] = BobuxItemInterface.bouncingItem.getStack();
                finalArray[i].setAmount(64);
                break;
                case 1: finalArray[i] = new ItemStack(Material.IRON_INGOT, 4);
                break;
                case 2: int logRng = (int) (Math.random()*9);
                Material material;
                switch (logRng) {
                    case 0: material = Material.OAK_LOG;
                    break;
                    case 1: material = Material.BIRCH_LOG;
                    break;
                    case 2: material = Material.ACACIA_LOG;
                    break;
                    case 3: material = Material.DARK_OAK_LOG;
                    break;
                    case 4: material = Material.JUNGLE_LOG;
                    break;
                    case 5: material = Material.CHERRY_LOG;
                    break;
                    case 6: material = Material.SPRUCE_LOG;
                    break;
                    case 7: material = Material.MANGROVE_LOG;
                    break;
                    case 8: material = Material.PALE_OAK_LOG;
                    break;
                    default: material = Material.OAK_LOG;
                    break;
                }
                finalArray[i] = new ItemStack(material, 64);
                break; 
                case 3: finalArray[i] = BobuxItemInterface.freakyCarrot.getStack();
                finalArray[i].setAmount(4);
                break; 
                case 4: finalArray[i] = BobuxItemInterface.freakySeeds.getStack();
                finalArray[i].setAmount(4);
                break;
                case 5: finalArray[i] = BobuxItemInterface.freakyWheat.getStack();
                finalArray[i].setAmount(4);
                break;
                case 6: finalArray[i] = new ItemStack(Material.DIAMOND);
                break;
                case 7: finalArray[i] = new ItemStack(Material.COOKED_BEEF);
                finalArray[i].setAmount(16);
                break;
                case 8: finalArray[i] = BobuxItemInterface.abcBlood.getStack();
                finalArray[i].setAmount(3);
                break;
                case 9: finalArray[i] = BobuxItemInterface.stinkyResidue.getStack();
                finalArray[i].setAmount(3);
                break;
                case 10: finalArray[i] = BobuxItemInterface.BW5Ammo.getStack();
                finalArray[i].setAmount(4);
                break;
                case 11: finalArray[i] = BobuxItemInterface.bobux.getStack();
                finalArray[i].setAmount(4);
                break;
                case 12: finalArray[i] = BobuxItemInterface.bobuxSquare.getStack();
                finalArray[i].setAmount(2);
                break;
                case 13: finalArray[i] = new ItemStack(Material.QUARTZ);
                finalArray[i].setAmount(64);
                break;
                case 14: finalArray[i] = new ItemStack(Material.ENDER_EYE);
                finalArray[i].setAmount(2);
                break;
                case 15: finalArray[i] = new ItemStack(Material.ENCHANTED_BOOK);
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) finalArray[i].getItemMeta();
                meta.addStoredEnchant(Enchantment.SHARPNESS, 1, true);
                meta.addStoredEnchant(Enchantment.EFFICIENCY, 1, true);
                meta.addStoredEnchant(Enchantment.PROTECTION, 1, true);
                finalArray[i].setItemMeta(meta);
                break;
                default:
                break;
            }
            noDupe[i] = rng;
        }
        return finalArray;
    }

    public static ItemStack[] generateGreaterStack(int amount) {
        int[] noDupe = new int[amount];
        ItemStack[] finalArray = new ItemStack[amount];
        for (int i = 0; i < amount; i++) {
            int rng = (int) (Math.random()*LESSER_LOOT_AMNT);
            for (int j = 0; j < i; j++) {
                if (rng == noDupe[j]) {
                    rng = (int) (Math.random()*LESSER_LOOT_AMNT);
                    j = -1;
                }
            }
            switch(rng) {
                case 0: finalArray[i] = BobuxItemInterface.bouncingItem.getStack();
                finalArray[i].setAmount(8);
                break;
                case 1: finalArray[i] = new ItemStack(Material.IRON_INGOT, 4);
                break;
                case 2: int logRng = (int) (Math.random()*9);
                Material material;
                switch (logRng) {
                    case 0: material = Material.OAK_LOG;
                    break;
                    case 1: material = Material.BIRCH_LOG;
                    break;
                    case 2: material = Material.ACACIA_LOG;
                    break;
                    case 3: material = Material.DARK_OAK_LOG;
                    break;
                    case 4: material = Material.JUNGLE_LOG;
                    break;
                    case 5: material = Material.CHERRY_LOG;
                    break;
                    case 6: material = Material.SPRUCE_LOG;
                    break;
                    case 7: material = Material.MANGROVE_LOG;
                    break;
                    case 8: material = Material.PALE_OAK_LOG;
                    break;
                    default: material = Material.OAK_LOG;
                    break;
                }
                finalArray[i] = new ItemStack(material, 64);
                break; 
                case 3: finalArray[i] = BobuxItemInterface.freakyCarrot.getStack();
                finalArray[i].setAmount(4);
                break; 
                case 4: finalArray[i] = BobuxItemInterface.freakySeeds.getStack();
                finalArray[i].setAmount(4);
                break;
                case 5: finalArray[i] = BobuxItemInterface.freakyWheat.getStack();
                finalArray[i].setAmount(4);
                break;
                case 6: finalArray[i] = new ItemStack(Material.DIAMOND);
                break;
                case 7: finalArray[i] = new ItemStack(Material.COOKED_BEEF);
                finalArray[i].setAmount(16);
                break;
                case 8: finalArray[i] = BobuxItemInterface.abcBlood.getStack();
                finalArray[i].setAmount(3);
                break;
                case 9: finalArray[i] = BobuxItemInterface.stinkyResidue.getStack();
                finalArray[i].setAmount(3);
                break;
                case 10: finalArray[i] = BobuxItemInterface.BW5Ammo.getStack();
                finalArray[i].setAmount(4);
                break;
                case 11: finalArray[i] = BobuxItemInterface.bobux.getStack();
                finalArray[i].setAmount(4);
                break;
                case 12: finalArray[i] = BobuxItemInterface.bobuxSquare.getStack();
                finalArray[i].setAmount(2);
                break;
                case 13: finalArray[i] = new ItemStack(Material.QUARTZ);
                finalArray[i].setAmount(64);
                break;
                case 14: finalArray[i] = new ItemStack(Material.ENDER_EYE);
                finalArray[i].setAmount(2);
                break;
                case 15: finalArray[i] = new ItemStack(Material.ENCHANTED_BOOK);
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) finalArray[i].getItemMeta();
                meta.addStoredEnchant(Enchantment.SHARPNESS, 1, true);
                meta.addStoredEnchant(Enchantment.EFFICIENCY, 1, true);
                meta.addStoredEnchant(Enchantment.PROTECTION, 1, true);
                finalArray[i].setItemMeta(meta);
                break;
                default:
                break;
            }
            noDupe[i] = rng;
        }
        return finalArray;
    }
}
