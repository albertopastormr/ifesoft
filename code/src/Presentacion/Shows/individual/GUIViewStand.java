package Presentacion.Shows.individual;

import Negocio.Stand.Tstand;
import Presentacion.UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIViewStand extends JFrame implements UI {

    private String metres;
    private String number;
    private String cost;
    private String id;
    private String assignation;
    private String participation;

    private String idAssignation;
    private String idParticipation;

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JLabel title;
    private JPanel formContainer;
    private JPanel buttonBar;


    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cBackButton = new Color(146, 35, 59);
    private Color cHelpButton = new Color(66,35,146);

    String helpMessage = "<html><h1>SHOW INDIVIDUAL STAND HELP</h1>Here you have the possibility to" +
            "<b>See</b> the data of the specific <u>Stand</u> that you chose" +
            "</html>" +
            "";

<<<<<<< HEAD


=======
>>>>>>> 0fd4ab01739112570bdb3eb1ff15d50433427ce2
    public GUIViewStand(Tstand tstand) {

        metres = "" + tstand.getTotal_m2();
        number = "" + tstand.getNum_at_fair();
        cost = "" + tstand.getCost();
        id = "" + tstand.getId();
<<<<<<< HEAD
        assignation = "" + tstand.getAssignation_id();
        participation = "" + tstand.getParticipation_id();
=======
        idAssignation = "" + tstand.getAssignation_id();
        idParticipation = "" + tstand.getParticipation_id();
>>>>>>> 0fd4ab01739112570bdb3eb1ff15d50433427ce2

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }


    private void backButtonActionPerformed() {
        this.setVisible(false);

        //TODO
    }

    private void helpButtonActionPerformed(ActionEvent e) {
        new Presentacion.Utils.ActionHelp(helpMessage);
    }

    private void setupTitle(){
        title = new JLabel();
        title.setText("Stand: " + id);
        title.setFont(fTitle);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));
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


        JLabel assignationLabel = createLabel("Assignation ID:");
        JLabel participationLabel = createLabel("Participation ID:");
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
<<<<<<< HEAD
        formCon.gridy = 3;
        formPanel.add(assignationLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 4;
        formPanel.add(participationLabel, formCon);
=======
        formCon.gridy = 4;
        formPanel.add(metresLabel, formCon);

        JLabel assignationField = createLabel(idAssignation);
        assignationField.setMinimumSize(minDim);
        assignationField.setPreferredSize(prefDim);
        assignationField.setMaximumSize(maxDim);

        JLabel participationField = createLabel(idAssignation);
        participationField.setMinimumSize(minDim);
        participationField.setPreferredSize(prefDim);
        participationField.setMaximumSize(maxDim);
>>>>>>> 0fd4ab01739112570bdb3eb1ff15d50433427ce2

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

        JLabel assignationField = createLabel(assignation);
        costField.setMinimumSize(minDim);
        costField.setPreferredSize(prefDim);
        costField.setMaximumSize(maxDim);

        JLabel participationField = createLabel(participation);
        costField.setMinimumSize(minDim);
        costField.setPreferredSize(prefDim);
        costField.setMaximumSize(maxDim);

        formCon.anchor = GridBagConstraints.EAST;

        formCon.insets = new Insets(20,10,20,0);

        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(assignationLabel, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(participationLabel, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(metresField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 3;
        formPanel.add(numberField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 4;
        formPanel.add(costField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 3;
        formPanel.add(assignationField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 4;
        formPanel.add(participationField, formCon);
        formContainer.add(formPanel);
    }

    private void setUpButtonBar(){

        Dimension buttonDim = new Dimension(150, 80);


        JButton backButton = new JButton();
        backButton.setText("Back");
        backButton.setFont(fButton);
        backButton.setBackground(cBackButton);
        backButton.setForeground(Color.WHITE);
        backButton.setPreferredSize(buttonDim);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backButtonActionPerformed();
            }
        });

        buttonBar = new JPanel();
        FlowLayout layout = new FlowLayout();
        layout.setHgap(25);
        buttonBar.setLayout(layout);
        buttonBar.add(backButton);

        //---- helpButton ----
        JButton helpButton = new JButton();
        helpButton.setText("Help");
        helpButton.setFont(fButton);
        helpButton.setBackground(cHelpButton);
        helpButton.setForeground(Color.WHITE);
        helpButton.setPreferredSize(buttonDim);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpButtonActionPerformed(e);
            }
        });

    }

    private void initComponents() {

        this.setMinimumSize(minScreenSize);

        JPanel dialogPanel = new JPanel();
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

    @Override
    public void update(int event, Object data) {

    }
}
