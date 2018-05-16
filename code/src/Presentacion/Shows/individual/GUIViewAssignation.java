package Presentacion.Shows.individual;

import Controller.Controller;
import Negocio.Asignacion.Tasignacion;
import Presentacion.Events.Event;
import Presentacion.UIStructureFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIViewAssignation extends UIStructureFrame {

    private int id;
    private int idFair;
    private int idPavilion;
    private int usedMetres;
    private int totalMetres;
    private boolean activo;

    private JPanel formContainer;

    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.MONOSPACED, Font.PLAIN, 30);

    public GUIViewAssignation(Tasignacion assignation) {
        super("");

        this.helpMessage = "<html><h1>SHOW INDIVIDUAL CLIENT HELP</h1>Here you have the possibility to" +
                "<b>See</b> the data of the specific <u>Client</u>" +
                " that you chose.</html>" +
                "";

        id = assignation.getId();
        idFair = assignation.getFair_id();
        idPavilion = assignation.getPavilion_id();
        usedMetres = assignation.getUsed_m2();
        totalMetres = assignation.getTotal_m2();
        activo = assignation.getActive();

        initComponents();

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
    protected void setUpTitle() {
        title = new JLabel();
        title.setText("Assignation: " + id);
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

        JLabel idLabel = createLabel("Assignation ID:");
        JLabel idFairLabel = createLabel("Fair ID:");
        JLabel idPavilionLabel = createLabel("Pavilion ID:");
        JLabel usedMetresLabel = createLabel("Used square metres:");
        JLabel totalMetresLabel = createLabel("Total square metres:");
        JLabel activelabel = createLabel("Active:");

        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;

        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(idLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(idFairLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 2;
        formPanel.add(idPavilionLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 3;
        formPanel.add(usedMetresLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 4;
        formPanel.add(totalMetresLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 5;
        formPanel.add(activelabel, formCon);

        JLabel idField = createLabel(id + "");
        idField.setMinimumSize(minDim);
        idField.setPreferredSize(prefDim);
        idField.setMaximumSize(maxDim);

        JLabel idFairField = createLabel(idFair + "");
        idFairField.setMinimumSize(minDim);
        idFairField.setPreferredSize(prefDim);
        idFairField.setMaximumSize(maxDim);

        JLabel idPavilionField = createLabel(idPavilion + "");
        idPavilionField.setMinimumSize(minDim);
        idPavilionField.setPreferredSize(prefDim);
        idPavilionField.setMaximumSize(maxDim);

        JLabel usedMetresField = createLabel(usedMetres + "");
        usedMetresField.setMinimumSize(minDim);
        usedMetresField.setPreferredSize(prefDim);
        usedMetresField.setMaximumSize(maxDim);

        JLabel totaleMetresField = createLabel(totalMetres + "");
        totaleMetresField.setMinimumSize(minDim);
        totaleMetresField.setPreferredSize(prefDim);
        totaleMetresField.setMaximumSize(maxDim);

        JLabel activeField = createLabel(activo + "");
        activeField.setMinimumSize(minDim);
        activeField.setPreferredSize(prefDim);
        activeField.setMaximumSize(maxDim);

        formCon.anchor = GridBagConstraints.WEST;

        formCon.insets = new Insets(20,10,20,0);

        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(idField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(idFairField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(idPavilionField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 3;
        formPanel.add(usedMetresField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 4;
        formPanel.add(totaleMetresField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 5;
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
        if(data != null) JOptionPane.showMessageDialog(null,"Here you can see the Assignation's data");
        else JOptionPane.showMessageDialog(null, "A problem in the 'show' process occurred, insert Assignation's data another time please", "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
