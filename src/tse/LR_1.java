package tse;

import tse.lr1.ImageScaleDown;
import tse.lr1.Triangle;

/**
 * @author aNNiMON
 */
public class LR_1 implements ILabRab {
    
    private static final String[] TITLES = {
        "Калькулятор",
        "Площадь и периметр треугольника",
        "Уменьшить изображение"
    };

    @Override
    public void execute(int index) {
        switch(index) {
            case 0:
                break;
            case 1:
                Triangle.main();
                break;
            case 2:
                ImageScaleDown.main();
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
