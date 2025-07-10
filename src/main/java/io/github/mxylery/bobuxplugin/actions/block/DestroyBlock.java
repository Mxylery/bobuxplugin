package io.github.mxylery.bobuxplugin.actions.block;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;

import io.github.mxylery.bobuxplugin.actions.BobuxAction;
import io.github.mxylery.bobuxplugin.blocks.MultiBlock;

public class DestroyBlock extends BobuxAction {
    
    private int length;
    private int width;
    private int height;

    public DestroyBlock(int length, int width, int height) {
        super.requiresLocation = true;
        super.requiresVector = true;
    }

    public void run() {
        MultiBlock blocksToRemove = new MultiBlock(location, vector);
        ArrayList<Block> blockList = blocksToRemove.getBlocksAlongVector(length, width, height);
        for (Block block : blockList) {
            block.setType(Material.AIR);
        }
    }

}
