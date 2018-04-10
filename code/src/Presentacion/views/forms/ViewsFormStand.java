package Presentacion.views.forms;

import Negocio.Stand.Tstand;
import Presentacion.UIimp;
import Presentacion.views.events.Event;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class ViewsFormStand extends JFrame {

    private JPanel dialogPanel;
    private JPanel contentPanel;
    private JPanel contentPanel2;
    private JLabel label1;
    private JTextField textFieldCoste;
    private JPanel contentPanel3;
    private JLabel label2;
    private JTextField textFieldMUsados;
    private JPanel contentPanel4;
    private JLabel label3;
    private JTextField textFieldNumero;
    private JPanel buttonBar;
    private JButton createButtonForm;
    private JButton cancelButton;
    private JButton helpButton;

    private Tstand tStandModify;
    private boolean isOptionCreate;

    public ViewsFormStand() {
        initComponents();

        isOptionCreate = true;

        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    public ViewsFormStand(Tstand tstand) {
        initComponents();

        isOptionCreate = false;
        this.tStandModify = tstand;

        initComponentsModify();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void initComponentsModify() {
        textFieldCoste.setText(String.valueOf(tStandModify.getCost()));
        textFieldMUsados.setText(String.valueOf(tStandModify.getTotal_m2()));
        textFieldNumero.setText(String.valueOf(tStandModify.getNum_at_fair()));
    }

    private void createButtonFormActionPerformed() {
        setVisible(false);
        String coste = textFieldCoste.getText();
        String m_usados = textFieldMUsados.getText();
        String numero = textFieldNumero.getText();
        Tstand tStand = new Tstand(Integer.parseInt(coste), Integer.parseInt(m_usados), Integer.parseInt(numero), true);

        if (isOptionCreate)  UIimp.getInstance().execute(Presentacion.views.events.Event.INSERT_STAND, tStand);
        else UIimp.getInstance().execute(Event.MODIFY_STAND,tStand);
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
        textFieldCoste = new JTextField();
        contentPanel3 = new JPanel();
        label2 = new JLabel();
        textFieldMUsados = new JTextField();
        contentPanel4 = new JPanel();
        label3 = new JLabel();
        textFieldNumero = new JTextField();
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
                    label1.setText("Coste");
                    contentPanel2.add(label1);
                    contentPanel2.add(textFieldCoste);
                }
                contentPanel.add(contentPanel2);

                //======== contentPanel3 ========
                {
                    contentPanel3.setLayout(new BoxLayout(contentPanel3, BoxLayout.X_AXIS));

                    //---- label2 ----
                    label2.setText("Metros usados");
                    contentPanel3.add(label2);
                    contentPanel3.add(textFieldMUsados);
                }
                contentPanel.add(contentPanel3);

                //======== contentPanel4 ========
                {
                    contentPanel4.setLayout(new BoxLayout(contentPanel4, BoxLayout.X_AXIS));

                    //---- label3 ----
                    label3.setText("Numero");
                    contentPanel4.add(label3);
                    contentPanel4.add(textFieldNumero);
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
