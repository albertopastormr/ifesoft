package Presentacion.views.forms;

import Negocio.Feria.Tferia;
import Presentacion.Controller;
import Presentacion.utils.Utilities;
import Presentacion.views.events.Event;
import Presentacion.UIimp;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.ldap.Control;
import javax.swing.*;
import javax.swing.border.*;

public class ViewsFormFeria extends JFrame {

    private String name = "";
    private String description = "";
    private String iniDate = "";
    private String finDate = "";

    private boolean mod;

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JPanel dialogPanel;
    private JLabel title;
    private JPanel formPanel;
    private JPanel formContainer;
    private JLabel nameLabel;
    private JLabel descLabel;
    private JLabel iniDateLabel;
    private JLabel finDateLabel;
    private JTextField nameField;
    private JTextField descField;
    private JTextField iniDateField;
    private JTextField finDateField;
    private JButton okButton;
    private JButton cancelButton;
    private JButton helpButton;
    private JPanel buttonBar;


    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fField = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cField = new Color(243,243,243);
    private Color cHelpButton = new Color(66,35,146);
    private Color cCancelButton = new Color(146, 35, 59);
    private Color cOkButton = new Color(26, 184, 59);


    public ViewsFormFeria() {

        mod = false;
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);

    }

    public ViewsFormFeria(Tferia tferia) {

        mod = true;

        name = tferia.getName();
        description = tferia.getDescription();
        iniDate = Utilities.parseDateToString(tferia.getIniDate());
        finDate = Utilities.parseDateToString(tferia.getEndDate());

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void createButtonFormActionPerformed() {
        this.setVisible(false);
        String name = nameField.getText();
        String descrip = descField.getText();
        String dateStart = iniDateField.getText();
        String dateEnd = finDateField.getText();

        if(!mod) Controller.getInstance().execute(Event.INSERT_FAIR, new Tferia(name, descrip, Utilities.parseStringToDate(dateStart), Utilities.parseStringToDate(dateEnd)));
        else Controller.getInstance().execute(Event.MODIFY_FAIR, new Tferia(name, descrip, Utilities.parseStringToDate(dateStart), Utilities.parseStringToDate(dateEnd)));

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
            title.setText("Modify Fair");
        else
            title.setText("Create Fair");
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


        nameLabel = createLabel("Name:");
        descLabel = createLabel("Description:");
        iniDateLabel = createLabel("Start Date:");
        finDateLabel = createLabel("End Date:");

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
