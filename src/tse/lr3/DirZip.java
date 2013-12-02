package tse.lr3;

import com.annimon.ui.AbstractFileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author aNNiMON
 */
public class DirZip extends AbstractFileChooser {
    
    private static final boolean EXTRACT = false;
    
    public static void main() {
        new DirZip().setVisible(true);
    }
    
    private File zipFile;

    public DirZip() {
        super("Работа с Zip");
        createFilesAndDirs();
    }

    private void createFilesAndDirs() {
        zipFile = new File("E:\\LR_3\\task5.zip");
    }

    @Override
    protected void onFileSelected(File directory) {
        try {
            if (EXTRACT) {
                FileInputStream fis = new FileInputStream(zipFile);
                ZipInputStream zis = new ZipInputStream(fis);
                unzipDirectory(zis, directory);
                zis.close();
            } else {
                // Запаковываем
                FileOutputStream fos = new FileOutputStream(zipFile);
                ZipOutputStream zos = new ZipOutputStream(fos);
                zipDirectory(zos, directory.getParent(), directory);
                zos.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Готово!");
    }
    
    private void unzipDirectory(ZipInputStream zis, File directory) throws IOException {
        ZipEntry entry;
        while ( (entry = zis.getNextEntry()) != null) {
            final String name = entry.getName();
            if (entry.isDirectory()) {
                // Создаём папку
                new File(directory, name).mkdirs();
            } else {
                int separatorIndex = name.lastIndexOf(File.separator);
                if (separatorIndex != -1) {
                    // Создаём папки на пути к файлу
                    new File(directory, name.substring(0, separatorIndex)).mkdirs();
                }
                // Распаковываем файл
                byte[] buffer = new byte[1024];
                FileOutputStream fos = new FileOutputStream(new File(directory, name));
                int length;
                while ( (length = zis.read(buffer)) > 0 ) {
                    fos.write(buffer, 0, length);
                }
                fos.flush();
                fos.close();
            }
        }
        zis.closeEntry();
    }
    
    private void zipDirectory(ZipOutputStream zos, String sourceDir, File fileSource) throws IOException {
        File[] files = fileSource.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                zipDirectory(zos, sourceDir, file);
            } else {
                byte[] buffer = new byte[1024];
                FileInputStream fin = new FileInputStream(file);
                zos.putNextEntry(new ZipEntry(getRelativePath(sourceDir, file)));
                int length;
                while ( (length = fin.read(buffer)) > 0 ) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
                fin.close();
            }
        }
    }
    
    private String getRelativePath(String sourceDir, File file) {
        String path = file.getPath().substring(sourceDir.length());
        if (path.startsWith(File.separator)) {
            path = path.substring(File.separator.length());
        }
        return path;
    }
}