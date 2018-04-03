package Presentacion.Feria.views;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.border.*;

public class ViewHalfDrop extends JFrame {

    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel labelTitle;
    private JComboBox<String> comboBoxDrop;
    private JPanel contentPanel2;
    private JLabel labelID;
    private JTextField textField1;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private JButton helpButton;

    public ViewHalfDrop() {
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void okButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void helpButtonActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        labelTitle = new JLabel();
        comboBoxDrop = new JComboBox<String>();
        contentPanel2 = new JPanel();
        labelID = new JLabel();
        textField1 = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();
        helpButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPanel ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new CompoundBorder(
                new TitledBorder(new EmptyBorder(0, 0, 0, 0),
                    "", TitledBorder.CENTER,
                    TitledBorder.BOTTOM, new Font("Dialog", Font.BOLD, 12),
                    Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new PropertyChangeListener(){public void propertyChange(PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPane.setLayout(new BorderLayout());

            comboBoxDrop.addItem("Pabellon");
            comboBoxDrop.addItem("Feria");
            comboBoxDrop.addItem("Stand");
            comboBoxDrop.addItem("Participante");
            comboBoxDrop.addItem("Asignacion");
            comboBoxDrop.addItem("Praticipacion");

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

                //---- labelTitle ----
                labelTitle.setText("DROP");
                contentPanel.add(labelTitle);
                contentPanel.add(comboBoxDrop);

                //======== contentPanel2 ========
                {
                    contentPanel2.setLayout(new BoxLayout(contentPanel2, BoxLayout.X_AXIS));

                    //---- labelID ----
                    labelID.setText("ID:");
                    contentPanel2.add(labelID);
                    contentPanel2.add(textField1);
                }
                contentPanel.add(contentPanel2);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("Drop");
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
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }
}
