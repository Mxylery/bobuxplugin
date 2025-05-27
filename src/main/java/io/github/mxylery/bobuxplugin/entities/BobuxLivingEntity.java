package io.github.mxylery.bobuxplugin.entities;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.listeners.BobuxEntityListener;

public abstract class BobuxLivingEntity extends BobuxEntity {

    protected int maxHealth;
    protected boolean isDead;
    protected ItemStack[] dropTable;
    protected double[] dropWeights;
    protected int[][] dropRanges;

    public BobuxLivingEntity(BobuxPlugin plugin, BobuxEntityListener listener, Location location) {
        super(plugin, listener, location);
        isDead = false;
    }

    public void rollLootTable(Location location) {
        Location dropLoc = new Location(location.getWorld(), location.getX() + 0.5, location.getY() + 0.5, location.getZ() + 0.5);
        if (dropTable == null || dropWeights == null || dropRanges == null) {

        } else {
            for (int i = 0; i < dropTable.length; i++) {
                Double rng = Math.random();
                if (rng < dropWeights[i]) {
                    int amount = (int) (dropRanges[i][0] + Math.random()*(dropRanges[i][0] - dropRanges[i][1]));
                    for (int j = 0; j < amount; j++) {
                        location.getWorld().dropItem(dropLoc, dropTable[i]);
                    }
                }
            }
        }
    }

    public boolean isDead() {
        return entity.isDead();
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }
    
}
