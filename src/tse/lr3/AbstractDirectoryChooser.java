package tse.lr3;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author aNNiMON
 */
public abstract class AbstractDirectoryChooser extends JDialog implements ActionListener {

    private final JFileChooser chooser;

    public AbstractDirectoryChooser(String title) {
        setTitle(title);
        setPreferredSize(new Dimension(200, 80));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initWindow();
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
    }
    
    private void initWindow() {
        JPanel panel = new JPanel();
        
        JButton button = new JButton("Выбрать директорию");
        button.setPreferredSize(new Dimension(150, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.addActionListener(this);
        panel.add(button);
        
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    directorySelected(chooser.getSelectedFile());
                    System.out.println("Готово!");
                }
            }).start();
        } else {
            System.out.println("Ничего не выбрано");
        }
    }
    
    protected abstract void directorySelected(File directory);
}
