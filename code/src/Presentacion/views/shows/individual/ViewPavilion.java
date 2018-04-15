package Presentacion.views.shows.individual;

import Negocio.Pabellon.Tpabellon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewPavilion extends JFrame {

    private String capacity;
    private String m2tot;
    private String m2util;
    private String id;

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JLabel title;
    private JPanel formContainer;
    private JPanel buttonBar;

    private Font fTitle = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fLabel = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);

    private Color cBackButton = new Color(146, 35, 59);

    /*
    SOLO PARA DEPURAR
     */
    public ViewPavilion() {

        capacity = "7";
        m2tot = "420";
        m2util = "69";
        id = "1";

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    public ViewPavilion(Tpabellon tpabellon) {

        capacity = tpabellon.getCapacity() +"";
        m2tot = tpabellon.getTotal_m2() +"";
        m2util = tpabellon.getUtil_m2() +"";
        id = tpabellon.getId() +"";

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
        title.setText("Pavilion: " + id);
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


        JLabel aforoLabel = createLabel("Capacity:");
        JLabel m2totLabel = createLabel("Total square-metres:");
        JLabel m2utilesLabel = createLabel("Useful square-metres:");

        formCon.insets = new Insets(20, 0, 20, 0);
        formCon.anchor = GridBagConstraints.WEST;

        formCon.gridx = 0;
        formCon.gridy = 0;
        formPanel.add(aforoLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 1;
        formPanel.add(m2totLabel, formCon);
        formCon.gridx = 0;
        formCon.gridy = 2;
        formPanel.add(m2utilesLabel, formCon);

        JLabel aforoField = createLabel(capacity);
        aforoField.setMinimumSize(minDim);
        aforoField.setPreferredSize(prefDim);
        aforoField.setMaximumSize(maxDim);

        JLabel m2totField = createLabel(m2tot);
        m2totField.setMinimumSize(minDim);
        m2totField.setPreferredSize(prefDim);
        m2totField.setMaximumSize(maxDim);

        JLabel m2utilesField = createLabel(m2util);
        m2utilesField.setMinimumSize(minDim);
        m2utilesField.setPreferredSize(prefDim);
        m2utilesField.setMaximumSize(maxDim);

        formCon.anchor = GridBagConstraints.WEST;

        formCon.insets = new Insets(20,10,20,0);

        formCon.gridx = 1;
        formCon.gridy = 0;
        formPanel.add(aforoField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 1;
        formPanel.add(m2totField, formCon);
        formCon.gridx = 1;
        formCon.gridy = 2;
        formPanel.add(m2utilesField, formCon);
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
