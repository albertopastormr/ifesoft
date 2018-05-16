package Presentacion.Shows.individual;

import Controller.Controller;
import Negocio.Participante.Tparticipante;
import Negocio.Participante.TparticipanteInternacional;
import Negocio.Participante.TparticipanteNacional;
import Presentacion.Events.Event;
import Presentacion.UI;
import Presentacion.UIStructureFrame;

import java.awt.*;
import java.awt.event.*;
import javax.print.DocFlavor;
import javax.swing.*;

public class GUIViewClient extends UIStructureFrame {

    private String id;
    private String name;
    private String phone;
    private boolean active;
    private String specialization;
    private spec_t type;

    private enum spec_t{NACIONAL, INTERNACIONAL}


    private JPanel formContainer;

    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.MONOSPACED, Font.PLAIN, 30); //era dialog

    public GUIViewClient(Tparticipante tclient) {
        super("");

        this.helpMessage = "<html><h1>SHOW INDIVIDUAL CLIENT HELP</h1>Here you have the possibility to" +
                "<b>See</b> the data of the specific <u>Client</u>" +
                " that you chose.</html>" +
                "";

        id = tclient.getId() + "";
        name = tclient.getName();
        phone = tclient.getPhone() + "";
        active = tclient.getActive();
        if( tclient instanceof TparticipanteNacional){
            specialization = ((TparticipanteNacional) tclient).getRegion();
            type = spec_t.NACIONAL;
        }
        else if ( tclient instanceof TparticipanteInternacional){
            specialization = ((TparticipanteInternacional) tclient).getCountry();
            type = spec_t.INTERNACIONAL;
        }

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



        JLabel idLabel = createLabel("ID:");
        JLabel nameLabel = createLabel("Name:");
        JLabel phoneLabel = createLabel("Phone:");
        JLabel activeLabel = createLabel("Active:");
        JLabel specLabel = null;
        switch (type){
            case NACIONAL:{
                specLabel = createLabel("Region:");
            } break;
            case INTERNACIONAL:{
                specLabel = createLabel("Country:");
            }
        }

        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;


        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(idLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(nameLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 2;
        formPanel.add(phoneLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 3;
        formPanel.add(activeLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 4;
        formPanel.add(specLabel, formCon);


        JLabel idField = createLabel(id);
        idLabel.setMinimumSize(minDim);
        idLabel.setPreferredSize(prefDim);
        idLabel.setMaximumSize(maxDim);

        JLabel nameField = createLabel(name);
        nameField.setMinimumSize(minDim);
        nameField.setPreferredSize(prefDim);
        nameField.setMaximumSize(maxDim);

        JLabel phoneField = createLabel(phone);
        phoneField.setMinimumSize(minDim);
        phoneField.setPreferredSize(prefDim);
        phoneField.setMaximumSize(new Dimension(maxDim.width, maxDim.height + 100));

        JLabel activeField = createLabel(String.valueOf(active));
        activeField.setMinimumSize(minDim);
        activeField.setPreferredSize(prefDim);
        activeField.setMaximumSize(maxDim);

        JLabel specField = createLabel(specialization);
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
        formPanel.add(nameField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(phoneField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 3;
        formPanel.add(activeField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 4;
        formPanel.add(specField, formCon);
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
        if(data != null) JOptionPane.showMessageDialog(null,"Here you can see the Client's data");
        else JOptionPane.showMessageDialog(null, "A problem in the 'show' process occurred, insert Client's data another time please", "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
