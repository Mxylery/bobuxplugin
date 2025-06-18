package io.github.mxylery.bobuxplugin.guis.questboard;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.mxylery.bobuxplugin.entities.BobuxEntity;
import io.github.mxylery.bobuxplugin.entities.livingentities.hostiles.BigChicken;
import io.github.mxylery.bobuxplugin.entities.mobs.CulturalCultist;
import io.github.mxylery.bobuxplugin.entities.mobs.JumpySkeleton;
import io.github.mxylery.bobuxplugin.entities.mobs.Sandbagger;
import io.github.mxylery.bobuxplugin.entities.mobs.ScoutZombie;
import io.github.mxylery.bobuxplugin.entities.mobs.StinkyMob;
import io.github.mxylery.bobuxplugin.items.BobuxItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public enum Quest {
    //Normal
    STINKY_MOB(StinkyMob.class, BobuxItemInterface.stinkyResidue, "Stinky Mob", 3, 6),
    SCOUT_ZOMBIE(ScoutZombie.class, BobuxItemInterface.cinnamonSquare, "Scout Zombie", 3, 6),
    BIG_CHICKEN(BigChicken.class, BobuxItemInterface.mutantOrb, "Big Chicken", 2, 4),
    CULTURAL_CULTIST(CulturalCultist.class, BobuxItemInterface.culturalShard, "Cultural Cultist", 1, 2),
    JUMPY_SKELETON(JumpySkeleton.class, BobuxItemInterface.abcBlood, "Jumpy Skeleton", 3, 6),

    //Expert


    //Non questboard entities go below this comment
    SANDBAGGER(Sandbagger.class, BobuxItemInterface.bobuxBrewRemnants, "Sandbagger", 1, 1);

    private final Class<? extends BobuxEntity> clazz;
    private final BobuxItem drop;
    private final String name;
    public final int min;
    public final int max;

    public static final int TOTAL_MOBS = 6;
    public static final int TOTAL_QUESTMOBS = 5;
    public static final int TOTAL_BEGINNER_MOBS = 5;
    public static final int TOTAL_EXPERT_MOBS = 0;

    Quest(Class<? extends BobuxEntity> clazz, BobuxItem drop, String name, int min, int max) {
        this.clazz = clazz;
        this.drop = drop;
        this.name = name;
        this.min = min;
        this.max = max;
    }

    public String getName() {
        return name;
    }

    public Class<? extends BobuxEntity> getType() {
        return clazz;
    }

    public BobuxItem getDrop() {
        return drop;
    }
    
}
