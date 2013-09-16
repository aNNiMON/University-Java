package tse;

/**
 * Интерфейс работы с лабораторными работами.
 * @author aNNiMON
 */
public interface ILabRab {
    
    /**
     * Запустить лабораторную работу.
     * @param index номер задания
     */
    public void execute(int index);
    
    /**
     * Получить заголовки заданий.
     * @return массив имён
     */
    public String[] getTitles();
    
    /**
     * Получить описание задания.
     * @param index номер задания
     * @return текстовое описание
     */
    public String getDescription(int index);
    
}
