package tse.lr5;

import java.awt.Color;
import java.util.Random;
import javax.swing.JFrame;

/**
 * @author aNNiMON
 */
public class FileIconsWindow extends JFrame {
    
    public static final int WIDTH = 600, HEIGHT = 450;
    
    private final PaintPanelTask2 panel;

    public FileIconsWindow() {
        super("Перевёрнутые иконки файла");
        setAlwaysOnTop(true);
        setLocationByPlatform(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        panel = new PaintPanelTask2(600, 450);
        add(panel);
        pack();
    }
    
    public void execute() {
        setVisible(true);
        
        // Добавляем элемент которым будем управлять.
        FileIconPaintable controllable = new FileIconPaintable(1.8, new Color(0x0E7405), true);
        controllable.setLocation(10, HEIGHT - 80);
        panel.addPaintable(controllable);
        
        Random rnd = new Random();
        final int size = 8;
        for (int i = 0; i < size; i++) {
            double scale = rnd.nextDouble() * 1 + 0.4;
            FileIconPaintable paintable = new FileIconPaintable(scale,
                    new Color(rnd.nextInt(0xDDDDDD)), rnd.nextBoolean());
            paintable.move(i * (WIDTH / size), i * (HEIGHT / size));
            paintable.start();
            panel.addPaintable(paintable);
        }
        panel.startRepainterThread();
    }
}
