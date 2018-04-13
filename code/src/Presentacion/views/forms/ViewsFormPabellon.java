package Presentacion.views.forms;

import Negocio.Pabellon.Tpabellon;
import Presentacion.Controller;
import Presentacion.UIimp;
import Presentacion.views.events.Event;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.*;

public class ViewsFormPabellon extends JFrame {

    private String aforo = "";
    private String m2tot = "";
    private String m2utiles = "";

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JLabel title;
    private JPanel formContainer;
    private JPanel formPanel;
    private JPanel dialogPanel;
    private JLabel aforoLabel;
    private JLabel m2totLabel;
    private JLabel m2utilesLabel;
    private JTextField aforoField;
    private JTextField m2totField;
    private JTextField m2utilesField;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    private JButton helpButton;

    private boolean mod;

    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fField = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cField = new Color(243,243,243);
    private Color cHelpButton = new Color(66,35,146);
    private Color cCancelButton = new Color(146, 35, 59);
    private Color cOkButton = new Color(26, 184, 59);

    public ViewsFormPabellon() {
        mod = false;
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    public ViewsFormPabellon(Tpabellon tpabellon) {
        mod = true;

        aforo = "" + tpabellon.getCapacity();
        m2tot = "" + tpabellon.getTotal_m2();
        m2utiles = "" + tpabellon.getUtil_m2();

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void createButtonFormActionPerformed() {
        setVisible(false);
        String aforo = aforoField.getText();
        String m2_utiles = m2utilesField.getText();
        String m2_totales = m2totField.getText();
        Tpabellon tPabellon = new Tpabellon(Integer.parseInt(aforo), Integer.parseInt(m2_utiles), Integer.parseInt(m2_totales), true);

        if (!mod)  Controller.getInstance().execute(Event.INSERT_PAVILION, tPabellon);
        else Controller.getInstance().execute(Event.MODIFY_PAVILION, tPabellon);
    }

    private void cancelButtonStateChanged() {
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
            title.setText("Create Pavilion");
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

        formPanel = new JPanel();
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


        aforoLabel = createLabel("Capacity:");
        m2totLabel= createLabel("Total square-metres:");
        m2utilesLabel = createLabel("Useful square-metres:");

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

        aforoField = setupTextField();
        aforoField.setMinimumSize(minDim);
        aforoField.setPreferredSize(prefDim);
        aforoField.setMaximumSize(maxDim);
        aforoField.setText(aforo);

        m2totField = setupTextField();
        m2totField.setMinimumSize(minDim);
        m2totField.setPreferredSize(prefDim);
        m2totField.setMaximumSize(maxDim);
        m2totField.setText(m2tot);

        m2utilesField = setupTextField();
        m2utilesField.setMinimumSize(minDim);
        m2utilesField.setPreferredSize(prefDim);
        m2utilesField.setMaximumSize(maxDim);
        m2utilesField.setText(m2utiles);

        formCon.anchor = GridBagConstraints.WEST;

        formCon.insets = new Insets(20,10,20,0);

        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(aforoField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(m2totField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(m2utilesField, formCon);
        formContainer.add(formPanel);
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
                cancelButtonStateChanged();
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
                helpButtonActionPerformed();
            }
        });


        //---- okButton ----
        okButton = new JButton();
        okButton.setText("Add");
        okButton.setFont(fButton);
        okButton.setBackground(cOkButton);
        okButton.setForeground(Color.WHITE);
        okButton.setPreferredSize(buttonDim);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createButtonFormActionPerformed();
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

        dialogPanel = new JPanel();
        BorderLayout dialogLayout = new BorderLayout();
        dialogPanel.setLayout(dialogLayout);
        dialogPanel.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));


        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

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
}
