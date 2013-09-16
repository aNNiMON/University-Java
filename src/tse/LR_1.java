package tse;

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
        System.out.println(TITLES[index]);
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
