package Presentacion.views.shows.individual;

import Negocio.Stand.Tstand;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewStand extends JFrame {

    private String metres;
    private String number;
    private String cost;
    private String id;

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JLabel title;
    private JPanel formContainer;
    private JPanel buttonBar;


    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cBackButton = new Color(146, 35, 59);

    public ViewStand() {

        metres = "6789";
        number = "42";
        cost = "1 millón de camellos";
        id = "7";

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    public ViewStand(Tstand tstand) {

        metres = "" + tstand.getTotal_m2();
        number = "" + tstand.getNum_at_fair();
        cost = "" + tstand.getCost();
        id = "" + tstand.getId();

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }


    private void backButtonActionPerformed() {
        this.setVisible(false);

        //TODO
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


        JLabel metresLabel = createLabel("Metres:");
        JLabel numberLabel = createLabel("Number:");
        JLabel costLabel = createLabel("Cost:");

        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;

        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(metresLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(numberLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 2;
        formPanel.add(costLabel, formCon);

        JLabel metresField = createLabel(metres);
        metresField.setMinimumSize(minDim);
        metresField.setPreferredSize(prefDim);
        metresField.setMaximumSize(maxDim);

        JLabel numberField = createLabel(number);
        numberField.setMinimumSize(minDim);
        numberField.setPreferredSize(prefDim);
        numberField.setMaximumSize(new Dimension(maxDim.width, maxDim.height + 100));

        JLabel costField = createLabel(cost);
        costField.setMinimumSize(minDim);
        costField.setPreferredSize(prefDim);
        costField.setMaximumSize(maxDim);

        formCon.anchor = GridBagConstraints.WEST;

        formCon.insets = new Insets(20,10,20,0);


        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(metresField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(numberField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(costField, formCon);
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
}
