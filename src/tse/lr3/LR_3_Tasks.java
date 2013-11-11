package tse.lr3;

import tse.lr2.*;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aNNiMON
 */
public class LR_3_Tasks {
    
    private static LR_3_Tasks instance;
    
    public static synchronized LR_3_Tasks getInstance() {
        if (instance == null) instance = new LR_3_Tasks();
        return instance;
    }
    
    private Ellipse ellipse1, ellipse2, ellipse3;
    private Circle circle1, circle2;
    
    public void task1() {
        makeObjects();
        System.out.println("До сериализации");
        System.out.println(ellipse1.toString());
        serialize("t1_ellipse.ser", ellipse1);
        
        Ellipse el = (Ellipse) deserialize("t1_ellipse.ser");
        if (el != null) {
            System.out.println("После сериализации");
            System.out.println(el.toString());
        }
        System.out.println("Готово!");
    }

    public void task2() {
        makeObjects();
        ArrayList<Ellipse> ellipses = new ArrayList<>();
        ellipses.add(ellipse1);
        ellipses.add(ellipse2);
        ellipses.add(ellipse3);
        ellipses.add(circle1);
        ellipses.add(circle2);
        printList(ellipses);
    }
    
    public void task5() {
        makeObjects();
    }
    
    private void makeObjects() {
        ellipse1 = new Ellipse(
                new Point(2, 2), new Point(8, 2),
                new Point(2, 6), new Point(8, 6)
        );
        ellipse2 = new Ellipse(
                new Point(0, -4), new Point(20, -4),
                new Point(0, 12), new Point(20, 12)
        );
        ellipse3 = new Ellipse(ellipse1);
        
        circle1 = new Circle( new Point(3, 3),  6 );
        circle2 = new Circle( new Point(5, 4),  2 );
    }
    
    private void serialize(String filename, Object object) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private Object deserialize(String filename) {
        Object object = null;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            object = ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return object;
    }
    
    private void printList(List<Ellipse> list) {
        for (int i = 0; i < list.size(); i++) {
            Ellipse ellipse = list.get(i);
            System.out.println( String.format("%2d. %f", i + 1, ellipse.getSquare()) );
        }
    }

}
