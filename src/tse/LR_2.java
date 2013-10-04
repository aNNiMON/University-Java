package tse;

import tse.lr2.CompareArrays1D;
import tse.lr2.CompareArrays2D;
import tse.lr2.LR_2_Tasks;

/**
 * @author aNNiMON
 */
public class LR_2 implements ILabRab {
    
    private static final String[] TITLES = {
        "Копирование и сравнение массивов",
        "Двумерный массив строк",
        "Эллипсы и круги. Массив",
        "Эллипсы и круги 2. Сортировка списка",
        "Эллипсы и круги 3. HashMap"
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
                LR_2_Tasks.getInstance().task3();
                break;
            case 3:
                LR_2_Tasks.getInstance().task4();
                break;
            case 4:
                LR_2_Tasks.getInstance().task5();
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
