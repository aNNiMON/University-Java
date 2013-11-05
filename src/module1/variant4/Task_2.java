package module1.variant4;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author aNNiMON
 */
public class Task_2 {
    
    public static void main(String[] args) {
        Random rnd = new Random();
        
        Matrix mt1 = new Matrix(1, 3);
        for (int i = 0; i < mt1.getColumnSize(); i++) {
            mt1.setCell(0, i, rnd.nextInt(10));
        }
        
        Matrix mt2 = new Matrix(3, 3);
        for (int i = 0; i < mt2.getRowSize(); i++) {
            for (int j = 0; j < mt2.getColumnSize(); j++) {
                mt2.setCell(i, j, rnd.nextInt(10));
            }
        }
        
        Vector vector = new Vector(3);
        for (int i = 0; i < vector.getVectorSize(); i++) {
            vector.setCell(i, rnd.nextInt(10));
        }
        
        
        ArrayList<Matrix> list = new ArrayList<>();
        list.add(mt1);
        list.add(mt2);
        list.add(vector);
        
        for (Matrix matrix : list) {
            System.out.println(matrix.toString());
        }
        System.out.println("AxB= " + mt1.multiply(mt2).toString());
        System.out.println("V*A= " + vector.getMultiplyMatrixAndVector(mt1).toString());
        System.out.println("V*B= " + vector.getMultiplyMatrixAndVector(mt2).toString());
        System.out.println("V*V= " + vector.getVectorScalarMultiply(vector));
    }
    
}
