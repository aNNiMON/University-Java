package tse.lr5;

import java.awt.Point;
import java.io.File;
import java.io.FilenameFilter;

/**
 * @author aNNiMON
 */
public class Utils {

    public static File[] readFiles(String path, final String extension) {
        File dir = new File(path);
        return dir.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(extension);
            }
        });
    }
    
    public static Point readPoint(String s1, String s2) {
        int x = Integer.parseInt(s1);
        int y = Integer.parseInt(s2);
        return new Point(x, y);
    }
}