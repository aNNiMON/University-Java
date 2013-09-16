package tse.lr1;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import tse.Util;

/**
 * Уменьшить изображение вдвое.
 * @author aNNiMON
 */
public class ImageScaleDown extends JFrame {
    
    public static void main() {
        new ImageScaleDown().setVisible(true);
    }
    
    public ImageScaleDown() {
        super("Уменьшить изображение вдвое");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        initPanel(panel);
        
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void initPanel(JPanel panel) {
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        BufferedImage source = (BufferedImage) Util.readImageRes("lena.png");
        panel.add(new JLabel(new ImageIcon(source)), BorderLayout.WEST);
        
        BufferedImage dest = scaleDown(source);
        panel.add(new JLabel(new ImageIcon(dest)), BorderLayout.EAST);
    }
    
    private BufferedImage scaleDown(BufferedImage source) {
        final int width = source.getWidth();
        final int height = source.getHeight();
        // Получаем массив пикселей
        int[] srcPixels = new int[width * height];
        source.getRGB(0, 0, width, height, srcPixels, 0, width);
        // Уменьшаем изображение
        int[] destPixels = scaleDownArray(srcPixels, width, height);
        // Создаём новую картинку
        BufferedImage dest = new BufferedImage(width / 2, height / 2, BufferedImage.TYPE_INT_ARGB);
        dest.setRGB(0, 0, width / 2, height / 2, destPixels, 0, width / 2);
        
        return dest;
    }
    
    private int[] scaleDownArray(int[] src, int w, int h) {
        int newWidth = w / 2;
        int newHeight = h / 2;
        final int[] dest = new int[newWidth * newHeight];
        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                // Т.к по заданию изображение черно-белое, будем брать только один канал цвета.
                int pixel = src[ (y*2) * w + (x*2) ] & 0xFF;
                pixel += src[ (y*2) * w + (x*2+1) ] & 0xFF;
                pixel += src[ (y*2+1) * w + (x*2) ] & 0xFF;
                pixel += src[ (y*2+1) * w + (x*2+1) ] & 0xFF;
                pixel = pixel / 4;
                
                dest[y * newWidth + x] = 0xFF000000 | (pixel << 16) | (pixel << 8) | pixel;
            }
        }
        return dest;
    }
}
