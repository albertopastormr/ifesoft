package Presentacion.views.forms;

import Negocio.Pabellon.Tpabellon;
import Presentacion.UIimp;
import Presentacion.views.events.Event;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class ViewsFormPabellon extends JFrame {

    private JPanel dialogPanel;
    private JPanel contentPanel;
    private JPanel contentPanel2;
    private JLabel label1;
    private JTextField textFieldAforo;
    private JPanel contentPanel3;
    private JLabel label2;
    private JTextField textFieldM2Utiles;
    private JPanel contentPanel4;
    private JLabel label3;
    private JTextField textFieldM2Tot;
    private JPanel buttonBar;
    private JButton createButtonForm;
    private JButton cancelButton;
    private JButton helpButton;

    private Tpabellon tpavilionModify;
    private boolean isOptionCreate;

    public ViewsFormPabellon() {
        initComponents();
        isOptionCreate = true;
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    public ViewsFormPabellon(Tpabellon tpabellon) {
        initComponents();

        isOptionCreate = false;
        this.tpavilionModify = tpabellon;

        initComponentsModify();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void initComponentsModify() {
        textFieldAforo.setText(String.valueOf(tpavilionModify.getCapacity()));
        textFieldM2Tot.setText(String.valueOf(tpavilionModify.getTotal_m2()));
        textFieldM2Utiles.setText(String.valueOf(tpavilionModify.getUtil_m2()));
    }

    private void createButtonFormActionPerformed() {
        setVisible(false);
        String aforo = textFieldAforo.getText();
        String m2_utiles = textFieldM2Utiles.getText();
        String m2_totales = textFieldM2Tot.getText();
        Tpabellon tPabellon = new Tpabellon(Integer.parseInt(aforo), Integer.parseInt(m2_utiles), Integer.parseInt(m2_totales), true);

        if (isOptionCreate)  UIimp.getInstance().execute(Event.INSERT_PAVILION, tPabellon);
        else UIimp.getInstance().execute(Event.MODIFY_PAVILION, tPabellon);
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
        textFieldAforo = new JTextField();
        contentPanel3 = new JPanel();
        label2 = new JLabel();
        textFieldM2Utiles = new JTextField();
        contentPanel4 = new JPanel();
        label3 = new JLabel();
        textFieldM2Tot = new JTextField();
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
                    label1.setText("Aforo");
                    contentPanel2.add(label1);
                    contentPanel2.add(textFieldAforo);
                }
                contentPanel.add(contentPanel2);

                //======== contentPanel3 ========
                {
                    contentPanel3.setLayout(new BoxLayout(contentPanel3, BoxLayout.X_AXIS));

                    //---- label2 ----
                    label2.setText("Metros cuadros utiles");
                    contentPanel3.add(label2);
                    contentPanel3.add(textFieldM2Utiles);
                }
                contentPanel.add(contentPanel3);

                //======== contentPanel4 ========
                {
                    contentPanel4.setLayout(new BoxLayout(contentPanel4, BoxLayout.X_AXIS));

                    //---- label3 ----
                    label3.setText("Metros cuadros totales");
                    contentPanel4.add(label3);
                    contentPanel4.add(textFieldM2Tot);
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
