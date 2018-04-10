package Presentacion.Feria.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ViewDropVerificate extends JFrame {

    private Dimension Size = new Dimension(1000, 100);

    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel labelQuestion;
    private JPanel buttonBar;
    private JButton cancelButton;
    private JButton okButton;

    private Font fQuestion = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cOkButton = new Color(146, 35, 59);
    private Color cCancelButton = new Color(66,35,146);


    public ViewDropVerificate() {
        initComponents();
    }

    private void okButtonActionPerformed(ActionEvent e) {

    }

    private void cancelButtonActionPerformed(ActionEvent e) {

    }

    private void setupQuestion(){
        labelQuestion = new JLabel();
        labelQuestion.setText("Are you sure that you want to permanently delete this module?");
        labelQuestion.setFont(fQuestion);
        labelQuestion.setHorizontalAlignment(SwingConstants.CENTER);
        labelQuestion.setPreferredSize(Size);
    }

    private void setUpButtonBar(){

        Dimension buttonDim = new Dimension(150, 80);

        //---- cancelButton ----
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        cancelButton.setFont(fButton);
        cancelButton.setBackground(cCancelButton);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(buttonDim);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonActionPerformed(e);
            }
        });


        //---- okButton ----
        okButton = new JButton();
        okButton.setText("Drop");
        okButton.setFont(fButton);
        okButton.setBackground(cOkButton);
        okButton.setForeground(Color.WHITE);
        okButton.setPreferredSize(buttonDim);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButtonActionPerformed(e);
            }
        });

        buttonBar = new JPanel();
        FlowLayout layout = new FlowLayout();
        layout.setHgap(25);
        buttonBar.setLayout(layout);
        buttonBar.add(cancelButton);
        buttonBar.add(Box.createHorizontalStrut(200));
        buttonBar.add(okButton);



    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();



        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setSize(Size);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //======== dialogPane ========

            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPane.setLayout(new BorderLayout());


        setupQuestion();
        dialogPane.add(labelQuestion, BorderLayout.CENTER);

        setUpButtonBar();
        dialogPane.add(buttonBar, BorderLayout.PAGE_END);

        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }
}
