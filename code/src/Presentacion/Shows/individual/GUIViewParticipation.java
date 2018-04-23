package Presentacion.Shows.individual;

import Negocio.Participacion.Tparticipacion;
import Negocio.Participante.Tparticipante;
import Presentacion.UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIViewParticipation extends JFrame implements UI {


    private int id;
    private int idFair;
    private int idClient;

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JLabel title;
    private JPanel formContainer;
    private JPanel buttonBar;


    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cBackButton = new Color(146, 35, 59);
    private Color cHelpButton = new Color(66,35,146);

    String helpMessage = "<html><h1>SHOW INDIVIDUAL CLIENT HELP</h1>Here you have the possibility to" +
            "<b>See</b> the data of the specific <u>Client</u>" +
            " that you chose.</html>" +
            "";


    public GUIViewParticipation(Tparticipacion participation) {
        super("View Participation");

        id = participation.getId();
        idFair = participation.getFair_id();
        idClient = participation.getClient_id();

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
        title.setText("Participation: " + id);
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



        JLabel idLabel = createLabel("Participation ID:");
        JLabel idFairLabel = createLabel("Fair ID:");
        JLabel idPavilionLabel = createLabel("Client ID:");


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


        JLabel idField = createLabel(id + "");
        idField.setMinimumSize(minDim);
        idField.setPreferredSize(prefDim);
        idField.setMaximumSize(maxDim);

        JLabel idFairField = createLabel(idFair + "");
        idFairField.setMinimumSize(minDim);
        idFairField.setPreferredSize(prefDim);
        idFairField.setMaximumSize(maxDim);

        JLabel idPavilionField = createLabel(idClient + "");
        idPavilionField.setMinimumSize(minDim);
        idPavilionField.setPreferredSize(prefDim);
        idPavilionField.setMaximumSize(maxDim);

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


        ImageIcon img = new ImageIcon("Resources//Icon.png");
        this.setIconImage(img.getImage());

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
