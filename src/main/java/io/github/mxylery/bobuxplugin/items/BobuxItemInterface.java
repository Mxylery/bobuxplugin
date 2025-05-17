package io.github.mxylery.bobuxplugin.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Server;
import org.bukkit.enchantments.Enchantment;

import io.github.mxylery.bobuxplugin.actions.*;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.ability_types.AbilityOneTime;

/**
 * The class where all items will be instantiated and retrieved. 
 * They will all be public static variables, so a simple BobuxItemInterface.<itemname>.initializeStack() will be the method to retrieve items.
 * 
 * There are many options when it comes to making bobux items; a bobux item REQUIRES the following:
 * 
 * private static ItemStack (Stack)
 * private static String[] (Desc)
 * private static String (Name)
 * private static BobuxAction[] (ActionList)
 * private static BobuxAbility (Ability)
 * public static BobuxItem (Item)
 * 
 * There are a lot of optional things too, which will require a bit of effort to use
 * 
 * Enchantments: If you would like to add enchantments to a bobux item, you must initialize
 * an array of enchantments (EnchantList) and an array of integers (EnchantLevels)
 * 
 * RandomAction: To use a random action, you must initialize a 2D array of BobuxActions and 
 * an array of weights (that add up to 1) corresponding to the chances to proc each set of actions.
 * 
 * 
 * 
 * https://www.gamergeeks.net/apps/minecraft/color-codes for examples of colored text
 */

public class BobuxItemInterface {

    //As of 0.2.3, generates 3 items randomly.
    public static BobuxItem[] marketMenu = new BobuxItem[3];
    public static void randomizeMarketItems() {
        BobuxItem[] randomStack = new BobuxItem[3];
        int[] noDupe = new int[3];
        for (int i = 0; i < 3; i++) {
            int rng = (int) (Math.random()*marketItemTotal);
            for (int j = 0; j < i; j++) {
                if (rng == noDupe[j]) {
                    rng = (int) (Math.random()*marketItemTotal);
                    j = -1;
                }
            }
            switch(rng) {
                case 0: marketMenu[i] = bounceBoots;
                break;
                case 1: marketMenu[i] = hurriedStopwatch;
                break;
                case 2: marketMenu[i] = harmfulSubstance;
                break;
                case 3:
                break;
                case 4:
                break;
                case 5:
                break;
                case 6:
                break;
                case 7:
                break;
                case 8:
                break;
                case 9:
                break;
                case 10:
                break;
                case 11:
                break;
                case 12:
                break;
            }
            noDupe[i] = rng;
        }
    }

    private static final int itemTotal = 9;
    private static final int marketItemTotal = 3;
    private static Server server = BobuxTimer.getServer();
    private static ItemFactory itemFactory = server.getItemFactory();

