package Presentacion.Create_Modify.Modify;

import Controller.Controller;
import Presentacion.Events.Event;
import Presentacion.UI;
import Presentacion.UIStructureFrame;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class GUIModify extends UIStructureFrame implements UI {

    private JComboBox<String> comboBoxMod;
    private JTextField textID;

    private Font fComboBox = new Font(Font.DIALOG, Font.PLAIN, 40);
    private Font fTitle  = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fLabelSubId = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fTextField = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cOkButton = new Color(26, 184, 59);
    private Color cComboBoxActive = new Color(207, 216, 220);
    private Color cComboBoxInactive = new Color(187, 196, 200);
    private Color cComboBoxFont = new Color(84, 91, 94);
    private Color cComboBoxSelectedFont = new Color(52, 56, 58);
    private Color cTextFieldBG = new Color(243,243,243);

    public GUIModify() {
        super("Modify");

        this.helpMessage = "<html><h1>MODIFY PAGE HELP</1>Here you have the possibility to <b>Modify</b> a <u>Fair</u>" +
                " or other entities that you can choose by clicking on the comboBox." +
                "<br>Click <b>'Next'</b> to confirm or <b>'Cancel'</b> to go back to the previous page." +
                "</html>";

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    @Override
    protected void okButtonActionPerformed(ActionEvent e) throws Exception {

        switch (String.valueOf(comboBoxMod.getSelectedItem())){
            case "Fair":
                this.setVisible(false);
                Controller.getInstance().execute(Event.MODIFY_FORM_FAIR, Integer.parseInt(textID.getText()));
                break;
            case "Pavilion":
                this.setVisible(false);
                Controller.getInstance().execute(Event.MODIFY_FORM_PAVILION, Integer.parseInt(textID.getText()));
                break;
            case "Client":
                this.setVisible(false);
                Controller.getInstance().execute(Event.MODIFY_FORM_CLIENT, Integer.parseInt(textID.getText()));
                break;
            case "Assignation":
                this.setVisible(false);
                Controller.getInstance().execute(Event.MODIFY_FORM_ASSIGNATION, Integer.parseInt(textID.getText()));
                break;
            case "Participation":
                this.setVisible(false);
                Controller.getInstance().execute(Event.MODIFY_FORM_PARTICIPATION, Integer.parseInt(textID.getText()));
                break;
            case "Stand":
                this.setVisible(false);
                Controller.getInstance().execute(Event.MODIFY_FORM_STAND, Integer.parseInt(textID.getText()));
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
        title .setText("Modify");
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


        comboBoxMod = new JComboBox<>();
        comboBoxMod.getEditor().getEditorComponent().setBackground(cComboBoxActive);
        comboBoxMod.setFont(fComboBox);
        comboBoxMod.setForeground(cComboBoxFont);
        comboBoxMod.setMinimumSize(new Dimension(200, 50));
        comboBoxMod.setMaximumSize(new Dimension(800, 50));

        comboBoxMod.addItem("Assignation");
        comboBoxMod.addItem("Client");
        comboBoxMod.addItem("Fair");
        comboBoxMod.addItem("Participation");
        comboBoxMod.addItem("Pavilion");
        comboBoxMod.addItem("Stand");

        comboBoxMod.setBorder(BorderFactory.createEmptyBorder(0,0, 20, 0));
        centerPanel.add(comboBoxMod);

        //===== TextField =====

        JPanel textFieldPanel = new JPanel();
        FlowLayout textFieldPanelLayout = new FlowLayout();
        textFieldPanel.setLayout(textFieldPanelLayout);

        JLabel labelSubID = new JLabel();
        labelSubID.setText("ID:");
        labelSubID.setFont(fLabelSubId);

        textFieldPanel.add(labelSubID);

        textID = new JTextField();
        textID.setFont(fTextField);
        textID.setBackground(cTextFieldBG);
        textID.setMinimumSize(new Dimension(200, 50));
        textID.setPreferredSize(new Dimension(400, 50));
        textID.setMaximumSize(new Dimension(400, 50));

        textFieldPanel.add(textID);

        centerPanel.add(textFieldPanel);
    }


    @Override
    protected void setUpButtonBar(){
        super.setUpButtonBar();
        Dimension buttonDim = new Dimension(150, 80);

        //---- okButton ----
        JButton okButton = new JButton();
        okButton.setText("Modify");
        okButton.setFont(fButton);
        okButton.setBackground(cOkButton);
        okButton.setForeground(Color.WHITE);
        okButton.setPreferredSize(buttonDim);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    okButtonActionPerformed(e);
                } catch (Exception e1){
                    new PanelProblemUser(e1.getMessage());
                }
            }
        });

        buttonBar.add(okButton);
    }

    @Override
    public void update(int event, Object data) {

    }
}
