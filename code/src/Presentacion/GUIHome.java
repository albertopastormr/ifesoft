package Presentacion;

import Controller.Controller;
import Presentacion.Create_Modify.Create.GUICreate;
import Presentacion.Create_Modify.Forms.GUIFormFair;
import Presentacion.Events.Event;
import Presentacion.Events.EventGUI;
import Presentacion.Utils.ActionHelp;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GUIHome extends UIimp {

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JPanel dialogPanel;
    private static JPanel centerPanel;
    private JLabel title;
    private JButton buttonCreateHome;
    private JButton buttonShowHome;
    private JButton buttonDropHome;
    private JButton buttonModifyHome;
    private JPanel buttonBar;
    private JButton exitButton;
    private JButton helpButton;

    private Font fTitle = new Font(Font.MONOSPACED, Font.PLAIN, 60);
    private Font fBigButton = new Font(Font.MONOSPACED, Font.PLAIN, 30);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);


    private Color cBigButton = new Color(49,49,49);
    private Color cHelpButton = new Color(66,35,146);
    private Color cExitButton = new Color(146, 35, 59);

    String helpMessage = "<html>this is a really long message<br>this is a really long message this is a really long message this is " +
            "a really long message this is a really long message this is a really long message this is a really long message";

    public GUIHome() {
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(100,100, minScreenSize.width, minScreenSize.height);
        this.setVisible(true);
    }

    @Override
    public void update(int event, Object object) {
        switch (event){
            case EventGUI.UPDATE_CREATE_FERIA_OK:
                System.out.println("Yeeeeee 2");
                break;
            case EventGUI.UPDATE_CREATE_FERIA_FAIL:

                break;

        }
    }

    private void buttonCreateHomeActionPerformed() {
        this.setVisible(false);
        try {
            Controller.getInstance().execute(Event.CREATE_HALF, null);
        }catch (Exception e1){
            new PanelProblemUser(e1.getMessage());
        }
    }

    private void buttonShowHomeActionPerformed() {
        this.setVisible(false);
        try {
            Controller.getInstance().execute(Event.SHOW_HALF, null);
        }catch (Exception e1){
            new PanelProblemUser(e1.getMessage());
        }
    }

    private void buttonDropHomeActionPerformed() {
        this.setVisible(false);
        try {
            Controller.getInstance().execute(Event.DROP_HALF, null);
        } catch (Exception e1){
            new PanelProblemUser(e1.getMessage());
        }
    }

    private void buttonModifyHomeActionPerformed() {
        this.setVisible(false);
        try {
            Controller.getInstance().execute(Event.MODIFY_HALF, null);
        } catch (Exception e1){
            new PanelProblemUser(e1.getMessage());
        }
    }

    private void cancelButtonActionPerformed() {
        System.exit(0);
    }

    private void helpButtonActionPerformed() {
        new ActionHelp(helpMessage);
    }

    private void setUpTitle(){

        title = new JLabel();
        title .setText("Ifesoft Software");
        title .setFont(fTitle);
        title .setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));

    }

    private JButton createBigButton(String text){
        JButton bigButton = new JButton();
        bigButton.setText(text);
        bigButton.setBackground(cBigButton);
        bigButton.setForeground(Color.WHITE);
        bigButton.setFont(fBigButton);
        bigButton.setMinimumSize(new Dimension(200, 5));
        bigButton.setMaximumSize(new Dimension(400, 10));

        return bigButton;
    }

    private void setUpCenter() {

         centerPanel = new JPanel();
         GridBagLayout centerLayout = new GridBagLayout();
         centerPanel.setLayout(centerLayout);
         centerPanel.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));

         GridBagConstraints centerConst = new GridBagConstraints();
         centerConst.fill = GridBagConstraints.BOTH;
         centerConst.insets = new Insets(20,40,20,40);
         centerConst.weightx = 0.5;
         centerConst.weighty = 0.5;

         buttonCreateHome = createBigButton("Create_Modify");
            buttonCreateHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonCreateHomeActionPerformed();
                }
            });
         centerConst.gridx = 0;
         centerConst.gridy = 0;
         centerPanel.add(buttonCreateHome, centerConst);

         buttonShowHome = createBigButton("Show");
            buttonShowHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonShowHomeActionPerformed();
                }
            });
        centerConst.gridx = 1;
        centerConst.gridy = 0;
        centerPanel.add(buttonShowHome, centerConst);

         buttonDropHome = createBigButton("Drop");
            buttonDropHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonDropHomeActionPerformed();
                }
            });
        centerConst.gridx = 0;
        centerConst.gridy = 1;
        centerPanel.add(buttonDropHome, centerConst);

         buttonModifyHome = createBigButton("Modify");
            buttonModifyHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonModifyHomeActionPerformed();
                }
            });
        centerConst.gridx = 1;
        centerConst.gridy = 1;
        centerPanel.add(buttonModifyHome, centerConst);

    }

    private void setUpButtonBar(){

        Dimension buttonDim = new Dimension(150, 80);

        //---- cancelButton ----
        exitButton = new JButton();
        exitButton.setText("Cancel");
        exitButton.setFont(fButton);
        exitButton.setBackground(cExitButton);
        exitButton.setForeground(Color.WHITE);
        exitButton.setPreferredSize(buttonDim);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonActionPerformed();
            }
        });

        //---- helpButton ----
        helpButton = new JButton();
        helpButton.setText("Help");
        helpButton.setFont(fButton);
        helpButton.setBackground(cHelpButton);
        helpButton.setForeground(Color.WHITE);
        helpButton.setPreferredSize(buttonDim);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpButtonActionPerformed();
            }
        });

        buttonBar = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        layout.setHgap(25);
        buttonBar.setLayout(layout);
        buttonBar.add(Box.createHorizontalStrut(80));
        buttonBar.add(exitButton);
        buttonBar.add(helpButton);



    }

    private void initComponents() {

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        ImageIcon img = new ImageIcon("Resources//Icon.png");
        this.setIconImage(img.getImage());

        //======== dialogPanel ========

        dialogPanel = new JPanel();
        dialogPanel.setBorder(new LineBorder(Color.BLUE));
        dialogPanel.setBorder(new EmptyBorder(50, 50, 80, 50));
        this.setMinimumSize(minScreenSize);
        this.setPreferredSize(minScreenSize);

        // JFormDesigner evaluation mark
        dialogPanel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                        "", javax.swing.border.TitledBorder.CENTER,
                        javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font(Font.DIALOG, java.awt.Font.BOLD, 12),
                        java.awt.Color.red), dialogPanel.getBorder()));
        dialogPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent e) {
                if ("border".equals(e.getPropertyName())) throw new RuntimeException();
            }
        });

        dialogPanel.setLayout(new BorderLayout());

        //======== Title ========
        setUpTitle();
        dialogPanel.add(title, BorderLayout.PAGE_START);

        //======== contentPanel ========

        setUpCenter();
        dialogPanel.add(centerPanel, BorderLayout.CENTER);

        //========= ButtonBar ========

        setUpButtonBar();
        dialogPanel.add(buttonBar, BorderLayout.PAGE_END);

        contentPane.add(dialogPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }
}

