package tse.tools;

import com.annimon.ui.AbstractFileChooser;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import tse.Util;

/**
 *
 * @author aNNiMON
 */
public class ImageCsvCreator {
    
    private static final int SCALE_OPT = 6;
    private static final int CSV_FILES = 6;
    private static final int STROKE_COLOR = -1;
    private static final String FILE_PREFIX = "00";

    public static void main() {
        ImageCsvCreator creator = new ImageCsvCreator();
        creator.chooseImage();
    }
    
    private BufferedImage image;
    private File directory;
    
    private void chooseImage() {
        new ImageChooser().setVisible(true);
    }
    
    private void chooseOutputDirectory() {
        new DirectoryChooser().setVisible(true);
    }
    
    private void finish() {
        List<TriangleCSV> list = new ArrayList<>();
        readImage(list);
        Collections.shuffle(list);
        try {
            saveCsv(list);
        } catch (IOException ex) {
            Util.handleException(ex);
        }
    }
    
    private void readImage(List<TriangleCSV> list) {
        final int width = image.getWidth();
        final int height = image.getHeight();
        // Получаем массив пикселей
        int[] srcPixels = new int[width * height];
        image.getRGB(0, 0, width, height, srcPixels, 0, width);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = srcPixels[y * width + x] & 0xFFFFFF;
                
                int x1 = x * SCALE_OPT;
                int y1 = y * SCALE_OPT;
                int x2 = x1 + SCALE_OPT;
                int y2 = y1 + SCALE_OPT;
                String color = "#" + Integer.toHexString(pixel).toUpperCase();
                String strokeColor;
                if (STROKE_COLOR == -1) strokeColor = color;
                else strokeColor = "#" + Integer.toHexString(STROKE_COLOR).toUpperCase();
                
                list.add(new TriangleCSV(color, strokeColor, x1, y1, x2, y1, x1, y2));
                list.add(new TriangleCSV(color, strokeColor, x2, y1, x1, y2, x2, y2));
            }
        }
    }
    
    private void saveCsv(List<TriangleCSV> list) throws IOException {
        BufferedWriter[] writers = new BufferedWriter[CSV_FILES];
        for (int i = 0; i < CSV_FILES; i++) {
            writers[i] = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(
                            new File(directory, FILE_PREFIX + i + ".csv")), "UTF-8")
            );
            
        }
        
        int fileIndex = 0;
        for (TriangleCSV csv : list) {
            writers[fileIndex].write(csv.toCsvLine());
            writers[fileIndex].newLine();
            
            fileIndex++;
            if (fileIndex >= CSV_FILES) fileIndex = 0;
        }
        
        for (int i = 0; i < CSV_FILES; i++) {
            writers[i].flush();
            writers[i].close();
        }
    }
    
    private class TriangleCSV {
        String color, strokeColor;
        int x1, y1, x2, y2, x3, y3;
        
        public TriangleCSV(String color, String strokeColor,
                int x1, int y1, int x2, int y2, int x3, int y3) {
            this.color = color;
            this.strokeColor = strokeColor;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.x3 = x3;
            this.y3 = y3;
        }
        
        String toCsvLine() {
            return String.format("%s,%s,%d,%d,%d,%d,%d,%d", color, strokeColor,
                    x1, y1, x2, y2, x3, y3);
        }
    }
    
    private class ImageChooser extends AbstractFileChooser {

        public ImageChooser() {
            super("", "Выбрать изображение", JFileChooser.FILES_ONLY);
        }

        @Override
        protected void onFileSelected(File file) {
            try {
                image = ImageIO.read(file);
            } catch (IOException ex) {
                Util.handleException(ex);
            }
            chooseOutputDirectory();
            setVisible(false);
        }
    }
    
    private class DirectoryChooser extends AbstractFileChooser {

        public DirectoryChooser() {
            super("", "Папка сохранения CSV", JFileChooser.DIRECTORIES_ONLY);
        }

        @Override
        protected void onFileSelected(File file) {
            directory = file;
            finish();
            setVisible(false);
        }
    }
}
