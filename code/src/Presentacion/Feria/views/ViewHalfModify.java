package Presentacion.Feria.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ViewHalfModify extends JFrame {

    private JPanel dialogPanel;
    private JPanel contentPanel;
    private JLabel labelModify;
    private JComboBox<String> comboBoxModify;
    private JPanel contentPanel2;
    private JLabel labeliD;
    private JTextField textID;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private JButton helpButton;

    public ViewHalfModify() {
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void okButtonActionPerformed(ActionEvent e) {

    }

    private void cancelButtonActionPerformed(ActionEvent e) {

    }

    private void helpButtonActionPerformed(ActionEvent e) {

    }

    private void initComponents() {
        dialogPanel = new JPanel();
        contentPanel = new JPanel();
        labelModify = new JLabel();
        comboBoxModify = new JComboBox<String>();
        contentPanel2 = new JPanel();
        labeliD = new JLabel();
        textID = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();
        helpButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPanel ========
        {
            dialogPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
            dialogPanel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPanel.getBorder())); dialogPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPanel.setLayout(new BorderLayout());

            comboBoxModify.addItem("Pabellon");
            comboBoxModify.addItem("Feria");
            comboBoxModify.addItem("Stand");
            comboBoxModify.addItem("Participante");
            comboBoxModify.addItem("Asignacion");
            comboBoxModify.addItem("Praticipacion");

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

                //---- labelModify ----
                labelModify.setText("text");
                contentPanel.add(labelModify);
                contentPanel.add(comboBoxModify);

                //======== contentPanel2 ========
                {
                    contentPanel2.setLayout(new BoxLayout(contentPanel2, BoxLayout.X_AXIS));

                    //---- labeliD ----
                    labeliD.setText("ID:");
                    contentPanel2.add(labeliD);
                    contentPanel2.add(textID);
                }
                contentPanel.add(contentPanel2);
            }
            dialogPanel.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        okButtonActionPerformed(e);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancelButtonActionPerformed(e);
                    }
                });
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- helpButton ----
                helpButton.setText("Help");
                helpButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        helpButtonActionPerformed(e);
                    }
                });
                buttonBar.add(helpButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPanel.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }
}
