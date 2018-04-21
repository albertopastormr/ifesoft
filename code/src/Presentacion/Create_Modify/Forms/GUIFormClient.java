package Presentacion.Create_Modify.Forms;

import Negocio.Participante.Tparticipante;
import Controller.Controller;
import Presentacion.Events.Event;
import Presentacion.UI;
import Presentacion.Utils.ActionHelp;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIFormClient extends JFrame implements UI {

    private String name;
    private String phone;
    private String specialization;

    private boolean mod;

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JLabel title;
    private JPanel formContainer;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField specializationField;
    private JPanel buttonBar;


    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fField = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cField = new Color(243,243,243);
    private Color cHelpButton = new Color(66,35,146);
    private Color cCancelButton = new Color(146, 35, 59);
    private Color cOkButton = new Color(26, 184, 59);

    String helpMessage = "<html><h1>CLIENT INFO</1>Here you can <b>insert</b> " +
            "<u>Client</u>'s data just by inserting them into the" +
            " text areas, then click <b>'Next'</b> to continue or <b>'Cancel'</b> to go back." +
            "In the first field you have to insert the name of the client, in the second one its telephone" +
            " number and in the last one the specialization of this company." +
            "</html>";

    // CONSTRUCTOR OPTION CREATE
    public GUIFormClient() {
        mod = false;
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    // CONSTRUCTOR OPTION MODIFY
    public GUIFormClient(Tparticipante client) {
        mod = true;

        name = client.getName();
        phone = "" + (client.getPhone());
        //specialization = client.getSpecialization();

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void createButtonFormActionPerformed() throws Exception {
        setVisible(false);
        String name = nameField.getText();
        String numPhone = phoneField.getText();
        String specialization = specializationField.getText();
        Tparticipante client = new Tparticipante(name, Integer.parseInt(numPhone), Boolean.parseBoolean(specialization));

        if (!mod)  Controller.getInstance().execute(Presentacion.Events.Event.INSERT_CLIENT, client);
        else Controller.getInstance().execute(Event.MODIFY_CLIENT, client);
    }

    private void cancelButtonStateChanged() throws Exception {
        this.setVisible(false);
        if (!mod) Controller.getInstance().execute(Event.CREATE_HALF, null);
        else Controller.getInstance().execute(Event.MODIFY_HALF, null);
    }

    private void helpButtonActionPerformed() {
        new ActionHelp(helpMessage);
    }

    private void setupTitle(){
        title = new JLabel();
        if(mod)
            title.setText("Modify Client");
        else
            title.setText("Create_Modify Client");
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


        JLabel nameLabel = createLabel("Name:");
        JLabel phoneLabel = createLabel("Phone:");
        JLabel specializationLabel = createLabel("Specialization");

        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;

        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(nameLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(phoneLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 2;
        formPanel.add(specializationLabel, formCon);

        nameField = setupTextField();
        nameField.setMinimumSize(minDim);
        nameField.setPreferredSize(prefDim);
        nameField.setMaximumSize(maxDim);
        nameField.setText(name);

        phoneField = setupTextField();
        phoneField.setMinimumSize(minDim);
        phoneField.setPreferredSize(prefDim);
        phoneField.setMaximumSize(new Dimension(maxDim.width, maxDim.height + 100));
        phoneField.setText(phone);

        specializationField = setupTextField();
        specializationField.setMinimumSize(minDim);
        specializationField.setPreferredSize(prefDim);
        specializationField.setMaximumSize(maxDim);
        specializationField.setText(specialization);

        formCon.anchor = GridBagConstraints.WEST;

        formCon.insets = new Insets(20,10,20,0);

        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(nameField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(phoneField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(specializationField, formCon);
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

        ImageIcon img = new ImageIcon("Resources//Icon.png");
        this.setIconImage(img.getImage());

        JPanel dialogPanel = new JPanel();
        BorderLayout dialogLayout = new BorderLayout();
        dialogPanel.setLayout(dialogLayout);
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));


        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

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

    @Override
    public void update(int event, Object data) {
        //JOptionPane.showMessageDialog(null, "The Client has been created successfully");
        //JOptionPane.showMessageDialog(null, "A problem in the creation process occurred, insert Client's data another time please", "Error",
        //                            JOptionPane.ERROR_MESSAGE);
    }
}
