package io.github.mxylery.bobuxplugin.guis.bounty;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Bounty {

	//Junk
    ANDESITE (0.4, 12, 24, new ItemStack(Material.ANDESITE)),
    COBBLESTONE (0.25, 16, 32, new ItemStack(Material.COBBLESTONE)),
    DIORITE (0.4, 12, 24, new ItemStack(Material.DIORITE)),
	DIRT (0.2, 12, 24, new ItemStack(Material.DIRT)),
	GRAVEL (0.2, 12, 24, new ItemStack(Material.GRAVEL)),
	KELP (0.2, 16, 32, new ItemStack(Material.KELP)),
	MUTTON (1, 2, 4, new ItemStack(Material.MUTTON)),
	ROTTEN_FLESH (0.5, 4, 8, new ItemStack(Material.ROTTEN_FLESH)),
	SAND (0.3, 12, 24, new ItemStack(Material.SAND)),
	SPIDER_EYE (0.5, 2, 8, new ItemStack(Material.SPIDER_EYE)),
	STRING (2, 2, 4, new ItemStack(Material.STRING)),
	BONE (1, 2, 4, new ItemStack(Material.BONE)),

	//Common Goods
	BROWN_MUSHROOM (1, 2, 8, new ItemStack(Material.BROWN_MUSHROOM)),
	BIRCH_LOG (1, 8, 16, new ItemStack(Material.BIRCH_LOG)),
	COAL (1, 8, 16, new ItemStack(Material.COAL)),
	COD (1, 8, 16, new ItemStack(Material.COD)),
	CARROT (1, 8, 16, new ItemStack(Material.CARROT)),
	DARK_OAK_LOG (1, 8, 16, new ItemStack(Material.DARK_OAK_LOG)),
	FLINT (2, 3, 6, new ItemStack(Material.BROWN_MUSHROOM)),
	GLASS (2,8,16, new ItemStack(Material.GLASS)),
	IRON_INGOT (3, 6, 12, new ItemStack(Material.IRON_INGOT)),
	LAPIS_LAZULI (1, 11, 18, new ItemStack(Material.LAPIS_LAZULI)),
	LEATHER (2, 6, 10, new ItemStack(Material.LEATHER)),
	OAK_LOG (1, 8, 16, new ItemStack(Material.OAK_LOG)),
	POTATO (1, 8, 16, new ItemStack(Material.POTATO)),
	PUFFERFISH (3, 3, 6, new ItemStack(Material.PUFFERFISH)),
	RED_MUSHROOM (6, 2, 8, new ItemStack(Material.RED_MUSHROOM)),
	REDSTONE_BLOCK (2, 4, 7, new ItemStack(Material.REDSTONE_BLOCK)),
	SALMON (1.5, 6, 12, new ItemStack(Material.SALMON)),
	SEEDS (0.5, 15, 30, new ItemStack(Material.WHEAT_SEEDS)),
	STEAK (4, 4, 8, new ItemStack(Material.COOKED_BEEF)),
	SUGAR_CANE (1, 8, 16, new ItemStack(Material.SUGAR_CANE)),
	WHEAT (1, 6, 12, new ItemStack(Material.WHEAT)),
	WOOL (3, 5, 10, new ItemStack(Material.WHITE_WOOL)),

	//Rarities
	DIAMOND (40, 2, 4, new ItemStack(Material.DIAMOND)),
	ENDER_PEARL (12, 4, 12, new ItemStack(Material.ENDER_PEARL)),
	GOLD_INGOT (4, 8, 16, new ItemStack(Material.GOLD_INGOT)),
	HEART_OF_THE_SEA (64, 1, 1, new ItemStack(Material.HEART_OF_THE_SEA)),
	NAUTILUS_SHELL (32, 2, 4, new ItemStack(Material.NAUTILUS_SHELL)),
	NETHERITE_INGOT (128, 1, 1, new ItemStack(Material.NETHERITE_INGOT)),
	SHULKER_SHELL (128, 2, 3, new ItemStack(Material.SHULKER_SHELL));

    public double bobux;
	public int min;
	public int max;
	public ItemStack stack;

    public static int typeCount = 36;
	public static int junkCount = 12;
	public static int goodsCount = 22;
	public static int raritiesCount = 7;

    Bounty(double bobux, int min, int max, ItemStack stack) {
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

