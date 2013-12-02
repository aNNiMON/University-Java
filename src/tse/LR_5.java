package tse;

import tse.lr5.FileIconsWindow;
import tse.lr5.TrianglesWindow;


/**
 * @author aNNiMON
 */
public class LR_5 implements ILabRab {
    
    private static final String[] TITLES = {
        "Чтение и рисование объектов из CSV",
        "Движущиеся объекты и их управление"
    };

    @Override
    public void execute(int index) {
        switch(index) {
            case 0:
                new TrianglesWindow().execute();
                break;
            case 1:
                new FileIconsWindow().execute();
                break;
        }
    }

    @Override
    public String[] getTitles() {
        return TITLES;
    }

    @Override
    public String getDescription(int index) {
        return Util.readDescription(5, index);
    }
    
}
