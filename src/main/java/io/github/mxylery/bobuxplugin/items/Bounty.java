package io.github.mxylery.bobuxplugin.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Bounty {

    BROWN_MUSHROOM (3, 8, 16, new ItemStack(Material.BROWN_MUSHROOM)),
	CARROT (4, 16, 32, new ItemStack(Material.CARROT)),
	DIAMOND (14, 2, 4, new ItemStack(Material.DIAMOND)),
	ENDER_PEARL (8, 2, 5, new ItemStack(Material.ENDER_PEARL)),
	GLASS (4,16,32, new ItemStack(Material.GLASS)),
	GOLD_INGOT (12, 6, 10, new ItemStack(Material.GOLD_INGOT)),
	HEART_OF_THE_SEA (32, 1, 1, new ItemStack(Material.HEART_OF_THE_SEA)),
	LAPIS_LAZULI (10, 6, 10, new ItemStack(Material.LAPIS_LAZULI)),
	LEATHER (7, 6, 10, new ItemStack(Material.LEATHER)),
	MUTTON (4, 8, 16, new ItemStack(Material.MUTTON)),
	NAUTILUS_SHELL (11, 1, 2, new ItemStack(Material.NAUTILUS_SHELL)),
	NETHERITE_INGOT (27, 1, 1, new ItemStack(Material.NETHERITE_INGOT)),
	OAK_LOG (7, 16, 32, new ItemStack(Material.OAK_LOG)),
	POTATO (6, 16, 32, new ItemStack(Material.POTATO)),
	RED_MUSHROOM (3, 8, 16, new ItemStack(Material.RED_MUSHROOM)),
	REDSTONE_BLOCK (4, 2, 4, new ItemStack(Material.REDSTONE_BLOCK)),
	ROTTEN_FLESH (8, 12, 24, new ItemStack(Material.ROTTEN_FLESH)),
	STEAK (7, 8, 16, new ItemStack(Material.COOKED_BEEF)),
	WHEAT (5, 16, 32, new ItemStack(Material.WHEAT)),
	WOOL (5, 8, 16, new ItemStack(Material.WHITE_WOOL));

    public int bobux;
	public int min;
	public int max;
	public ItemStack stack;

    public static int typeCount = 20;

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

