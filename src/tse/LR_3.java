package tse;

import tse.lr3.DirCopy;
import tse.lr3.DirFindText;
import tse.lr3.DirZip;
import tse.lr3.LR_3_Tasks;

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
                LR_3_Tasks.getInstance().task1();
                break;
            case 1:
                LR_3_Tasks.getInstance().task2();
                break;
            case 2:
                DirCopy.main();
                break;
            case 3:
                DirFindText.main();
                break;
            case 4:
                DirZip.main();
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
