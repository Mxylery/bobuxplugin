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

    private static final int itemTotal = 40;

    private static ItemStack testingItemStack = new ItemStack(Material.IRON_INGOT);
    private static String[] testingItemDesc = 
    {"§7§oBalls"};
    private static String testingItemName = "§fTesting Item";
    public static BobuxItem testingItem = 
    new BobuxItem(testingItemStack, testingItemDesc, testingItemName, new TestingItemAbility("Testing Item Ability", false, 40), 5000);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bouncingItemStack = new ItemStack(Material.SLIME_BALL);
    private static String[] bouncingItemDesc = 
    {"§7Allows you to leap in the direction you are looking.", 
    "",
    "§81s CD (Left Click)",
    "§8Consumed on use"};
    private static String bouncingItemName = "§2Bouncing Item";
    public static BobuxItem bouncingItem = 
    new BobuxItem(bouncingItemStack, bouncingItemDesc, bouncingItemName, new BouncingItemAbility(), 3);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack hurriedStopwatchStack = new ItemStack(Material.CLOCK);
    private static String[] hurriedStopwatchDesc = 
    {"§7Gives the user Speed III for 5 seconds",
    "",
    "§815s CD (Right Click)"};
    private static String hurriedStopwatchName = "§eHurried Stopwatch";
    private static Enchantment[] hurriedStopwatchEnchantList = {Enchantment.UNBREAKING, Enchantment.CHANNELING};
    private static int[] hurriedStopwatchEnchantLevels = {2, 2};
    public static BobuxItem hurriedStopwatch = new BobuxItem
    (hurriedStopwatchStack, hurriedStopwatchDesc, hurriedStopwatchName, new HurriedStopwatchAbility(), 
    hurriedStopwatchEnchantList, hurriedStopwatchEnchantLevels, true, true, 24);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxStack = new ItemStack(Material.LIME_DYE);
    private static String[] bobuxDesc = 
    {"§7Official currency of the Bobux SMP",
    "",
    "§8Worth 1 $BBX"};
    private static String bobuxName = "§l§aBobux";
    public static BobuxItem bobux = new BobuxItem
    (bobuxStack, bobuxDesc, bobuxName, 1);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxSquareStack = new ItemStack(Material.LIME_WOOL);
    private static String[] bobuxSquareDesc = 
    {"§7Official currency of the Bobux SMP",
    "",
    "§8Worth 8 $BBX"};
    private static String bobuxSquareName = "§l§aBobux Square";
    public static BobuxItem bobuxSquare = new BobuxItem
    (bobuxSquareStack, bobuxSquareDesc, bobuxSquareName, 8);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxCubeStack = new ItemStack(Material.LIME_TERRACOTTA);
    private static String[] bobuxCubeDesc = 
    {"§7Official currency of the Bobux SMP",
    "",
    "§8Worth 64 $BBX"};
    private static String bobuxCubeName = "§l§aBobux Cube";
    public static BobuxItem bobuxCube = new BobuxItem
    (bobuxCubeStack, bobuxCubeDesc, bobuxCubeName, 64);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxTesseractStack = new ItemStack(Material.EMERALD_BLOCK);
    private static String[] bobuxTesseractDesc = 
    {"§7Official currency of the Bobux SMP",
    "",
    "§8Worth 512 $BBX"};
    private static String bobuxTesseractName = "§l§aBobux Tesseract";
    public static BobuxItem bobuxTesseract = new BobuxItem
    (bobuxTesseractStack, bobuxTesseractDesc, bobuxTesseractName, 512);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack marketStack = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
    private static String[] marketDesc = 
    {"§7Access the daily market here.",
    "",
    "§8(Left/Right Click)"};
    private static String marketName = "§6§lMarket";
    public static BobuxItem market = new BobuxItem
    (marketStack, marketDesc, marketName);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack cleaverStack = new ItemStack(Material.GOLDEN_SWORD);
    private static String[] cleaverDesc = 
    {"§7Hitting mobs with this sword cleaves the mobs behind it.",
    "",
    "§85s CD"};
    private static String cleaverName = "§6Cleaver";
    public static BobuxItem cleaver = new BobuxItem
    (cleaverStack, cleaverDesc, cleaverName, new CleaverAbility(), true, 64);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack railgunStack = new ItemStack(Material.IRON_HOE);
    private static String[] railgunDesc = 
    {"§7Shoot a beam that pierces enemies.",
    "",
    "§85s CD (Right Click)"};
    private static String railgunName = "§r§fRailgun";
    public static BobuxItem railgun = new BobuxItem
    (railgunStack, railgunDesc, railgunName, new RailgunAbility("Railgun Ability", false, 100), true, 128);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack grantsStack = new ItemStack(Material.LIME_DYE);
    private static String[] grantsDesc = 
    {"§7Access your grants here.",
    "",
    "§8(Left/Right Click)"};
    private static String grantsName = "§a§lGrants";
    public static BobuxItem grants = new BobuxItem
    (grantsStack, grantsDesc, grantsName);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack theHotStickStack = new ItemStack(Material.BLAZE_ROD);
    private static String[] theHotStickDesc = 
    {"§7Right click to launch a blazing line of pure fire!",
    "",
    "§85s CD (Right Click)"};
    private static String theHotStickName = "§cTh§6e H§eot §6St§cick";
    public static BobuxItem theHotStick = new BobuxItem
    (theHotStickStack, theHotStickDesc, theHotStickName, new TheHotStickAbility(), 64);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack BW5AmmoStack = new ItemStack(Material.LIGHT_BLUE_DYE);
    private static String[] BW5AmmoDesc = 
    {"§7Ammunition used for the BW5 \"Nitro Express\" Rifle.",
    "",
    "§8This item will be consumed upon one use of the BW5."};
    private static String BW5AmmoName = "§9BW-5 Ammo";
    public static BobuxItem BW5Ammo = new BobuxItem
    (BW5AmmoStack, BW5AmmoDesc, BW5AmmoName, 4);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack BW5Stack = new ItemStack(Material.DIAMOND_HOE);
    private static String[] BW5Desc = 
    {"§7From nowhere would anyone expect this...",
    "",
    "§85s CD (Right Click)"};
    private static String BW5Name = "§9BW-5 \"Nitro Express\"";
    public static BobuxItem BW5 = new BobuxItem
    (BW5Stack, BW5Desc, BW5Name, new BW5Ability(), 384);
    //////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack kungFuGlovesStack = new ItemStack(Material.BLACK_WOOL);
    private static String[] kungFuGlovesDesc = 
    {"§7Punch.",
    "",
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
    "",
    "§o§8Maybe tears are endless...",
    "§o§8I just need some refreshment...",
    "§o§8Just to cool my heart",
    "",
    "§830s CD (Right Click)"};
    private static String fruitcakeAndCookiesName = "§4Fruitcake §fand §6Cookies";
    public static BobuxItem fruitcakeAndCookies = new BobuxItem
    (fruitcakeAndCookiesStack, fruitcakeAndCookiesDesc, fruitcakeAndCookiesName, new FruitcakeAndCookiesAbility("Fruitcake And Cookies Ability", false, 600), true, 1024);
    //////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxinatorStack = new ItemStack(Material.STICKY_PISTON);
    private static String[] bobuxinatorDesc = 
    {"§7It's an investment!",
    "",
    "§860s CD (Right Click)"};
    private static String bobuxinatorName = "§aBobuxinator";
    public static BobuxItem bobuxinator = new BobuxItem
    (bobuxinatorStack, bobuxinatorDesc, bobuxinatorName, new BobuxinatorAbility(), true, 512);
    //////////////////////////////////////////////////////////////////////////////////////////
     
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxBrewStack = new ItemStack(Material.HONEY_BOTTLE);
    private static String[] bobuxBrewDesc = 
    {"§7Surely there are no side effects...",
    "",
    "§830s CD (Right Click)"};
    private static String bobuxBrewName = "§6Bobux Brew";
    public static BobuxItem bobuxBrew = new BobuxItem
    (bobuxBrewStack, bobuxBrewDesc, bobuxBrewName, new BobuxBrewAbility(), false, 400);
    //////////////////////////////////////////////////////////////////////////////////////////
   
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bobuxBrewRemnantsStack = new ItemStack(Material.HONEY_BLOCK);
    private static String[] bobuxBrewRemnantsDesc = 
    {"§7Bobux Brew will reappear in this slot.",
    "",
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
    private static ItemStack freakyCarrotStack = new ItemStack(Material.CARROT);
    private static String[] freakyCarrotDesc = 
    {"§7When breeding with this carrot, animals will make three children instead of one.",
    "",
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
    "",
    "§85s CD (Sneak)"};
    private static String stinkyPantsName = "§2Stinky Pants";
    public static BobuxItem stinkyPants = new BobuxItem
    (stinkyPantsStack, stinkyPantsDesc, stinkyPantsName, new StinkyPantsAbility("Stinky Pants Ability", true, 100), 32);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack freakyWheatStack = new ItemStack(Material.WHEAT);
    private static String[] freakyWheatDesc = 
    {"§7When breeding with this wheat, animals will make three children instead of one.",
    "",
    "§8No CD (Right Click)"};
    private static String freakyWheatName = "§eFreaky Wheat";
    public static BobuxItem freakyWheat = new BobuxItem
    (freakyWheatStack, freakyWheatDesc, freakyWheatName, new FreakyFoodAbility("Freaky Wheat Ability", true, 1), 8);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack freakySeedsStack = new ItemStack(Material.WHEAT_SEEDS);
    private static String[] freakySeedsDesc = 
    {"§7When breeding with these seeds, animals will make three children instead of one.",
    "",
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
    {"§7Access the Questboard here.",
    "",
    "§8(Left/Right Click)"};
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
    private static ItemStack lesserLootboxStack = new ItemStack(Material.SPRUCE_WOOD);
    private static String[] lesserLootboxDesc = 
    {"§7Gives you three pieces of Lesser Bobux Loot",
    "",
    "§85s CD (Right Click)"};
    private static String lesserLootboxName = "§cLesser Lootbox";
    public static BobuxItem lesserLootbox = new BobuxItem
    (lesserLootboxStack, lesserLootboxDesc, lesserLootboxName, new LesserLootBoxAbility("Lesser Lootbox Ability", true, 100), 32);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bardCapStack = new ItemStack(Material.LEATHER_HELMET);
    private static BobuxMetaManipulator bardCapManip = new BobuxMetaManipulator(bardCapStack, (ArmorMeta) bardCapStack.getItemMeta(), TrimMaterial.EMERALD, TrimPattern.WILD);
    private static BobuxAttributeSet bardCapAttribute1 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.MOVEMENT_SPEED, 0.05, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.HEAD);
    private static BobuxAttributeSet bardCapAttribute2 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.ARMOR, 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);
    private static BobuxAttributeSet[] bardCapAttributeList = {bardCapAttribute1, bardCapAttribute2};
    private static String[] bardCapDesc = 
    {"§8This handy cap assures a great time for all nearby players.",
    "",
    "§7(If full set is worn, allies and user receive a variety of buffs.)"};
    private static String bardCapName = "§dBard Cap";
    public static BobuxItem bardCap = new BobuxItem
    (bardCapStack, bardCapDesc, bardCapName, bardCapAttributeList, 100);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bardVestStack = new ItemStack(Material.LEATHER_CHESTPLATE);
    private static BobuxAttributeSet bardVestAttribute1 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.MOVEMENT_SPEED, 0.05, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.CHEST);
    private static BobuxAttributeSet bardVestAttribute2 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.ARMOR, 5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
    private static BobuxAttributeSet[] bardVestAttributeList = {bardVestAttribute1, bardVestAttribute2};
    private static BobuxMetaManipulator bardVestManip = new BobuxMetaManipulator(bardVestStack, (ArmorMeta) bardVestStack.getItemMeta(), TrimMaterial.EMERALD, TrimPattern.WILD);
    private static String[] bardVestDesc = 
    {"§7This vest assures a great time for all nearby players.",
    "§8(If full set is worn, allies and user receive a variety of buffs.)"};
    private static String bardVestName = "§dBard Vest";
    public static BobuxItem bardVest = new BobuxItem
    (bardVestStack, bardVestDesc, bardVestName, bardVestAttributeList, 150);
    //////////////////////////////////////////////////////////////////////////////////////////
   
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bardLeggingsStack = new ItemStack(Material.LEATHER_LEGGINGS);
    private static BobuxAttributeSet bardLeggingsAttribute1 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.MOVEMENT_SPEED, 0.05, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.LEGS);
    private static BobuxAttributeSet bardLeggingsAttribute2 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.ARMOR, 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);
    private static BobuxAttributeSet[] bardLeggingsAttributeList = {bardLeggingsAttribute1, bardLeggingsAttribute2};
    private static BobuxMetaManipulator bardLeggingsManip = new BobuxMetaManipulator(bardLeggingsStack, (ArmorMeta) bardLeggingsStack.getItemMeta(), TrimMaterial.EMERALD, TrimPattern.WILD);
    private static String[] bardLeggingsDesc = 
    {"§7These handy pants assure a great time for all nearby players.",
    "",
    "§8(If full set is worn, allies and user receive a variety of buffs.)"};
    private static String bardLeggingsName = "§dBard Leggings";
    public static BobuxItem bardLeggings = new BobuxItem
    (bardLeggingsStack, bardLeggingsDesc, bardLeggingsName,  bardLeggingsAttributeList, 150);
    //////////////////////////////////////////////////////////////////////////////////////////
   
    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack bardKicksStack = new ItemStack(Material.IRON_BOOTS);
    private static BobuxAttributeSet bardKicksAttribute1 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.MOVEMENT_SPEED, 0.05, AttributeModifier.Operation.ADD_SCALAR, EquipmentSlotGroup.FEET);
    private static BobuxAttributeSet bardKicksAttribute2 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.ARMOR, 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);
    private static BobuxAttributeSet[] bardKicksAttributeList = {bardKicksAttribute1, bardKicksAttribute2};
    private static BobuxMetaManipulator bardKicksManip = new BobuxMetaManipulator(bardKicksStack, (ArmorMeta) bardKicksStack.getItemMeta(), TrimMaterial.EMERALD, TrimPattern.WILD);
    private static String[] bardKicksDesc = 
    {"§7These slick shoes assure a great time for all nearby players.",
    "",
    "§8(If full set is worn, allies and user receive a variety of buffs.)"};
    private static String bardKicksName = "§dBard Kicks";
    public static BobuxItem bardKicks = new BobuxItem
    (bardKicksStack, bardKicksDesc, bardKicksName, bardKicksAttributeList, 100);
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
    "",
    "§810s CD (Right Click)"};
    private static String flockingFeatherName = "§fFlocking Feather";
    public static BobuxItem flockingFeather = new BobuxItem
    (flockingFeatherStack, flockingFeatherDesc, flockingFeatherName, new FlockingFeatherAbility("Flocking Feather Ability", false, 200), 12);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack peaceTreatyStack = new ItemStack(Material.WRITABLE_BOOK);
    private static String[] peaceTreatyDesc = 
    {"§7When used, pacifies all mobs around you for a short amount of time.",
    "",
    "§815 CD (Right Click)"};
    private static String peaceTreatyName = "§9Peace Treaty";
    public static BobuxItem peaceTreaty = new BobuxItem
    (peaceTreatyStack, peaceTreatyDesc, peaceTreatyName, new PeaceTreatyAbility(), 128);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack hypeTrainStack = new ItemStack(Material.MINECART);
    private static String[] hypeTrainDesc = 
    {"§7WEEEEEE",
    "",
    "§810s CD (Right Click)"};
    private static String hypeTrainName = "§cHype Train";
    public static BobuxItem hypeTrain = new BobuxItem
    (hypeTrainStack, hypeTrainDesc, hypeTrainName, new HypeTrainAbility(), 64);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack raffleStack = new ItemStack(Material.PAPER);
    private static String[] raffleDescription = 
    {"§7Access the raffle here.",
    "",
    "§8(Left/Right Click)"};
    private static String raffleName = "§d§lRaffle";
    public static BobuxItem raffle = new BobuxItem
    (raffleStack, raffleDescription, raffleName);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack slowPogoStack = new ItemStack(Material.IRON_HOE);
    private static String[] slowPogoDesc = 
    {"§7Slow. Pogo. Stick.",
    "",
    "§83s CD (Right Click)"};
    private static String slowPogoName = "§cSlow Pogo";
    public static BobuxItem slowPogo = new BobuxItem
    (slowPogoStack, slowPogoDesc, slowPogoName, new SlowPogoLauncherAbility(), true, 150);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack pogoLauncherStack = new ItemStack(Material.IRON_PICKAXE);
    private static String[] pogoLauncherDesc = 
    {"§7Pogo. Stick.",
    "",
    "§83s CD (Right Click)"};
    private static String pogoLauncherName = "§cPogo Launcher";
    public static BobuxItem pogoLauncher = new BobuxItem
    (pogoLauncherStack, pogoLauncherDesc, pogoLauncherName, new PogoLauncherAbility(), true, 150);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack lootboxStack = new ItemStack(Material.STRIPPED_BIRCH_LOG);
    private static String[] lootboxDesc = 
    {"§7Gives you three pieces of Bobux Loot",
    "",
    "§85s CD (Right Click)"};
    private static String lootboxName = "§cLootbox";
    public static BobuxItem lootbox = new BobuxItem
    (lootboxStack, lootboxDesc, lootboxName, new LootboxAbility(), 128);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack greaterLootboxStack = new ItemStack(Material.CRIMSON_ROOTS);
    private static String[] greaterLootboxDesc = 
    {"§7Gives you three pieces of Greater Bobux Loot",
    "",
    "§85s CD (Right Click)"};
    private static String greaterLootboxName = "§cGreater Lootbox";
    public static BobuxItem greaterLootbox = new BobuxItem
    (greaterLootboxStack, greaterLootboxDesc, greaterLootboxName, new GreaterLootboxAbility(), 512);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack fishermanVestStack = new ItemStack(Material.GOLDEN_CHESTPLATE);
    private static BobuxAttributeSet fishermanVestAttribute1 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.LUCK, 0.05, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
    private static BobuxAttributeSet fishermanVestAttribute2 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.ARMOR, 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.CHEST);
    private static BobuxAttributeSet[] fishermanVestAttributeList = {fishermanVestAttribute1, fishermanVestAttribute2};
    private static BobuxMetaManipulator fishermanVestManip = new BobuxMetaManipulator(fishermanVestStack, (ArmorMeta) fishermanVestStack.getItemMeta(), TrimMaterial.DIAMOND, TrimPattern.SNOUT);
    private static String[] fishermanVestDesc = 
    {"§7A nice vest to wear on a fishing trip.",
    "",
    "§8(If full set is worn, gain a small chance of fishing up lootboxes)"};
    private static String fishermanVestName = "§dFisherman Vest";
    public static BobuxItem fishermanVest = new BobuxItem
    (fishermanVestStack, fishermanVestDesc, fishermanVestName, fishermanVestAttributeList, true, 75);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack fishermanPantsStack = new ItemStack(Material.GOLDEN_LEGGINGS);
    private static BobuxAttributeSet fishermanPantsAttribute1 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.LUCK, 0.05, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);
    private static BobuxAttributeSet fishermanPantsAttribute2 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.ARMOR, 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.LEGS);
    private static BobuxAttributeSet[] fishermanPantsAttributeList = {fishermanPantsAttribute1, fishermanPantsAttribute2};
    private static BobuxMetaManipulator fishermanPantsManip = new BobuxMetaManipulator(fishermanPantsStack, (ArmorMeta) fishermanPantsStack.getItemMeta(), TrimMaterial.DIAMOND, TrimPattern.SNOUT);
    private static String[] fishermanPantsDesc = 
    {"§7These pants will protect you from the murky waters...",
    "",
    "§8(If full set is worn, gain a small chance of fishing up lootboxes)"};
    private static String fishermanPantsName = "§dFisherman Pants";
    public static BobuxItem fishermanPants = new BobuxItem
    (fishermanPantsStack, fishermanPantsDesc, fishermanPantsName, fishermanPantsAttributeList, true, 75);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack fishermanHatStack = new ItemStack(Material.GOLDEN_HELMET);
    private static BobuxAttributeSet fishermanHatAttribute1 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.LUCK, 0.05, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);
    private static BobuxAttributeSet fishermanHatAttribute2 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.ARMOR, 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HEAD);
    private static BobuxAttributeSet[] fishermanHatAttributeList = {fishermanHatAttribute1, fishermanHatAttribute2};
    private static BobuxMetaManipulator fishermanHatManip = new BobuxMetaManipulator(fishermanHatStack, (ArmorMeta) fishermanHatStack.getItemMeta(), TrimMaterial.DIAMOND, TrimPattern.SNOUT);
    private static String[] fishermanHatDesc = 
    {"§7Perfect hat to wear on a fishing trip.",
    "",
    "§8(If full set is worn, gain a small chance of fishing up lootboxes)"};
    private static String fishermanHatName = "§dFisherman Hat";
    public static BobuxItem fishermanHat = new BobuxItem
    (fishermanHatStack, fishermanHatDesc, fishermanHatName, fishermanHatAttributeList, true, 50);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack fishermanBootsStack = new ItemStack(Material.GOLDEN_BOOTS);
    private static BobuxAttributeSet fishermanBootsAttribute1 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.LUCK, 0.05, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);
    private static BobuxAttributeSet fishermanBootsAttribute2 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.ARMOR, 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.FEET);
    private static BobuxAttributeSet[] fishermanBootsAttributeList = {fishermanBootsAttribute1, fishermanBootsAttribute2};
    private static BobuxMetaManipulator fishermanBootsManip = new BobuxMetaManipulator(fishermanBootsStack, (ArmorMeta) fishermanBootsStack.getItemMeta(), TrimMaterial.DIAMOND, TrimPattern.SNOUT);
    private static String[] fishermanBootsDesc = 
    {"§7No water's going in these shoes!",
    "",
    "§8(If full set is worn, gain a small chance of fishing up lootboxes)"};
    private static String fishermanBootsName = "§dFisherman Boots";
    public static BobuxItem fishermanBoots = new BobuxItem
    (fishermanBootsStack, fishermanBootsDesc, fishermanBootsName, fishermanBootsAttributeList, true, 50);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack fishermansPoleStack = new ItemStack(Material.FISHING_ROD);
    private static BobuxAttributeSet fishermansPoleAttribute1 = new BobuxAttributeSet(org.bukkit.attribute.Attribute.LUCK, 0.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.HAND);
    private static BobuxAttributeSet[] fishermansPoleAttributeList = {fishermansPoleAttribute1};
    private static String[] fishermansPoleDesc = 
    {"§7Perfect gear for a fishing trip!",
    "",
    "§8(If full set is worn, gain a small chance of fishing up lootboxes)"};
    private static String fishermansPoleName = "§dFisherman's Pole";
    public static BobuxItem fishermansPole = new BobuxItem
    (fishermansPoleStack, fishermansPoleDesc, fishermansPoleName, new FishermansPoleAbility(), fishermansPoleAttributeList, 128);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack culturalCoreLOTSStack = new ItemStack(Material.ARMADILLO_SCUTE);
    private static String[] culturalCoreLOTSDesc = 
    {"§7Right clicking a fishing rod in your inventory", 
    "",
    "§7with this item will give it Luck Of The Sea IV."};
    private static String culturalCoreLOTSName = "§dCultural Core (Luck Of The Sea)";
    public static BobuxItem culturalCoreLOTS = new BobuxItem
    (culturalCoreLOTSStack, culturalCoreLOTSDesc, culturalCoreLOTSName);
    //////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////
    private static ItemStack culturalCoreLureStack = new ItemStack(Material.ARMADILLO_SCUTE);
    private static String[] culturalCoreLureDesc = 
    {"§7Right clicking a fishing rod in your inventory", 
    "",
    "§7with this item will give it Lure IV."};
    private static String culturalCoreLureName = "§dCultural Core (Lure)";
    public static BobuxItem culturalCoreLure = new BobuxItem
    (culturalCoreLureStack, culturalCoreLureDesc, culturalCoreLureName);
    //////////////////////////////////////////////////////////////////////////////////////////



























   
    /**
     * ARMOR SETS
     */

    public static BobuxArmorSet bardSet = new BobuxArmorSet(bardCap, bardVest, bardLeggings, bardKicks, null, new BardSetAbility());
    public static BobuxArmorSet fishermansSet = new BobuxArmorSet(fishermanHat, fishermanVest, fishermanPants, fishermanBoots, new FishermansSetAbility(), null);
}

