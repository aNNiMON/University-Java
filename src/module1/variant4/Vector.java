package module1.variant4;

/**
 *
 * @author aNNiMON
 */
public class Vector extends Matrix {
    
    public Vector(int size) {
        super(new int[1][size]);
    }
    
    public Vector(int[] vector) {
        super(new int[][] {
            vector
        });
    }

    public int[] getVector() {
        return matrix[0];
    }
    
    public int getVectorSize() {
        return matrix[0].length;
    }
    
    public void setCell(int column, int value) {
        super.setCell(0, column, value);
    }
    
    public Vector getMultiplyMatrixAndVector(Matrix matrix) {
        int[][] matrixA = matrix.getMatrix();
        int rowsA = matrixA.length;
        int columnsA = matrixA[0].length;
        
        int[] vector = getVector();
        
        if (vector.length != columnsA) {
            throw new RuntimeException("Размер матрицы и длина вектора не совпадают");
        }
        int[] result = new int[rowsA];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < columnsA; j++) {
                result[i] += (matrixA[i][j] * vector[j]);
            }
        }
        return new Vector(result);
    }
    
    public int getVectorScalarMultiply(Vector other) {
        int[] vectorA = getVector();
        int[] vectorB = other.getVector();
        
        int result = 0;
        for (int i = 0; i < vectorA.length; i++) {
                result += vectorA[i] * vectorB[i];
        }
        
        return result;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Вектор\n");
        for (int j = 0; j < matrix[0].length; j++) {
            sb.append(matrix[0][j]).append(' ');
        }
        sb.append("\n");
        return sb.toString();
    }
    
}
