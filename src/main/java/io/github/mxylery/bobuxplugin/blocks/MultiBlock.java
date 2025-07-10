package io.github.mxylery.bobuxplugin.blocks;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import io.github.mxylery.bobuxplugin.core.BobuxUtils;
import io.github.mxylery.bobuxplugin.vectors.ParticleSequence.ParticleSequenceOrientations;

public class MultiBlock {

    private Location location;
    private Vector vector;

    public MultiBlock(Location location, Vector vector) {
        this.location = location;
        this.vector = vector;
    }

    public ArrayList<Block> getBlocksAlongVector(int length, int width, int height) {
        Vector[] matrix = BobuxUtils.getNormalizedMatrix(vector, ParticleSequenceOrientations.NORMAL);
        ArrayList<Block> blockList = new ArrayList<Block>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                for (int h = 0; h < height; h++) {
                    double x = location.getX();
                    double y = location.getY();
                    double z = location.getZ();
                    Location newLocation = new Location(location.getWorld(), x, y, z);
                    newLocation.add(matrix[0].getX()*i + matrix[1].getX()*j + matrix[2].getX()*h, 
                    matrix[0].getY()*i + matrix[1].getY()*j + matrix[2].getY()*h,
                    matrix[0].getZ()*i + matrix[1].getZ()*j + matrix[2].getZ()*h);
                    Block block = newLocation.getWorld().getBlockAt(newLocation);
                    blockList.add(block);
                }
            }
        }
        return blockList;
    }

    public ArrayList<Block> getBlocksStraightUp(int amount) {
        ArrayList<Block> blockList = new ArrayList<Block>();
        for (int i = 0; i < amount; i++) {
            double x = location.getX();
            double y = location.getY();
            double z = location.getZ();
            Location newLocation = new Location(location.getWorld(), x, y + i, z);
            Block block = newLocation.getWorld().getBlockAt(newLocation);
            blockList.add(block);
        }
        return blockList;
    }

}