    //Items start here
    private static ItemStack testingItemStack = new ItemStack(Material.IRON_INGOT);
    private static String[] testingItemDesc = 
    {"§7§oBalls"};
    private static String testingItemName = "§r§fTesting Item";
    private static BobuxAction[] testingItemActionList = 
    {new DamageEntity(5, false)};
    private static BobuxAbility testingItemAbility = new AbilityOneTime
    (20, null, testingItemActionList, "Testing Item Abil", false);
    public static BobuxItem testingItem = 
    new BobuxItem(testingItemStack, testingItemDesc, testingItemName, testingItemAbility, 
    null, null, false, 5000);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bouncingItemStack = new ItemStack(Material.SLIME_BALL);
    private static String[] bouncingItemDesc = 
    {"§7§oLegendary Bounce Ball", 
    "§7§oSecond Line Because Why Not"};
    private static String bouncingItemName = "§r§fBouncing Item";
    private static BobuxAction[] bouncingItemActionList = 
    {new ChangeVelocity(1, false)};
    private static BobuxAbility bouncingItemAbility = new AbilityOneTime
    (100, null, bouncingItemActionList, "Bouncing Item Abil", false);
    public static BobuxItem bouncingItem = 
    new BobuxItem(bouncingItemStack, bouncingItemDesc, bouncingItemName, bouncingItemAbility,
    null, null, false);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bounceBootsStack = new ItemStack(Material.LEATHER_BOOTS);
    private static String[] bounceBootsDesc = 
    {"§7§oThese boots make you bounce when you jump.",
    "§7§oNo cooldown"};
    private static String bounceBootsName = "§r§fBouncing Boots";
    private static BobuxAction[] bounceBootsActionList = 
    {new ChangeVelocity(0.8, false)};
    private static BobuxAbility bounceBootsAbility = new AbilityOneTime
    (1, null, bounceBootsActionList, "Bouncing Boots Abil", false);
    private static Enchantment[] bounceBootsEnchantList = {Enchantment.FEATHER_FALLING};
    private static int[] bounceBootsEnchantLevels = {3};
    public static BobuxItem bounceBoots = new BobuxItem
    (bounceBootsStack, bounceBootsDesc, bounceBootsName, bounceBootsAbility,
     bounceBootsEnchantList, bounceBootsEnchantLevels, true, 10);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack harmfulSubstanceStack = new ItemStack(Material.FERMENTED_SPIDER_EYE);
    private static String[] harmfulSubstanceDesc = 
    {"§7§oSome say this substance can cause harm..."};
    private static String harmfulSubstanceName = "§r§fHarmful Substance?";
    //
    // Example of making a random action
    //
    private static BobuxAction[][] randomHarmfulSubstanceActionList1 = 
    {
    {new EffectGive(PotionEffectType.REGENERATION, 300, 1, false), 
    new EffectGive(PotionEffectType.ABSORPTION, 300, 0, false)
    },
    {new EffectGive(PotionEffectType.POISON, 300, 1, false), 
    new DamageEntity(4, false)
    }
    };
    private static double[] randomHarmfulSubstanceActionList1Weights = {0.2, 0.8};
    //
    //
    //
    private static BobuxAction[] harmfulSubstanceActionList = 
    {new RandomAction(randomHarmfulSubstanceActionList1, 
    randomHarmfulSubstanceActionList1Weights, 
    false)};
    private static BobuxAbility harmfulSubstanceAbility = new AbilityOneTime 
    ((long) 250, null, harmfulSubstanceActionList, "Harmful Substance Abil", false);
    private static Enchantment[] harmfulSubstanceEnchantList = {Enchantment.SHARPNESS};
    private static int[] harmfulSubstanceEnchantLevels = {3};
    public static BobuxItem harmfulSubstance = new BobuxItem
    (harmfulSubstanceStack, harmfulSubstanceDesc, harmfulSubstanceName, harmfulSubstanceAbility, 
    harmfulSubstanceEnchantList, harmfulSubstanceEnchantLevels, false);
    //////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack hurriedStopwatchStack = new ItemStack(Material.CLOCK);
    private static String[] hurriedStopwatchDesc = 
    {"§7§oRight clicking gives the user Speed III for 5 seconds",
    "§7§o15s CD"};
    private static String hurriedStopwatchName = "§r§fHurried Stopwatch";
    private static BobuxAction[] hurriedStopwatchActionList = 
    {new EffectGive(PotionEffectType.SPEED, 100, 2, false)};
    private static BobuxAbility hurriedStopwatchAbility = new AbilityOneTime
    (300, null, hurriedStopwatchActionList, hurriedStopwatchName, false);
    private static Enchantment[] hurriedStopwatchEnchantList = {Enchantment.UNBREAKING, Enchantment.CHANNELING};
    private static int[] hurriedStopwatchEnchantLevels = {2, 2};
    public static BobuxItem hurriedStopwatch = new BobuxItem
    (hurriedStopwatchStack, hurriedStopwatchDesc, hurriedStopwatchName, hurriedStopwatchAbility, 
    hurriedStopwatchEnchantList, hurriedStopwatchEnchantLevels, false);
    //////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxStack = new ItemStack(Material.LIME_DYE);
    private static String[] bobuxDesc = 
    {"§7§oOfficial currency of the Bobux SMP",
    "§7§oWorth 1 $BBX"};
    private static String bobuxName = "§l§aBobux";
    public static BobuxItem bobux = new BobuxItem
    (bobuxStack, bobuxDesc, bobuxName, null, 
    null, null, false);
    //////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxSquareStack = new ItemStack(Material.LIME_WOOL);
    private static String[] bobuxSquareDesc = 
    {"§7§oOfficial currency of the Bobux SMP",
    "§7§oWorth 4 $BBX"};
    private static String bobuxSquareName = "§l§aBobux Square";
    public static BobuxItem bobuxSquare = new BobuxItem
    (bobuxSquareStack, bobuxSquareDesc, bobuxSquareName, null, 
    null, null, false);
    //////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxCubeStack = new ItemStack(Material.LIME_TERRACOTTA);
    private static String[] bobuxCubeDesc = 
    {"§7§oOfficial currency of the Bobux SMP",
    "§7§oWorth 16 $BBX"};
    private static String bobuxCubeName = "§l§aBobux Cube";
    public static BobuxItem bobuxCube = new BobuxItem
    (bobuxCubeStack, bobuxCubeDesc, bobuxCubeName, null, 
    null, null, false);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxTesseractStack = new ItemStack(Material.EMERALD_BLOCK);
    private static String[] bobuxTesseractDesc = 
    {"§7§oOfficial currency of the Bobux SMP",
    "§7§oWorth 64 $BBX"};
    private static String bobuxTesseractName = "§l§aBobux Tesseract";
    public static BobuxItem bobuxTesseract = new BobuxItem
    (bobuxTesseractStack, bobuxTesseractDesc, bobuxTesseractName, null, 
    null, null, false);
    //////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack marketStack = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
    private static String[] marketDesc = 
    {"§6Access the daily market here."};
    private static String marketName = "§6Market";
    public static BobuxItem market = new BobuxItem
    (marketStack, marketDesc, marketName, null, 
    null, null, false);
    //////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack cleaverStack = new ItemStack(Material.GOLDEN_SWORD);
    private static String[] cleaverDesc = 
    {"§7§oHitting mobs with this sword cleaves the mobs behind it.",
    "§7§o5s CD"};
    private static String cleaverName = "§r§fCleaver";
    private static BobuxAction[] cleaverActionList = 
    {new DamageEntity(2,false), 
    new ChangeVelocity(0.5, false)};
    private static BobuxAbility cleaverAbility = new AbilityOneTime
    (100, null, cleaverActionList, "Cleaver Ability", false);
    public static BobuxItem cleaver = new BobuxItem
    (cleaverStack, cleaverDesc, cleaverName, cleaverAbility, 
    null, null, true);
    //////////////////////////////////////////////////////////////////////////////////////////

}

