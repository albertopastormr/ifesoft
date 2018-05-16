package Presentacion.Shows;

import Negocio.Feria.Tferia;
import Controller.Controller;
import Presentacion.UIStructureFrame;
import Presentacion.Utils.Utilities;
import Presentacion.Events.Event;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class GUIShow extends UIStructureFrame {

    private JLabel labelSubID;
    private JLabel labelSubIDdateStart;
    private JLabel labelSubIDdateEnd;
    private JLabel labelAllList;

    private JComboBox<String> comboBoxViews;
    private JRadioButton radioButtonLeft;
    private JRadioButton radioButtonRight;
    private JRadioButton radioButtonOptional;
    private JRadioButton radioButtonList;

    private JTextField textID;
    private JTextField textDateStart;
    private JTextField textDateEnd;
    private JPanel radioButtonPanel;

    private ButtonGroup radioButtons;

    private Font fComboBox = new Font(Font.MONOSPACED, Font.PLAIN, 40);
    private Font fTitle  = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fRadioButton = new Font(Font.MONOSPACED, Font.PLAIN, 35);
    private Font fButton  = new Font(Font.MONOSPACED, Font.PLAIN, 30);
    private Font fLabelSubId = new Font(Font.MONOSPACED, Font.PLAIN, 30);
    private Font fTextField = new Font(Font.MONOSPACED, Font.PLAIN, 30);

    private Color cOkButton = new Color(26, 184, 59);
    private Color cComboBoxActive = new Color(207, 216, 220);
    private Color cComboBoxInactive = new Color(187, 196, 200);
    private Color cComboBoxFont = new Color(84, 91, 94);
    private Color cComboBoxSelectedFont = new Color(52, 56, 58);
    private Color cTextFieldBG = new Color(243,243,243);

    private boolean isHalfEntity;
    private boolean isHalfEntityList;
    private boolean isStand;
    private boolean isListAll;

    public GUIShow() {
        super("Show");

        this.helpMessage = "<html><h1>SHOW PAGE HELP</h1>Here you have the possibility to" +
                "<b>See</b> <u>Fairs</u> or other entities just" +
                " by choosing one with the comboBox. <br>You can choose between showing just a specific one" +
                " finding it by its ID or a list.</html>" +
                "";

        this.isHalfEntity = false;
        this.isHalfEntityList = false;
        this.isStand = false;
        this.isListAll = false;

        initComponents();
        viewVisibleLogic();

        this.setVisible(true);
    }

    @Override
    protected void okButtonActionPerformed(ActionEvent e) throws Exception {
        viewLogicListener();
    }

    @Override
    protected void cancelButtonActionPerformed(ActionEvent e) throws Exception {
        this.setVisible(false);
        Controller.getInstance().execute(Event.HOME, null);
    }

    @Override
    protected void helpButtonActionPerformed(ActionEvent e) {
        new Presentacion.Utils.ActionHelp(helpMessage);
    }

    @Override
    protected void setUpTitle(){
        this.title = new JLabel();
        this.title .setText("View");
        this.title .setFont(fTitle);
        this.title .setHorizontalAlignment(JLabel.CENTER);
        this.title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));
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


        comboBoxViews = new JComboBox<>();
        comboBoxViews.getEditor().getEditorComponent().setBackground(cComboBoxActive);
        comboBoxViews.setFont(fComboBox);
        comboBoxViews.setForeground(cComboBoxFont);
        comboBoxViews.setMinimumSize(new Dimension(200, 50));
        comboBoxViews.setMaximumSize(new Dimension(800, 50));

        comboBoxViews.addItem("Pavilion");
        comboBoxViews.addItem("Fair");
        comboBoxViews.addItem("Assignation");
        comboBoxViews.addItem("Client");
        comboBoxViews.addItem("Participation");
        comboBoxViews.addItem("Stand");

        comboBoxViews.setBorder(BorderFactory.createEmptyBorder(0,0, 20, 0));
        centerPanel.add(comboBoxViews);

        //===== RadioButtonPanel =====

        this.radioButtonPanel = new JPanel(new FlowLayout());

        //---- radioButtonLeft ----
        radioButtonLeft = new JRadioButton();
        radioButtonLeft.setText("Individual");
        radioButtonLeft.setFont(fRadioButton);
        radioButtonLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeVisibleLeft();
            }
        });

        //---- radioButtonRight ----
        radioButtonRight = new JRadioButton();
        radioButtonRight.setText("List");
        radioButtonRight.setFont(fRadioButton);
        radioButtonRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeVisibleRight();
                if(isStand){
                    changeVisibleLeft();
                }
            }
        });



        //---- radioButtonOptional ----
        radioButtonOptional = new JRadioButton();
        radioButtonOptional.setText("List by dates");
        radioButtonOptional.setFont(fRadioButton);
        radioButtonOptional.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeVisibleOptional();
                if(isStand){
                    changeVisibleLeft();
                }
            }
        });

        //---- radioButtonOptional ----
        radioButtonList = new JRadioButton();
        radioButtonList.setText("List");
        radioButtonList.setFont(fRadioButton);
        radioButtonList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeVisibleList();
            }
        });

        radioButtonList.setVisible(false);

        radioButtonOptional.setVisible(false);

        radioButtons = new ButtonGroup();
        radioButtons.add(radioButtonLeft);
        radioButtons.add(radioButtonRight);
        radioButtons.add(radioButtonOptional);
        radioButtons.add(radioButtonList);

        radioButtonPanel.add(radioButtonLeft);
        radioButtonPanel.add(radioButtonRight);
        radioButtonPanel.add(radioButtonOptional);
        radioButtonPanel.add(radioButtonList);

        radioButtonPanel.setVisible(true);
        centerPanel.add(radioButtonPanel);

        //===== TextField =====

        JPanel textFieldPanel = new JPanel();
        FlowLayout textFieldPanelLayout = new FlowLayout();
        textFieldPanel.setLayout(textFieldPanelLayout);

        labelSubID = new JLabel();
        labelSubID.setText("ID:");
        labelSubID.setFont(fLabelSubId);

        labelSubID.setVisible(false);

        textFieldPanel.add(labelSubID);

        textID = new JTextField();
        textID.setFont(fTextField);
        textID.setBackground(cTextFieldBG);
        textID.setMinimumSize(new Dimension(200, 50));
        textID.setPreferredSize(new Dimension(400, 50));
        textID.setMaximumSize(new Dimension(400, 50));

        textID.setVisible(false);

        textFieldPanel.add(textID);

        centerPanel.add(textFieldPanel);

        //===== TextField Dates Start =====

        JPanel panelDateStart = new JPanel();
        FlowLayout panelLayoutDateStart = new FlowLayout();
        panelDateStart.setLayout(panelLayoutDateStart);

        labelSubIDdateStart = new JLabel();
        labelSubIDdateStart.setText("Start date:");
        labelSubIDdateStart.setFont(fLabelSubId);

        labelSubIDdateStart.setVisible(false);

        panelDateStart.add(labelSubIDdateStart);

        textDateStart = new JTextField();
        textDateStart.setFont(fTextField);
        textDateStart.setBackground(cTextFieldBG);
        textDateStart.setMinimumSize(new Dimension(200, 50));
        textDateStart.setPreferredSize(new Dimension(400, 50));
        textDateStart.setMaximumSize(new Dimension(400, 50));

        textDateStart.setVisible(false);

        panelDateStart.add(textDateStart);

        centerPanel.add(panelDateStart);

        //===== TextField Dates End =====

        JPanel panelDateEnd = new JPanel();
        FlowLayout panelLayoutDateEnd = new FlowLayout();
        panelDateEnd.setLayout(panelLayoutDateEnd);

        labelSubIDdateEnd = new JLabel();
        labelSubIDdateEnd.setText("End date:");
        labelSubIDdateEnd.setFont(fLabelSubId);

        labelSubIDdateEnd.setVisible(false);

        panelDateEnd.add(labelSubIDdateEnd);

        textDateEnd = new JTextField();
        textDateEnd.setFont(fTextField);
        textDateEnd.setBackground(cTextFieldBG);
        textDateEnd.setMinimumSize(new Dimension(200, 50));
        textDateEnd.setPreferredSize(new Dimension(400, 50));
        textDateEnd.setMaximumSize(new Dimension(400, 50));

        textDateEnd.setVisible(false);

        panelDateEnd.add(textDateEnd);

        centerPanel.add(panelDateEnd);
    }

    private void changeVisibleList() {
        radioButtonList.setVisible(true);
        this.isListAll = true;
    }

    private void changeVisibleOptional() {
        textID.setVisible(false);
        labelSubID.setVisible(false);
        textDateEnd.setVisible(true);
        textDateStart.setVisible(true);
        labelSubIDdateStart.setVisible(true);
        labelSubIDdateEnd.setVisible(true);

        if(isHalfEntityList){
            this.labelSubIDdateStart.setText("ID Fair: ");
            this.labelSubIDdateEnd.setText("ID Client: ");
        }
    }

    private void changeVisibleLeft() {
        textID.setVisible(true);
        labelSubID.setVisible(true);
        textDateEnd.setVisible(false);
        textDateStart.setVisible(false);
        labelSubIDdateStart.setVisible(false);
        labelSubIDdateEnd.setVisible(false);
    }

    private void changeVisibleRight(){
        if(!this.isHalfEntity) {
            textID.setVisible(false);
            labelSubID.setVisible(false);
            textDateEnd.setVisible(false);
            textDateStart.setVisible(false);
            labelSubIDdateStart.setVisible(false);
            labelSubIDdateEnd.setVisible(false);
            this.isHalfEntity = false;
        }else{
            changeVisibleLeft();
        }

        if(isHalfEntityList){
            this.labelSubIDdateStart.setText("ID Fair: ");
            this.labelSubIDdateEnd.setText("ID Pavilion: ");
            labelSubIDdateStart.setVisible(true);
            labelSubIDdateEnd.setVisible(true);
            textDateStart.setVisible(true);
            textDateEnd.setVisible(true);
        }
    }

    private void viewLogicListener() throws Exception {
        switch (String.valueOf(comboBoxViews.getSelectedItem())){
            case "Fair":
                this.setVisible(false);
                if(radioButtonLeft.isSelected()) Controller.getInstance().execute(Event.SHOW_FAIR_INDIVIDUAL ,Integer.parseInt(textID.getText()));
                else if(radioButtonOptional.isSelected()) Controller.getInstance().execute(Event.SHOW_FAIR_LIST_DATES, new Tferia(null, null, Utilities.parseStringToDate(textDateStart.getText()), Utilities.parseStringToDate(textDateEnd.getText()), null));
                else Controller.getInstance().execute(Event.SHOW_FAIR_LIST ,null);
                break;
            case "Pavilion":
                this.setVisible(false);
                if(radioButtonLeft.isSelected()) Controller.getInstance().execute(Event.SHOW_PAVILION_INDIVIDUAL ,Integer.parseInt(textID.getText()));
                else Controller.getInstance().execute(Event.SHOW_PAVILION_LIST, null);
                break;
            case "Client":
                this.setVisible(false);
                if(radioButtonLeft.isSelected()) Controller.getInstance().execute(Event.SHOW_CLIENT_INDIVIDUAL ,Integer.parseInt(textID.getText()));
                else Controller.getInstance().execute(Event.SHOW_CLIENT_LIST, null);
                break;
            case "Assignation":
                this.setVisible(false);
                if(radioButtonLeft.isSelected()) Controller.getInstance().execute(Event.SHOW_ASSIGNATION_INDIVIDUAL,Integer.parseInt(textID.getText()));
                else if(radioButtonOptional.isSelected()) Controller.getInstance().execute(Event.SHOW_ASSIGNATION_PAVILION, Integer.parseInt(textID.getText()));
                else if(!isListAll) Controller.getInstance().execute(Event.SHOW_ASSIGANTION_FAIR, Integer.parseInt(textID.getText()));
                else Controller.getInstance().execute(Event.SHOW_ASSIGANTION_LIST ,null);
                break;
            case "Participation":
                this.setVisible(false);
                if(radioButtonLeft.isSelected()) Controller.getInstance().execute(Event.SHOW_PARTICIPATION_INDIVIDUAL ,Integer.parseInt(textID.getText()));
                else if(radioButtonOptional.isSelected()) Controller.getInstance().execute(Event.SHOW_PARTICIPATION_FAIR, Integer.parseInt(textID.getText()));
                else if(!isListAll) Controller.getInstance().execute(Event.SHOW_PARTICIPATION_CLIENT, Integer.parseInt(textID.getText()));
                else Controller.getInstance().execute(Event.SHOW_PARTICIPATION_LIST ,null);
                break;
            case "Stand":
                this.setVisible(false);
                if(radioButtonLeft.isSelected()) Controller.getInstance().execute(Event.SHOW_STAND_INDIVIDUAL ,Integer.parseInt(textID.getText()));
                else if(radioButtonOptional.isSelected()) Controller.getInstance().execute(Event.SHOW_STAND_ASSIGNATION, Integer.parseInt(textID.getText()));
                else if(!isListAll) Controller.getInstance().execute(Event.SHOW_STAND_PARTICIPATION ,Integer.parseInt(textID.getText()));
                else Controller.getInstance().execute(Event.SHOW_STAND_LIST ,null);
                break;

        }
    }

    @Override
    protected void setUpButtonBar(){
        super.setUpButtonBar();

        Dimension buttonDim = new Dimension(150, 80);

        //---- okButton ----
        JButton okButton = new JButton();
        okButton.setText("Show");
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

        this.buttonBar.add(okButton);

    }

    private void viewVisibleLogic(){
        comboBoxViews.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itemStateChanged(evt);
            }
        });
    }

    private void itemStateChanged(ActionEvent evt) {

        clearVisibles();

        switch (Objects.requireNonNull(comboBoxViews.getSelectedItem()).toString()){
            case "Fair":
                radioButtonPanel.setVisible(true);
                radioButtonOptional.setVisible(true);
                break;
            case "Pavilion":
                radioButtonPanel.setVisible(true);
                break;
            case "Stand":
                radioButtonPanel.setVisible(true);
                radioButtonOptional.setVisible(true);
                radioButtonRight.setText("List by Assignation");
                radioButtonOptional.setText("List by Participation");
                radioButtonList.setVisible(true);
                this.isStand = true;
                break;
            case "Client":
                radioButtonPanel.setVisible(true);
                break;
            case "Assignation":
                radioButtonPanel.setVisible(true);
                radioButtonList.setVisible(true);
                radioButtonRight.setText("List by pavilion id");
                radioButtonOptional.setText("List by fair id");
                radioButtonOptional.setVisible(true);
                this.isStand = true;
                break;
            case "Participation":
                radioButtonPanel.setVisible(true);
                radioButtonRight.setText("List by client id");
                radioButtonOptional.setText("List by fair id");
                radioButtonList.setVisible(true);
                radioButtonOptional.setVisible(true);
                this.isStand = true;
                break;
        }
    }

    private void clearVisibles() {
        textID.setVisible(false);
        labelSubID.setVisible(false);
        labelSubIDdateStart.setVisible(false);
        labelSubIDdateEnd.setVisible(false);
        textDateStart.setVisible(false);
        textDateEnd.setVisible(false);
        radioButtonPanel.setVisible(false);
        radioButtonOptional.setVisible(false);
        radioButtonList.setVisible(false);
        radioButtons.clearSelection();

        radioButtonLeft.setText("Individual");
        radioButtonRight.setText("List");
        labelSubIDdateEnd.setText("End date:");
        labelSubIDdateStart.setText("Start date:");

        this.isStand = false;
        this.isHalfEntityList = false;
        this.isHalfEntity = false;
        this.isListAll = false;
    }

    @Override
    public void update(int event, Object data) {

    }
}
