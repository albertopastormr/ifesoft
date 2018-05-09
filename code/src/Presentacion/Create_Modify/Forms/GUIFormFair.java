package Presentacion.Create_Modify.Forms;

import Negocio.Feria.Tferia;
import Controller.Controller;
import Presentacion.Events.EventGUI;
import Presentacion.UIStructureFrame;
import Presentacion.Utils.Utilities;
import Presentacion.Events.Event;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIFormFair extends UIStructureFrame {

    private int idFair;

    private String name;
    private String description;
    private String iniDate;
    private String finDate;

    private boolean mod;

    private JLabel title;
    private JPanel formContainer;
    private JTextField nameField;
    private JTextField descField;
    private JTextField iniDateField;
    private JTextField finDateField;

    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fField = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cField = new Color(243,243,243);
    private Color cOkButton = new Color(26, 184, 59);

    public GUIFormFair() {
        super("");

        this.helpMessage = "<html><h1>FAIR INFO</1>Here you can <b>insert</b> <u>Fair</u>'s data just by inserting" +
                " them into the text areas, then click <b>'Next'</b> " +
                "to continue or <b>'Cancel'</b> to go back." +
                "In the first field you have to insert the name of the fair," +
                "in the second one description to give some information and define the theme of the fair," +
                "in the third field you have to write the starting date " +
                "and in the last one the end date." +
                "</html>";

        this.mod = false;
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);

    }

    public GUIFormFair(Tferia fair) {
        super("");

        this.helpMessage = "<html><h1>FAIR INFO</1>Here you can <b>insert</b> <u>Fair</u>'s data just by inserting" +
                " them into the text areas, then click <b>'Next'</b> " +
                "to continue or <b>'Cancel'</b> to go back." +
                "In the first field you have to insert the name of the fair," +
                "in the second one description to give some information and define the theme of the fair," +
                "in the third field you have to write the starting date " +
                "and in the last one the end date." +
                "</html>";

        this.mod = true;

        this.idFair = fair.getId();
        this.name = fair.getName();
        this.description = fair.getDescription();
        this.iniDate = Utilities.parseDateToString(fair.getIniDate());
        this.finDate = Utilities.parseDateToString(fair.getEndDate());

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


        JLabel nameLabel = createLabel("Name:");
        JLabel descLabel = createLabel("Description:");
        JLabel iniDateLabel = createLabel("Start Date:");
        JLabel finDateLabel = createLabel("End Date:");

        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;

        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(nameLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(descLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 2;
        formPanel.add(iniDateLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 3;
        formPanel.add(finDateLabel, formCon);

        nameField = setupTextField();
        nameField.setMinimumSize(minDim);
        nameField.setPreferredSize(prefDim);
        nameField.setMaximumSize(maxDim);
        nameField.setText(name);

        descField = setupTextField();
        descField.setMinimumSize(new Dimension(minDim.width, minDim.height + 100));
        descField.setPreferredSize(new Dimension(prefDim.width, prefDim.height + 100));
        descField.setMaximumSize(new Dimension(maxDim.width, maxDim.height + 100));
        descField.setText(description);

        iniDateField = setupTextField();
        iniDateField.setMinimumSize(minDim);
        iniDateField.setPreferredSize(prefDim);
        iniDateField.setMaximumSize(maxDim);
        iniDateField.setText(iniDate);

        finDateField = setupTextField();
        finDateField.setMinimumSize(minDim);
        finDateField.setPreferredSize(prefDim);
        finDateField.setMaximumSize(maxDim);
        finDateField.setText(finDate);

        formCon.anchor = GridBagConstraints.WEST;

        formCon.insets = new Insets(20,10,20,0);

        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(nameField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(descField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(iniDateField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 3;
        formPanel.add(finDateField, formCon);
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
        String name = nameField.getText();
        String description = descField.getText();
        String dateStart = iniDateField.getText();
        String dateEnd = finDateField.getText();

        if(!mod) Controller.getInstance().execute(Event.INSERT_FAIR, new Tferia(name, description, Utilities.parseStringToDate(dateStart), Utilities.parseStringToDate(dateEnd)));
        else Controller.getInstance().execute(Event.MODIFY_FAIR, new Tferia(idFair ,name, description, Utilities.parseStringToDate(dateStart), Utilities.parseStringToDate(dateEnd), true));
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
            title.setText("Modify Fair");
        else
            title.setText("Create_Modify Fair");
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
        switch (event){
            case EventGUI.UPDATE_CREATE_FERIA_FAIL:
                JOptionPane.showMessageDialog(null, "A problem in the creation process occurred, insert Fair's data another time please", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
}
