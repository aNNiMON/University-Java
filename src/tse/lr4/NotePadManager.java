package tse.lr4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
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

    private ArrayList<NotePad> notepads;
    
    public NotePadManager() {
        notepads = (ArrayList<NotePad>) readFromCSV(FILENAME);
        if (notepads == null) notepads = new ArrayList<>();
    }
    
    public ArrayList<NotePad> getNotepads() {
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
    
    private void saveToCSV(String filename, ArrayList<NotePad> list) {
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
    
    private Object readFromCSV(String filename) {
        try {
            List<NotePad> list = new ArrayList<>();
            BufferedReader reader = new BufferedReader(
                new InputStreamReader( new FileInputStream(filename), "UTF-8" )
            );
            reader.readLine(); // Имя,Описание,Дата,Важное
            String line;
            while ( (line = reader.readLine()) != null ) {
                NotePad pad = null;
                try {
                    pad = NotePad.readFromCsvLine(line);
                } catch (RuntimeException | ParseException ex) {
                    System.out.println(ex.toString());
                }
                if (pad != null) list.add(pad);
            }
            reader.close();
            return list;
        } catch (IOException ex) {
            Util.handleException(ex);
        }
        return null;
    }
}
