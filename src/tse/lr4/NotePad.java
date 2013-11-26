package tse.lr4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс представления данных записи ежедневника.
 * @author aNNiMON
 */
public class NotePad {
    
    private static final String DATE_PATTERN = "yyyy.MM.dd HH:mm:ss";

    private String name, description;
    private Date date;
    private boolean important;

    public NotePad(String name, String description, Date date, boolean important) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.important = important;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }
    
    @Override
    public String toString() {
        return getName() + ". " + getDescription();
    }
    
    public static NotePad readFromCsvLine(String line) throws ParseException {
        if (line.isEmpty()) throw new RuntimeException("Пустая строка");
        String[] params = line.split("\t");
        if (params.length != 4) throw new RuntimeException("Неверное количество параметров");

        String name = params[0];
        String description = params[1];
        Date data = new SimpleDateFormat(DATE_PATTERN).parse(params[2]);
        boolean important = Boolean.parseBoolean(params[3]);
        
        return new NotePad(name, description, data, important);
    }
    
    public String storeToCsvLine() {
        final char SEPARATOR = '\t';
        return name + SEPARATOR +
                description + SEPARATOR +
                new SimpleDateFormat(DATE_PATTERN).format(date) + SEPARATOR + 
                important;
    }
}
