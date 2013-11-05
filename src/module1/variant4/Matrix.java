package module1.variant4;

/**
 *
 * @author aNNiMON
 */
public class Matrix {
    
    protected int[][] matrix;
    
    public Matrix(int row, int columns) {
        if ( (row < 1) || (columns < 1) ) {
            throw new RuntimeException("Размер матрицы должен быть больше 0");
        }
        matrix = new int[row][columns];
    }
    
    public Matrix(int[][] matrix) {
        this.matrix = matrix;
    }
    
    public int getRowSize() {
        return matrix.length;
    }
    
    public int getColumnSize() {
        return matrix[0].length;
    }
    
    public int[][] getMatrix() {
        return matrix;
    }
    
    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
    
    public void setRow(int row, int[] vector) {
        this.matrix[row] = vector;
    }
    
    public void setCell(int row, int column, int value) {
        this.matrix[row][column] = value;
    }
    
    public Matrix multiply(Matrix other) {
        int[][] matrixA = matrix;
        int[][] matrixB = other.matrix;
        
        int rowsA = matrixA.length;
        int columnsA = matrixA[0].length;
        int columnsB = matrixB.length;
        if (columnsB != columnsA) {
            throw new RuntimeException("Размеры матриц не совпадают");
        }
        
        int[][] matrixC = new int[rowsA][columnsB];
        for (int i = 0; i < rowsA; i++) {
           for (int j = 0; j < columnsB; j++) {
               for (int k = 0; k < columnsA; k++) {
                   matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
               }
           }
       }
        
        return new Matrix(matrixC);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Матрица\n");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sb.append(matrix[i][j]).append(' ');
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    
}
