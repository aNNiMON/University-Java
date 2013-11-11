package tse.lr3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;

/**
 *
 * @author aNNiMON
 */
public class DirCopy extends AbstractDirectoryChooser {
    
    private static final int DAYS = 3;

    public static void main() {
        new DirCopy().setVisible(true);
    }
    
    private File dirSource, dirDest, logFile;
    private BufferedWriter writer;

    public DirCopy() {
        super("Копирование директорий");
        createFilesAndDirs();
    }

    private void createFilesAndDirs() {
        dirDest = new File("E:\\LR_3\\task3");
        dirDest.mkdirs();
        logFile = new File("E:\\LR_3\\task3_log.txt");
        try {
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(logFile, true), "UTF-8")
            );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void directorySelected(File directory) {
        dirSource = directory;
        
        dirDest.mkdirs();
        File[] files = dirSource.listFiles(lastModifiedFilter);
        if (files.length > 0) {
            writeLog("Копируем из " + dirSource.getAbsolutePath() + " в " + dirDest.getAbsolutePath());
            for (File file : files) {
                try {
                    copyFile(file, dirDest);
                    writeLog(file.getAbsolutePath());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else writeLog("Нечего копировать из " + dirSource.getAbsolutePath());
        
        if (writer != null) {
            try {
                writer.flush();
                writer.close();
            } catch (IOException ex) {}
        }
    }
    
    private void copyFile(File sourceFile, File destDirectory) throws IOException {
        File destFile = new File(destDirectory, sourceFile.getName());
        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }
    
    private void writeLog(String filename) {
        System.out.println(filename);
        try {
            writer.write(filename);
            writer.newLine();
        } catch (IOException ex) { }
    }
    
    private final FileFilter lastModifiedFilter = new FileFilter() {

        @Override
        public boolean accept(File pathname) {
            if (pathname.isDirectory()) return false;
            
            long lastDays = System.currentTimeMillis() - (DAYS * 24 * 60 * 60 * 1000);
            return (pathname.lastModified() >= lastDays);
        }
    };
}