package io.github.mxylery.bobuxplugin.items;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BobuxLoot {

    private final static int LESSER_LOOT_AMNT = 16;
    private final static int NORMAL_LOOT_AMNT = 16;
    private final static int GREATER_LOOT_AMNT = 4;
    
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
                finalArray[i].setAmount(16);
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
            int rng = (int) (Math.random()*NORMAL_LOOT_AMNT);
            for (int j = 0; j < i; j++) {
                if (rng == noDupe[j]) {
                    rng = (int) (Math.random()*NORMAL_LOOT_AMNT);
                    j = -1;
                }
            }
            switch(rng) {
                case 0: finalArray[i] = BobuxItemInterface.bouncingItem.getStack();
                finalArray[i].setAmount(64);
                break;
                case 1: finalArray[i] = new ItemStack(Material.IRON_INGOT, 32);
                break;
                case 2: int logRng = (int) (Math.random()*9);
                Material material;
                switch (logRng) {
                    case 0: material = Material.MUSIC_DISC_BLOCKS;
                    break;
                    case 1: material = Material.MUSIC_DISC_CHIRP;
                    break;
                    case 2: material = Material.MUSIC_DISC_CAT;
                    break;
                    case 3: material = Material.MUSIC_DISC_STRAD;
                    break;
                    case 4: material = Material.MUSIC_DISC_MELLOHI;
                    break;
                    case 5: material = Material.MUSIC_DISC_STAL;
                    break;
                    case 6: material = Material.MUSIC_DISC_WARD;
                    break;
                    case 7: material = Material.MUSIC_DISC_MALL;
                    break;
                    case 8: material = Material.MUSIC_DISC_WAIT;
                    break;
                    default: material = Material.MUSIC_DISC_BLOCKS;
                    break;
                }
                finalArray[i] = new ItemStack(material, 1);
                break; 
                case 3: finalArray[i] = new ItemStack(Material.LEATHER);
                finalArray[i].setAmount(24);
                break; 
                case 4: PotionEffect potionEffect1 = new PotionEffect(PotionEffectType.HASTE, 3600, 2);
                PotionEffect potionEffect2 = new PotionEffect(PotionEffectType.SPEED, 3600, 2);
                finalArray[i] = new ItemStack(Material.POTION);
                PotionMeta potionMeta = (PotionMeta) finalArray[i].getItemMeta();
                potionMeta.addCustomEffect(potionEffect1, true);
                potionMeta.addCustomEffect(potionEffect2, true);
                potionMeta.setColor(Color.fromRGB(150, 150, 240));
                finalArray[i].setItemMeta(potionMeta);
                break;
                case 5: finalArray[i] = new ItemStack(Material.GOLDEN_CARROT);
                finalArray[i].setAmount(28);
                break;
                case 6: finalArray[i] = new ItemStack(Material.NETHERITE_SCRAP);
                finalArray[i].setAmount(2);
                break;
                case 7: finalArray[i] = new ItemStack(Material.BLAZE_ROD);
                finalArray[i].setAmount(32);
                break;
                case 8: finalArray[i] = new ItemStack(Material.GOLDEN_APPLE);
                finalArray[i].setAmount(4);
                break;
                case 9: finalArray[i] = new ItemStack(Material.GUNPOWDER);
                finalArray[i].setAmount(50);
                break;
                case 10: finalArray[i] = new ItemStack(Material.INK_SAC);
                finalArray[i].setAmount(24);
                break;
                case 11: finalArray[i] = BobuxItemInterface.bobuxSquare.getStack();
                finalArray[i].setAmount(2);
                break;
                case 12: finalArray[i] = BobuxItemInterface.bobuxCube.getStack();
                finalArray[i].setAmount(1);
                break;
                case 13: finalArray[i] = new ItemStack(Material.DIAMOND);
                finalArray[i].setAmount(2);
                break;
                case 14: finalArray[i] = new ItemStack(Material.GOLD_INGOT);
                finalArray[i].setAmount(16);
                break;
                case 15: finalArray[i] = new ItemStack(Material.ENCHANTED_BOOK);
                EnchantmentStorageMeta meta = (EnchantmentStorageMeta) finalArray[i].getItemMeta();
                meta.addStoredEnchant(Enchantment.SHARPNESS, 5, true);
                meta.addStoredEnchant(Enchantment.EFFICIENCY, 5, true);
                meta.addStoredEnchant(Enchantment.PROTECTION, 5, true);
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
            int rng = (int) (Math.random()*GREATER_LOOT_AMNT);
            for (int j = 0; j < i; j++) {
                if (rng == noDupe[j]) {
                    rng = (int) (Math.random()*GREATER_LOOT_AMNT);
                    j = -1;
                }
            }
            switch(rng) {
                case 0: finalArray[i] = BobuxItemInterface.bobuxCube.getStack();
                finalArray[i].setAmount(2);
                break;
                case 1: finalArray[i] = new ItemStack(Material.IRON_BLOCK, 24);
                break;
                case 2: finalArray[i] = BobuxItemInterface.culturalCoreLOTS.getStack();
                break;
                case 3: finalArray[i] = BobuxItemInterface.culturalCoreLure.getStack();
                break;
                default:
                break;
            }
            noDupe[i] = rng;
        }
        return finalArray;
    }
}
