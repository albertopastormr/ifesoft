package Presentacion.Feria.views;

import Negocio.Feria.Tferia;
import Presentacion.Feria.Controller;
import Presentacion.Feria.views.events.Event;
import Presentacion.Feria.UIimp;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;

public class ViewsHalfCreate extends JFrame {

    private JPanel dialogPanel;
    private JPanel contentPanel;
    private JLabel JlabelTitle;
    private JComboBox<String> comboBox;
    private JPanel buttonBar;
    private JButton NextButton;
    private JButton backButton;
    private JButton helpButtonHalfCreate;

    public ViewsHalfCreate() {
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void NextButtonActionPerformed() {

        switch (String.valueOf(comboBox.getSelectedItem())){
            case "Feria":
                this.setVisible(false);
                UIimp.getInstance().execute(Event.INSERT_FORM_FERIA, null);
                break;
            case "Pabellon":
                UIimp.getInstance().execute(Event.INSERT_FORM_PABELLON, null);
                break;
            case "Stand":
                UIimp.getInstance().execute(Event.INSERT_FORM_STAND, null);
                break;
            case "Participante":
                UIimp.getInstance().execute(Event.INSERT_FORM_PARTICIPANTE, null);
                break;
            case "Asignacion":
                UIimp.getInstance().execute(Event.INSERT_FORM_ASIGNACION, null);
                break;
            case "Participacion":
                UIimp.getInstance().execute(Event.INSERT_FORM_PARTICIPANTE, null);
                break;

        }


    }

    private void backButtonActionPerformed() {
        this.setVisible(false);
        UIimp.getInstance().execute(Event.BACK_CREATE_HALF_FERIA, null);
        // Volver a mostrar la primera
    }

    private void helpButtonHalfCreateActionPerformed() {
    }

    private void initComponents() {
        dialogPanel = new JPanel();
        contentPanel = new JPanel();
        JlabelTitle = new JLabel();
        comboBox = new JComboBox<String>();
        buttonBar = new JPanel();
        NextButton = new JButton();
        backButton = new JButton();
        helpButtonHalfCreate = new JButton();

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
                    java.awt.Color.red), dialogPanel.getBorder()));

            dialogPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){
                public void propertyChange(java.beans.PropertyChangeEvent e){
                    if("border".equals(e.getPropertyName()))
                        throw new RuntimeException();
                }
            });

            dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));

            //======== ComboBox =========

            comboBox.addItem("Pabellon");
            comboBox.addItem("Feria");
            comboBox.addItem("Stand");
            comboBox.addItem("Participante");
            comboBox.addItem("Asignacion");
            comboBox.addItem("Praticipacion");

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

                //---- JlabelTitle ----
                JlabelTitle.setText("IFESOFT SOFTWARE");
                JlabelTitle.setFont(JlabelTitle.getFont().deriveFont(JlabelTitle.getFont().getSize() + 7f));
                contentPanel.add(JlabelTitle);
                contentPanel.add(comboBox);
            }
            dialogPanel.add(contentPanel);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0};

                //---- NextButton ----
                NextButton.setText("Next");
                NextButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NextButtonActionPerformed();
                    }
                });
                buttonBar.add(NextButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- backButton ----
                backButton.setText("Back");
                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        backButtonActionPerformed();
                    }
                });
                buttonBar.add(backButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- helpButtonHalfCreate ----
                helpButtonHalfCreate.setText("Help");
                helpButtonHalfCreate.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        helpButtonHalfCreateActionPerformed();
                    }
                });
                buttonBar.add(helpButtonHalfCreate, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
            }
            dialogPanel.add(buttonBar);
        }
        contentPane.add(dialogPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }
}
