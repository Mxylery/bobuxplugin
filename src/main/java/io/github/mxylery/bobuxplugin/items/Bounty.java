package io.github.mxylery.bobuxplugin.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Bounty {

    BROWN_MUSHROOM (6, 2, 16, new ItemStack(Material.BROWN_MUSHROOM)),
	CARROT (16, 16, 32, new ItemStack(Material.CARROT)),
	DIAMOND (71, 2, 4, new ItemStack(Material.DIAMOND)),
	ENDER_PEARL (29, 2, 5, new ItemStack(Material.ENDER_PEARL)),
	GLASS (21,16,32, new ItemStack(Material.GLASS)),
	GOLD_INGOT (31, 9, 14, new ItemStack(Material.GOLD_INGOT)),
	HEART_OF_THE_SEA (64, 1, 1, new ItemStack(Material.HEART_OF_THE_SEA)),
	LAPIS_LAZULI (16, 6, 10, new ItemStack(Material.LAPIS_LAZULI)),
	LEATHER (14, 6, 10, new ItemStack(Material.LEATHER)),
	MUTTON (11, 8, 16, new ItemStack(Material.MUTTON)),
	NAUTILUS_SHELL (11, 1, 2, new ItemStack(Material.NAUTILUS_SHELL)),
	NETHERITE_INGOT (256, 1, 1, new ItemStack(Material.NETHERITE_INGOT)),
	OAK_LOG (16, 16, 32, new ItemStack(Material.OAK_LOG)),
	POTATO (11, 16, 32, new ItemStack(Material.POTATO)),
	RED_MUSHROOM (8, 2, 16, new ItemStack(Material.RED_MUSHROOM)),
	REDSTONE_BLOCK (11, 2, 4, new ItemStack(Material.REDSTONE_BLOCK)),
	ROTTEN_FLESH (13, 12, 24, new ItemStack(Material.ROTTEN_FLESH)),
	STEAK (19, 8, 16, new ItemStack(Material.COOKED_BEEF)),
	WHEAT (12, 16, 32, new ItemStack(Material.WHEAT)),
	WOOL (11, 5, 16, new ItemStack(Material.WHITE_WOOL));

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

