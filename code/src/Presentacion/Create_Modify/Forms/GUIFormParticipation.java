package Presentacion.Create_Modify.Forms;

import Negocio.Participacion.Tparticipacion;
import Controller.Controller;
import Presentacion.Events.Event;
import Presentacion.UIStructureFrame;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIFormParticipation extends UIStructureFrame{

    private int idParticipation;
    private String metres;
    private String idFair;
    private String idClient;

    private boolean mod;

    private JLabel title;
    private JPanel formContainer;
    private JTextField metresField;
    private JTextField idFairField;
    private JTextField idClientField;


    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fField = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cField = new Color(243,243,243);
    private Color cOkButton = new Color(26, 184, 59);

    public GUIFormParticipation() {
        super("");

        this.helpMessage = "<html><h1>PARTICIPATION'S FORM HELP </1>Here you can <b>insert</b> <u>Participation</u>" +
                "'s data just " +
                "by inserting them into the text areas, then click <b>'Next'</b> " +
                "to continue or <b>'Cancel'</b> to go back." +
                "In the first field you have to insert the metres used by the stand of the client in the fair," +
                "in the second one the ID of the fair in which the client will take part," +
                "in the third one the ID of the client and" +
                "in the last one the ID of its stand." +
                "</html>";

        mod = false;
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    public GUIFormParticipation(Tparticipacion participation) {
        super("");

        this.helpMessage = "<html><h1>PARTICIPATION'S FORM HELP </1>Here you can <b>insert</b> <u>Participation</u>" +
                "'s data just " +
                "by inserting them into the text areas, then click <b>'Next'</b> " +
                "to continue or <b>'Cancel'</b> to go back." +
                "In the first field you have to insert the metres used by the stand of the client in the fair," +
                "in the second one the ID of the fair in which the client will take part," +
                "in the third one the ID of the client and" +
                "in the last one the ID of its stand." +
                "</html>";

        mod = true;

        this.idParticipation = participation.getId();

        idFair = (String.valueOf(participation.getFair_id()));
        idClient = (String.valueOf(participation.getClient_id()));

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
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

        JLabel idFairLabel = createLabel("Fair id:");
        JLabel idParticipantLabel = createLabel("Client id:");

        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;

        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(idFairLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(idParticipantLabel, formCon);

        metresField = setupTextField();
        metresField.setMinimumSize(minDim);
        metresField.setPreferredSize(prefDim);
        metresField.setMaximumSize(maxDim);
        metresField.setText(metres);

        idFairField = setupTextField();
        idFairField.setMinimumSize(minDim);
        idFairField.setPreferredSize(prefDim);
        idFairField.setMaximumSize(new Dimension(maxDim.width, maxDim.height + 100));
        idFairField.setText(idFair);

        idClientField = setupTextField();
        idClientField.setMinimumSize(minDim);
        idClientField.setPreferredSize(prefDim);
        idClientField.setMaximumSize(maxDim);
        idClientField.setText(idClient);

        formCon.anchor = GridBagConstraints.WEST;

        formCon.insets = new Insets(20,10,20,0);

        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(idFairField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(idClientField, formCon);
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
                    new PanelProblemUser("Inserte caracteres validos.");
                } catch (Exception e2){
                    new PanelProblemUser(e2.getMessage());
                }
            }
        });

        buttonBar.add(okButton);
    }

    @Override
    protected void okButtonActionPerformed(ActionEvent e) throws Exception {
        this.setVisible(false);
        int idFair = Integer.valueOf(idFairField.getText());
        int idClient = Integer.valueOf(idClientField.getText());

        if (!mod)  Controller.getInstance().execute(Event.INSERT_PARTICIPATION, new Tparticipacion(idFair, idClient, true));
        else Controller.getInstance().execute(Event.MODIFY_PARTICIPATION, new Tparticipacion(idParticipation ,idFair, idClient, true));
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
            title.setText("Modify Participation");
        else
            title.setText("Create_Modify Participation");
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

        this.setMinimumSize(minScreenSize);

        //----Form----
        setupForm();
        dialogPanel.add(formContainer, BorderLayout.CENTER);
    }

    @Override
    public void update(int event, Object data) {
        JOptionPane.showMessageDialog(null, "The Participation has been created successfully");
        JOptionPane.showMessageDialog(null, "A problem in the creation process occurred, insert Participation's data another time please", "Error",
                                    JOptionPane.ERROR_MESSAGE);
    }
}