package Presentacion.Create_Modify.Create;

import Controller.Controller;
import Presentacion.Events.Event;
import Presentacion.Events.EventGUI;
import Presentacion.UI;
import Presentacion.UIimp;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.ColorUIResource;

public class GUICreate extends UIimp {

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JPanel centerPanel;
    private JPanel buttonBar;
    private JLabel title;
    //private JLabel labelSubID;
    private JComboBox<String> comboBoxCreate;
    //private JTextField textField;

    private Font fComboBox = new Font(Font.DIALOG, Font.PLAIN, 40);
    private Font fTitle  = new Font(Font.MONOSPACED, Font.BOLD, 80);
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

    public GUICreate() {
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    @Override
    public void update(int event, Object data) {
        switch (event){
            case EventGUI.UPDATE_CREATE_FERIA_OK:

                break;

        }
    }

    private void nextButtonActionPerformed() throws Exception {

        switch (String.valueOf(comboBoxCreate.getSelectedItem())){
            case "Fair":
                this.setVisible(false);
                Controller.getInstance().execute(Event.INSERT_FORM_FERIA, null);
                break;
            case "Pavilion":
                this.setVisible(false);
                Controller.getInstance().execute(Event.INSERT_FORM_PABELLON, null);
                break;
            case "Stand":
                this.setVisible(false);
                Controller.getInstance().execute(Event.INSERT_FORM_STAND, null);
                break;
            case "Client":
                this.setVisible(false);
                Controller.getInstance().execute(Event.INSERT_FORM_PARTICIPANTE, null);
                break;
            case "Assignation":
                this.setVisible(false);
                Controller.getInstance().execute(Event.INSERT_FORM_ASIGNACION, null);
                break;
            case "Participation":
                this.setVisible(false);
                Controller.getInstance().execute(Event.INSERT_FORM_PARTICIPANTE, null);
                break;

        }


    }

    private void backButtonActionPerformed() throws Exception {
        this.setVisible(false);
        Controller.getInstance().execute(Event.HOME, null);
        // Volver a mostrar la primera
    }

    private void helpButtonHalfCreateActionPerformed() {
    }


    private void setUpTitle(){

        title = new JLabel();
        title .setText("Create_Modify");
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


        comboBoxCreate = new JComboBox<>();
        comboBoxCreate.getEditor().getEditorComponent().setBackground(cComboBoxActive);
        comboBoxCreate.setFont(fComboBox);
        comboBoxCreate.setForeground(cComboBoxFont);
        comboBoxCreate.setMinimumSize(new Dimension(200, 50));
        comboBoxCreate.setMaximumSize(new Dimension(800, 50));

        comboBoxCreate.addItem("Assignation");
        comboBoxCreate.addItem("Fair");
        comboBoxCreate.addItem("Client");
        comboBoxCreate.addItem("Pavilion");
        comboBoxCreate.addItem("Participation");
        comboBoxCreate.addItem("Stand");

        comboBoxCreate.setBorder(BorderFactory.createEmptyBorder(0,0, 20, 0));
        centerPanel.add(comboBoxCreate);
    }

    private void setUpButtonBar(){

        Dimension buttonDim = new Dimension(150, 80);

        //---- cancelButton ----
        JButton backButton = new JButton();
        backButton.setText("Back");
        backButton.setFont(fButton);
        backButton.setBackground(cCancelButton);
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(buttonDim);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    backButtonActionPerformed();
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
                helpButtonHalfCreateActionPerformed();
            }
        });


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
                    nextButtonActionPerformed();
                } catch (Exception e1){
                    new PanelProblemUser(e1.getMessage());
                }
            }
        });

        buttonBar = new JPanel();
        FlowLayout layout = new FlowLayout();
        layout.setHgap(25);
        buttonBar.setLayout(layout);
        buttonBar.add(backButton);
        buttonBar.add(helpButton);
        buttonBar.add(Box.createHorizontalStrut(500));
        buttonBar.add(nextButton);



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
}
