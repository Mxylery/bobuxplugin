package io.github.mxylery.bobuxplugin.entities;

import io.github.mxylery.bobuxplugin.entities.livingentities.BigChicken;
import io.github.mxylery.bobuxplugin.entities.mobs.CulturalCultist;
import io.github.mxylery.bobuxplugin.entities.mobs.Sandbagger;
import io.github.mxylery.bobuxplugin.entities.mobs.ScoutZombie;
import io.github.mxylery.bobuxplugin.entities.mobs.StinkyMob;

public enum BobuxEntityType {

    //Questboard entities go below this comment
    STINKY_MOB(StinkyMob.class),
    SCOUT_ZOMBIE(ScoutZombie.class),
    SANDBAGGER(Sandbagger.class),
    BIG_CHICKEN(BigChicken.class),
    CULTURAL_CULTIST(CulturalCultist.class);
    //Non questboard entities go below this comment

    private Class<? extends BobuxEntity> clazz;
    private final int TOTALMOBS = 5;
    private final int TOTALQUESTMOBS = 5;

    BobuxEntityType(Class<? extends BobuxEntity> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends BobuxEntity> getType() {
        return clazz;
    }

}
