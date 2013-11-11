package tse;

/**
 * @author aNNiMON
 */
public class LR_3 implements ILabRab {
    
    private static final String[] TITLES = {
        "Сериализация классов",
        "(Де)Сериализация коллекций",
        "Копирование 3-дневных файлов",
        "Поиск текста в файлах",
        "Архивирование"
    };

    @Override
    public void execute(int index) {
        switch(index) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    @Override
    public String[] getTitles() {
        return TITLES;
    }

    @Override
    public String getDescription(int index) {
        return Util.readDescription(3, index);
    }
    
}
