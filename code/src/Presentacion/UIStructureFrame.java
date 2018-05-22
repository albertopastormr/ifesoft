package Presentacion;

import Presentacion.Utils.ActionHelp;
import Presentacion.Utils.PanelProblemUser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class UIStructureFrame extends JFrame implements UI {

    protected String helpMessage;

    protected JPanel buttonBar;
    protected Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);
    protected Color cHelpButton = new Color(66,35,146);
    protected Color cCancelButton = new Color(146, 35, 59);

    protected Dimension minScreenSize = new Dimension(1920, 1080);

    protected JPanel centerPanel;
    protected JLabel title;

    protected JButton cancelButton = new JButton();
    protected JButton helpButton = new JButton();

    protected JPanel dialogPanel = new JPanel();

    public UIStructureFrame(String titleText){
        super(titleText);
    }

    protected abstract void okButtonActionPerformed(ActionEvent e) throws Exception;
    protected abstract void cancelButtonActionPerformed(ActionEvent e) throws Exception;
    protected abstract void setUpTitle();
    protected abstract void setUpCenter();

    protected void helpButtonActionPerformed(ActionEvent e) throws Exception{
        new ActionHelp(helpMessage);
    }


    protected void initComponents(){
        //======== this ========
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(100,100, 800,800);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        ImageIcon img = new ImageIcon("Resources//Icon.png");
        this.setIconImage(img.getImage());

        //======== dialogPanel ========
        dialogPanel.setBorder(new LineBorder(Color.BLUE));
        dialogPanel.setBorder(new EmptyBorder(50, 50, 80, 50));
        this.setMinimumSize(minScreenSize);

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
        this.setUpTitle();
        if(this.title != null) dialogPanel.add(this.title, BorderLayout.PAGE_START);

        //======== contentPanel ========

        setUpCenter();
        if(centerPanel != null) dialogPanel.add(centerPanel, BorderLayout.CENTER);

        //========= ButtonBar ========

        setUpButtonBar();
        if(buttonBar != null) dialogPanel.add(buttonBar, BorderLayout.PAGE_END);

        contentPane.add(dialogPanel, BorderLayout.CENTER);
        this.setVisible(true);
        pack();
        setLocationRelativeTo(getOwner());

    }

    protected void setUpButtonBar(){

        Dimension buttonDim = new Dimension(150, 80);

        //---- cancelButton ----
        cancelButton.setText("Back");
        cancelButton.setFont(fButton);
        cancelButton.setBackground(cCancelButton);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(buttonDim);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cancelButtonActionPerformed(e);
                }catch (Exception e1){
                    new PanelProblemUser(e1.getMessage());
                }
            }
        });


        //---- helpButton ----

        helpButton.setText("Help");
        helpButton.setFont(fButton);
        helpButton.setBackground(cHelpButton);
        helpButton.setForeground(Color.WHITE);
        helpButton.setPreferredSize(buttonDim);
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    helpButtonActionPerformed(e);
                } catch (Exception e1) {
                    e1.printStackTrace();
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
    }

}
