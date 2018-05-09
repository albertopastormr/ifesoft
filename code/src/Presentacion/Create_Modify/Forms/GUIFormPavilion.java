package Presentacion.Create_Modify.Forms;

import Negocio.Pabellon.Tpabellon;
import Controller.Controller;
import Presentacion.Events.Event;
import Presentacion.UIStructureFrame;
import Presentacion.Utils.ActionHelp;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIFormPavilion extends UIStructureFrame {

    private int idPavilion;
    private String capacity;
    private String m2tot;

    private JLabel title;
    private JPanel formContainer;
    private JTextField capacityField;
    private JTextField m2totField;

    private boolean mod;

    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fField = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cField = new Color(243,243,243);
    private Color cOkButton = new Color(26, 184, 59);



    public GUIFormPavilion() {
        super("");

        this.helpMessage = "<html><h1>PAVILION'S FORM HELP </1>Here you can <b>insert</b> <u>Pavilion</u>'s data" +
                " just by inserting them into the text areas," +
                " then click <b>'Next'</b> to continue or <b>'Cancel'</b>" +
                " to go back." +
                "In the first field you have to define the total capacity of the pavillion," +
                "in the second one the total square meters that can be used for company stands and" +
                "in the last one the square meters not yet occupied and therefore available. " +
                "</html>";

        mod = false;
        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    public GUIFormPavilion(Tpabellon pavilion) {
        super("");

        this.helpMessage = "<html><h1>PAVILION'S FORM HELP </1>Here you can <b>insert</b> <u>Pavilion</u>'s data" +
                " just by inserting them into the text areas," +
                " then click <b>'Next'</b> to continue or <b>'Cancel'</b>" +
                " to go back." +
                "In the first field you have to define the total capacity of the pavillion," +
                "in the second one the total square meters that can be used for company stands and" +
                "in the last one the square meters not yet occupied and therefore available. " +
                "</html>";

        mod = true;

        this.idPavilion = pavilion.getId();

        capacity = "" + pavilion.getCapacity();
        m2tot = "" + pavilion.getTotal_m2();

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
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

        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;

        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(aforoLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(m2totLabel, formCon);

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
        formContainer.add(formPanel);
    }

    @Override
    protected void setUpButtonBar(){
        super.setUpButtonBar();

        Dimension buttonDim = new Dimension(150, 80);

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
                    okButtonActionPerformed(e);
                }catch (NumberFormatException e2){
                    new PanelProblemUser("Inserte caracteres validos.");
                } catch (Exception e1){
                    new PanelProblemUser(e1.getMessage());
                }
            }
        });

        buttonBar.add(okButton);
    }

    @Override
    protected void okButtonActionPerformed(ActionEvent e) throws Exception {
        setVisible(false);
        try {
            String capacity = capacityField.getText();
            String m2_total = m2totField.getText();

            if (!mod)  Controller.getInstance().execute(Event.INSERT_PAVILION, new Tpabellon(Integer.parseInt(capacity), Integer.parseInt(m2_total), true));
            else Controller.getInstance().execute(Event.MODIFY_PAVILION, new Tpabellon(idPavilion ,Integer.parseInt(capacity), Integer.parseInt(m2_total), true));

        }catch (NumberFormatException i){
            throw new NumberFormatException("You have to insert a valid number in the fields." + ActionHelp.strHelpBasic());
        }
    }

    @Override
    protected void cancelButtonActionPerformed(ActionEvent e) throws Exception {
        this.setVisible(false);
        if (!mod) Controller.getInstance().execute(Event.CREATE_HALF, null);
        else Controller.getInstance().execute(Event.MODIFY_HALF, null);
    }

    @Override
    protected void setUpTitle() {
        title = new JLabel();
        if(mod)
            title.setText("Modify Pavilion");
        else
            title.setText("Create_Modify Pavilion");
        title.setFont(fTitle);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));
    }

    @Override
    protected void setUpCenter() {

    }

    @Override
    protected void initComponents() {
        super.initComponents();

        this.setMinimumSize(minScreenSize);

        //----Form----
        setupForm();
        dialogPanel.add(formContainer, BorderLayout.CENTER);
    }

    @Override
    public void update(int event, Object data) {
        JOptionPane.showMessageDialog(null, "The Pavilion has been created successfully");
        JOptionPane.showMessageDialog(null, "A problem in the creation process occurred, insert Pavilion's data another time please", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
