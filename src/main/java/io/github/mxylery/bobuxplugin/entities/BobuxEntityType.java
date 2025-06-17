package io.github.mxylery.bobuxplugin.entities;

import io.github.mxylery.bobuxplugin.entities.livingentities.hostiles.*;
import io.github.mxylery.bobuxplugin.entities.mobs.*;
import io.github.mxylery.bobuxplugin.items.BobuxItem;
import io.github.mxylery.bobuxplugin.items.BobuxItemInterface;

public enum BobuxEntityType {

    //Questboard entities go below this comment
    STINKY_MOB(StinkyMob.class, BobuxItemInterface.stinkyResidue, 2, 4),
    SCOUT_ZOMBIE(ScoutZombie.class, BobuxItemInterface.cinnamonSquare, 4, 10),
    SANDBAGGER(Sandbagger.class, BobuxItemInterface.bobuxBrewRemnants, 1, 1),
    BIG_CHICKEN(BigChicken.class, BobuxItemInterface.mutantOrb, 2, 4),
    CULTURAL_CULTIST(CulturalCultist.class, BobuxItemInterface.bobux, 1, 2),
    JUMPY_SKELETON(JumpySkeleton.class, BobuxItemInterface.abcBlood, 4, 8);
    //Non questboard entities go below this comment

    private final Class<? extends BobuxEntity> clazz;
    private final BobuxItem drop;
    private final int min;
    private final int max;

    private static final int TOTAL_MOBS = 6;
    private static final int TOTAL_QUESTMOBS = 6;
    private static final int TOTAL_BEGINNER_MOBS = 6;
    private static final int TOTAL_EXPERT_MOBS = 6;

    BobuxEntityType(Class<? extends BobuxEntity> clazz, BobuxItem drop, int min, int max) {
        this.clazz = clazz;
        this.drop = drop;
        this.min = min;
        this.max = max;
    }

    public Class<? extends BobuxEntity> getType() {
        return clazz;
    }

    public BobuxItem getDrop() {
        return drop;
    }

}
