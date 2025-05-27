package io.github.mxylery.bobuxplugin.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Bounty {

    BROWN_MUSHROOM (8, 2, 8, new ItemStack(Material.BROWN_MUSHROOM)),
	CARROT (6, 8, 16, new ItemStack(Material.CARROT)),
	DARK_OAK_LOG (6, 8, 16, new ItemStack(Material.DARK_OAK_LOG)),
	DIAMOND (23, 1, 4, new ItemStack(Material.DIAMOND)),
	ENDER_PEARL (11, 1, 3, new ItemStack(Material.ENDER_PEARL)),
	GLASS (8,8,16, new ItemStack(Material.GLASS)),
	GOLD_INGOT (11, 3, 6, new ItemStack(Material.GOLD_INGOT)),
	HEART_OF_THE_SEA (64, 1, 1, new ItemStack(Material.HEART_OF_THE_SEA)),
	LAPIS_LAZULI (12, 11, 18, new ItemStack(Material.LAPIS_LAZULI)),
	LEATHER (10, 6, 10, new ItemStack(Material.LEATHER)),
	MUTTON (5, 5, 10, new ItemStack(Material.MUTTON)),
	NAUTILUS_SHELL (26, 1, 2, new ItemStack(Material.NAUTILUS_SHELL)),
	NETHERITE_INGOT (128, 1, 1, new ItemStack(Material.NETHERITE_INGOT)),
	OAK_LOG (6, 8, 16, new ItemStack(Material.OAK_LOG)),
	POTATO (5, 8, 16, new ItemStack(Material.POTATO)),
	RED_MUSHROOM (8, 2, 8, new ItemStack(Material.RED_MUSHROOM)),
	REDSTONE_BLOCK (11, 4, 7, new ItemStack(Material.REDSTONE_BLOCK)),
	ROTTEN_FLESH (13, 8, 24, new ItemStack(Material.ROTTEN_FLESH)),
	STEAK (16, 8, 16, new ItemStack(Material.COOKED_BEEF)),
	WHEAT (6, 8, 16, new ItemStack(Material.WHEAT)),
	WOOL (11, 5, 10, new ItemStack(Material.WHITE_WOOL));

    public int bobux;
	public int min;
	public int max;
	public ItemStack stack;

    public static int typeCount = 21;

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

