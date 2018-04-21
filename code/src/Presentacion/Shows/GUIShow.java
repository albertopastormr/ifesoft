package Presentacion.Shows;

import Negocio.Asignacion.Tasignacion;
import Negocio.Feria.Tferia;
import Negocio.Pabellon.Tpabellon;
import Negocio.Participacion.Tparticipacion;
import Negocio.Participante.Tparticipante;
import Negocio.Stand.Tstand;
import Controller.Controller;
import Presentacion.UI;
import Presentacion.Utils.Utilities;
import Presentacion.Events.Event;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.ColorUIResource;

public class GUIShow extends JFrame implements UI {

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JPanel centerPanel;
    private JPanel buttonBar;
    private JLabel title;
    private JLabel labelSubID;
    private JLabel labelSubIDdateStart;
    private JLabel labelSubIDdateEnd;
    private JComboBox<String> comboBoxViews;
    private JRadioButton radioButtonLeft;
    private JRadioButton radioButtonRight;
    private JRadioButton radioButtonOptional;
    private JTextField textID;
    private JTextField textDateStart;
    private JTextField textDateEnd;
    private JPanel radioButtonPanel;
    private ButtonGroup radioButtons;

    private Font fComboBox = new Font(Font.DIALOG, Font.PLAIN, 40);
    private Font fTitle  = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fRadioButton = new Font(Font.DIALOG, Font.PLAIN, 35);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fLabelSubId = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fTextField = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cHelpButton = new Color(66,35,146);
    private Color cCancelButton = new Color(146, 35, 59);
    private Color cOkButton = new Color(26, 184, 59);
    private Color cComboBoxActive = new Color(207, 216, 220);
    private Color cComboBoxInactive = new Color(187, 196, 200);
    private Color cComboBoxFont = new Color(84, 91, 94);
    private Color cComboBoxSelectedFont = new Color(52, 56, 58);
    private Color cTextFieldBG = new Color(243,243,243);

    private boolean isHalfEntity;
    private boolean isHalfEntityList;

