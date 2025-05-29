package io.github.mxylery.bobuxplugin.entities;

import java.util.ArrayList;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.BobuxDay;
import io.github.mxylery.bobuxplugin.core.BobuxDay.DayType;
import io.github.mxylery.bobuxplugin.entities.mobs.BigChicken;
import io.github.mxylery.bobuxplugin.entities.mobs.Sandbagger;
import io.github.mxylery.bobuxplugin.entities.mobs.ScoutZombie;
import io.github.mxylery.bobuxplugin.entities.mobs.StinkyMob;
import io.github.mxylery.bobuxplugin.listeners.BobuxEntityListener;

// no use
public class BobuxSpawnChances {
    
    private BobuxPlugin plugin;
    private Block bloc;
    private Location loc;
    private double goodModifier = 1.0;
    private double badModifier = 1.0;
    private ArrayList<BobuxEntity> list;

    /**
     * To make spawn chances (where the number is approx how many mobs per chunk)
     * xMobAmount = (int) ((min + Math.random()*(min - max))/spawnableNonspawnableRatio)
     */
    public BobuxSpawnChances(BobuxPlugin plugin, ArrayList<BobuxEntity> list) {
        this.plugin = plugin;
        this.list = list;
    }

    private boolean canSpawn(Location location) {
        Block block = location.getBlock();
        Block groundBlock = new Location(location.getWorld(), location.getX(), location.getY() - 1, location.getZ()).getBlock();
        if (block.getType().equals(Material.AIR) && !groundBlock.getType().equals(Material.AIR) && !groundBlock.getType().equals(Material.LAVA)) {
            return true;
        }
        return false;
    }

