package Presentacion.Feria.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ViewHalfShow extends JFrame {

    private JPanel dialogPanel;
    private JPanel contentPanel;
    private JLabel labelTitle;
    private JComboBox<String> comboBoxViews;
    private JPanel contentPanel2;
    private JRadioButton radioButtonIndividual;
    private JRadioButton radioButtonList;
    private JPanel contentPanel3;
    private JLabel labelSubID;
    private JTextField textFieldID;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private JButton helpButton;

    public ViewHalfShow() {
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
        labelTitle = new JLabel();
        comboBoxViews = new JComboBox<>();
        contentPanel2 = new JPanel();
        radioButtonIndividual = new JRadioButton();
        radioButtonList = new JRadioButton();
        contentPanel3 = new JPanel();
        labelSubID = new JLabel();
        textFieldID = new JTextField();
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

            //======== ComboBox =========

            comboBoxViews.addItem("Pabellon");
            comboBoxViews.addItem("Feria");
            comboBoxViews.addItem("Stand");
            comboBoxViews.addItem("Participante");
            comboBoxViews.addItem("Asignacion");
            comboBoxViews.addItem("Praticipacion");

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

                //---- labelTitle ----
                labelTitle.setText("View");
                contentPanel.add(labelTitle);
                contentPanel.add(comboBoxViews);

                //======== contentPanel2 ========
                {
                    contentPanel2.setLayout(new BoxLayout(contentPanel2, BoxLayout.X_AXIS));

                    //---- radioButtonIndividual ----
                    radioButtonIndividual.setText("Individual");
                    contentPanel2.add(radioButtonIndividual);

                    //---- radioButtonList ----
                    radioButtonList.setText("List");
                    contentPanel2.add(radioButtonList);
                }
                contentPanel.add(contentPanel2);

                //======== contentPanel3 ========
                {
                    contentPanel3.setLayout(new BoxLayout(contentPanel3, BoxLayout.X_AXIS));

                    //---- labelSubID ----
                    labelSubID.setText("ID:");
                    contentPanel3.add(labelSubID);
                    contentPanel3.add(textFieldID);
                }
                contentPanel.add(contentPanel3);
            }
            dialogPanel.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {

                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("Show");
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
                buttonBar.add(cancelButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
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
