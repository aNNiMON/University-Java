package tse.lr4;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * Панель авторизации.
 * @author aNNiMON
 */
public class LoginPanel extends JPanel {
    
    private static final boolean STATE_LOGIN = false, STATE_SIGNUP = true;
    
    private final JTextField loginTextField;
    private final JPasswordField passwordTextField, confirmPasswordTextField;
    private final JButton loginButton, signupButton, cancelButton;
    
    private boolean state;

    public LoginPanel() {
        state = STATE_LOGIN;
        
        setLayout(new AbsoluteLayout());

        final JLabel loginLabel = new JLabel();
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setText("Логин");
        add(loginLabel, new AbsoluteConstraints(50, 20, 245, -1));

        loginTextField = new JTextField();
        loginTextField.setHorizontalAlignment(JTextField.CENTER);
        add(loginTextField, new AbsoluteConstraints(50, 40, 245, -1));

        final JLabel passwordLabel = new JLabel();
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setText("Пароль");
        add(passwordLabel, new AbsoluteConstraints(50, 71, 245, -1));

        passwordTextField = new JPasswordField();
        passwordTextField.setHorizontalAlignment(JTextField.CENTER);
        add(passwordTextField, new AbsoluteConstraints(50, 91, 245, -1));

        loginButton = new JButton();
        loginButton.setText("Войти");
        add(loginButton, new AbsoluteConstraints(112, 129, 125, -1));

        signupButton = new JButton();
        signupButton.setText("Регистрация");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (state == STATE_LOGIN) {
                    // Показываем форму регистрации
                    loginLabel.setText("Пароль");
                    passwordLabel.setText("Повторите пароль");
                    loginTextField.setVisible(false);
                    confirmPasswordTextField.setVisible(true);
                    loginButton.setVisible(false);
                    state = STATE_SIGNUP;
                } else {
                    // Завершаем регистрацию и переходим в режим авторизации
                    loginLabel.setText("Логин");
                    passwordLabel.setText("Пароль");
                    loginTextField.setVisible(true);
                    confirmPasswordTextField.setVisible(false);
                    loginButton.setVisible(true);
                    state = STATE_LOGIN;
                }
            }
        });
        add(signupButton, new AbsoluteConstraints(112, 158, 125, -1));

        cancelButton = new JButton();
        cancelButton.setText("Отмена");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (state == STATE_SIGNUP) {
                    // Отменяем регистрацию
                    loginLabel.setText("Логин");
                    passwordLabel.setText("Пароль");
                    loginTextField.setVisible(true);
                    confirmPasswordTextField.setVisible(false);
                    loginButton.setVisible(true);
                    state = STATE_LOGIN;
                } else {
                    // Закрываем окно
                    Window window = SwingUtilities.getWindowAncestor(LoginPanel.this);
                    window.setVisible(false);
                }
            }
        });
        add(cancelButton, new AbsoluteConstraints(112, 187, 125, -1));

        confirmPasswordTextField = new JPasswordField();
        confirmPasswordTextField.setHorizontalAlignment(JTextField.CENTER);
        add(confirmPasswordTextField, new AbsoluteConstraints(50, 40, 245, -1));
    }
}
