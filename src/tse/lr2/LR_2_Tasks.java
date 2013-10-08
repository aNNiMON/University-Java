package tse.lr2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    public void task4() {
        makeObjects();
        ArrayList<Ellipse> ellipses = new ArrayList<>();
        ellipses.add(ellipse1);
        ellipses.add(ellipse2);
        ellipses.add(ellipse3);
        ellipses.add(circle1);
        ellipses.add(circle2);
        printList(ellipses);
        System.out.println("Collections.sort");
        ArrayList<Ellipse> copy = (ArrayList<Ellipse>) ellipses.clone();
        Collections.sort(copy, squareComparator);
        printList(copy);
        System.out.println("user sort");
        copy = (ArrayList<Ellipse>) ellipses.clone();
        userSort(copy, squareComparator);
        printList(copy);
    }
    
    public void task5() {
        makeObjects();
        HashMap<Ellipse, String> map = new HashMap<>();
        map.put(ellipse1, "Эллипс Первый");
        map.put(ellipse2, "Эллипс Иоанн Второй");
        map.put(ellipse3, "Эллипс Третий");
        map.put(circle1, "Круг один");
        map.put(circle2, "Круг 2. Атака клонов");
        printMap(map);
        
        System.out.println("\nВыводим ellipse1");
        printMapEntry(ellipse1, map.get(ellipse1));
        
        System.out.println("\nДобавляем другой ellipse1 и выводим");
        map.put(ellipse1, "Снова Эллипс Первый");
        printMapEntry(ellipse1, map.get(ellipse1));
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
        
        point1 = new Point(3, 3);
    }
    
    private void printList(List<Ellipse> list) {
        for (int i = 0; i < list.size(); i++) {
            Ellipse ellipse = list.get(i);
            System.out.println( String.format("%2d. %f", i + 1, ellipse.getSquare()) );
        }
    }
    
    private void printMap(Map<Ellipse, String> map) {
        for (Map.Entry<Ellipse, String> entry : map.entrySet()) {
            printMapEntry(entry.getKey(), entry.getValue());
        }
    }
    
    private void printMapEntry(Ellipse key, String value) {
        System.out.println( String.format("%f\t- %s", key.getSquare(), value) );
    }
    
    private void userSort(List<Ellipse> list, Comparator<Ellipse> comparator) {
        final int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (comparator.compare(list.get(j), list.get(min)) < 0) {
                    min = j;
                }
            }
            Ellipse temp = list.get(i);
            list.set(i, list.get(min));
            list.set(min, temp);
        }
    }
    
    private final Comparator<Ellipse> squareComparator = new Comparator<Ellipse>() {

        @Override
        public int compare(Ellipse e1, Ellipse e2) {
            return Double.compare(e1.getSquare(), e2.getSquare());
        }
    };

}
