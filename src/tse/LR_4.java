package tse;

import tse.lr4.LoginWindow;


/**
 * @author aNNiMON
 */
public class LR_4 implements ILabRab {
    
    private static final String[] TITLES = {
        "Окно входа в систему",
        "Ежедневник"
    };

    @Override
    public void execute(int index) {
        switch(index) {
            case 0:
                new LoginWindow().setVisible(true);
                break;
            case 1:
                
                break;
        }
    }

    @Override
    public String[] getTitles() {
        return TITLES;
    }

    @Override
    public String getDescription(int index) {
        return Util.readDescription(4, index);
    }
    
}
