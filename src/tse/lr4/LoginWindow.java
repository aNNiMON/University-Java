package tse.lr4;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Окно входа в систему.
 * @author aNNiMON
 */
public class LoginWindow extends JFrame {
    
    public LoginWindow() {
        super("Вход в систему");
        setResizable(false);
        setAlwaysOnTop(true);
        setLocationByPlatform(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        LoginPanel panel = new LoginPanel();
        panel.setPreferredSize(new Dimension(350, 230));
        add(panel);
        pack();
    }
    
}
