package io.github.mxylery.bobuxplugin.guis.animations;

import io.github.mxylery.bobuxplugin.data_structures.GUIStructure;

public class GUIAnimation {

    protected void moveUp(GUIStructure structure, int index) {
        index += 9;
    }

    protected void moveRight(int index) {
        index += 1;
    }

    protected void moveDown(int index) {
        index -= 9;
    }

    protected void moveleft(int index) {
        index -= 1;
    }

    public static void aroundSlotAnimation() {
        
    }

}
