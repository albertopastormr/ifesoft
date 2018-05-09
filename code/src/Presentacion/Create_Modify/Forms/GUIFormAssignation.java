package Presentacion.Create_Modify.Forms;

import Negocio.Asignacion.Tasignacion;
import Controller.Controller;
import Presentacion.Events.Event;
import Presentacion.UI;
import Presentacion.UIStructureFrame;
import Presentacion.Utils.ActionHelp;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIFormAssignation extends UIStructureFrame {

    private boolean mod;

    private int idAssignation;
    private String idFair;
    private String idPavilion;
    private String metres;

    private JPanel formContainer;
    private JTextField idFairField;
    private JTextField idPavilionField;
    private JTextField metresTotalField;

    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fField = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cField = new Color(243, 243, 243);
    private Color cOkButton = new Color(26, 184, 59);

    private String setTitle(){
        return mod? "Modify" : "Create";
    }

    public GUIFormAssignation() {
        super("");

        this.helpMessage = "<html><h1>ASSIGNATION INFO</1>Here you can <b>insert</b> <u>Assignation</u>'s " +
                "data just by inserting them into" +
                " the text areas, then click <b>'Next'</b> to continue or <b>'Cancel'</b> to go back." +
                " </html>";

        this.setTitle(setTitle());
        mod = false;
        initComponents();
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }


    public GUIFormAssignation(Tasignacion assignation) {
        super("");

        this.helpMessage = "<html><h1>ASSIGNATION INFO</1>Here you can <b>insert</b> <u>Assignation</u>'s " +
                "data just by inserting them into" +
                " the text areas, then click <b>'Next'</b> to continue or <b>'Cancel'</b> to go back." +
                " </html>";

        mod = true;

        this.idAssignation = assignation.getId();

        idFair = String.valueOf(assignation.getFair_id());
        idPavilion = String.valueOf(assignation.getPavilion_id());
        metres = String.valueOf(assignation.getUsed_m2());

        initComponents();
        this.setBounds(100, 100, 800, 800);
        this.setVisible(true);
    }


    @Override
    protected void okButtonActionPerformed(ActionEvent e) throws Exception {
        this.setVisible(false);
        int idFair = Integer.valueOf(idFairField.getText());
        int idPavilion = Integer.valueOf(idPavilionField.getText());
        int mTotal = Integer.valueOf(metresTotalField.getText());

        Tasignacion tAssignation = new Tasignacion(idAssignation, idFair, idPavilion, mTotal, 0, true);

        if (!mod) Controller.getInstance().execute(Event.INSERT_ASSIGNATION, tAssignation);
        else Controller.getInstance().execute(Event.MODIFY_ASSIGNATION, tAssignation);
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
        if (mod)
            title.setText("Modify Assignation");
        else
            title.setText("Create_Modify Assignation");
        title.setFont(fTitle);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));
    }

    @Override
    protected void setUpCenter() {

    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text, JLabel.RIGHT);
        label.setFont(fLabel);
        return label;
    }

    private JTextField setupTextField() {
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


        JLabel idFairLabel = createLabel("Fair ID:");
        JLabel idPavilionLabel = createLabel("Pavilion ID:");
        JLabel metresUsedLabel = createLabel("Assignate metres:");

        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;


        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(idFairLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(idPavilionLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 2;
        formPanel.add(metresUsedLabel, formCon);

        idFairField = setupTextField();
        idFairField.setMinimumSize(minDim);
        idFairField.setPreferredSize(prefDim);
        idFairField.setMaximumSize(new Dimension(maxDim.width, maxDim.height + 100));
        idFairField.setText(idFair);

        idPavilionField = setupTextField();
        idPavilionField.setMinimumSize(minDim);
        idPavilionField.setPreferredSize(prefDim);
        idPavilionField.setMaximumSize(maxDim);
        idPavilionField.setText(idPavilion);

        metresTotalField = setupTextField();
        metresTotalField.setMinimumSize(minDim);
        metresTotalField.setPreferredSize(prefDim);
        metresTotalField.setMaximumSize(maxDim);
        metresTotalField.setText(metres);

        formCon.anchor = GridBagConstraints.WEST;

        formCon.insets = new Insets(20, 10, 20, 0);

        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(idFairField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(idPavilionField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(metresTotalField, formCon);
        formContainer.add(formPanel);
    }

    @Override
    protected void setUpButtonBar() {
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
                }catch (NumberFormatException e1){
                    new PanelProblemUser("Inserte caracteres validos.");
                } catch (Exception e1) {
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

    @Override
    protected void initComponents() {
        super.initComponents();

        //----Form----
        setupForm();
        dialogPanel.add(formContainer, BorderLayout.CENTER);
    }

    @Override
    public void update(int event, Object data) {
        //JOptionPane.showMessageDialog(null,"The Assignation has been created successfully");
        //JOptionPane.showMessageDialog(null, "A problem in the creation process occurred, insert Assignation's data another time please", "Error",
        //                            JOptionPane.ERROR_MESSAGE);
    }
}