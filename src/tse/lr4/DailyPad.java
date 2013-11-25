package tse.lr4;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.UIManager;

/**
 * Ежедневник.
 * @author aNNiMON
 */
public class DailyPad extends JFrame {
    
    private PadPanel panel;
    
    public DailyPad() {
        super("DailyPad");
        setLocationByPlatform(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        addMenu();
        addToolBar();
        
        panel = new PadPanel();
        add(panel, BorderLayout.CENTER);
        pack();
    }

    private void addMenu() {
        JMenuBar mainMenu = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem newMenuItem = new JMenuItem("New");
        newMenuItem.setMnemonic(KeyEvent.VK_N);
        newMenuItem.addActionListener(newActionListener);
        fileMenu.add(newMenuItem);
        fileMenu.add(new JMenuItem("Open"));
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setMnemonic(KeyEvent.VK_S);
        saveMenuItem.addActionListener(openActionListener);
        fileMenu.add(saveMenuItem);
        fileMenu.add(new JMenuItem("Save As"));
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_X);
        exitMenuItem.addActionListener(exitActionListener);
        fileMenu.add(exitMenuItem);
        mainMenu.add(fileMenu);

        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(new JMenuItem("About"));
        mainMenu.add(helpMenu);
        
        setJMenuBar(mainMenu);
    }
    
    private void addToolBar() {
        JToolBar toolbar = new JToolBar();
        JButton newButton = new JButton(UIManager.getIcon("Tree.collapsedIcon"));
        newButton.addActionListener(newActionListener);
        toolbar.add(newButton);
        JButton openButton = new JButton(UIManager.getIcon("Tree.openIcon"));
        openButton.addActionListener(openActionListener);
        toolbar.add(openButton);
        JButton exitButton = new JButton(UIManager.getIcon("FileChooser.upFolderIcon"));
        exitButton.addActionListener(exitActionListener);
        toolbar.add(exitButton);
        add(toolbar, BorderLayout.NORTH);
    }
    
    private final ActionListener newActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    };
    
    private final ActionListener openActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    };
    
    private final ActionListener exitActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    };
}
