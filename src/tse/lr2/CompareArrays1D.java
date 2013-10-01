package tse.lr2;

import java.util.Arrays;

/**
 *
 * @author aNNiMON
 */
public class CompareArrays1D {

    public static void main() {
        String[] a = {"1","2","3"};
        String[] b1 = a;
        String[] b2 = a.clone();
        String[] b3 = Arrays.copyOf(a, a.length);
        String[] b4 = {"1","2","3"};
        
        printRow("b=a\t\t\t", getValues(a, b1));
        printRow("b=a.clone()\t\t", getValues(a, b2));
        printRow("b=Arrays.copyOf(a)\t", getValues(a, b3));
        printRow("b4 = {\"1\",\"2\",\"3\"}\t", getValues(a, b4));
        
    }

    private static void printRow(String col1, boolean[] values) {
        System.out.println();
        System.out.print(col1 + "\t");
        for (boolean val : values) {
            System.out.print(val + "\t");
        }
    }

    private static boolean[] getValues(String[] a, String[] b) {
        boolean[] values = new boolean[] {
            b == a,
            b.equals(a),
            Arrays.equals(a, b),
            Arrays.deepEquals(a, b),
            b[0] == a[0]
        };
        
        return values;
    }
}
