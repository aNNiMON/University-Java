package tse.lr3;

import java.io.File;

/**
 *
 * @author aNNiMON
 */
public class DirCopy extends AbstractDirectoryChooser {

    public static void main() {
        new DirCopy().setVisible(true);
    }
    
    private File dirSource;

    public DirCopy() {
        super("Копирование директорий");
    }

    @Override
    protected void directorySelected(File directory) {
        System.out.println(directory.getAbsolutePath());
    }
}