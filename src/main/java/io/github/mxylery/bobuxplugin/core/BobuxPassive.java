package io.github.mxylery.bobuxplugin.core;

public class BobuxPassive {
    
    private BobuxAction[] passive;
    private int delay;

    public BobuxPassive(BobuxAction[] passive, int delay) {
        this.passive = passive;
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    public BobuxAction[] getPassive() {
        return passive;
    }

}
