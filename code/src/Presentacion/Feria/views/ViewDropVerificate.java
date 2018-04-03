package Presentacion.Feria.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ViewDropVerificate extends JFrame {

    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel labelQuestion;
    private JPanel buttonBar;
    private JButton cancelButton;
    private JButton okButton;

    public ViewDropVerificate() {
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void okButtonActionPerformed(ActionEvent e) {

    }

    private void cancelButtonActionPerformed(ActionEvent e) {

    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        labelQuestion = new JLabel();
        buttonBar = new JPanel();
        cancelButton = new JButton();
        okButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new FlowLayout());

                //---- labelQuestion ----
                labelQuestion.setText("\u00bfQuieres borrar -modulo- de verdad?");
                contentPanel.add(labelQuestion);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 0, 0, 0, 0, 85, 85, 0, 0, 0, 0};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

                //---- cancelButton ----
                cancelButton.setText("No");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancelButtonActionPerformed(e);
                    }
                });
                buttonBar.add(cancelButton, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- okButton ----
                okButton.setText("Si");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        okButtonActionPerformed(e);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(7, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }
}
