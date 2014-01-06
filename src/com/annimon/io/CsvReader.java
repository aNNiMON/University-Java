package com.annimon.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import tse.Util;

/**
 * Чтение CSV файла.
 * @author aNNiMON
 * @param <T>
 */
public class CsvReader<T> {
    
    public interface ReaderHandler<T> {
        
        void onStartRead(File file);
        
        T createObject(String[] params);
        
        void onFinishRead(File file);
    }
    
    private final File file;
    private ReaderHandler handler;

    public CsvReader(File file) {
        this.file = file;
    }
    
    public void setReaderHandler(ReaderHandler handler) {
        this.handler = handler;
    }
    
    public List<T> readCsvToList() throws IOException {
        return readCsvToList(true);
    }
    
    public List<T> readCsvToList(boolean skipHeaderLine) throws IOException {
        return readCsvToList(skipHeaderLine, ",");
    }
    
    public List<T> readCsvToList(boolean skipHeaderLine, String separator) throws IOException {
        if (handler == null) throw new RuntimeException("Не установлен ReaderHandler");
        
        handler.onStartRead(file);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader( new FileInputStream(file), "UTF-8" )
        );
        if (skipHeaderLine) {
            reader.readLine();
        }
        String line;
        List<T> list = new ArrayList<>();
        while ( (line = reader.readLine()) != null ) {
            T obj = null;
            try {
                if (!line.isEmpty()) {
                    String[] params = line.split(separator);
                    obj = (T) handler.createObject(params);
                }
            } catch (RuntimeException ex) {
                Util.handleException(ex);
            }
            if (obj != null) list.add(obj);
        }
        reader.close();
        handler.onFinishRead(file);
        return list;
    }
    
    public void readCsv(boolean skipHeaderLine, String separator) throws IOException {
        if (handler == null) throw new RuntimeException("Не установлен ReaderHandler");
        
        handler.onStartRead(file);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader( new FileInputStream(file), "UTF-8" )
        );
        if (skipHeaderLine) {
            reader.readLine();
        }
        String line;
        while ( (line = reader.readLine()) != null ) {
            try {
                if (!line.isEmpty()) {
                    String[] params = line.split(separator);
                    handler.createObject(params);
                }
            } catch (RuntimeException ex) {
                Util.handleException(ex);
            }
        }
        reader.close();
        handler.onFinishRead(file);
    }
}
