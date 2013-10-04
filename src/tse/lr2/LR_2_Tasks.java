package tse.lr2;

import java.awt.Point;

/**
 *
 * @author aNNiMON
 */
public class LR_2_Tasks {
    
    private static LR_2_Tasks instance;
    
    public static synchronized LR_2_Tasks getInstance() {
        if (instance == null) instance = new LR_2_Tasks();
        return instance;
    }
    
    private Ellipse ellipse1, ellipse2, ellipse3;
    private Circle circle1, circle2;
    private Point point1;
    
    public void task3() {
        makeObjects();
        
        Ellipse[] arr1 = new Ellipse[] {
            ellipse1, ellipse2, ellipse3, circle1, circle2
        };
        for (Ellipse item : arr1) {
            System.out.println("----------");
            System.out.println("Класс: " + item.getClass().getName());
            System.out.println("Центр: " + item.getCenterPoint().toString());
            System.out.println("Площадь: " + item.getSquare());
            System.out.println("Расстояние от другого эллипса: " + item.getDistanceOfCentres(ellipse2));
            if (item instanceof Circle) {
                boolean consist = ((Circle)item).isConsist(point1);
                System.out.println("Круг. Находится ли точка внутри? " + (consist ? "Да" : "Нет"));
            }
            System.out.println(item.toString());
        }
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
        
        circle1 = new Circle(
                new Point(1, 1), new Point(6, 1),
                new Point(1, 6), new Point(6, 6)
        );
        circle2 = new Circle(
                new Point(5, 4),  new Point(15, 4),
                new Point(5, 14), new Point(15, 14)
        );
        
        point1 = new Point(3, 3);
    }
}
