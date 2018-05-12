package Presentacion.Shows.individual;

import Controller.Controller;
import Negocio.Feria.Tferia;
import Presentacion.Events.Event;
import Presentacion.UIStructureFrame;
import Presentacion.Utils.Utilities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIViewFair extends UIStructureFrame {

    private String id;
    private String name;
    private String description;
    private String iniDate;
    private String finDate;
    private boolean active;

    private JPanel formContainer;

    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);

    public GUIViewFair(Tferia tferia) {
        super("");

        this.helpMessage = "<html><h1>SHOW INDIVIDUAL FAIR HELP</h1>Here you have the possibility to" +
                "<b>See</b> the data of the specific <u>Fair</u> that you chose." +
                "</html>" +
                "";

        this.id = "" + tferia.getId();
        this.name = tferia.getName();
        this.description = tferia.getDescription();
        this.iniDate = Utilities.parseDateToString(tferia.getIniDate());
        this.finDate = Utilities.parseDateToString(tferia.getEndDate());
        this.active = tferia.getActive();

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    @Override
    protected void okButtonActionPerformed(ActionEvent e) throws Exception {

    }

    @Override
    protected void cancelButtonActionPerformed(ActionEvent e) throws Exception {
        this.setVisible(false);
        Controller.getInstance().execute(Event.SHOW_HALF, null);
    }

    @Override
    protected void setUpTitle(){
        title = new JLabel();
        title.setText("Fair: " + id);
        title.setFont(fTitle);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));
    }

    @Override
    protected void setUpCenter() {

    }

    private JLabel createLabel(String text){

        JLabel label = new JLabel(text, JLabel.RIGHT);
        label.setFont(fLabel);
        return label;
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


        JLabel nameLabel = createLabel("Name:");
        JLabel descLabel = createLabel("Description:");
        JLabel iniDateLabel = createLabel("Start Date:");
        JLabel finDateLabel = createLabel("End Date:");
        JLabel activeLabel = createLabel("Active");

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
        formCon.gridx = 0;
        formCon.gridy = 4;
        formPanel.add(activeLabel, formCon);

        JLabel nameField = createLabel(name);
        nameField.setMinimumSize(minDim);
        nameField.setPreferredSize(prefDim);
        nameField.setMaximumSize(maxDim);

        JTextArea descField = new JTextArea();
        descField.setMinimumSize(new Dimension(minDim.width, minDim.height + 100));
        descField.setPreferredSize(new Dimension(prefDim.width, prefDim.height + 100));
        descField.setMaximumSize(new Dimension(maxDim.width, maxDim.height + 100));
        descField.setLineWrap(true);
        descField.setEditable(false);
        descField.setFont(fLabel);
        descField.setBackground(this.getBackground());
        descField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        descField.setText(description);

        JLabel iniDateField = createLabel(iniDate);
        iniDateField.setMinimumSize(minDim);
        iniDateField.setPreferredSize(prefDim);
        iniDateField.setMaximumSize(maxDim);

        JLabel finDateField = createLabel(finDate);
        finDateField.setMinimumSize(minDim);
        finDateField.setPreferredSize(prefDim);
        finDateField.setMaximumSize(maxDim);

        JLabel activeField = createLabel(String.valueOf(active));
        activeField.setMinimumSize(minDim);
        activeField.setPreferredSize(prefDim);
        activeField.setMaximumSize(maxDim);

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
        formCon.gridx = 1;
        formCon.gridy = 4;
        formPanel.add(activeField, formCon);
        formContainer.add(formPanel);
    }

    @Override
    protected void initComponents() {
        super.initComponents();

        //----Form----
        setupForm();
        dialogPanel.add(formContainer, BorderLayout.CENTER);
    }

    @Override
    public void update(int event, Object data) {

    }
}
