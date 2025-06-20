package io.github.mxylery.bobuxplugin.items;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlotGroup;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ArmorMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;


import io.github.mxylery.bobuxplugin.abilities.player_abilities.*;


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
    {"§7Allows you to leap in the direction you are looking.", 
    "§81s CD (Left Click)",
    "§8Consumed on use"};
    private static String bouncingItemName = "§r§fBouncing Item";
    public static BobuxItem bouncingItem = 
    new BobuxItem(bouncingItemStack, bouncingItemDesc, bouncingItemName, new BouncingItemAbility("Bouncing Item Ability", false, 20), 3);
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
    hurriedStopwatchEnchantList, hurriedStopwatchEnchantLevels, true, true, 24);
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
    "§8(Left/Right Click)"};
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
    (cleaverStack, cleaverDesc, cleaverName, new CleaverAbility("Cleaver Ability", true, 100), true, 64);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack lineSpawnerStack = new ItemStack(Material.END_ROD);
    private static String[] lineSpawnerDesc = 
    {"§7test",
    "§81s CD"};
    private static String lineSpawnerName = "§r§fLine Spawner";
    public static BobuxItem lineSpawner = new BobuxItem
    (lineSpawnerStack, lineSpawnerDesc, lineSpawnerName, new LineSpawnerAbility("Line Spawner Passive", false, 5, 0), 16);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack railgunStack = new ItemStack(Material.IRON_HOE);
    private static String[] railgunDesc = 
    {"§7Shoot a beam that pierces enemies.",
    "§85s CD (Right Click)"};
    private static String railgunName = "§r§fRailgun";
    public static BobuxItem railgun = new BobuxItem
    (railgunStack, railgunDesc, railgunName, new RailgunAbility("Railgun Ability", false, 100), true, 128);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bountyStack = new ItemStack(Material.PAPER);
    private static String[] bountyDesc = 
    {"§7Access your bounties here.",
    "§8(Left/Right Click)"};
    private static String bountyName = "§c§lBounties";
    public static BobuxItem bounty = new BobuxItem
    (bountyStack, bountyDesc, bountyName);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack theHotStickStack = new ItemStack(Material.BLAZE_ROD);
    private static String[] theHotStickDesc = 
    {"§7Right click to launch a blazing line of pure fire!",
    "§85s CD (Right Click)"};
    private static String theHotStickName = "§cTh§6e H§eot §6St§cick";
    public static BobuxItem theHotStick = new BobuxItem
    (theHotStickStack, theHotStickDesc, theHotStickName, new TheHotStickAbility("The Hot Stick Ability", false, 100), 64);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack BW5AmmoStack = new ItemStack(Material.LIGHT_BLUE_DYE);
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
    "§85s CD (Right Click)"};
    private static String BW5Name = "§1BW-5 \"Nitro Express\"";
    public static BobuxItem BW5 = new BobuxItem
    (BW5Stack, BW5Desc, BW5Name, new BW5Ability("BW5 Ability", false, 100), 384);
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
    (kungFuGlovesStack, kungFuGlovesDesc, kungFuGlovesName, new KungFuGlovesAbility("Kung Fu Gloves Ability", false, 200), new KungFuGlovesPassive("Kung Fu Gloves Passive", false, 0, 40), kungFuGlovesAttributeList, true, 128);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack fruitcakeAndCookiesStack = new ItemStack(Material.CAKE);
    private static String[] fruitcakeAndCookiesDesc = 
    {"§7Some fruitcake and cookies to ease your soul.",
    "§830s CD (Right Click)",
    "",
    "§o§8Maybe tears are endless...",
    "§o§8I just need some refreshment...",
    "§o§8Just to cool my heart"};
    private static String fruitcakeAndCookiesName = "§4Fruitcake §fand §6Cookies";
    public static BobuxItem fruitcakeAndCookies = new BobuxItem
    (fruitcakeAndCookiesStack, fruitcakeAndCookiesDesc, fruitcakeAndCookiesName, new FruitcakeAndCookiesAbility("Fruitcake And Cookies Ability", false, 600), true, 1024);
    //////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxinatorStack = new ItemStack(Material.STICKY_PISTON);
    private static String[] bobuxinatorDesc = 
    {"§7It's an investment!",
    "§860s CD (Right Click)"};
    private static String bobuxinatorName = "§aBobuxinator";
    public static BobuxItem bobuxinator = new BobuxItem
    (bobuxinatorStack, bobuxinatorDesc, bobuxinatorName, new BobuxinatorAbility("Bobuxinator Ability", false, 1199), true, 512);
    //////////////////////////////////////////////////////////////////////////////////////////
     
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxBrewStack = new ItemStack(Material.HONEY_BOTTLE);
    private static String[] bobuxBrewDesc = 
    {"§7Surely there are no side effects...",
    "§830s CD (Right Click)"};
    private static String bobuxBrewName = "§6Bobux Brew";
    public static BobuxItem bobuxBrew = new BobuxItem
    (bobuxBrewStack, bobuxBrewDesc, bobuxBrewName, new BobuxBrewAbility("Bobux Brew Ability", false, 600), false, 400);
    //////////////////////////////////////////////////////////////////////////////////////////
   
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxBrewRemnantsStack = new ItemStack(Material.HONEY_BLOCK);
    private static String[] bobuxBrewRemnantsDesc = 
    {"§7Bobux Brew will reappear in this slot.",
    "§830s CD"};
    private static String bobuxBrewRemnantsName = "§7Bobux Brew Remnants";
    public static BobuxItem bobuxBrewRemnants = new BobuxItem
    (bobuxBrewRemnantsStack, bobuxBrewRemnantsDesc, bobuxBrewRemnantsName);
    //////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack straightPearlStack = new ItemStack(Material.ENDER_PEARL);
    private static String[] straightPearlDesc = 
    {"§8This special ender pearl doesn't seem to have gravity..."};
    private static String straightPearlName = "§7Straight Pearl";
    public static BobuxItem straightPearl = new BobuxItem
    (straightPearlStack, straightPearlDesc, straightPearlName, new StraightPearlAbility("Straight Pearl Ability", true, 1), 8);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack megaLongBowStack = new ItemStack(Material.BOW);
    private static String[] megaLongBowDesc = 
    {"§7Why is this here?",
    "§82s CD (Right Click)"};
    private static String megaLongBowName = "§7Mega Long Bow";
    public static BobuxItem megaLongBow = new BobuxItem
    (megaLongBowStack, megaLongBowDesc, megaLongBowName, new MegaLongBowAbility("Mega Longbow Ability", true, 20), 512);
    //////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack freakyCarrotStack = new ItemStack(Material.CARROT);
    private static String[] freakyCarrotDesc = 
    {"§7When breeding with this carrot, animals will make three children instead of one.",
    "§8No CD (Right Click)"};
    private static String freakyCarrotName = "§6Freaky Carrot";
    public static BobuxItem freakyCarrot = new BobuxItem
    (freakyCarrotStack, freakyCarrotDesc, freakyCarrotName, new FreakyFoodAbility("Freaky Carrot Ability", true, 1), 8);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack stinkyPantsStack = new ItemStack(Material.LEATHER_LEGGINGS);
    private static BobuxMetaManipulator stinkyPantsColorManip = new BobuxMetaManipulator
    (stinkyPantsStack, (LeatherArmorMeta) stinkyPantsStack.getItemMeta(), Color.fromRGB(0, 100, 0));
    private static String[] stinkyPantsDesc = 
    {"§7Eww...",
    "§85s CD (Sneak)"};
    private static String stinkyPantsName = "§2Stinky Pants";
    public static BobuxItem stinkyPants = new BobuxItem
    (stinkyPantsStack, stinkyPantsDesc, stinkyPantsName, new StinkyPantsAbility("Stinky Pants Ability", true, 100), 32);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack freakyWheatStack = new ItemStack(Material.WHEAT);
    private static String[] freakyWheatDesc = 
    {"§7When breeding with this wheat, animals will make three children instead of one.",
    "§8No CD (Right Click)"};
    private static String freakyWheatName = "§eFreaky Wheat";
    public static BobuxItem freakyWheat = new BobuxItem
    (freakyWheatStack, freakyWheatDesc, freakyWheatName, new FreakyFoodAbility("Freaky Wheat Ability", true, 1), 8);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack freakySeedsStack = new ItemStack(Material.WHEAT_SEEDS);
    private static String[] freakySeedsDesc = 
    {"§7When breeding with these seeds, animals will make three children instead of one.",
    "§8No CD (Right Click)"};
    private static String freakySeedsName = "§aFreaky Seeds";
    public static BobuxItem freakySeeds = new BobuxItem
    (freakySeedsStack, freakySeedsDesc, freakySeedsName, new FreakyFoodAbility("Freaky Seeds Ability", true, 1), 8);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack stinkyResidueStack = new ItemStack(Material.GREEN_DYE);
    private static String[] stinkyResidueDesc = 
    {"§8The stinkiest of smells..."};
    private static String stinkyResidueName = "§2Stinky Residue";
    public static BobuxItem stinkyResidue = new BobuxItem
    (stinkyResidueStack, stinkyResidueDesc, stinkyResidueName);
    //////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack cinnamonSquareStack = new ItemStack(Material.BROWN_CARPET);
    private static String[] cinnamonSquareDesc = 
    {"§8This square seems to produce a lot of energy when ingested."};
    private static String cinnamonSquareName = "§6Cinnamon Square";
    public static BobuxItem cinnamonSquare = new BobuxItem
    (cinnamonSquareStack, cinnamonSquareDesc, cinnamonSquareName);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack abcBloodStack = new ItemStack(Material.REDSTONE);
    private static String[] abcBloodDesc = 
    {"§8This shouldn't exist..."};
    private static String abcBloodName = "§cABC Blood";
    public static BobuxItem abcBlood = new BobuxItem
    (abcBloodStack, abcBloodDesc, abcBloodName);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack mutantOrbStack = new ItemStack(Material.HEART_OF_THE_SEA);
    private static String[] mutantOrbDesc = 
    {"§8This orb seems to have been the reason behind such an odd creature."};
    private static String mutantOrbName = "§bMutant Orb";
    public static BobuxItem mutantOrb = new BobuxItem
    (mutantOrbStack, mutantOrbDesc, mutantOrbName);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack questboardStack = new ItemStack(Material.OAK_SIGN);
    private static String[] questboardDesc = 
    {"§8(Left Click/Right Click)"};
    private static String questboardName = "§c§lQuestboard";
    public static BobuxItem questboard = new BobuxItem
    (questboardStack, questboardDesc, questboardName);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack culturalShardStack = new ItemStack(Material.RAW_COPPER);
    private static String[] culturalShardDesc = 
    {"§8An odd artifact left by strange cultists... it could be capable of many things"};
    private static String culturalShardName = "§5Cultural Shard";
    public static BobuxItem culturalShard = new BobuxItem
    (culturalShardStack, culturalShardDesc, culturalShardName);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack lesserLootboxStack = new ItemStack(Material.REDSTONE);
    private static String[] lesserLootboxDesc = 
    {"§7Gives you three pieces of Lesser Bobux Loot"};
    private static String lesserLootboxName = "§cLesser Lootbox";
    public static BobuxItem lesserLootbox = new BobuxItem
    (lesserLootboxStack, lesserLootboxDesc, lesserLootboxName, new LesserLootBoxAbility("Lesser Lootbox Ability", true, 100), 64);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bardCapStack = new ItemStack(Material.LEATHER_HELMET);
    private static String[] bardCapDesc = 
    {"§8This handy cap assures a great time for all nearby players.",
    "§7(If full set is worn, allies and user receive a variety of buffs.)"};
    private static String bardCapName = "§dBard Cap";
    public static BobuxItem bardCap = new BobuxItem
    (bardCapStack, bardCapDesc, bardCapName, 100);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bardVestStack = new ItemStack(Material.IRON_CHESTPLATE);
    private static BobuxMetaManipulator bardVestManip = new BobuxMetaManipulator((ArmorMeta) bardVestStack.getItemMeta(), TrimMaterial.LAPIS, TrimPattern.DUNE);
    private static String[] bardVestDesc = 
    {"§8This handy cap assures a great time for all nearby players.",
    "§7(If full set is worn, allies and user receive a variety of buffs.)"};
    private static String bardVestName = "§dBard Vest";
    public static BobuxItem bardVest = new BobuxItem
    (bardVestStack, bardVestDesc, bardVestName, 150);
    //////////////////////////////////////////////////////////////////////////////////////////
   
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bardLeggingsStack = new ItemStack(Material.IRON_LEGGINGS);
    private static BobuxMetaManipulator bardLeggingsManip = new BobuxMetaManipulator((ArmorMeta) bardLeggingsStack.getItemMeta(), TrimMaterial.LAPIS, TrimPattern.DUNE);
    private static String[] bardLeggingsDesc = 
    {"§8This handy cap assures a great time for all nearby players.",
    "§7(If full set is worn, allies and user receive a variety of buffs.)"};
    private static String bardLeggingsName = "§dBard Leggings";
    public static BobuxItem bardLeggings = new BobuxItem
    (bardLeggingsStack, bardLeggingsDesc, bardLeggingsName,  150);
    //////////////////////////////////////////////////////////////////////////////////////////
   
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bardKicksStack = new ItemStack(Material.LEATHER_BOOTS);
    private static BobuxAttributeSet bardKicksAttribute1 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.MOVEMENT_SPEED, 0.1, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.FEET);
    private static BobuxAttributeSet[] bardKicksAttributeList = {bardKicksAttribute1};
    private static String[] bardKicksDesc = 
    {"§8This handy cap assures a great time for all nearby players.",
    "§7(If full set is worn, allies and user receive a variety of buffs.)"};
    private static String bardKicksName = "§dBard Kicks";
    public static BobuxItem bardKicks = new BobuxItem
    (bardKicksStack, bardKicksDesc, bardKicksName, bardKicksAttributeList, 128);
    //////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack miniPickStack = new ItemStack(Material.IRON_PICKAXE);
    private static BobuxAttributeSet miniPickAttribute1 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.SCALE, -0.5, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.MAINHAND);
    private static BobuxAttributeSet[] miniPickAttributeList = {miniPickAttribute1};
    private static String[] miniPickDesc = 
    {"§7Shrinks you down to half size when holding."};
    private static String miniPickName = "§dMini Pick";
    public static BobuxItem miniPick = new BobuxItem
    (miniPickStack, miniPickDesc, miniPickName, miniPickAttributeList, 128);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack flockingFeatherStack = new ItemStack(Material.FEATHER);
    private static String[] flockingFeatherDesc = 
    {"§7Great way to swiftly escape a bad situation.",
    "§85s CD (Right Click)"};
    private static String flockingFeatherName = "§7Flocking Feather";
    public static BobuxItem flockingFeather = new BobuxItem
    (flockingFeatherStack, flockingFeatherDesc, flockingFeatherName, 8);
    //////////////////////////////////////////////////////////////////////////////////////////











}

