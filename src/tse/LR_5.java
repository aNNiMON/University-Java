package tse;


/**
 * @author aNNiMON
 */
public class LR_5 implements ILabRab {
    
    private static final String[] TITLES = {
        "Чтение объектов из CSV и рисование их на экране",
        "Движущиеся объекты и их управление"
    };

    @Override
    public void execute(int index) {
        switch(index) {
            case 0:
                // new LoginWindow().setVisible(true);
                break;
            case 1:
                // new DailyPad().setVisible(true);
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
