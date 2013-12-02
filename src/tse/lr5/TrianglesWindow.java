package tse.lr5;

import java.io.File;
import javax.swing.JFrame;

/**
 * @author aNNiMON
 */
public class TrianglesWindow extends JFrame {
    
    private final PaintPanel panel;

    public TrianglesWindow() {
        super("Треугольники");
        setLocationByPlatform(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        panel = new PaintPanel(600, 400);
        add(panel);
        pack();
    }
    
    public void execute() throws InterruptedException {
        setVisible(true);
        
        File[] csvFiles = Utils.readFiles("lr5", ".csv");
        System.out.println("Найдено " + csvFiles.length + " файлов.");
        System.out.println("3");
        Thread.sleep(1000);
        System.out.println("2");
        Thread.sleep(1000);
        System.out.println("1");
        Thread.sleep(1000);
        System.out.println("Понеслась!!");
    }
}
