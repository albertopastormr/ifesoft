package Presentacion.Create_Modify.Forms;

import Negocio.Participante.Tparticipante;
import Controller.Controller;
import Negocio.Participante.TparticipanteInternacional;
import Negocio.Participante.TparticipanteNacional;
import Presentacion.Events.Event;
import Presentacion.UI;
import Presentacion.UIStructureFrame;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class GUIFormClient extends UIStructureFrame {

    private int idClient;

    private String name;
    private String phone;
    private String regionCountryText;

    private boolean mod;

    private JPanel formContainer;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField regionCountryField;
    private JComboBox<String> comboBoxCreate;

    private JLabel regionCountryLabel;

    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fField = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fComboBox = new Font(Font.DIALOG, Font.PLAIN, 40);

    private Color cField = new Color(243,243,243);
    private Color cOkButton = new Color(26, 184, 59);
    private Color cComboBoxActive = new Color(207, 216, 220);
    private Color cComboBoxInactive = new Color(187, 196, 200);
    private Color cComboBoxFont = new Color(84, 91, 94);
    private Color cComboBoxSelectedFont = new Color(52, 56, 58);

    // CONSTRUCTOR OPTION CREATE
    public GUIFormClient() {
        super("");

        this.helpMessage = "<html><h1>CLIENT INFO</1>Here you can <b>insert</b> " +
                "<u>Client</u>'s data just by inserting them into the" +
                " text areas, then click <b>'Next'</b> to continue or <b>'Cancel'</b> to go back." +
                "In the first field you have to insert the name of the client, in the second one its telephone" +
                " number and in the last one the specialization of this company." +
                "</html>";

        mod = false;
        initComponents();
        viewVisibleLogic();

        comboBoxCreate.setEnabled(true);
        regionCountryField.setEnabled(true);

        this.setVisible(true);
    }

    // CONSTRUCTOR OPTION MODIFY
    public GUIFormClient(Tparticipante client) {
        super("");
        mod = true;

        this.helpMessage = "<html><h1>CLIENT INFO</1>Here you can <b>insert</b> " +
                "<u>Client</u>'s data just by inserting them into the" +
                " text areas, then click <b>'Next'</b> to continue or <b>'Cancel'</b> to go back." +
                "In the first field you have to insert the name of the client, in the second one its telephone" +
                " number and in the last one the specialization of this company." +
                "</html>";


        this.idClient = client.getId();

        name = client.getName();
        phone = "" + (client.getPhone());

        initComponents();
        viewVisibleLogic();

        comboBoxCreate.setEnabled(false);
        regionCountryField.setEnabled(false);
    }

   /*private void nextButtonActionPerformed() throws Exception {
        switch (String.valueOf(comboBoxCreate.getSelectedItem())) {
            case "National":
                this.setVisible(false);
                Controller.getInstance().execute(Event.SHOW_REGION_PABELLON, null);
                break;
            case "International":
                this.setVisible(false);
                Controller.getInstance().execute(Event.SHOW_PAIS_PABELLON, null);
                break;
        }
    }*/

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
        regionCountryLabel = createLabel("Region");

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
        formCon.gridx = 0;
        formCon.gridy = 3;
        formPanel.add(regionCountryLabel, formCon);

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

        regionCountryField = setupTextField();
        regionCountryField.setMinimumSize(minDim);
        regionCountryField.setPreferredSize(prefDim);
        regionCountryField.setMaximumSize(new Dimension(maxDim.width, maxDim.height + 100));
        regionCountryField.setText(regionCountryText);

        formCon.anchor = GridBagConstraints.WEST;

        formCon.insets = new Insets(20,10,20,0);

        //===== JComboBox =====

        UIManager.put("JTextField.background", new ColorUIResource(cComboBoxInactive));
        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(cComboBoxActive));
        UIManager.put("ComboBox.selectionForeground", new ColorUIResource(cComboBoxSelectedFont));
        UIManager.put("ComboBox.disabledBackground", new ColorUIResource(cComboBoxInactive));
        UIManager.put("ComboBox.disabledForeground", new ColorUIResource(cComboBoxFont));

        comboBoxCreate = new JComboBox<>();
        comboBoxCreate.getEditor().getEditorComponent().setBackground(cComboBoxActive);
        comboBoxCreate.setFont(fComboBox);
        comboBoxCreate.setForeground(cComboBoxFont);
        comboBoxCreate.setMinimumSize(new Dimension(200, 50));
        comboBoxCreate.setMaximumSize(new Dimension(800, 50));

        comboBoxCreate.addItem("National");
        comboBoxCreate.addItem("International");

        comboBoxCreate.setBorder(BorderFactory.createEmptyBorder(0,0, 20, 0));
        formPanel.add(comboBoxCreate);

        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(nameField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(phoneField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(comboBoxCreate, formCon);
        formCon.gridx = 1;
        formCon.gridy = 3;
        formPanel.add(regionCountryField, formCon);
        formContainer.add(formPanel);
    }

    @Override
    protected void setUpButtonBar(){
        super.setUpButtonBar();

        Dimension buttonDim = new Dimension(150, 80);

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
                    okButtonActionPerformed(e);
                }catch (NumberFormatException e1){
                    new PanelProblemUser("Enter valid characters.");
                } catch (Exception e2){
                    new PanelProblemUser(e2.getMessage());
                }
            }
        });

        buttonBar.add(okButton);
    }

    @Override
    protected void okButtonActionPerformed(ActionEvent e) throws Exception {
        setVisible(false);
        Tparticipante client;
        String name = nameField.getText();
        String numPhone = phoneField.getText();
        String regionCountry = regionCountryField.getText();

        if(String.valueOf(comboBoxCreate.getSelectedItem()).equals("National"))
            client = new TparticipanteNacional(name, Integer.parseInt(numPhone), true, regionCountry);
        else client = new TparticipanteInternacional(name, Integer.parseInt(numPhone), true, regionCountry);

        if (!mod)  Controller.getInstance().execute(Presentacion.Events.Event.INSERT_CLIENT, client);
        else{
            client.setId(idClient);
            Controller.getInstance().execute(Event.MODIFY_CLIENT, client);
        }
    }

    @Override
    protected void cancelButtonActionPerformed(ActionEvent e) throws Exception {
        this.setVisible(false);
        if (!mod) Controller.getInstance().execute(Event.CREATE_HALF, null);
        else Controller.getInstance().execute(Event.MODIFY_HALF, null);
    }

    @Override
    protected void setUpTitle() {
        title = new JLabel();
        if(mod)
            title.setText("Modify Client");
        else
            title.setText("Create_Modify Client");
        title.setFont(fTitle);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));
    }

    @Override
    protected void setUpCenter() {

    }

    @Override
    protected void initComponents() {
        super.initComponents();

        setupForm();
        dialogPanel.add(formContainer, BorderLayout.CENTER);
    }

    private void viewVisibleLogic(){
        comboBoxCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                switch (Objects.requireNonNull(comboBoxCreate.getSelectedItem()).toString()){
                    case "National":
                        regionCountryLabel.setText("Region");
                        break;
                    case "International":
                        regionCountryLabel.setText("Country");
                        break;
                }
            }
        });
    }

    @Override
    public void update(int event, Object data) {
        JOptionPane.showMessageDialog(null, "The Client has been created successfully");
        JOptionPane.showMessageDialog(null, "A problem in the creation process occurred, insert Client's data another time please", "Error",
                                  JOptionPane.ERROR_MESSAGE);
    }
}
