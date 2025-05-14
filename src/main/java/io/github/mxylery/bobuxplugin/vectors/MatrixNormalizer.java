package io.github.mxylery.bobuxplugin.vectors;

import org.bukkit.util.Vector;

public class MatrixNormalizer {
    
    public Vector[] getNormalizedMatrix(Vector vector) {
        //The normalized matrix will be as if the player was facing straight up.
        Vector normalYVector = new Vector(0.0,1.0,0.0);
        Vector firstBasisVector = vector.crossProduct(normalYVector);
        firstBasisVector.normalize();
        Vector secondBasisVector = firstBasisVector.crossProduct(vector);
        secondBasisVector.normalize();
        Vector thirdBasisVector = firstBasisVector.crossProduct(secondBasisVector);
        thirdBasisVector.normalize();
        Vector[] matrix = {firstBasisVector, secondBasisVector, thirdBasisVector};
        return matrix;
        
    }

}
