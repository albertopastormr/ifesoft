package Presentacion.Create_Modify.Forms;

import Negocio.Pabellon.Tpabellon;
import Controller.Controller;
import Presentacion.Events.Event;
import Presentacion.UI;
import Presentacion.Utils.ActionHelp;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIFormPavilion extends JFrame implements UI {

    private String capacity;
    private String m2tot;
    private String m2utils;

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JLabel title;
    private JPanel formContainer;
    private JTextField capacityField;
    private JTextField m2totField;
    private JTextField m2utilsField;
    private JPanel buttonBar;

    private boolean mod;

    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fField = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cField = new Color(243,243,243);
    private Color cHelpButton = new Color(66,35,146);
    private Color cCancelButton = new Color(146, 35, 59);
    private Color cOkButton = new Color(26, 184, 59);

    public GUIFormPavilion() {
        super("Pavilion");
        mod = false;
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    public GUIFormPavilion(Tpabellon pavilion) {
        super("Pavilion");
        mod = true;

        capacity = "" + pavilion.getCapacity();
        m2tot = "" + pavilion.getTotal_m2();
        m2utils = "" + pavilion.getUtil_m2();

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void createButtonFormActionPerformed() throws Exception {
        setVisible(false);
        try {
       String capacity = capacityField.getText();
        String m2_utils = m2utilsField.getText();
        String m2_total = m2totField.getText();
        Tpabellon tPabellon = new Tpabellon(Integer.parseInt(capacity), Integer.parseInt(m2_utils), Integer.parseInt(m2_total), true);


            if (!mod)  Controller.getInstance().execute(Event.INSERT_PAVILION, tPabellon);
            else Controller.getInstance().execute(Event.MODIFY_PAVILION, tPabellon);

        }catch (NumberFormatException e){
            throw new NumberFormatException("Debes insertar un numero valido en los campos." + ActionHelp.strHelpBasic());
        }
    }

    private void cancelButtonStateChanged() throws Exception {
        this.setVisible(false);
        if (!mod) Controller.getInstance().execute(Event.CREATE_HALF, null);
        else Controller.getInstance().execute(Event.MODIFY_HALF, null);
    }

    private void helpButtonActionPerformed() {

    }

    private void setupTitle(){
        title = new JLabel();
        if(mod)
            title.setText("Modify Pavilion");
        else
            title.setText("Create_Modify Pavilion");
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


        JLabel aforoLabel = createLabel("Capacity:");
        JLabel m2totLabel = createLabel("Total square-metres:");
        JLabel m2utilesLabel = createLabel("Useful square-metres:");

        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;

        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(aforoLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(m2totLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 2;
        formPanel.add(m2utilesLabel, formCon);

        capacityField = setupTextField();
        capacityField.setMinimumSize(minDim);
        capacityField.setPreferredSize(prefDim);
        capacityField.setMaximumSize(maxDim);
        capacityField.setText(capacity);

        m2totField = setupTextField();
        m2totField.setMinimumSize(minDim);
        m2totField.setPreferredSize(prefDim);
        m2totField.setMaximumSize(maxDim);
        m2totField.setText(m2tot);

        m2utilsField = setupTextField();
        m2utilsField.setMinimumSize(minDim);
        m2utilsField.setPreferredSize(prefDim);
        m2utilsField.setMaximumSize(maxDim);
        m2utilsField.setText(m2utils);

        formCon.anchor = GridBagConstraints.WEST;

        formCon.insets = new Insets(20,10,20,0);

        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(capacityField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(m2totField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(m2utilsField, formCon);
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

    }
}
