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

    private void setUnbreakables() {
        bounceBootsStack.getItemMeta().setUnbreakable(true);
    }

    private static final int itemTotal = 3;
    private static Server server = BobuxTimer.getServer();
    private static ItemFactory itemFactory = server.getItemFactory();

    //Items start here
    private static ItemStack testingItemStack = new ItemStack(Material.IRON_INGOT);
    private static String[] testingItemDesc = 
    {"Balls"};
    private static String testingItemName = "Testing Item";
    private static BobuxAction[] testingItemActionList = 
    {new DamageEntity(5, false)};
    private static BobuxAbility testingItemAbility = new AbilityOneTime
    (100, null, testingItemActionList, "Testing Item Abil");
    public static BobuxItem testingItem = 
    new BobuxItem(testingItemStack, testingItemDesc, testingItemName, testingItemAbility, 
    null, null, false, 5000);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bouncingItemStack = new ItemStack(Material.SLIME_BALL);
    private static String[] bouncingItemDesc = 
    {"Legendary Bounce Ball", 
    "Second Line Because Why Not"};
    private static String bouncingItemName = "Bouncing Item";
    private static BobuxAction[] bouncingItemActionList = 
    {new ChangeVelocity(1, false)};
    private static BobuxAbility bouncingItemAbility = new AbilityOneTime
    (100, null, bouncingItemActionList, "Bouncing Item Abil");
    public static BobuxItem bouncingItem = 
    new BobuxItem(bouncingItemStack, bouncingItemDesc, bouncingItemName, bouncingItemAbility,
    null, null, false);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bounceBootsStack = new ItemStack(Material.LEATHER_BOOTS);
    private static String[] bounceBootsDesc = 
    {"§7These boots make you bounce when you jump.",
    "§l§aNo cooldown"};
    private static String bounceBootsName = "Bouncing Boots";
    private static BobuxAction[] bounceBootsActionList = 
    {new ChangeVelocity(0.8, false)};
    private static BobuxAbility bounceBootsAbility = new AbilityOneTime
    (1, null, bounceBootsActionList, "Bouncing Boots Abil");
    private static Enchantment[] bounceBootsEnchantList = {Enchantment.FEATHER_FALLING};
    private static int[] bounceBootsEnchantLevels = {3};
    public static BobuxItem bounceBoots = new BobuxItem
    (bounceBootsStack, bounceBootsDesc, bounceBootsName, bounceBootsAbility,
     bounceBootsEnchantList, bounceBootsEnchantLevels, true, 10);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack harmfulSubstanceStack = new ItemStack(Material.FERMENTED_SPIDER_EYE);
    private static String[] harmfulSubstanceDesc = 
    {"§7Some say this substance can cause harm..."};
    private static String harmfulSubstanceName = "Harmful Substance?";
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
    ((long) 250, null, harmfulSubstanceActionList, "Harmful Substance Abil");
    private static Enchantment[] harmfulSubstanceEnchantList = {Enchantment.SHARPNESS};
    private static int[] harmfulSubstanceEnchantLevels = {3};
    public static BobuxItem harmfulSubstance = new BobuxItem
    (harmfulSubstanceStack, harmfulSubstanceDesc, harmfulSubstanceName, harmfulSubstanceAbility, 
    harmfulSubstanceEnchantList, harmfulSubstanceEnchantLevels, false);
    //////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack hurriedStopwatchStack = new ItemStack(Material.CLOCK);
    private static String[] hurriedStopwatchDesc = 
    {"§7Right clicking gives the user Speed III for 5 seconds",
    "§l§a 15s CD"};
    private static String hurriedStopwatchName = "Hurried Stopwatch";
    private static BobuxAction[] hurriedStopwatchActionList = 
    {new EffectGive(PotionEffectType.SPEED, 100, 2, false)};
    private static BobuxAbility hurriedStopwatchAbility = new AbilityOneTime
    (300, null, hurriedStopwatchActionList, hurriedStopwatchName);
    private static Enchantment[] hurriedStopwatchEnchantList = {Enchantment.UNBREAKING, Enchantment.CHANNELING};
    private static int[] hurriedStopwatchEnchantLevels = {2, 2};
    public static BobuxItem hurriedStopwatch = new BobuxItem
    (hurriedStopwatchStack, hurriedStopwatchDesc, hurriedStopwatchName, hurriedStopwatchAbility, 
    hurriedStopwatchEnchantList, hurriedStopwatchEnchantLevels, false);
    //////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxStack = new ItemStack(Material.LIME_DYE);
    private static String[] bobuxDesc = 
    {"§l§aOfficial currency of the Bobux SMP",
    "§l§aWorth 1 $BBX"};
    private static String bobuxName = "§l§aBobux";
    public static BobuxItem bobux = new BobuxItem
    (bobuxStack, bobuxDesc, bobuxName, null, 
    null, null, false);
    //////////////////////////////////////////////////////////////////////////////////////////

}

