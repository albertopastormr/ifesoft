package Presentacion.views.forms;

import Negocio.Asignacion.Tasignacion;
import Presentacion.Controller;
import Presentacion.views.events.Event;
import Presentacion.views.optionsPanel.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewsFormAssignation extends JFrame {

    private String metres;

    private boolean mod;

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JLabel title;
    private JPanel formContainer;
    private JTextField metresField;
    private JTextField idFairField;
    private JTextField idPavilionField;
    private JTextField idStandField;
    private JPanel buttonBar;


    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fField = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cField = new Color(243,243,243);
    private Color cHelpButton = new Color(66,35,146);
    private Color cCancelButton = new Color(146, 35, 59);
    private Color cOkButton = new Color(26, 184, 59);

    public ViewsFormAssignation() {
       super("Assignation");
        mod = false;
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }


    public ViewsFormAssignation(Tasignacion assignation) {
        super("Assignation");
        mod = true;
        metres = assignation.getUsed_m2() + "";

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }


    private void createButtonFormActionPerformed() throws Exception {
        this.setVisible(false);
        int mUsed = Integer.valueOf(metresField.getText());
        int idFair = Integer.valueOf(idFairField.getText());
        int idPavilion = Integer.valueOf(idPavilionField.getText());
        int idStand = Integer.valueOf(idStandField.getText());

        Tasignacion tAssignation = new Tasignacion(idFair, idPavilion, idStand, mUsed, true);

        if (!mod)  Controller.getInstance().execute(Event.INSERT_ASIGNACION, tAssignation);
        else Controller.getInstance().execute(Event.MODIFY_ASIGNACION, tAssignation);
    }

    private void cancelButtonStateChanged() throws Exception {
        this.setVisible(false);
        if (!mod) Controller.getInstance().execute(Event.CREATE_HALF, null);
        else Controller.getInstance().execute(Event.MODIFY_HALF, null);
    }

    private void helpButtonActionPerformed() {

    }

    private void setupTitle(){
        title = new JLabel();
        if(mod)
            title.setText("Modify Assignation");
        else
            title.setText("Create Assignation");
        title.setFont(fTitle);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));
    }

    private JLabel createLabel(String text){

        JLabel label = new JLabel(text, JLabel.RIGHT);
        label.setFont(fLabel);
        return label;
    }

    private JTextField setupTextField(){
        JTextField field = new JTextField();
        field.setFont(fField);
        field.setBackground(cField);
        return field;
    }

    private void setupForm() {

        formContainer = new JPanel();
        formContainer.setLayout(new FlowLayout());

        JPanel formPanel = new JPanel();
        GridBagLayout formLayout = new GridBagLayout();
        formPanel.setLayout(formLayout);


        //---- Labels ----

        Dimension minDim = new Dimension(500, 50);
        Dimension prefDim = new Dimension(600, 50);
        Dimension maxDim = new Dimension(700, 50);


        GridBagConstraints formCon = new GridBagConstraints();

        formCon.fill = GridBagConstraints.NONE;
        formCon.weightx = 0.5;
        formCon.weighty = 0.5;
        formCon.anchor = GridBagConstraints.EAST;


        JLabel metresLabel = createLabel("Name:");
        JLabel idFairLabel = createLabel("Description:");
        JLabel idPavilionLabel = createLabel("Start Date:");
        JLabel idStandLabel = createLabel("End Date:");

        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;

        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(metresLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(idFairLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 2;
        formPanel.add(idPavilionLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 3;
        formPanel.add(idStandLabel, formCon);

        metresField = setupTextField();
        metresField.setMinimumSize(minDim);
        metresField.setPreferredSize(prefDim);
        metresField.setMaximumSize(maxDim);
        metresField.setText(metres);

        idFairField = setupTextField();
        idFairField.setMinimumSize(minDim);
        idFairField.setPreferredSize(prefDim);
        idFairField.setMaximumSize(new Dimension(maxDim.width, maxDim.height + 100));
        String idFair = "";
        idFairField.setText(idFair);

        idPavilionField = setupTextField();
        idPavilionField.setMinimumSize(minDim);
        idPavilionField.setPreferredSize(prefDim);
        idPavilionField.setMaximumSize(maxDim);
        String idPavilion = "";
        idPavilionField.setText(idPavilion);

        idStandField = setupTextField();
        idStandField.setMinimumSize(minDim);
        idStandField.setPreferredSize(prefDim);
        idStandField.setMaximumSize(maxDim);
        String idStand = "";
        idStandField.setText(idStand);

        formCon.anchor = GridBagConstraints.WEST;

        formCon.insets = new Insets(20,10,20,0);

        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(metresField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(idFairField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(idPavilionField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 3;
        formPanel.add(idStandField, formCon);
        formContainer.add(formPanel);
    }

    private void setUpButtonBar(){

        Dimension buttonDim = new Dimension(150, 80);

        //---- cancelButton ----
        JButton cancelButton = new JButton();
        cancelButton.setText("Cancel");
        cancelButton.setFont(fButton);
        cancelButton.setBackground(cCancelButton);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(buttonDim);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cancelButtonStateChanged();
                } catch (Exception e1){
                    new PanelProblemUser(e1.getMessage());
                }
            }
        });


        //---- helpButton ----
        JButton helpButton = new JButton();
        helpButton.setText("Help");
        helpButton.setFont(fButton);
        helpButton.setBackground(cHelpButton);
        helpButton.setForeground(Color.WHITE);
        helpButton.setPreferredSize(buttonDim);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpButtonActionPerformed();
            }
        });


        //---- okButton ----
        JButton okButton = new JButton();
        okButton.setText("Add");
        okButton.setFont(fButton);
        okButton.setBackground(cOkButton);
        okButton.setForeground(Color.WHITE);
        okButton.setPreferredSize(buttonDim);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createButtonFormActionPerformed();
                } catch (Exception e1){
                    new PanelProblemUser(e1.getMessage());
                }
            }
        });

        buttonBar = new JPanel();
        FlowLayout layout = new FlowLayout();
        layout.setHgap(25);
        buttonBar.setLayout(layout);
        buttonBar.add(cancelButton);
        buttonBar.add(helpButton);
        buttonBar.add(Box.createHorizontalStrut(500));
        buttonBar.add(okButton);

    }

    private void initComponents() {

        this.setMinimumSize(minScreenSize);

        JPanel dialogPanel = new JPanel();
        BorderLayout dialogLayout = new BorderLayout();
        dialogPanel.setLayout(dialogLayout);
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));


        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());


        ImageIcon img = new ImageIcon("Resources//Icon.png");
        this.setIconImage(img.getImage());

        //======== contents ========

        //----Title----
        setupTitle();
        dialogPanel.add(title, BorderLayout.PAGE_START);
        //----Form----
        setupForm();
        dialogPanel.add(formContainer, BorderLayout.CENTER);
        //----Buttons----
        setUpButtonBar();
        dialogPanel.add(buttonBar, BorderLayout.PAGE_END);

        contentPane.add(dialogPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }
}