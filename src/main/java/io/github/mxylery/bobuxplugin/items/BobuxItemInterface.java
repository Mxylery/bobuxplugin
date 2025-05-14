package io.github.mxylery.bobuxplugin.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Server;

import io.github.mxylery.bobuxplugin.actions.*;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.ability_types.AbilityOneTime;

/**
 * The class where all items will be instantiated and retrieved. 
 * They will all be public static variables, so a simple BobuxItemInterface.<itemname>.initializeStack() will be the method to retrieve items.
 * 
 * https://www.gamergeeks.net/apps/minecraft/color-codes for examples of colored text
 */

//As long as the names are unique...
public class BobuxItemInterface {

    private void setUnbreakables() {
        bounceBootsStack.getItemMeta().setUnbreakable(true);
    }

    private static final int itemTotal = 3;
    private static Server server = BobuxTimer.getServer();
    private static ItemFactory itemFactory = server.getItemFactory();

    //Items start here
    private static ItemStack testingItemStack = new ItemStack(Material.IRON_INGOT);
    private static String[] testingItemDesc = {"Balls"};
    private static String testingItemName = "Testing Item";
    private static BobuxAction[] testingItemActionList = 
    {new DamageEntity(5, false)};
    private static BobuxAbility testingItemAbility = new AbilityOneTime
    (100, null, testingItemActionList, "Testing Item Abil");
    public static BobuxItem testingItem = 
    new BobuxItem(testingItemStack, testingItemDesc, testingItemName, testingItemAbility, 
    null, null, false, 5000);
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    private static ItemStack bouncingItemStack = new ItemStack(Material.SLIME_BALL);
    private static String[] bouncingItemDesc = {"Legendary Bounce Ball", 
    "Second Line Because Why Not"};
    private static String bouncingItemName = "Bouncing Item";
    private static BobuxAction[] bouncingItemActionList = 
    {new ChangeVelocity(1, false)};
    private static BobuxAbility bouncingItemAbility = new AbilityOneTime
    (100, null, bouncingItemActionList, "Bouncing Item Abil");
    public static BobuxItem bouncingItem = 
    new BobuxItem(bouncingItemStack, bouncingItemDesc, bouncingItemName, bouncingItemAbility,
    null, null, false);
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    private static ItemStack bounceBootsStack = new ItemStack(Material.LEATHER_BOOTS);
    private static String[] bounceBootsDesc = {"These boots make you bounce when you jump.","No cooldown"};
    private static String bounceBootsName = "Bouncing Boots";
    private static BobuxAction[] bounceBootsActionList = 
    {new ChangeVelocity(0.8, false)};
    private static BobuxAbility bounceBootsAbility = new AbilityOneTime
    (1, null, bounceBootsActionList, "Bouncing Boots Abil");
    public static BobuxItem bounceBoots = 
    new BobuxItem(bounceBootsStack, bounceBootsDesc, bounceBootsName, bounceBootsAbility,
     null, null, true, 10);
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
}