    //done up to savanna_plateau alphabetically, too lazy to do more
    public void attemptToSpawn(Biome biome, Location location) {
        //This rng is to regulate spawns, only a quarter of chunks will have mobs just for server
        double rng = Math.random();
        if (BobuxDay.getDay() != null) {
        switch(BobuxDay.getDay()) {
            case NORMAL: 
            goodModifier = 1.0;
            badModifier = 1.0;
            break;
            case HAPPY:
            goodModifier = 1.2;
            badModifier = 0.8;
            break;
            case AVARICIOUS:
            goodModifier = 1.2;
            badModifier = 1.2;
            break;
            case SUSPICIOUS:
            goodModifier = 0.8;
            badModifier = 1.2;
            break;
            case BLOOD:
            goodModifier = 0.0;
            badModifier = 2.0;
            break;
            case DANGEROUS:
            goodModifier = 1.0;
            badModifier = 1.5;
            break;
            case ADVENTUROUS:
            goodModifier = 1.2;
            badModifier = 1.5;
            break;
        }

        if (rng < 0.25) {
            //Deserty
        if (biome.equals(Biome.BADLANDS) || biome.equals(Biome.DESERT) || biome.equals(Biome.ERODED_BADLANDS)) {

            int scoutZombieAmount = (int) (6*badModifier);
            for (int i = 0; i < scoutZombieAmount; i++) {
                Location loc = new Location(location.getWorld(), location.getX() -8 + Math.random()*16, location.getY() + 40 + Math.random()*160, location.getZ() -8 + Math.random()*16);
                if (canSpawn(loc)) {
                    ScoutZombie scoutZombie = new ScoutZombie(plugin, loc);
                    list.add(scoutZombie);
                }
            }

            double sandbaggerRng = Math.random();
            if (sandbaggerRng < 0.1) {
                int sandbaggerAmount = (int) (5*goodModifier);
                for (int i = 0; i < sandbaggerAmount; i++) {
                    Location loc = new Location(location.getWorld(), location.getX() -8 + Math.random()*16, location.getY() + 60 + Math.random()*324, location.getZ() -8 + Math.random()*16);
                    if (canSpawn(loc)) {
                        Sandbagger sandbagger = new Sandbagger(plugin, loc);
                        list.add(sandbagger);
                    }
                }
            }

            

            //Jungle
        } else if (biome.equals(Biome.BAMBOO_JUNGLE) || biome.equals(Biome.JUNGLE)) {



            //Nether
        } else if (biome.equals(Biome.BASALT_DELTAS) || biome.equals(Biome.CRIMSON_FOREST) || biome.equals(Biome.NETHER_WASTES)) {



            //Forest
        } else if (biome.equals(Biome.BIRCH_FOREST) || biome.equals(Biome.DARK_FOREST) || biome.equals(Biome.FLOWER_FOREST) || biome.equals(Biome.FOREST)
        || biome.equals(Biome.OLD_GROWTH_BIRCH_FOREST) || biome.equals(Biome.OLD_GROWTH_PINE_TAIGA) || biome.equals(Biome.OLD_GROWTH_SPRUCE_TAIGA)) {

            //Night mobs
            if (!BobuxTimer.isDay()) {
                int stinkyMobAmount = (int) ((6)*badModifier);
                for (int i = 0; i < stinkyMobAmount; i++) {
                    Location loc = new Location(location.getWorld(), location.getX() -8 + Math.random()*16, location.getY() -64 + Math.random()*320, location.getZ() -8 + Math.random()*16);
                    if (canSpawn(loc)) {
                        StinkyMob stinkyMob = new StinkyMob(plugin, loc);
                        list.add(stinkyMob);
                    }
                }
            //Day mobs
            } else {

            }
        


            //Ocean
        } else if (biome.equals(Biome.COLD_OCEAN) || biome.equals(Biome.DEEP_OCEAN) || biome.equals(Biome.DEEP_FROZEN_OCEAN) 
        || biome.equals(Biome.DEEP_COLD_OCEAN) || biome.equals(Biome.DEEP_LUKEWARM_OCEAN) || biome.equals(Biome.BEACH) || biome.equals(Biome.OCEAN)) {

            if (biome.equals(Biome.BEACH)) {
                int scoutZombieAmount = (int) ((5)*badModifier);
                for (int i = 0; i < scoutZombieAmount; i++) {
                    Location loc = new Location(location.getWorld(), location.getX() -8 + Math.random()*16, location.getY() + 40 + Math.random()*80, location.getZ() -8 + Math.random()*16);
                    if (canSpawn(loc)) {
                        ScoutZombie scoutZombie = new ScoutZombie(plugin, loc);
                        list.add(scoutZombie);
                    }
                }
            }

            //Underground
        } else if (biome.equals(Biome.DEEP_DARK) || biome.equals(Biome.DRIPSTONE_CAVES) || biome.equals(Biome.LUSH_CAVES)) {



            //End
        } else if (biome.equals(Biome.END_BARRENS) || biome.equals(Biome.END_HIGHLANDS) || biome.equals(Biome.SMALL_END_ISLANDS)) {



            //Frozen
        } else if (biome.equals(Biome.FROZEN_PEAKS) || biome.equals(Biome.FROZEN_RIVER) || biome.equals(Biome.ICE_SPIKES)) {



            //High Altitude
        } else if (biome.equals(Biome.JAGGED_PEAKS)) {




        } else if (biome.equals(Biome.MANGROVE_SWAMP)) {



            //Overworld
        } else if (biome.equals(Biome.MEADOW) || biome.equals(Biome.PLAINS) || biome.equals(Biome.RIVER) || biome.equals(Biome.CHERRY_GROVE)) {

            int bigChickenAmount = (int) ((6)*badModifier);
            for (int i = 0; i < bigChickenAmount; i++) {
                Location loc = new Location(location.getWorld(), location.getX() -8 + Math.random()*16, location.getY() -64 + Math.random()*320, location.getZ() -8 + Math.random()*16);
                if (canSpawn(loc)) {
                    BigChicken bigChicken = new BigChicken(plugin, loc);
                    list.add(bigChicken);
                }
            }

            //Night mobs
            if (!BobuxTimer.isDay()) {
                int stinkyMobAmount = (int) ((5)*badModifier);
                for (int i = 0; i < stinkyMobAmount; i++) {
                    Location loc = new Location(location.getWorld(), location.getX() -8 + Math.random()*16, location.getY() -64 + Math.random()*320, location.getZ() -8 + Math.random()*16);
                    if (canSpawn(loc)) {
                        StinkyMob stinkyMob = new StinkyMob(plugin, loc);
                        list.add(stinkyMob);
                    }
                }
            //Day mobs
            }

        } else if (biome.equals(Biome.MUSHROOM_FIELDS)) {

        } else if (biome.equals(Biome.PALE_GARDEN)) {



            //Savanna
        } else if (biome.equals(Biome.SAVANNA) || biome.equals(Biome.SAVANNA_PLATEAU)) {

        } 

        }
        }
    }
}
