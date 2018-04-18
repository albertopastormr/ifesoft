package Presentacion.Shows.individual;

import Negocio.Feria.Tferia;
import Presentacion.Utils.Utilities;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewFair extends JFrame {

    private String id;
    private String name;
    private String description;
    private String iniDate;
    private String finDate;

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JLabel title;
    private JPanel formContainer;
    private JPanel buttonBar;


    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fField = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cCancelButton = new Color(146, 35, 59);

    public ViewFair(Tferia tferia) {

        id = tferia.getId() + "";
        name = tferia.getName();
        description = tferia.getDescription();
        iniDate = Utilities.parseDateToString(tferia.getIniDate());
        finDate = Utilities.parseDateToString(tferia.getEndDate());

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
        title.setText("Fair: " + id);
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


        JLabel nameLabel = createLabel("Name:");
        JLabel descLabel = createLabel("Description:");
        JLabel iniDateLabel = createLabel("Start Date:");
        JLabel finDateLabel = createLabel("End Date:");

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
        formContainer.add(formPanel);
    }

    private void setUpButtonBar(){

        Dimension buttonDim = new Dimension(150, 80);


        JButton backButton = new JButton();
        backButton.setText("Back");
        backButton.setFont(fButton);
        backButton.setBackground(cCancelButton);
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
