package tse.lr4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
    
    private JFrame parentFrame;
    private boolean state;

    public LoginPanel(JFrame parent) {
        state = STATE_LOGIN;
        parentFrame = parent;
        
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
                    confirmPasswordTextField.setVisible(true);
                    loginButton.setVisible(false);
                    parentFrame.setTitle("Регистрация в системе");
                    state = STATE_SIGNUP;
                } else {
                    // Завершаем регистрацию и переходим в режим авторизации
                    if (!checkSignUpCredentials()) {
                        return;
                    }
                    backToAuth();
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
                    backToAuth();
                } else {
                    // Закрываем окно
                    parentFrame.setVisible(false);
                }
            }
        });
        add(cancelButton, new AbsoluteConstraints(112, 187, 125, -1));

        confirmPasswordTextField = new JPasswordField();
        confirmPasswordTextField.setHorizontalAlignment(JTextField.CENTER);
        add(confirmPasswordTextField, new AbsoluteConstraints(50, 120, 245, -1));
        confirmPasswordTextField.setVisible(false);
    }

    private void backToAuth() {
        confirmPasswordTextField.setVisible(false);
        loginButton.setVisible(true);
        parentFrame.setTitle("Вход в систему");
        state = STATE_LOGIN;
    }
    
    private boolean checkSignUpCredentials() {
        if (loginTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Не введён логин", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String pass1 = String.valueOf( passwordTextField.getPassword() );
        String pass2 = String.valueOf( confirmPasswordTextField.getPassword() );
        if (!pass1.equals(pass2)) {
            JOptionPane.showMessageDialog(this, "Пароли не совпадают", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (pass1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Не введён пароль", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (pass1.length() < 4) {
            JOptionPane.showMessageDialog(this, "Слишком короткий пароль", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        JOptionPane.showMessageDialog(this, "Регистрация завершена");
        return true;
    }
}