    public GUIShow() {

        this.isHalfEntity = false;
        this.isHalfEntityList = false;

        initComponents();
        viewVisibleLogic();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void okButtonActionPerformed(ActionEvent e) throws Exception {
        viewLogicListener();
    }


    private void cancelButtonActionPerformed(ActionEvent e) throws Exception {
        this.setVisible(false);
        Controller.getInstance().execute(Event.HOME, null);
    }

    private void helpButtonActionPerformed(ActionEvent e) {

    }

    private void setUpTitle(){
        title = new JLabel();
        title .setText("View");
        title .setFont(fTitle);
        title .setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));
    }

    private void setUpCenter(){

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
            comboBoxViews.addItem("Assignation");
            comboBoxViews.addItem("Fair");
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
            }
        });

        radioButtonOptional.setVisible(false);

        radioButtons = new ButtonGroup();
        radioButtons.add(radioButtonLeft);
        radioButtons.add(radioButtonRight);
        radioButtons.add(radioButtonOptional);

        radioButtonPanel.add(radioButtonLeft);
        radioButtonPanel.add(radioButtonRight);
        radioButtonPanel.add(radioButtonOptional);

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

        textFieldPanel.add(labelSubIDdateStart);

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
                if(radioButtonLeft.isSelected()) Controller.getInstance().execute(Event.SHOW_FAIR_INDIVIDUAL ,new Tferia(Integer.parseInt(textID.getText()) , null, null, null, null, null));
                else if(radioButtonOptional.isSelected()) Controller.getInstance().execute(Event.SHOW_FAIR_LIST_DATES, new Tferia(null, null, Utilities.parseStringToDate(textDateStart.getText()), Utilities.parseStringToDate(textDateEnd.getText()), null));
                else Controller.getInstance().execute(Event.SHOW_FAIR_LIST ,null);
                break;
            case "Pavilion":
                this.setVisible(false);
                if(radioButtonLeft.isSelected()) Controller.getInstance().execute(Event.SHOW_PAVILION_INDIVIDUAL ,new Tpabellon(Integer.parseInt(textID.getText()) , -1, -1, null));
                else Controller.getInstance().execute(Event.SHOW_PAVILION_LIST, null);
                break;
            case "Stand":
                this.setVisible(false);
                if(radioButtonLeft.isSelected()) Controller.getInstance().execute(Event.SHOW_STAND_INDIVIDUAL ,new Tstand(Integer.parseInt(textID.getText()) , -1, -1, -1, -1, null));
                else Controller.getInstance().execute(Event.SHOW_STAND_LIST, null);
                break;
            case "Client":
                this.setVisible(false);
                if(radioButtonLeft.isSelected()) Controller.getInstance().execute(Event.SHOW_CLIENT_INDIVIDUAL ,new Tparticipante(Integer.parseInt(textID.getText()) , null, -1, null));
                else Controller.getInstance().execute(Event.SHOW_CLIENT_LIST, null);
                break;
            case "Assignation":
                this.setVisible(false);
                if(radioButtonLeft.isSelected()) Controller.getInstance().execute(Event.SHOW_ASSIGANTION_FAIR ,new Tasignacion(Integer.parseInt(textID.getText()) , -1, -1 , -1, null));
                else Controller.getInstance().execute(Event.SHOW_ASSIGNATION_PAVILION, new Tasignacion(-1 , Integer.parseInt(textID.getText()), -1 , -1, null));
                break;
            case "Participation":
                this.setVisible(false);
                if(radioButtonLeft.isSelected()) Controller.getInstance().execute(Event.SHOW_PARTICIPATION_FAIR ,new Tparticipacion(Integer.parseInt(textID.getText()) , -1, -1 , null));
                else Controller.getInstance().execute(Event.SHOW_PARTICIPACION_CLIENT, new Tparticipacion(-1 , Integer.parseInt(textID.getText()), -1 , null));

                break;

        }
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
                    cancelButtonActionPerformed(e);
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
                helpButtonActionPerformed(e);
            }
        });


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

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //======== this ========
        Container contentPane = getContentPane(); 
        contentPane.setLayout(new BorderLayout());

        ImageIcon img = new ImageIcon("Resources//Icon.png");
        this.setIconImage(img.getImage());


        //======== dialogPanel ========

        JPanel dialogPanel = new JPanel();
        dialogPanel.setBorder(new LineBorder(Color.BLUE));
        dialogPanel.setBorder(new EmptyBorder(50, 50, 80, 50));
        this.setMinimumSize(minScreenSize);

        // JFormDesigner evaluation mark
        dialogPanel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font(Font.DIALOG, java.awt.Font.BOLD, 12),
                        java.awt.Color.red), dialogPanel.getBorder()));
        dialogPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("border".equals(e.getPropertyName())) throw new RuntimeException();
            }
        });

        dialogPanel.setLayout(new BorderLayout());

        //======== Title ========
        setUpTitle();
        dialogPanel.add(title, BorderLayout.PAGE_START);

        //======== contentPanel ========

        setUpCenter();
        dialogPanel.add(centerPanel, BorderLayout.CENTER);

        //========= ButtonBar ========

        setUpButtonBar();
        dialogPanel.add(buttonBar, BorderLayout.PAGE_END);

        contentPane.add(dialogPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
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
                this.isHalfEntityList = true;
                break;
            case "Client":
                radioButtonPanel.setVisible(true);
                break;
            case "Assignation":
                radioButtonPanel.setVisible(true);
                radioButtonRight.setText("List by pavilion id");
                radioButtonLeft.setText("List by fair id");
                this.isHalfEntity = true;
                break;
            case "Participation":
                radioButtonPanel.setVisible(true);
                radioButtonRight.setText("List by client id");
                radioButtonLeft.setText("List by fair id");
                this.isHalfEntity = true;
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
        radioButtons.clearSelection();

        radioButtonLeft.setText("Individual");
        radioButtonRight.setText("List");
        labelSubIDdateEnd.setText("End date:");
        labelSubIDdateStart.setText("Start date:");

        this.isHalfEntity = false;
        this.isHalfEntityList = false;
    }

    @Override
    public void update(int event, Object data) {

    }
}
