package tse;

import tse.tools.ImageCsvCreator;

/**
 *
 * @author aNNiMON
 */
public class Tools implements ILabRab {
    
    private static final String[] TITLES = {
        "Конвертация изображения в CSV",
    };

    @Override
    public void execute(int index) {
        switch(index) {
            case 0:
                ImageCsvCreator.main();
                break;
        }
    }

    @Override
    public String[] getTitles() {
        return TITLES;
    }

    @Override
    public String getDescription(int index) {
        return TITLES[index];
    }
}