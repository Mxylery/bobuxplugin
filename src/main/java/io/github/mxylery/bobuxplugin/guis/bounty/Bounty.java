package io.github.mxylery.bobuxplugin.guis.bounty;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Bounty {

	//Junk
    COBBLESTONE (3, 16, 32, new ItemStack(Material.COBBLESTONE)),
	DIRT (3, 16, 32, new ItemStack(Material.DIRT)),
	MUTTON (2, 1, 3, new ItemStack(Material.MUTTON)),
	ROTTEN_FLESH (4, 4, 12, new ItemStack(Material.ROTTEN_FLESH)),
	SPIDER_EYE (5, 2, 8, new ItemStack(Material.SPIDER_EYE)),

	//Common Goods
	BROWN_MUSHROOM (6, 2, 8, new ItemStack(Material.BROWN_MUSHROOM)),
	BIRCH_LOG (6, 8, 16, new ItemStack(Material.BIRCH_LOG)),
	CARROT (6, 8, 16, new ItemStack(Material.CARROT)),
	DARK_OAK_LOG (6, 8, 16, new ItemStack(Material.DARK_OAK_LOG)),
	GLASS (8,8,16, new ItemStack(Material.GLASS)),
	GOLD_INGOT (12, 3, 6, new ItemStack(Material.GOLD_INGOT)),
	IRON_INGOT (16, 6, 12, new ItemStack(Material.IRON_INGOT)),
	LAPIS_LAZULI (12, 11, 18, new ItemStack(Material.LAPIS_LAZULI)),
	LEATHER (10, 6, 10, new ItemStack(Material.LEATHER)),
	OAK_LOG (6, 8, 16, new ItemStack(Material.OAK_LOG)),
	POTATO (5, 8, 16, new ItemStack(Material.POTATO)),
	RED_MUSHROOM (8, 2, 8, new ItemStack(Material.RED_MUSHROOM)),
	REDSTONE_BLOCK (11, 4, 7, new ItemStack(Material.REDSTONE_BLOCK)),
	SEEDS (8, 14, 21, new ItemStack(Material.WHEAT_SEEDS)),
	STEAK (12, 8, 16, new ItemStack(Material.COOKED_BEEF)),
	WHEAT (8, 6, 12, new ItemStack(Material.WHEAT)),
	WOOL (9, 5, 10, new ItemStack(Material.WHITE_WOOL)),

	//Rarities
	DIAMOND (40, 1, 3, new ItemStack(Material.DIAMOND)),
	HEART_OF_THE_SEA (64, 1, 1, new ItemStack(Material.HEART_OF_THE_SEA)),
	ENDER_PEARL (11, 1, 3, new ItemStack(Material.ENDER_PEARL)),
	NAUTILUS_SHELL (48, 1, 2, new ItemStack(Material.NAUTILUS_SHELL)),
	NETHERITE_INGOT (128, 1, 1, new ItemStack(Material.NETHERITE_INGOT));

    public int bobux;
	public int min;
	public int max;
	public ItemStack stack;

    public static int typeCount = 27;
	public static int junkCount = 5;
	public static int goodsCount = 17;
	public static int raritiesCount = 5;

    Bounty(int bobux, int min, int max, ItemStack stack) {
		this.bobux = bobux;
		this.stack = stack;
		if (min < max) {
		this.min = min;
		this.max = max;
		} else {
		this.min = max;
		this.max = min;
		}
    }
}

