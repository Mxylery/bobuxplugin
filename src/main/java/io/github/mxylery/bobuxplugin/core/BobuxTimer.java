package io.github.mxylery.bobuxplugin.core;

import org.bukkit.scheduler.BukkitRunnable;

/**
 * This class keeps track of the ticks of the server and reports to the
 * ability manager to update it with the current tick.
 */
public class BobuxTimer extends BukkitRunnable {
    
    private long ticksPassed;

    public BobuxTimer() {
        ticksPassed = 0;
    }

    public void run() {
        ticksPassed++;
    }

    public long getTicksPassed() {
        return ticksPassed;
    }
}
