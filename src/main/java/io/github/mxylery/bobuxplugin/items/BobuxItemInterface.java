package io.github.mxylery.bobuxplugin.items;

import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Server;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;


import io.github.mxylery.bobuxplugin.abilities.*;
import io.github.mxylery.bobuxplugin.abilities.player_abilities.BW5Ability;
import io.github.mxylery.bobuxplugin.abilities.player_abilities.BouncingItemAbility;
import io.github.mxylery.bobuxplugin.abilities.player_abilities.CleaverAbility;
import io.github.mxylery.bobuxplugin.abilities.player_abilities.FruitcakeAndCookiesAbility;
import io.github.mxylery.bobuxplugin.abilities.player_abilities.HurriedStopwatchAbility;
import io.github.mxylery.bobuxplugin.abilities.player_abilities.KungFuGlovesAbility;
import io.github.mxylery.bobuxplugin.abilities.player_abilities.KungFuGlovesPassive;
import io.github.mxylery.bobuxplugin.abilities.player_abilities.LineSpawnerAbility;
import io.github.mxylery.bobuxplugin.abilities.player_abilities.RailgunAbility;
import io.github.mxylery.bobuxplugin.abilities.player_abilities.TestingItemAbility;
import io.github.mxylery.bobuxplugin.abilities.player_abilities.TheHotStickAbility;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;


