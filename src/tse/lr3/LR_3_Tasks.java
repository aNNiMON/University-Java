package tse.lr3;

import tse.lr2.*;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
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
        boolean needCreateCSV = false;
        ArrayList<Ellipse> ellipses = new ArrayList<>();
        if (needCreateCSV) {
            ellipses.add(ellipse1);
            ellipses.add(ellipse2);
            ellipses.add(ellipse3);
            ellipses.add(circle1);
            ellipses.add(circle2);
            printList(ellipses);
        }
        
        try {
            // Создаём при необходимости CSV-файл.
            if (needCreateCSV) {
                writeToCSV("ellipses.csv", ellipses);
                ellipses = null;
            }
            
            // Читаем список из CSV
            ellipses = (ArrayList) readFromCSV("ellipses.csv");
            System.out.println("Прочитано из csv");
            printList(ellipses);
            
            // Сериализуем список в бинарный файл
            serialize("t2_list.ser", ellipses);
            ellipses = null;
            
            // Десериализуем список из файла
            ellipses = (ArrayList) deserialize("t2_list.ser");
            System.out.println("Десериализовано из бинарного файла");
            printList(ellipses);
            System.out.println("Готово");
        } catch (IOException ex) {
            ex.printStackTrace();
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
    
    private void writeToCSV(String filename, List<Ellipse> list) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filename), "UTF-8")
        );
        writer.write("Класс,X1,Y1,X2,Y2,X3,Y3,X4,Y4");
        for (Ellipse ellipse : list) {
            writer.newLine();
            writeCsvLine(writer, ellipse);
        }
        writer.flush();
        writer.close();
    }
    
    private void writeCsvLine(BufferedWriter writer, Ellipse ellipse) throws IOException {
        final String SEPARATOR = ",";
        writer.write(ellipse.getClass().getName());
        Point[] points = ellipse.getPoints();
        for (Point point : points) {
            writer.write(SEPARATOR);
            writer.write(String.valueOf(point.x));
            writer.write(SEPARATOR);
            writer.write(String.valueOf(point.y));
        }
    }
    
    private List<Ellipse> readFromCSV(String filename) throws IOException {
        List<Ellipse> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader( new FileInputStream(filename), "UTF-8" )
        );
        reader.readLine(); // Класс,X1,Y1,X2,Y2,X3,Y3,X4,Y4
        String line;
        while ( (line = reader.readLine()) != null ) {
            Ellipse ellipse = null;
            try {
                ellipse = readFromCsvLine(line);
            } catch (RuntimeException ex) {
                System.out.println(ex.toString());
            }
            if (ellipse != null) list.add(ellipse);
        }
        reader.close();
        return list;
    }
    
    private Ellipse readFromCsvLine(String line) {
        final int POINTS_COUNT = 4;
        if (line.isEmpty()) throw new RuntimeException("Пустая строка");
        String[] params = line.split(",");
        if (params.length < 9) throw new RuntimeException("Неверное количество параметров");
        
        final int[] coords = new int[POINTS_COUNT * 2];
        for (int i = 0; i < coords.length; i++) {
            coords[i] = Integer.parseInt(params[i + 1]);
        }
        
        Ellipse out;
        if (params[0].equalsIgnoreCase(Circle.class.getName())) {
            out = new Circle();
        } else {
            out = new Ellipse();
        }
        for (int i = 0; i < POINTS_COUNT; i++) {
            out.setPoint(i, new Point(coords[i * 2], coords[i * 2 + 1]));
        }
        return out;
    }
    
    private void printList(List<Ellipse> list) {
        for (int i = 0; i < list.size(); i++) {
            Ellipse ellipse = list.get(i);
            System.out.println( String.format("%2d. %s: Площадь %f\n", i + 1,
                    ellipse.getClass().getName(), ellipse.getSquare()) );
        }
    }

}
