package io.github.mxylery.bobuxplugin.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.actions.DamageEntity;
import io.github.mxylery.bobuxplugin.core.BobuxAbility;
import io.github.mxylery.bobuxplugin.core.BobuxAction;
import io.github.mxylery.bobuxplugin.core.ability_types.AbilityOneTime;

/**
 * The class where all items will be instantiated and retrieved. 
 * They will all be public static variables, so a simple BobuxItemInterface.<itemname>.initializeStack() will be the method to retrieve items.
 */

//As long as the names are unique...
public class BobuxItemInterface {

    private static final int itemTotal = 1;
    
    //First item initialization, this is an Iron_Ingot that damages the player when they left click the air with a 100 tick cooldown.
    private static ItemStack testingItemStack = new ItemStack(Material.IRON_INGOT);
    private static String[] testingItemDesc = {"Balls"};
    private static String testingItemName = "Testing Item";
    private static BobuxAction[] testingItemActionList = {new DamageEntity(5)};
    private static BobuxAbility testingItemAbility = new AbilityOneTime(100, null, testingItemActionList, "Testing Item Abil");

    public static BobuxItem testingItem = new BobuxItem(testingItemStack, testingItemDesc, testingItemName, testingItemAbility, 5000);

}
