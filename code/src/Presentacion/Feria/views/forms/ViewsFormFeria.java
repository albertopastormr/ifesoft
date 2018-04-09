package Presentacion.Feria.views.forms;

import Negocio.Feria.Tferia;
import Presentacion.Feria.views.events.Event;
import Presentacion.Feria.UIimp;

import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;

public class ViewsFormFeria extends JFrame {

    private String name = null;
    private String description = null;
    private String iniDate = null;
    private String finDate = null;

    private boolean mod;
    private Tferia tferiaModify;

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JPanel dialogPanel;
    private JLabel title;
    private JPanel formPanel;
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

    private int columnLength = 0;

    public ViewsFormFeria() {

        mod = false;
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    public ViewsFormFeria(Tferia tferia) {

        mod = true;
        initComponents();

        this.tferiaModify = tferia;

        initComponentsModify();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void initComponentsModify() {

    }

    private void createButtonFormActionPerformed() {
       /* this.setVisible(false);
        String name = textFieldNombre.getText();
        String descrip = textFieldNombre.getText();
        String a_fecha_ini = textFieldFechaIni.getText();
        String m_fecha_ini = textFieldFechaFin.getText();
        String d_fecha_ini = textFieldFechaFin.getText();
        String fecha_fin = textFieldFechaFin.getText();

        Tferia tFeria = new Tferia(name, descrip, new Date(Integer.parseInt(a_fecha_ini), Integer.parseInt(m_fecha_ini), Integer.parseInt(d_fecha_ini)), new Date(Integer.parseInt(fecha_fin)));

        if (isOptionCreate)  UIimp.getInstance().execute(Event.INSERT_FAIR, tFeria);
        else UIimp.getInstance().execute(Event.MODIFY_FAIR ,tFeria);*/
    }

    private void cancelButtonStateChanged() {
        /*this.setVisible(false);
        if (isOptionCreate) UIimp.getInstance().execute(Event.CREATE_HALF, null);
        else UIimp.getInstance().execute(Event.MODIFY_HALF, null);*/
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

        descField = setupTextField();
        descField.setMinimumSize(new Dimension(minDim.width, minDim.height + 100));
        descField.setPreferredSize(new Dimension(prefDim.width, prefDim.height + 100));
        descField.setMaximumSize(new Dimension(maxDim.width, maxDim.height + 100));

        iniDateField = setupTextField();
        iniDateField.setMinimumSize(minDim);
        iniDateField.setPreferredSize(prefDim);
        iniDateField.setMaximumSize(maxDim);

        finDateField = setupTextField();
        finDateField.setMinimumSize(minDim);
        finDateField.setPreferredSize(prefDim);
        finDateField.setMaximumSize(maxDim);

        formCon.anchor = GridBagConstraints.WEST;

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
            dialogPanel.add(formPanel, BorderLayout.CENTER);
        //----Buttons----
            setUpButtonBar();
            dialogPanel.add(buttonBar, BorderLayout.PAGE_END);

        contentPane.add(dialogPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }
}
