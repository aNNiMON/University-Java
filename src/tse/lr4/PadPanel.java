/*
 */

package tse.lr4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author aNNiMON
 */
public class PadPanel extends JPanel {
    
    private int padIndex;
    
    public PadPanel() {
        padIndex = 0;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        JLabel eventLabel = new JLabel();
        eventName = new JTextField();
        JLabel descriptionLabel = new JLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        descriptionTextArea = new JTextArea();
        JLabel dateTimeLabel = new JLabel();
        dateSpinner = new JSpinner();
        importantCheckBox = new JCheckBox();
        JButton applyButton = new JButton();
        JButton prevButton = new JButton();
        JButton nextButton = new JButton();

        eventLabel.setText("Название события:");

        descriptionLabel.setText("Описание:");

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        jScrollPane1.setViewportView(descriptionTextArea);

        dateTimeLabel.setText("Дата / время:");

        dateSpinner.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.MONTH));

        importantCheckBox.setText("Важное");

        applyButton.setText("Применить");
        applyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        prevButton.setText("<<");
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                prevButtonActionPerformed(evt);
            }
        });

        nextButton.setText(">>");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(eventLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eventName, GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(descriptionLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dateTimeLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateSpinner)
                        .addGap(18, 18, 18)
                        .addComponent(importantCheckBox))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(prevButton)
                        .addGap(18, 18, 18)
                        .addComponent(applyButton, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(nextButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(eventLabel)
                    .addComponent(eventName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionLabel)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dateSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateTimeLabel)
                    .addComponent(importantCheckBox))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(prevButton)
                    .addComponent(nextButton)
                    .addComponent(applyButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }//GEN-END:initComponents

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_applyButtonActionPerformed

    private void prevButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_prevButtonActionPerformed
        // TODO add your handling code here:
        selectPreviousNotePad();
    }//GEN-LAST:event_prevButtonActionPerformed

    private void nextButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO add your handling code here:
        selectNextNotePad();
    }//GEN-LAST:event_nextButtonActionPerformed

    private void selectPreviousNotePad() {
        if (padIndex <= 0) return;
        padIndex--;
        selectNotePad(getNotePad());
    }
    
    private void selectNextNotePad() {
        if (padIndex >= getNotepads().size() - 1) return;
        padIndex++;
        selectNotePad(getNotePad());
    }
    
    private void selectNotePad(NotePad pad) {
        if (pad == null) return;
        eventName.setText(pad.getName());
        descriptionTextArea.setText(pad.getDescription());
        dateSpinner.setValue(pad.getDate());
        importantCheckBox.setSelected(pad.isImportant());
    }
    
    private NotePad getNotePad() {
        return NotePadManager.getInstance().getNotepads().get(padIndex);
    }
    
    private ArrayList<NotePad> getNotepads() {
        return NotePadManager.getInstance().getNotepads();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JSpinner dateSpinner;
    private JTextArea descriptionTextArea;
    private JTextField eventName;
    private JCheckBox importantCheckBox;
    // End of variables declaration//GEN-END:variables
}
