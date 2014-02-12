package tse.lr4;

import com.annimon.io.CsvReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tse.Util;

/**
 * Менеджер записей ежедневника.
 * @author aNNiMON
 */
public class NotePadManager {
    
    private static final String FILENAME = "dailypad.csv";

    private static NotePadManager instance;

    public static NotePadManager getInstance() {
        if (instance == null) {
            instance = new NotePadManager();
        }
        return instance;
    }

    private List<NotePad> notepads;
    
    public NotePadManager() {
        notepads = readFromCSV(FILENAME);
        if (notepads == null) notepads = new ArrayList<>();
    }
    
    public List<NotePad> getNotepads() {
        return notepads;
    }
    
    public boolean isNotePadExists(String name) {
        for (NotePad pad : notepads) {
            if (name.equals(pad.getName())) return true;
        }
        return false;
    }
    
    public void createNewEntry(String name, String desc, Date date, boolean important) {
        notepads.add(new NotePad(name, desc, date, important));
        saveToCSV(FILENAME, notepads);
    }
    
    public void updateEntry(String name, String desc, Date date, boolean important) {
        for (NotePad pad : notepads) {
            if (name.equals(pad.getName())) {
                pad.setDescription(desc);
                pad.setDate(date);
                pad.setImportant(important);
                saveToCSV(FILENAME, notepads);
                return;
            }
        }
        // Не нашли что обновлять - добавляем
        createNewEntry(name, desc, date, important);
    }
    
    private void saveToCSV(String filename, List<NotePad> list) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(filename), "UTF-8")
            );
            writer.write("Имя,Описание,Дата,Важное");
            for (NotePad pad : list) {
                writer.newLine();
                writer.write(pad.storeToCsvLine());
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Util.handleException(ex);
        }
    }
    
    private List<NotePad> readFromCSV(String filename) {
        CsvReader<NotePad> csvReader = new CsvReader<>(new File(filename));
        csvReader.setReaderHandler(new CsvReader.ReaderHandler<NotePad>() {

            @Override
            public void onStartRead(File file) { }

            @Override
            public NotePad createObject(String[] params) {
                return NotePad.readFromCsvLine(params);
            }

            @Override
            public void onFinishRead(File file) { }
        });
        try {
            return csvReader.readCsvToList();
        } catch (IOException ex) {
            Util.handleException(ex);
        }
        return null;
    }
}