/**
 * The class where all items will be instantiated and retrieved. \n
 * They will all be public static variables, so a simple BobuxItemInterface.<itemname>.initializeStack() will be the method to retrieve items. \n
 * 
 * There are many options when it comes to making bobux items; a bobux item REQUIRES the following: \n
 * 
 * private static ItemStack (Stack)\n
 * private static String[] (Desc)\n
 * private static String (Name)\n
 * private static BobuxAction[] (ActionList)\n
 * private static BobuxAbility (Ability)\n
 * public static BobuxItem (Item)\n
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

    private static final int itemTotal = 13;
    private static Server server = BobuxTimer.getServer();
    private static ItemFactory itemFactory = server.getItemFactory();

    private static ItemStack testingItemStack = new ItemStack(Material.IRON_INGOT);
    private static String[] testingItemDesc = 
    {"§7§oBalls"};
    private static String testingItemName = "§r§fTesting Item";
    public static BobuxItem testingItem = 
    new BobuxItem(testingItemStack, testingItemDesc, testingItemName, new TestingItemAbility("Testing Item Ability", false, 40), 5000);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bouncingItemStack = new ItemStack(Material.SLIME_BALL);
    private static String[] bouncingItemDesc = 
    {"§7§oLegendary Bounce Ball", 
    "§7§oSecond Line Because Why Not"};
    private static String bouncingItemName = "§r§fBouncing Item";
    public static BobuxItem bouncingItem = 
    new BobuxItem(bouncingItemStack, bouncingItemDesc, bouncingItemName, new BouncingItemAbility("Bouncing Item Ability", false, 20), 1);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack hurriedStopwatchStack = new ItemStack(Material.CLOCK);
    private static String[] hurriedStopwatchDesc = 
    {"§7Gives the user Speed III for 5 seconds",
    "§815s CD (Right Click)"};
    private static String hurriedStopwatchName = "§r§fHurried Stopwatch";
    private static Enchantment[] hurriedStopwatchEnchantList = {Enchantment.UNBREAKING, Enchantment.CHANNELING};
    private static int[] hurriedStopwatchEnchantLevels = {2, 2};
    public static BobuxItem hurriedStopwatch = new BobuxItem
    (hurriedStopwatchStack, hurriedStopwatchDesc, hurriedStopwatchName, new HurriedStopwatchAbility("Hurried Stopwatch Ability", false, 600), 
    hurriedStopwatchEnchantList, hurriedStopwatchEnchantLevels, false, 15);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxStack = new ItemStack(Material.LIME_DYE);
    private static String[] bobuxDesc = 
    {"§7Official currency of the Bobux SMP",
    "§8Worth 1 $BBX"};
    private static String bobuxName = "§l§aBobux";
    public static BobuxItem bobux = new BobuxItem
    (bobuxStack, bobuxDesc, bobuxName, 1);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxSquareStack = new ItemStack(Material.LIME_WOOL);
    private static String[] bobuxSquareDesc = 
    {"§7Official currency of the Bobux SMP",
    "§8Worth 8 $BBX"};
    private static String bobuxSquareName = "§l§aBobux Square";
    public static BobuxItem bobuxSquare = new BobuxItem
    (bobuxSquareStack, bobuxSquareDesc, bobuxSquareName, 8);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxCubeStack = new ItemStack(Material.LIME_TERRACOTTA);
    private static String[] bobuxCubeDesc = 
    {"§7Official currency of the Bobux SMP",
    "§8Worth 64 $BBX"};
    private static String bobuxCubeName = "§l§aBobux Cube";
    public static BobuxItem bobuxCube = new BobuxItem
    (bobuxCubeStack, bobuxCubeDesc, bobuxCubeName, 64);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxTesseractStack = new ItemStack(Material.EMERALD_BLOCK);
    private static String[] bobuxTesseractDesc = 
    {"§7Official currency of the Bobux SMP",
    "§8Worth 512 $BBX"};
    private static String bobuxTesseractName = "§l§aBobux Tesseract";
    public static BobuxItem bobuxTesseract = new BobuxItem
    (bobuxTesseractStack, bobuxTesseractDesc, bobuxTesseractName, 512);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack marketStack = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
    private static String[] marketDesc = 
    {"§7Access the daily market here.",
    "§8(Left or right click)"};
    private static String marketName = "§6§lMarket";
    public static BobuxItem market = new BobuxItem
    (marketStack, marketDesc, marketName);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack cleaverStack = new ItemStack(Material.GOLDEN_SWORD);
    private static String[] cleaverDesc = 
    {"§7Hitting mobs with this sword cleaves the mobs behind it.",
    "§85s CD"};
    private static String cleaverName = "§r§fCleaver";
    public static BobuxItem cleaver = new BobuxItem
    (cleaverStack, cleaverDesc, cleaverName, new CleaverAbility("Cleaver Ability", true, 100), true, 16);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack lineSpawnerStack = new ItemStack(Material.END_ROD);
    private static String[] lineSpawnerDesc = 
    {"§7test",
    "§71s CD"};
    private static String lineSpawnerName = "§r§fLine Spawner";
    public static BobuxItem lineSpawner = new BobuxItem
    (lineSpawnerStack, lineSpawnerDesc, lineSpawnerName, new LineSpawnerAbility("Line Spawner Passive", false, 5, 0), 16);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack railgunStack = new ItemStack(Material.IRON_HOE);
    private static String[] railgunDesc = 
    {"§7Shoot a beam that pierces enemies.",
    "§81s CD (Right Click)"};
    private static String railgunName = "§r§fRailgun";
    public static BobuxItem railgun = new BobuxItem
    (railgunStack, railgunDesc, railgunName, new RailgunAbility("Railgun Ability", false, 100), true, 50);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bountyStack = new ItemStack(Material.PAPER);
    private static String[] bountyDesc = 
    {"§7Access your bounties here.",
    "§8(Left or right click)"};
    private static String bountyName = "§c§lBounties";
    public static BobuxItem bounty = new BobuxItem
    (bountyStack, bountyDesc, bountyName);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack theHotStickStack = new ItemStack(Material.BLAZE_ROD);
    private static String[] theHotStickDesc = 
    {"§7Right click to launch a blazing line of pure fire!",
    "§85s CD (Right click)"};
    private static String theHotStickName = "§cTh§6e H§eot §6St§cick";
    public static BobuxItem theHotStick = new BobuxItem
    (theHotStickStack, theHotStickDesc, theHotStickName, new TheHotStickAbility("The Hot Stick Ability", false, 100), 50);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack BW5AmmoStack = new ItemStack(Material.BLUE_WOOL);
    private static String[] BW5AmmoDesc = 
    {"§7Ammunition used for the BW5 \"Nitro Express\" Rifle.",
    "§8This item will be consumed upon one use of the BW5."};
    private static String BW5AmmoName = "§1BW-5 Ammo";
    public static BobuxItem BW5Ammo = new BobuxItem
    (BW5AmmoStack, BW5AmmoDesc, BW5AmmoName, 4);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack BW5Stack = new ItemStack(Material.DIAMOND_HOE);
    private static String[] BW5Desc = 
    {"§7Nowhere would anyone expect this...",
    "§85s CD (Right click to use)"};
    private static String BW5Name = "§1BW-5 \"Nitro Express\"";
    public static BobuxItem BW5 = new BobuxItem
    (BW5Stack, BW5Desc, BW5Name, new BW5Ability("BW5 Ability", false, 100), 256);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack kungFuGlovesStack = new ItemStack(Material.BLACK_WOOL);
    private static String[] kungFuGlovesDesc = 
    {"§7Punch.",
    "§8Harness your inner zen..."};
    private static String kungFuGlovesName = "§7Kung Fu Gloves";
    private static BobuxAttributeSet kungFuGlovesAttributes1 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.ATTACK_DAMAGE, 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND);
    private static BobuxAttributeSet kungFuGlovesAttributes2 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.ATTACK_SPEED, -2.4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.MAINHAND);
    private static BobuxAttributeSet[] kungFuGlovesAttributeList = {kungFuGlovesAttributes1, kungFuGlovesAttributes2};
    public static BobuxItem kungFuGloves = new BobuxItem
    (kungFuGlovesStack, kungFuGlovesDesc, kungFuGlovesName, new KungFuGlovesAbility("Kung Fu Gloves Ability", false, 200), new KungFuGlovesPassive("Kung Fu Gloves Passive", false, 0, 40), kungFuGlovesAttributeList, true, 256);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack fruitcakeAndCookiesStack = new ItemStack(Material.BROWN_GLAZED_TERRACOTTA);
    private static String[] fruitcakeAndCookiesDesc = 
    {"§7Some fruitcake and cookies to ease your soul.",
    "§860s CD (Right Click)",
    "",
    "§o§8Maybe tears are endless...",
    "§o§8I just need some refreshment...",
    "§o§8Just to cool my heart"};
    private static String fruitcakeAndCookiesName = "§4Fruitcake §fand §6Cookies";
    public static BobuxItem fruitcakeAndCookies = new BobuxItem
    (fruitcakeAndCookiesStack, fruitcakeAndCookiesDesc, fruitcakeAndCookiesName, new FruitcakeAndCookiesAbility("Fruitcake And Cookies Ability", false, 600), true, 2048);
    //////////////////////////////////////////////////////////////////////////////////////////
     
}

