package Presentacion.views.viewsHalf;

import Negocio.Feria.Tferia;
import Presentacion.Controller;
import Presentacion.views.events.Event;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Objects;
import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.ColorUIResource;

public class ViewHalfShow extends JFrame {

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JPanel dialogPanel;
    private JPanel centerPanel;
    private JPanel buttonBar;
    private JLabel title;
    private JLabel labelSubID;
    private JLabel labelSubIDdateStart;
    private JLabel labelSubIDdateEnd;
    private JComboBox<String> comboBoxViews;
    private JRadioButton radioButtonLeft;
    private JRadioButton radioButtonRight;
    private JButton okButton;
    private JButton cancelButton;
    private JButton helpButton;
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


    public ViewHalfShow() {
        super("Show");

        initComponents();
        logicView();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void okButtonActionPerformed(ActionEvent e) {

    }

    private void cancelButtonActionPerformed(ActionEvent e) {
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

            comboBoxViews.addItem("Assignation");
            comboBoxViews.addItem("Fair");
            comboBoxViews.addItem("Participant");
            comboBoxViews.addItem("Pavilion");
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

        //---- radioButtonRight ----
        radioButtonRight = new JRadioButton();
        radioButtonRight.setText("List");
        radioButtonRight.setFont(fRadioButton);

        radioButtons = new ButtonGroup();
        radioButtons.add(radioButtonLeft);
        radioButtons.add(radioButtonRight);

        radioButtonPanel.add(radioButtonLeft);
        radioButtonPanel.add(radioButtonRight);

        radioButtonPanel.setVisible(false);
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

    private void setUpButtonBar(){

        Dimension buttonDim = new Dimension(150, 80);

        //---- cancelButton ----
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        cancelButton.setFont(fButton);
        cancelButton.setBackground(cCancelButton);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(buttonDim);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonActionPerformed(e);
            }
        });


        //---- helpButton ----
        helpButton = new JButton();
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
        okButton = new JButton();
        okButton.setText("Show");
        okButton.setFont(fButton);
        okButton.setBackground(cOkButton);
        okButton.setForeground(Color.WHITE);
        okButton.setPreferredSize(buttonDim);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButtonActionPerformed(e);
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

        URL path = getClass().getResource("Icon.png");
        if(path!=null){
            ImageIcon img = new ImageIcon(path);
            this.setIconImage(img.getImage());
        }


        //======== dialogPanel ========

        dialogPanel = new JPanel();
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

    private void logicView(){

        comboBoxViews.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                itemStateChanged(evt);
            }
        });

    }

    private void itemStateChanged(ActionEvent evt) {

        textID.setVisible(false);
        labelSubID.setVisible(false);
        labelSubIDdateStart.setVisible(false);
        labelSubIDdateEnd.setVisible(false);
        textDateStart.setVisible(false);
        textDateEnd.setVisible(false);
        radioButtonPanel.setVisible(false);

        switch (Objects.requireNonNull(comboBoxViews.getSelectedItem()).toString()){
            case "Fair":

                radioButtonPanel.setVisible(true);

                if(radioButtonLeft.isSelected()){
                    textID.setVisible(true);
                    labelSubID.setVisible(true);
                    labelSubIDdateStart.setVisible(false);
                    labelSubIDdateEnd.setVisible(false);
                    textDateStart.setVisible(false);
                    textDateEnd.setVisible(false);
                }else{
                    textID.setVisible(false);
                    labelSubID.setVisible(false);
                    labelSubIDdateStart.setVisible(true);
                    labelSubIDdateEnd.setVisible(true);
                    textDateStart.setVisible(true);
                    textDateEnd.setVisible(true);
                }

                break;
            case "Pabellon":



                break;
            case "Stand":

                // Cambiar texto

                break;
            case "Participante":
                break;
            case "Asignacion":

                //Cambiar texto

                break;
            case "Participacion":

                //Cambiar texto

                break;
        }
    }
}
