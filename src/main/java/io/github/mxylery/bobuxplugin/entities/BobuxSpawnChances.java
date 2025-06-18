package io.github.mxylery.bobuxplugin.entities;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import io.github.mxylery.bobuxplugin.BobuxPlugin;
import io.github.mxylery.bobuxplugin.core.BobuxTimer;
import io.github.mxylery.bobuxplugin.core.BobuxDay;
import io.github.mxylery.bobuxplugin.entities.livingentities.hostiles.BigChicken;
import io.github.mxylery.bobuxplugin.entities.mobs.CulturalCultist;
import io.github.mxylery.bobuxplugin.entities.mobs.JumpySkeleton;
import io.github.mxylery.bobuxplugin.entities.mobs.Sandbagger;
import io.github.mxylery.bobuxplugin.entities.mobs.ScoutZombie;
import io.github.mxylery.bobuxplugin.entities.mobs.StinkyMob;

// no use
public class BobuxSpawnChances {
    
    private BobuxPlugin plugin;
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
        if (block.getType().equals(Material.AIR) && !groundBlock.getType().equals(Material.AIR) && !groundBlock.getType().equals(Material.LAVA) && !groundBlock.getType().equals(Material.WATER)) {
            return true;
        }
        return false;
    }

    private void spawnMethod(BobuxEntity bobuxEntity) {
        list.add(bobuxEntity);
        ArrayList<Entity> entityArrayList = new ArrayList<Entity>();
        Entity entity = bobuxEntity.getEntity();
        entityArrayList.add(entity);
    }

    private ArrayList<Location> possibleLocations(int count, Location location, int minHeight, int maxHeight, boolean aquatic) {
        ArrayList<Location> locList = new ArrayList<Location>();
        int min = Math.max(-64, minHeight);
        int max = Math.min(320, maxHeight);
        for (int i = 0; i < count; i++) {
            Location loc = new Location(location.getWorld(), location.getX() -8 + Math.random()*16, location.getY() + min + Math.random()*(max - min), location.getZ() -8 + Math.random()*16);
            if (!aquatic) {
                if (canSpawn(loc)) {
                    locList.add(loc);
                }
            }
        }
        return locList;
    }

    //done up to savanna_plateau alphabetically, too lazy to do more
    public void attemptToSpawn(Chunk chunk, Biome biome, Location location) { 
        //This rng is to regulate spawns, only a quarter of chunks will have mobs just for server
        double rng = Math.random();
        double goodModifier = BobuxDay.getGoodSpawnModifier();
        double badModifier = BobuxDay.getBadSpawnModifier();

        if (rng < 0.2) {
            int bigChickenAmount = (int) ((5)*badModifier);
            int stinkyMobAmount = (int) ((10)*badModifier);
            int jumpySkeletonAmount = (int) ((10)*badModifier);
            int scoutZombieAmount = (int) (8*badModifier);
            int sandbaggerAmount = (int) (2*goodModifier);
            int culturalCultistAmount = (int) (5*badModifier);

            //Deserty
        if (biome.equals(Biome.BADLANDS) || biome.equals(Biome.DESERT) || biome.equals(Biome.ERODED_BADLANDS) || biome.equals(Biome.WOODED_BADLANDS)) {

            for (Location loc : possibleLocations(scoutZombieAmount, location, 40, 90, false)) {
                ScoutZombie scoutZombie = new ScoutZombie(loc);
                spawnMethod(scoutZombie);
            }

            for (Location loc : possibleLocations(sandbaggerAmount, location, 50, 80, false)) {
                Sandbagger sandbagger = new Sandbagger(loc);
                spawnMethod(sandbagger);
            }
            
            //Jungle
        } else if (biome.equals(Biome.BAMBOO_JUNGLE) || biome.equals(Biome.JUNGLE)) {

            for (Location loc : possibleLocations(culturalCultistAmount, location, 40, 90, false)) {
                CulturalCultist culturalCultist = new CulturalCultist(loc);
                spawnMethod(culturalCultist);
            }



            //Nether
        } else if (biome.equals(Biome.BASALT_DELTAS) || biome.equals(Biome.CRIMSON_FOREST) || biome.equals(Biome.NETHER_WASTES)) {





            //Forest
        } else if (biome.equals(Biome.BIRCH_FOREST) || biome.equals(Biome.DARK_FOREST) || biome.equals(Biome.FLOWER_FOREST) || biome.equals(Biome.FOREST)
        || biome.equals(Biome.OLD_GROWTH_BIRCH_FOREST) || biome.equals(Biome.OLD_GROWTH_PINE_TAIGA) || biome.equals(Biome.OLD_GROWTH_SPRUCE_TAIGA)) {

            //Night mobs
            if (!BobuxTimer.isDay()) {

                for (Location loc : possibleLocations(stinkyMobAmount, location, 0, 100, false)) {
                    StinkyMob stinkyMob = new StinkyMob(loc);
                    spawnMethod(stinkyMob);
                }

                for (Location loc : possibleLocations(jumpySkeletonAmount, location, 0, 100, false)) {
                    JumpySkeleton jumpySkeleton = new JumpySkeleton(loc);
                    spawnMethod(jumpySkeleton);
                }


            //Day mobs
            } else {

            }
        



            //Ocean
        } else if (biome.equals(Biome.COLD_OCEAN) || biome.equals(Biome.DEEP_OCEAN) || biome.equals(Biome.DEEP_FROZEN_OCEAN) 
        || biome.equals(Biome.DEEP_COLD_OCEAN) || biome.equals(Biome.DEEP_LUKEWARM_OCEAN) || biome.equals(Biome.BEACH) || biome.equals(Biome.OCEAN)) {

           

            //Underground
        } else if (biome.equals(Biome.DEEP_DARK) || biome.equals(Biome.DRIPSTONE_CAVES) || biome.equals(Biome.LUSH_CAVES)) {

            for (Location loc : possibleLocations(culturalCultistAmount, location, 40, 90, false)) {
                CulturalCultist culturalCultist = new CulturalCultist(loc);
                spawnMethod(culturalCultist);
            }

            //End
        } else if (biome.equals(Biome.END_BARRENS) || biome.equals(Biome.END_HIGHLANDS) || biome.equals(Biome.SMALL_END_ISLANDS)) {



            //Frozen
        } else if (biome.equals(Biome.FROZEN_PEAKS) || biome.equals(Biome.FROZEN_RIVER) || biome.equals(Biome.ICE_SPIKES)) {



            //High Altitude
        } else if (biome.equals(Biome.JAGGED_PEAKS)) {




        } else if (biome.equals(Biome.MANGROVE_SWAMP)) {

            for (Location loc : possibleLocations(culturalCultistAmount, location, 40, 90, false)) {
                CulturalCultist culturalCultist = new CulturalCultist(loc);
                spawnMethod(culturalCultist);
            }

            //Overworld
        } else if (biome.equals(Biome.MEADOW) || biome.equals(Biome.PLAINS) || biome.equals(Biome.RIVER) || biome.equals(Biome.CHERRY_GROVE)) {

            for (Location loc : possibleLocations(bigChickenAmount, location, 50, 70, false)) {
                BigChicken bigChicken = new BigChicken(loc);
                spawnMethod(bigChicken);
            }

            for (Location loc : possibleLocations(culturalCultistAmount-3, location, 40, 90, false)) {
                CulturalCultist culturalCultist = new CulturalCultist(loc);
                spawnMethod(culturalCultist);
            }
            
                //Night mobs
            if (!BobuxTimer.isDay()) {

                for (Location loc : possibleLocations(stinkyMobAmount, location, 0, 100, false)) {
                    StinkyMob stinkyMob = new StinkyMob(loc);
                    spawnMethod(stinkyMob);
                }

                for (Location loc : possibleLocations(jumpySkeletonAmount, location, 0, 100, false)) {
                    JumpySkeleton jumpySkeleton = new JumpySkeleton(loc);
                    spawnMethod(jumpySkeleton);
                }


                //Day mobs
            } else {

            }

        } else if (biome.equals(Biome.MUSHROOM_FIELDS)) {

        } else if (biome.equals(Biome.PALE_GARDEN)) {



            //Savanna
        } else if (biome.equals(Biome.SAVANNA) || biome.equals(Biome.SAVANNA_PLATEAU)) {

        } 

        }
    }
}

