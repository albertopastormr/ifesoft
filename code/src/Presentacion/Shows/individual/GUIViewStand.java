package Presentacion.Shows.individual;

import Controller.Controller;
import Negocio.Stand.Tstand;
import Presentacion.Events.Event;
import Presentacion.UIStructureFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIViewStand extends UIStructureFrame {

    private String metres;
    private String number;
    private String cost;
    private String id;
    private String assignation;
    private String participation;

    private JPanel formContainer;

    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);

    public GUIViewStand(Tstand tstand) {
        super("");
        this.helpMessage = "<html><h1>SHOW INDIVIDUAL STAND HELP</h1>Here you have the possibility to" +
                "<b>See</b> the data of the specific <u>Stand</u> that you chose" +
                "</html>" +
                "";

        metres = "" + tstand.getTotal_m2();
        number = "" + tstand.getNum_at_fair();
        cost = "" + tstand.getCost();
        id = "" + tstand.getId();
        assignation = "" + tstand.getAssignation_id();
        participation = "" + tstand.getParticipation_id();

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
        title.setText("Stand: " + id);
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

        JLabel metresLabel = createLabel("Metres:");
        JLabel numberLabel = createLabel("Number:");
        JLabel costLabel = createLabel("Cost:");
        JLabel assignationLabel = createLabel("Assignation:");
        JLabel participationLabel = createLabel("Participation");


        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;

        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(assignationLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(participationLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 2;
        formPanel.add(numberLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 3;
        formPanel.add(costLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 4;
        formPanel.add(metresLabel, formCon);

        JLabel assignationField = createLabel(assignation);
        assignationField.setMinimumSize(minDim);
        assignationField.setPreferredSize(prefDim);
        assignationField.setMaximumSize(maxDim);

        JLabel participationField = createLabel(participation);
        participationField.setMinimumSize(minDim);
        participationField.setPreferredSize(prefDim);
        participationField.setMaximumSize(maxDim);

        JLabel metresField = createLabel(metres);
        metresField.setMinimumSize(minDim);
        metresField.setPreferredSize(prefDim);
        metresField.setMaximumSize(maxDim);

        JLabel numberField = createLabel(number);
        numberField.setMinimumSize(minDim);
        numberField.setPreferredSize(prefDim);
        numberField.setMaximumSize(maxDim);

        JLabel costField = createLabel(cost);
        costField.setMinimumSize(minDim);
        costField.setPreferredSize(prefDim);
        costField.setMaximumSize(maxDim);

        formCon.anchor = GridBagConstraints.EAST;

        formCon.insets = new Insets(20,10,20,0);

        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(assignationField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(participationField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(metresField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 3;
        formPanel.add(numberField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 4;
        formPanel.add(costField, formCon);
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
