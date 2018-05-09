package Presentacion.Shows.individual;

import Controller.Controller;
import Negocio.Participante.Tparticipante;
import Presentacion.Events.Event;
import Presentacion.UI;
import Presentacion.UIStructureFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIViewClient extends UIStructureFrame {

    private String name;
    private String phone;
    //private String specialization;
    private String id;

    private JLabel title;
    private JPanel formContainer;

    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);

    public GUIViewClient(Tparticipante tclient) {
        super("");

        this.helpMessage = "<html><h1>SHOW INDIVIDUAL CLIENT HELP</h1>Here you have the possibility to" +
                "<b>See</b> the data of the specific <u>Client</u>" +
                " that you chose.</html>" +
                "";

        id = tclient.getId() + "";
        name = tclient.getName();
        phone =tclient.getPhone() + "";
        //specialization =tclient.getSpec() + "";

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
        title.setText("Client: " + id);
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
        JLabel phoneLabel = createLabel("Phone:");
        //JLabel specializationLabel = createLabel("Specialization");

        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;


        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(nameLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(phoneLabel, formCon);
        /*
        formCon.gridx = 0;
        formCon.gridy = 2;
        formPanel.add(specializationLabel, formCon);
        */


        JLabel nameField = createLabel(name);
        nameField.setMinimumSize(minDim);
        nameField.setPreferredSize(prefDim);
        nameField.setMaximumSize(maxDim);

        JLabel phoneField = createLabel(phone);
        phoneField.setMinimumSize(minDim);
        phoneField.setPreferredSize(prefDim);
        phoneField.setMaximumSize(new Dimension(maxDim.width, maxDim.height + 100));

        /*
        JLabel specializationField = createLabel(specialization);
        specializationField.setMinimumSize(minDim);
        specializationField.setPreferredSize(prefDim);
        specializationField.setMaximumSize(maxDim);
        */

        formCon.anchor = GridBagConstraints.WEST;

        formCon.insets = new Insets(20,10,20,0);


        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(nameField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(phoneField, formCon);
        /*
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(specializationField, formCon);*/
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
