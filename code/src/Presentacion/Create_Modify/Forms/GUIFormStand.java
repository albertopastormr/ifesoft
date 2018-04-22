package Presentacion.Create_Modify.Forms;

import Negocio.Stand.Tstand;
import Controller.Controller;
import Presentacion.Events.Event;
import Presentacion.UI;
import Presentacion.Utils.ActionHelp;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIFormStand extends JFrame implements UI {

    private int idStand;
    private String idAssignation;
    private String idParticipation;
    private String number;
    private String metres;
    private String cost;

    private boolean mod;

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JLabel title;
    private JPanel formContainer;
    private JTextField metresField;
    private JTextField numberField;
    private JTextField costField;
    private JTextField idAssignationField;
    private JTextField idParticipationField;
    private JPanel buttonBar;


    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fField = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cField = new Color(243,243,243);
    private Color cHelpButton = new Color(66,35,146);
    private Color cCancelButton = new Color(146, 35, 59);
    private Color cOkButton = new Color(26, 184, 59);

    String helpMessage = "<html><h1>STAND'S FORM HELP </1>Here you can <b>insert</b> <u>Stand</u>'s data just by " +
            "inserting them into the text areas, then click <b>'Next'</b> to continue or <b>'Cancel'</b>" +
            " to go back." +
            "In the first field you have to insert the metres occupied by the stand," +
            "in the second one its number and" +
            "in the last one its cost."+
            "</html>";

    public GUIFormStand() {
        mod = false;
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    public GUIFormStand(Tstand tstand) {
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


    private void createButtonFormActionPerformed() throws Exception {
        setVisible(false);
        int idAssignation = Integer.parseInt(idAssignationField.getText());
        int idParticipation = Integer.parseInt(idParticipationField.getText());
        int cost = Integer.parseInt(costField.getText());
        int m_used = Integer.parseInt(metresField.getText());
        int number = Integer.parseInt(numberField.getText());

        if (!mod) Controller.getInstance().execute(Event.INSERT_STAND, new Tstand( idAssignation, idParticipation, cost, m_used, number, true));
        else Controller.getInstance().execute(Event.MODIFY_STAND, new Tstand(idStand, idAssignation, idParticipation, cost, m_used, number, true));
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
            title.setText("Modify Stand");
        else
            title.setText("Create_Modify Stand");
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

    @Override
    public void update(int event, Object data) {
        // JOptionPane.showMessageDialog(null,"The Stand has been created successfully");
        // JOptionPane.showMessageDialog(null, "A problem in the creation process occurred, insert Stand's data another time please", "Error",
        //                            JOptionPane.ERROR_MESSAGE);
    }
}