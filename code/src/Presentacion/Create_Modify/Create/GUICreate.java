package Presentacion.Create_Modify.Create;

import Controller.Controller;
import Presentacion.Events.Event;
import Presentacion.UI;
import Presentacion.UIStructureFrame;
import Presentacion.Utils.ActionHelp;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.ColorUIResource;

public class GUICreate extends UIStructureFrame {

    private JComboBox<String> comboBoxCreate;

    private Font fComboBox = new Font(Font.DIALOG, Font.PLAIN, 40);
    private Font fTitle  = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cOkButton = new Color(26, 184, 59);
    private Color cComboBoxActive = new Color(207, 216, 220);
    private Color cComboBoxInactive = new Color(187, 196, 200);
    private Color cComboBoxFont = new Color(84, 91, 94);
    private Color cComboBoxSelectedFont = new Color(52, 56, 58);

    public GUICreate() {
        super("Create");

        this.helpMessage = "<html><h1>CREATE PAGE HELP</1>Here you have the possibility to <b>Create</b> a <u>Fair</u>" +
                " or other entities that you can choose by clicking on the comboBox." +
                "<br>Click <b>'Next'</b> to confirm or <b>'Cancel'</b> to go back to the previous page." +
                "</html>";

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }


    @Override
    protected void okButtonActionPerformed(ActionEvent e) throws Exception {

        switch (String.valueOf(comboBoxCreate.getSelectedItem())){
            case "Fair":
                this.setVisible(false);
                Controller.getInstance().execute(Event.INSERT_FORM_FAIR, null);
                break;
            case "Pavilion":
                this.setVisible(false);
                Controller.getInstance().execute(Event.INSERT_FORM_PAVILION, null);
                break;
            case "Stand":
                this.setVisible(false);
                Controller.getInstance().execute(Event.INSERT_FORM_STAND, null);
                break;
            case "Client":
                this.setVisible(false);
                Controller.getInstance().execute(Event.INSERT_FORM_CLIENT, null);
                break;
            case "Assignation":
                this.setVisible(false);
                Controller.getInstance().execute(Event.INSERT_FORM_ASSIGNATION, null);
                break;
            case "Participation":
                this.setVisible(false);
                Controller.getInstance().execute(Event.INSERT_FORM_PARTICIPATION, null);
                break;
        }
    }

    @Override
    protected void cancelButtonActionPerformed(ActionEvent e) throws Exception {
        this.setVisible(false);
        Controller.getInstance().execute(Event.HOME, null);
    }

    @Override
    protected void setUpTitle(){
        title = new JLabel();
        title .setText("Create");
        title .setFont(fTitle);
        title .setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));

    }

    @Override
    protected void setUpCenter(){

        centerPanel = new JPanel();
        BoxLayout centerLayout = new BoxLayout(centerPanel, BoxLayout.Y_AXIS);
        centerPanel.setLayout(centerLayout);

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
        comboBoxCreate.setMinimumSize(new Dimension(200, 60));
        comboBoxCreate.setMaximumSize(new Dimension(800, 80));

        comboBoxCreate.addItem("Assignation");
        comboBoxCreate.addItem("Client");
        comboBoxCreate.addItem("Fair");
        comboBoxCreate.addItem("Participation");
        comboBoxCreate.addItem("Pavilion");
        comboBoxCreate.addItem("Stand");

        comboBoxCreate.setBorder(BorderFactory.createEmptyBorder(0,0, 20, 0));
        centerPanel.add(comboBoxCreate);
    }

    @Override
    protected void setUpButtonBar(){
        super.setUpButtonBar();

        Dimension buttonDim = new Dimension(150, 80);

        //---- okButton ----
        JButton nextButton = new JButton();
        nextButton.setText("Next");
        nextButton.setFont(fButton);
        nextButton.setBackground(cOkButton);
        nextButton.setForeground(Color.WHITE);
        nextButton.setPreferredSize(buttonDim);
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    okButtonActionPerformed(e);
                } catch (NumberFormatException e1){
                    new PanelProblemUser("Enter valid characters.");
                } catch (Exception e2){
                    new PanelProblemUser(e2.getMessage());
                }
            }
        });

        buttonBar.add(nextButton);

    }

    @Override
    public void update(int event, Object data) {

    }
}
