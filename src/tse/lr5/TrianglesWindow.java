package tse.lr5;

import com.annimon.io.CsvReader;
import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tse.Util;

/**
 * @author aNNiMON
 */
public class TrianglesWindow extends JFrame {
    
    private final PaintPanel panel;

    public TrianglesWindow() {
        super("Треугольники");
        setAlwaysOnTop(true);
        setLocationByPlatform(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        panel = new PaintPanel(600, 450);
        add(panel);
        pack();
    }
    
    public void execute() {
        File[] csvFiles = Util.readFiles("lr5", ".csv");
        if (csvFiles == null || csvFiles.length == 0) {
            JOptionPane.showMessageDialog(this, "CSV-файлов не обнаружено! Сворачиваемся, ребята",
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println("Найдено " + csvFiles.length + " файлов.");
        
        setVisible(true);
        try {
            System.out.println("3");
            Thread.sleep(1000);
            System.out.println("2");
            Thread.sleep(1000);
            System.out.println("1");
            Thread.sleep(1000);
        } catch (InterruptedException ex) { }
        System.out.println("Понеслась!!");
        
        for (File file : csvFiles) {
            startReadInNewThread(file);
        }
    }

    private void startReadInNewThread(final File file) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                readCsvFile(file);
            }
        }).start();
    }
    
    private void readCsvFile(File file) {
        CsvReader<TrianglePaintable> csvReader = new CsvReader<>(file);
        csvReader.setReaderHandler(trianglesHandler);
        try {
            csvReader.readCsv(false, ",");
        } catch (IOException ex) {
            Util.handleException(ex);
        } 
    }
    
    private final CsvReader.ReaderHandler<TrianglePaintable> trianglesHandler
            = new CsvReader.ReaderHandler() {

        @Override
        public void onStartRead(File file) {
            System.out.println("Читаем файл " + file.getName());
        }

        @Override
        public TrianglePaintable createObject(String[] params) {
            if (params.length != 8)  throw new RuntimeException("Неверное количество параметров");
            
            Color fill = Color.decode(params[0]);
            Color stroke = Color.decode(params[1]);
            Point p1 = Util.readPoint(params[2], params[3]);
            Point p2 = Util.readPoint(params[4], params[5]);
            Point p3 = Util.readPoint(params[6], params[7]);
            TrianglePaintable obj = new TrianglePaintable(fill, stroke, p1, p2, p3);
            panel.addPaintable(obj);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) { }
            return obj;
        }

        @Override
        public void onFinishRead(File file) {
            System.out.println("Чтение файла " + file.getName() + " завершено!");
        }
    };
}
