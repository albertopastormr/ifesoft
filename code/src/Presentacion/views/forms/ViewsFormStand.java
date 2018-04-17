package Presentacion.views.forms;

import Negocio.Stand.Tstand;
import Presentacion.Controller;
import Presentacion.UIimp;
import Presentacion.views.events.Event;
import Presentacion.views.optionsPanel.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ViewsFormStand extends JFrame {

    private String metres = "";
    private String number = "";
    private String cost = "";

    private boolean mod;

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JPanel dialogPanel;
    private JLabel title;
    private JPanel formPanel;
    private JPanel formContainer;
    private JLabel metresLabel;
    private JLabel numberLabel;
    private JLabel costLabel;
    private JTextField metresField;
    private JTextField numberField;
    private JTextField costField;
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

    public ViewsFormStand() {
        mod = false;
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    public ViewsFormStand(Tstand tstand) {
        mod = true;

        metres = "" + tstand.getTotal_m2();
        number = "" + tstand.getNum_at_fair();
        cost = "" + tstand.getCost();

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }


    private void createButtonFormActionPerformed() throws Exception {
        setVisible(false);
        String coste = costField.getText();
        String m_usados = metresField.getText();
        String numero = numberField.getText();
        Tstand tStand = new Tstand(Integer.parseInt(coste), Integer.parseInt(m_usados), Integer.parseInt(numero), true);

        if (!mod) Controller.getInstance().execute(Event.INSERT_STAND, tStand);
        else Controller.getInstance().execute(Event.MODIFY_STAND,tStand);
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
            title.setText("Modify Stand");
        else
            title.setText("Create Stand");
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


        metresLabel = createLabel("Metres:");
        numberLabel = createLabel("Number:");
        costLabel = createLabel("Cost:");

        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;

        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(metresLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(numberLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 2;
        formPanel.add(costLabel, formCon);

        metresField = setupTextField();
        metresField.setMinimumSize(minDim);
        metresField.setPreferredSize(prefDim);
        metresField.setMaximumSize(maxDim);
        metresField.setText(metres);

        numberField = setupTextField();
        numberField.setMinimumSize(minDim);
        numberField.setPreferredSize(prefDim);
        numberField.setMaximumSize(new Dimension(maxDim.width, maxDim.height + 100));
        numberField.setText(number);

        costField = setupTextField();
        costField.setMinimumSize(minDim);
        costField.setPreferredSize(prefDim);
        costField.setMaximumSize(maxDim);
        costField.setText(cost);

        formCon.anchor = GridBagConstraints.WEST;

        formCon.insets = new Insets(20,10,20,0);

        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(metresField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(numberField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(costField, formCon);
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
                try {
                    cancelButtonStateChanged();
                } catch (Exception e1){
                    new PanelProblemUser(e1.getMessage());
                }
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