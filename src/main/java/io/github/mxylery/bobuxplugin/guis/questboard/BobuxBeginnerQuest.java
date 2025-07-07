package io.github.mxylery.bobuxplugin.guis.questboard;

public class BobuxBeginnerQuest extends BobuxQuest {

    public BobuxBeginnerQuest(int questAmount) {
        super(questAmount);
        compensation = 50;
    }

    protected void generateQuests() {
        Quest quest;
        int[] rngTrack = new int[questAmount];
        for (int i = 0; i < questAmount; i++) {
            int rng = (int) (Math.random()*Quest.TOTAL_BEGINNER_MOBS);
            for (int j = 0; j < questAmount; j++) {
                if (rng == rngTrack[j]) {
                    rng = (int) (Math.random()*Quest.TOTAL_BEGINNER_MOBS);
                    j = -1;
                }
            }
            switch (rng) {
                case 0: quest = Quest.BIG_CHICKEN;
                break;
                case 1: quest = Quest.CULTURAL_CULTIST;
                break;
                case 2: quest = Quest.JUMPY_SKELETON;
                break;
                case 3: quest = Quest.SCOUT_ZOMBIE;
                break;
                case 4: quest = Quest.STINKY_MOB;
                break;
                default: quest = Quest.BIG_CHICKEN;
                break;
            }
            rngTrack[i] = rng;
            questArray[i] = quest;
        }
        generateDrops();
    }
}
