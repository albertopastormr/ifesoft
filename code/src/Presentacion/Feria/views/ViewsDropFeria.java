package Presentacion.Feria.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class ViewsDropFeria extends JFrame {

    private JPanel dialogPanel;
    private JPanel contentPanel;
    private JPanel contentPanel2;
    private JLabel label1;
    private JTextField textField1;
    private JPanel contentPanel3;
    private JLabel label2;
    private JTextField textField2;
    private JPanel contentPanel4;
    private JLabel label3;
    private JTextField textField3;
    private JPanel contentPanel5;
    private JLabel label4;
    private JTextField textField4;
    private JPanel buttonBar;
    private JButton createButtonForm; //tenemos que quitarlo de aqui? No hace falta creo
    private JButton cancelButton;
    private JButton helpButton;

    public ViewsFormDropFeria() {
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void createButtonFormActionPerformed() {  //tenemos que quitarlo de aqui? No hace falta creo

    }

    private void cancelButtonStateChanged() {

    }

    private void helpButtonActionPerformed() {

    }

    private void initComponents() {
        dialogPanel = new JPanel();
        contentPanel = new JPanel();
        contentPanel2 = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        contentPanel3 = new JPanel();
        label2 = new JLabel();
        textField2 = new JTextField();
        contentPanel4 = new JPanel();
        label3 = new JLabel();
        textField3 = new JTextField();
        contentPanel5 = new JPanel();
        label4 = new JLabel();
        textField4 = new JTextField();
        buttonBar = new JPanel();
        createButtonForm = new JButton(); //tenemos que quitarlo de aqui? No hace falta creo
        cancelButton = new JButton();
        helpButton = new JButton();

        //======== this ========
        Container contentPanel = getContentPanel();
        contentPanel.setLayout(new BorderLayout());

        //======== dialogPanel ========
        {
            dialogPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
            dialogPanel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPanel.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

                //======== contentPanel2 ========
                {
                    contentPanel2.setLayout(new BoxLayout(contentPanel2, BoxLayout.X_AXIS));

                    //---- label1 ----
                    label1.setText("¿QUIERES BORRAR -"get_feria()"- DE VERDAD?");
                    contentPanel2.add(label1);
                    contentPanel2.add(textField1);
                }
                contentPanel.add(contentPanel2);
                    /* no hace falta aqui no?
                    //---- label1 ----
                    label1.setText("Nombre");
                    contentPanel2.add(label1);
                    contentPanel2.add(textField1);
                }
                contentPanel.add(contentPanel2);

                //======== contentPanel3 ========
                {
                    contentPanel3.setLayout(new BoxLayout(contentPanel3, BoxLayout.X_AXIS));

                    //---- label2 ----
                    label2.setText("Descripcion");
                    contentPanel3.add(label2);
                    contentPanel3.add(textField2);
                }
                contentPanel.add(contentPanel3);

                //======== contentPanel4 ========
                {
                    contentPanel4.setLayout(new BoxLayout(contentPanel4, BoxLayout.X_AXIS));

                    //---- label3 ----
                    label3.setText("Fecha inicio");
                    contentPanel4.add(label3);
                    contentPanel4.add(textField3);
                }
                contentPanel.add(contentPanel4);

                //======== contentPanel5 ========
                {
                    contentPanel5.setLayout(new BoxLayout(contentPanel5, BoxLayout.X_AXIS));

                    //---- label4 ----
                    label4.setText("Fecha fin");
                    contentPanel5.add(label4);
                    contentPanel5.add(textField4);
                }
                contentPanel.add(contentPanel5);
            }
            dialogPanel.add(contentPanel, BorderLayout.CENTER);
                    */

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0};

                /* quitamos?? Hace falta?
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
                    */

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
        contentPanel.add(dialogPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }
}
