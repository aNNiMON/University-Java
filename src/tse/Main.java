package tse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Класс меню, запускающий лабораторные работы.
 * @author aNNiMON
 */
public class Main extends JFrame {
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ex) {
            System.out.println("Error setting native LAF: " + ex);
        }

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    private final ILabRab[] labs = new ILabRab[] {
        new LR_1(), new LR_2()
    };
    
    private JButton executeButton;
    private JTree labsTree;
    private JLabel description;
    
    public Main() {
        setTitle("Технология Программной Инженерии");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        initPanel(panel);
        initListeners();
        
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void initPanel(JPanel panel) {
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Дерево лабораторных работ
        final DefaultMutableTreeNode root = new DefaultMutableTreeNode("ТПИ");
        labsTree = new JTree(root);
        labsTree.setPreferredSize(new Dimension(300, 0));
        panel.add(new JScrollPane(labsTree), BorderLayout.WEST);
        
        for (int i = 0; i < labs.length; i++) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode();
            node.setUserObject("Лабораторная работа №" + (i+1));
            
            addNodes(node, labs[i].getTitles());
            
            root.add(node);
        }
        labsTree.expandRow(0);

        // Панель описания
        description = new JLabel();
        description.setPreferredSize(new Dimension(250, 0));
        description.setFont(new Font("Arial", Font.PLAIN, 14));

        executeButton = new JButton("Запустить");
        
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(new LineBorder(Color.GRAY));
        rightPanel.add(description, BorderLayout.CENTER);
        rightPanel.add(executeButton, BorderLayout.NORTH);
        
        panel.add(rightPanel, BorderLayout.CENTER);
    }
    
    private void addNodes(DefaultMutableTreeNode node, String[] titles) {
        for (String title : titles) {
            node.add(new DefaultMutableTreeNode(title));
        }
    }

    private void initListeners() {
        labsTree.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent ev) {
                int[] params = getSelectedLabIndex();
                if (params == null) return;
                
                int number = params[0];
                int index = params[1];
                description.setText( labs[number].getDescription(index) );
            }
        });
        
        executeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ev) {
                int[] params = getSelectedLabIndex();
                if (params == null) return;
                
                int number = params[0];
                int index = params[1];
                labs[number].execute(index);
            }
        });
    }
    
    private int[] getSelectedLabIndex() {
        if (labsTree.isSelectionEmpty()) return null;
        
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) labsTree.getLastSelectedPathComponent();
        if (!node.isLeaf()) return null;

        DefaultMutableTreeNode parent = (DefaultMutableTreeNode)node.getParent();
        String labrab = (String) parent.getUserObject();
        String strNumber = labrab.substring(labrab.lastIndexOf('№') + 1);
        int number = Integer.parseInt(strNumber) - 1;
        int index = parent.getIndex(node);

        return new int[] {number, index};
    }
}