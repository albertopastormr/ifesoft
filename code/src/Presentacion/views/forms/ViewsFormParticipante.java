package Presentacion.views.forms;

import Negocio.Participante.Tparticipante;
import Presentacion.UIimp;
import Presentacion.views.events.Event;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class ViewsFormParticipante extends JFrame {

    private JPanel dialogPanel;
    private JPanel contentPanel;
    private JPanel contentPanel2;
    private JLabel label1;
    private JTextField textFieldNombre;
    private JPanel contentPanel3;
    private JLabel label2;
    private JTextField textFieldTelefono;
    private JPanel contentPanel4;
    private JLabel label3;
    private JTextField textFieldEspecializacion;
    private JPanel buttonBar;
    private JButton createButtonForm;
    private JButton cancelButton;
    private JButton helpButton;

    private Tparticipante tparticipanteModify;
    private boolean isOptionCreate;

    // CONSTRUCTOR OPTION CREATE
    public ViewsFormParticipante() {
        initComponents();

        this.isOptionCreate = true;

        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    // CONSTRUCTOR OPTION MODIFY
    public ViewsFormParticipante(Tparticipante tparticipante) {
        initComponents();

        this.isOptionCreate = true;
        this.tparticipanteModify = tparticipante;

        initComponentsModify();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void initComponentsModify() {
        textFieldNombre.setText(String.valueOf(tparticipanteModify.getName()));
       // textFieldEspecializacion.setText(String.valueOf(tparticipanteModify.getEspecializacion()));
        textFieldTelefono.setText(String.valueOf(tparticipanteModify.getPhone()));
    }

    private void createButtonFormActionPerformed() {
        setVisible(false);
        String name = textFieldNombre.getText();
        String numPhone = textFieldTelefono.getText();
        String specialization = textFieldEspecializacion.getText();
        Tparticipante tParticipante = new Tparticipante(name, Integer.parseInt(numPhone), Boolean.parseBoolean(specialization));

        if (isOptionCreate)  UIimp.getInstance().execute(Presentacion.views.events.Event.INSERT_CLIENT, tParticipante);
        else UIimp.getInstance().execute(Event.MODIFY_CLIENT, tParticipante);
    }

    private void cancelButtonStateChanged() {
        this.setVisible(false);
        if (isOptionCreate) UIimp.getInstance().execute(Event.CREATE_HALF, null);
        else UIimp.getInstance().execute(Event.MODIFY_HALF, null);
    }

    private void helpButtonActionPerformed() {

    }

    private void initComponents() {
        dialogPanel = new JPanel();
        contentPanel = new JPanel();
        contentPanel2 = new JPanel();
        label1 = new JLabel();
        textFieldNombre = new JTextField();
        contentPanel3 = new JPanel();
        label2 = new JLabel();
        textFieldTelefono = new JTextField();
        contentPanel4 = new JPanel();
        label3 = new JLabel();
        textFieldEspecializacion = new JTextField();
        buttonBar = new JPanel();
        createButtonForm = new JButton();
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

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

                //======== contentPanel2 ========
                {
                    contentPanel2.setLayout(new BoxLayout(contentPanel2, BoxLayout.X_AXIS));

                    //---- label1 ----
                    label1.setText("Nombre");
                    contentPanel2.add(label1);
                    contentPanel2.add(textFieldNombre);
                }
                contentPanel.add(contentPanel2);

                //======== contentPanel3 ========
                {
                    contentPanel3.setLayout(new BoxLayout(contentPanel3, BoxLayout.X_AXIS));

                    //---- label2 ----
                    label2.setText("Telefono");
                    contentPanel3.add(label2);
                    contentPanel3.add(textFieldTelefono);
                }
                contentPanel.add(contentPanel3);

                //======== contentPanel4 ========
                {
                    contentPanel4.setLayout(new BoxLayout(contentPanel4, BoxLayout.X_AXIS));

                    //---- label3 ----
                    label3.setText("Especialización");
                    contentPanel4.add(label3);
                    contentPanel4.add(textFieldEspecializacion);
                }
                contentPanel.add(contentPanel4);
            }
            dialogPanel.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0};

                //---- createButtonForm ----
                createButtonForm.setText("Add");
                createButtonForm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createButtonFormActionPerformed();
                    }
                });
                buttonBar.add(createButtonForm, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        cancelButtonStateChanged();
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
                        helpButtonActionPerformed();
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
