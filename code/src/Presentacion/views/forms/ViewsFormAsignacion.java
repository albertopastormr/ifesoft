package Presentacion.views.forms;

import Negocio.Asignacion.Tasignacion;
import Presentacion.UIimp;
import Presentacion.views.events.Event;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class ViewsFormAsignacion extends JFrame {

    private JPanel dialogPanel;
    private JPanel contentPanel;
    private JPanel contentPanel2;
    private JLabel label1;
    private JTextField textFieldMUsed;
    private JTextField textFieldIdFair;
    private JTextField textFieldIdPavilion;
    private JTextField textFieldIdStand;
    private JPanel buttonBar;
    private JButton createButtonForm;
    private JButton cancelButton;
    private JButton helpButton;

    private Tasignacion tAsignacionModify;
    private boolean isOptionCreate;

    public ViewsFormAsignacion() {
        initComponents();

        isOptionCreate = true;

        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }


    public ViewsFormAsignacion(Tasignacion tAsigancion) {
        initComponents();

        isOptionCreate = false;
        this.tAsignacionModify = tAsigancion;

        initComponentsModify();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void initComponentsModify() {
        textFieldIdFair.setText(String.valueOf(tAsignacionModify.getFair_id()));
        textFieldIdPavilion.setText(String.valueOf(tAsignacionModify.getPavilion_id()));
        textFieldIdStand.setText(String.valueOf(tAsignacionModify.getStand_id()));
        textFieldMUsed.setText(String.valueOf(tAsignacionModify.getUsed_m2()));
    }

    private void createButtonFormActionPerformed() {
        this.setVisible(false);
        int mUsed = Integer.valueOf(textFieldMUsed.getText());
        int idFair = Integer.valueOf(textFieldIdFair.getText());
        int idPavilion = Integer.valueOf(textFieldIdPavilion.getText());
        int idStand = Integer.valueOf(textFieldIdStand.getText());

        Tasignacion tasignacion = new Tasignacion(idFair, idPavilion, idStand, mUsed, true);

        if (isOptionCreate)  UIimp.getInstance().execute(Event.INSERT_ASIGNACION, tasignacion);
        else UIimp.getInstance().execute(Event.MODIFY_ASIGNACION, tasignacion);
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
        textFieldMUsed = new JTextField();
        textFieldIdFair = new JTextField();
        textFieldIdPavilion = new JTextField();
        textFieldIdStand = new JTextField();
        buttonBar = new JPanel();
        createButtonForm = new JButton();
        cancelButton = new JButton();
        helpButton = new JButton();

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
                    label1.setText("Metros usados");
                    contentPanel2.add(label1);
                    contentPanel2.add(textFieldMUsed);
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
