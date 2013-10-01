package tse;

/**
 * @author aNNiMON
 */
public class LR_2 implements ILabRab {
    
    private static final String[] TITLES = {
        "Копирование и сравнение массивов",
        "Двумерный массив строк",
        "3",
        "4",
        "5"
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
        return Util.readDescription(1, index);
    }
    
}
