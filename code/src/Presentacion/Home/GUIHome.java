package Presentacion.Home;

import Controller.Controller;
import Presentacion.Events.Event;
import Presentacion.Events.EventGUI;
import Presentacion.UI;
import Presentacion.UIStructureFrame;
import Presentacion.Utils.ActionHelp;
import Presentacion.Utils.PanelProblemUser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GUIHome extends UIStructureFrame implements UI {

    private Font fTitle = new Font(Font.MONOSPACED, Font.PLAIN, 60);
    private Font fBigButton = new Font(Font.MONOSPACED, Font.PLAIN, 30);
    Dimension ss = Toolkit.getDefaultToolkit ().getScreenSize ();
    Dimension frameSize = new Dimension ( 500, 300 );


    private Color cBigButton = new Color(49,49,49);

    String helpMessage = "<html><h1>HOME PAGE HELP</h1>Here you have the possibility to <b>Create</b>," +
            " <b>Modify</b>, <b>Show</b> or <b>Delete</b> <u>Fairs</u> or other entities just" +
            " by clicking on the specific button.</html>\n" +
            "";

    public GUIHome() {
        super("IFESOFT");
        initComponents();
        this.cancelButton.setText("Exit");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setVisible(true);
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


    @Override
    protected void okButtonActionPerformed(ActionEvent e) throws Exception {

    }

    @Override
    protected void cancelButtonActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    @Override
    protected void helpButtonActionPerformed(ActionEvent e) {
        new ActionHelp(helpMessage);
    }

    @Override
    protected void setUpTitle(){

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

    @Override
    protected void setUpCenter() {

         centerPanel = new JPanel();
         GridBagLayout centerLayout = new GridBagLayout();
         centerPanel.setLayout(centerLayout);
         centerPanel.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));

         GridBagConstraints centerConst = new GridBagConstraints();
         centerConst.fill = GridBagConstraints.BOTH;
         centerConst.insets = new Insets(20,40,20,40);
         centerConst.weightx = 0.5;
         centerConst.weighty = 0.5;

        JButton buttonCreateHome = createBigButton("Create");
            buttonCreateHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonCreateHomeActionPerformed();
                }
            });
         centerConst.gridx = 0;
         centerConst.gridy = 0;
         centerPanel.add(buttonCreateHome, centerConst);

        JButton buttonShowHome = createBigButton("Show");
            buttonShowHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonShowHomeActionPerformed();
                }
            });
        centerConst.gridx = 1;
        centerConst.gridy = 0;
        centerPanel.add(buttonShowHome, centerConst);

        JButton buttonDropHome = createBigButton("Drop");
            buttonDropHome.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonDropHomeActionPerformed();
                }
            });
        centerConst.gridx = 0;
        centerConst.gridy = 1;
        centerPanel.add(buttonDropHome, centerConst);

        JButton buttonModifyHome = createBigButton("Modify");
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

    @Override
    public void update(int event, Object object) {
        switch (event){
            case EventGUI.UPDATE_CREATE_FAIR_OK:
                JOptionPane.showMessageDialog(null, "The Fair has been created successfully");
                break;
            case EventGUI.UPDATE_CREATE_PAVILION_OK:
                JOptionPane.showMessageDialog(null, "The Pavilion has been created successfully");
                break;
            case EventGUI.UPDATE_CREATE_CLIENT_OK:
                JOptionPane.showMessageDialog(null, "The Client has been created successfully");
                break;
            case EventGUI.UPDATE_CREATE_ASSIGNATION_OK:
                JOptionPane.showMessageDialog(null, "The Assignation has been created successfully");
                break;
            case EventGUI.UPDATE_CREATE_PARTICIPATION_OK:
                JOptionPane.showMessageDialog(null, "The Participation has been created successfully");
                break;
            case EventGUI.UPDATE_CREATE_STAND_OK:
                JOptionPane.showMessageDialog(null, "The Stand has been created successfully");
                break;
        }
    }
}

