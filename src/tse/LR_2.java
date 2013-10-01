package tse;

import tse.lr2.CompareArrays1D;
import tse.lr2.CompareArrays2D;

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
                CompareArrays1D.main();
                break;
            case 1:
                CompareArrays2D.main();
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
        return Util.readDescription(2, index);
    }
    
}
