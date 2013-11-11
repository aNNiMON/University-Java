package tse.lr3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author aNNiMON
 */
public class DirFindText extends AbstractDirectoryChooser {
    
    private static final String SEARCH_TEXT = "class";
    
    public static void main() {
        new DirFindText().setVisible(true);
    }
    
    private final List<FindInfo> findInfos;
    private File logFile;
    private BufferedWriter writer;

    public DirFindText() {
        super("Поиск текста в директориях");
        findInfos = new ArrayList<>();
        createFilesAndDirs();
    }

    private void createFilesAndDirs() {
        logFile = new File("E:\\LR_3\\task4_log.txt");
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
        scanFiles(directory);
        writeFindInfo(findInfos);
        if (writer != null) {
            try {
                writer.flush();
                writer.close();
            } catch (IOException ex) {}
        }
    }
    
    private void scanFiles(File dir) {
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                scanFiles(file);
            } else {
                // Ищем строку в файле
                try {
                    findString(file, SEARCH_TEXT);
                } catch (IOException ex) {}
            }
        }
    }
    
    private void findString(File file, String searchText) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader( new FileInputStream(file), "UTF-8" )
        );
        int lineNumber = 0;
        String line;
        while ( (line = reader.readLine()) != null ) {
            int pos = -1;
            while( (pos = line.indexOf(searchText, pos + 1)) != -1) {
                findInfos.add(new FindInfo(file.getAbsolutePath(), lineNumber, pos));
            }
            lineNumber++;
        }
        reader.close();
        reader = null;
    }
    
    private void writeFindInfo(List<FindInfo> infos) {
        writeLog("------------------");
        writeLog("Искали: " + SEARCH_TEXT);
        Collections.sort(infos, filenameComparator);
        for (FindInfo info : infos) {
            writeLog(String.format("[%d:%d]\tfile://%s", info.linenumber + 1, info.position + 1, info.filename));
        }
    }
    
    private void writeLog(String filename) {
        System.out.println(filename);
        try {
            writer.write(filename);
            writer.newLine();
        } catch (IOException ex) { }
    }

    private class FindInfo {
        String filename;
        int linenumber, position;

        public FindInfo(String filename, int linenumber, int position) {
            this.filename = filename;
            this.linenumber = linenumber;
            this.position = position;
        }
    }
    
    private final Comparator<FindInfo> filenameComparator = new Comparator<FindInfo>() {

        @Override
        public int compare(FindInfo o1, FindInfo o2) {
            return o1.filename.compareToIgnoreCase(o2.filename);
        }
    };
}