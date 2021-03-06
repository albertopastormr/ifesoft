package Presentacion.Shows.individual;

import Controller.Controller;
import Negocio.Pabellon.Tpabellon;
import Presentacion.Events.Event;
import Presentacion.UIStructureFrame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIViewPavilion extends UIStructureFrame {

    private String capacity;
    private String m2tot;
    private String id;
    private boolean active;
    private JPanel formContainer;

    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.MONOSPACED, Font.PLAIN, 30);

    public GUIViewPavilion(Tpabellon tpabellon) {
        super("");
        this.helpMessage = "<html><h1>SHOW INDIVIDUAL PAVILION HELP</h1>Here you have the possibility to" +
                "<b>See</b> the data of the specific <u>Pavilion</u> that you chose" +
                "</html>" +
                "";

        capacity = tpabellon.getCapacity() +"";
        m2tot = tpabellon.getTotal_m2() +"";
        id = tpabellon.getId() +"";
        active = tpabellon.getActive();

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
        title.setText("Pavilion: " + id);
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

        Dimension minDim = new Dimension(700, 50);
        Dimension prefDim = new Dimension(900, 50);
        Dimension maxDim = new Dimension(1000, 50);


        GridBagConstraints formCon = new GridBagConstraints();

        formCon.fill = GridBagConstraints.NONE;
        formCon.weightx = 0.5;
        formCon.weighty = 0.5;
        formCon.anchor = GridBagConstraints.EAST;


        JLabel aforoLabel = createLabel("Capacity:");
        JLabel m2totLabel = createLabel("Total square-metres:");
        JLabel activeLabel = createLabel("Active:");
        JLabel idLabel = createLabel("Pavilion id:");

        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;

        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(idLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(aforoLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 2;
        formPanel.add(m2totLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 3;
        formPanel.add(activeLabel, formCon);

        JLabel idField = createLabel(id);
        idField.setMinimumSize(minDim);
        idField.setPreferredSize(prefDim);
        idField.setMaximumSize(maxDim);

        JLabel aforoField = createLabel(capacity);
        aforoField.setMinimumSize(minDim);
        aforoField.setPreferredSize(prefDim);
        aforoField.setMaximumSize(maxDim);

        JLabel m2totField = createLabel(m2tot);
        m2totField.setMinimumSize(minDim);
        m2totField.setPreferredSize(prefDim);
        m2totField.setMaximumSize(maxDim);

        JLabel activeField = createLabel(String.valueOf(active));
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
        formPanel.add(aforoField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(m2totField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 3;
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
        if(data != null) JOptionPane.showMessageDialog(null,"Here you can see the Pavilion's data");
        else JOptionPane.showMessageDialog(null, "A problem in the 'show' process occurred, insert Pavilion's data another time please", "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
