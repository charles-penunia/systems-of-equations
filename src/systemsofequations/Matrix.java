
package systemsofequations;

/* Charles Penunia. CMPSC 221.
 * This class contains a 2x2 matrix
 */

public class Matrix {
    int[][] theMatrix;
    public Matrix(int topLeft, int topRight, int botLeft, int botRight){
        theMatrix = new int[2][2];
        theMatrix[0][0] = topLeft;
        theMatrix[0][1] = topRight;
        theMatrix[1][0] = botLeft;
        theMatrix[1][1] = botRight;
    }
    public int determinant(){
        return theMatrix[1][1]*theMatrix[0][0] 
                - theMatrix[0][1]*theMatrix[1][0];
    }
}
