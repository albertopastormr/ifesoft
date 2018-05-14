package Presentacion.Create_Modify.Forms;

import Negocio.Stand.Tstand;
import Controller.Controller;
import Presentacion.Events.Event;
import Presentacion.UIStructureFrame;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIFormStand extends UIStructureFrame {

    private int idStand;
    private String idAssignation;
    private String idParticipation;
    private String number;
    private String metres;
    private String cost;

    private boolean mod;

    private JLabel title;
    private JPanel formContainer;
    private JTextField metresField;
    private JTextField numberField;
    private JTextField costField;
    private JTextField idAssignationField;
    private JTextField idParticipationField;


    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fField = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cField = new Color(243,243,243);
    private Color cOkButton = new Color(26, 184, 59);

    public GUIFormStand() {
        super("");

        this.helpMessage = "<html><h1>STAND'S FORM HELP </1>Here you can <b>insert</b> <u>Stand</u>'s data just by " +
                "inserting them into the text areas, then click <b>'Next'</b> to continue or <b>'Cancel'</b>" +
                " to go back." +
                "In the first field you have to insert the metres occupied by the stand," +
                "in the second one its number and" +
                "in the last one its cost."+
                "</html>";

        mod = false;
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    public GUIFormStand(Tstand tstand) {
        super("");

        this.helpMessage = "<html><h1>STAND'S FORM HELP </1>Here you can <b>insert</b> <u>Stand</u>'s data just by " +
                "inserting them into the text areas, then click <b>'Next'</b> to continue or <b>'Cancel'</b>" +
                " to go back." +
                "In the first field you have to insert the metres occupied by the stand," +
                "in the second one its number and" +
                "in the last one its cost."+
                "</html>";

        mod = true;

        this.idStand = tstand.getId();
        this.idAssignation = "" + tstand.getAssignation_id();
        this.idParticipation = "" + tstand.getParticipation_id();
        this.metres = "" + tstand.getTotal_m2();
        this.number = "" + tstand.getNum_at_fair();
        this.cost = "" + tstand.getCost();

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

        JLabel idAssignationLabel = createLabel("Assignation ID:");
        JLabel idParticipacionLabel = createLabel("Participation ID:");
        JLabel metresLabel = createLabel("Metres:");
        JLabel numberLabel = createLabel("Number:");
        JLabel costLabel = createLabel("Cost:");

        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;

        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(idAssignationLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(idParticipacionLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 2;
        formPanel.add(metresLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 3;
        formPanel.add(numberLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 4;
        formPanel.add(costLabel, formCon);

        idAssignationField = setupTextField();
        idAssignationField.setMinimumSize(minDim);
        idAssignationField.setPreferredSize(prefDim);
        idAssignationField.setMaximumSize(maxDim);
        idAssignationField.setText(idAssignation);

        idParticipationField = setupTextField();
        idParticipationField.setMinimumSize(minDim);
        idParticipationField.setPreferredSize(prefDim);
        idParticipationField.setMaximumSize(maxDim);
        idParticipationField.setText(idParticipation);

        metresField = setupTextField();
        metresField.setMinimumSize(minDim);
        metresField.setPreferredSize(prefDim);
        metresField.setMaximumSize(maxDim);
        metresField.setText(metres);

        numberField = setupTextField();
        numberField.setMinimumSize(minDim);
        numberField.setPreferredSize(prefDim);
        numberField.setMaximumSize(new Dimension(maxDim.width, maxDim.height + 100));
        numberField.setText(number);

        costField = setupTextField();
        costField.setMinimumSize(minDim);
        costField.setPreferredSize(prefDim);
        costField.setMaximumSize(maxDim);
        costField.setText(cost);

        formCon.anchor = GridBagConstraints.WEST;

        formCon.insets = new Insets(20,10,20,0);

        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(idAssignationField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(idParticipationField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(metresField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 3;
        formPanel.add(numberField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 4;
        formPanel.add(costField, formCon);
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
                }catch (NumberFormatException e2){
                    new PanelProblemUser("Enter valid characters.");
                } catch (Exception e1){
                    new PanelProblemUser(e1.getMessage());
                }
            }
        });

        buttonBar.add(okButton);
    }

    @Override
    protected void okButtonActionPerformed(ActionEvent e) throws Exception {
        setVisible(false);
        int idAssignation = Integer.parseInt(idAssignationField.getText());
        int idParticipation = Integer.parseInt(idParticipationField.getText());
        float cost = Float.parseFloat(costField.getText());
        int m_used = Integer.parseInt(metresField.getText());
        int number = Integer.parseInt(numberField.getText());

        if (!mod) Controller.getInstance().execute(Event.INSERT_STAND, new Tstand( idAssignation, idParticipation, number, cost, m_used, true));
        else Controller.getInstance().execute(Event.MODIFY_STAND, new Tstand(idStand, idAssignation, idParticipation, number, cost, m_used,true));
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
            title.setText("Modify Stand");
        else
            title.setText("Create_Modify Stand");
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
        JOptionPane.showMessageDialog(null,"The Stand has been created successfully");
        JOptionPane.showMessageDialog(null, "A problem in the creation process occurred, insert Stand's data another time please", "Error",
                                   JOptionPane.ERROR_MESSAGE);
    }
}