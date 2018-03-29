package Presentacion.Feria.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ViewsHalfCreate extends JFrame {

    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel JlabelTitle;
    private JComboBox comboBox1;
    private JPanel buttonBar;
    private JButton NextButton;
    private JButton backButton;
    private JButton helpButtonHalfCreate;

    public ViewsHalfCreate() {
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void NextButtonActionPerformed(ActionEvent e) {
    }

    private void backButtonActionPerformed(ActionEvent e) {
    }

    private void helpButtonHalfCreateActionPerformed(ActionEvent e) {
    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        JlabelTitle = new JLabel();
        comboBox1 = new JComboBox();
        buttonBar = new JPanel();
        NextButton = new JButton();
        backButton = new JButton();
        helpButtonHalfCreate = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPane.getBorder()));
            
            dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){
                public void propertyChange(java.beans.PropertyChangeEvent e){
                    if("border".equals(e.getPropertyName()))
                        throw new RuntimeException();
                }
            });

            dialogPane.setLayout(new BoxLayout(dialogPane, BoxLayout.Y_AXIS));

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

                //---- JlabelTitle ----
                JlabelTitle.setText("IFESOFT SOFTWATE");
                JlabelTitle.setFont(JlabelTitle.getFont().deriveFont(JlabelTitle.getFont().getSize() + 7f));
                contentPanel.add(JlabelTitle);
                contentPanel.add(comboBox1);
            }
            dialogPane.add(contentPanel);

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
                        NextButtonActionPerformed(e);
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
                        backButtonActionPerformed(e);
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
                        helpButtonHalfCreateActionPerformed(e);
                    }
                });
                buttonBar.add(helpButtonHalfCreate, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));
            }
            dialogPane.add(buttonBar);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }
}
