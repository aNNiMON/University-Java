package tse.lr3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author aNNiMON
 */
public class DirZip extends AbstractDirectoryChooser {
    
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
    protected void directorySelected(File directory) {
        try {
            if (EXTRACT) {

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