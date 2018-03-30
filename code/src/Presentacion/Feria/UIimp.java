package Presentacion.Feria;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class UIimp extends JFrame {

    private static ControllerImp control;

    private JPanel dialogPane;
    private static JPanel contentPanel;
    private JLabel JlabelTitle;
    private JPanel buttonBar2;
    private JButton buttonCreateHome;
    private JButton buttonShowHome;
    private JButton buttonDropHome;
    private JButton buttonModifyHome;
    private JPanel buttonBar;
    private JButton cancelButton;
    private JButton helpButton;

    public UIimp() {
        super("IFESOFT SOFTWARE");

        // Se le da un valor a controller
        control = new ControllerImp();

        initComponents();
        this.setBounds(100,100, 800,800);
        this.setVisible(true);
    }

    private void buttonCreateHomeActionPerformed() {
        this.setVisible(false);
        UIimp.getInstance().execute(Event.CREATE_HALF, null);
    }

    private void buttonShowHomeActionPerformed() {
        this.setVisible(false);
        UIimp.getInstance().execute(Event.SHOW_HALF, null);
    }

    private void buttonDropHomeActionPerformed() {
        this.setVisible(false);
        UIimp.getInstance().execute(Event.DROP_HALF, null);
    }

    private void buttonModifyHomeActionPerformed() {
        this.setVisible(false);
        UIimp.getInstance().execute(Event.MODIFY_HALF, null);
    }

    private void cancelButtonActionPerformed() {
        System.exit(0);
    }

    private void helpButtonActionPerformed() {

    }

    private void initComponents() {
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        JlabelTitle = new JLabel();
        buttonBar2 = new JPanel();
        buttonCreateHome = new JButton();
        buttonShowHome = new JButton();
        buttonDropHome = new JButton();
        buttonModifyHome = new JButton();
        buttonBar = new JPanel();
        cancelButton = new JButton();
        helpButton = new JButton();

        //======== this ========
        setTitle("IFESOFT ");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== Panel ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

                //---- JlabelTitle ----
                JlabelTitle.setText("IFESOFT SOFTWATE");
                JlabelTitle.setFont(JlabelTitle.getFont().deriveFont(JlabelTitle.getFont().getSize() + 7f));
                contentPanel.add(JlabelTitle);

                //======== button ========
                {
                    buttonBar2.setBorder(new EmptyBorder(12, 0, 0, 0));
                    buttonBar2.setLayout(new GridLayout(2, 2));

                    //---- buttonCreateHome ----
                    buttonCreateHome.setText("CREATE");
                    buttonCreateHome.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            buttonCreateHomeActionPerformed();
                        }
                    });
                    buttonBar2.add(buttonCreateHome);

                    //---- buttonShowHome ----
                    buttonShowHome.setText("SHOW");
                    buttonShowHome.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            buttonShowHomeActionPerformed();
                        }
                    });
                    buttonBar2.add(buttonShowHome);

                    //---- buttonDropHome ----
                    buttonDropHome.setText("DROP");
                    buttonDropHome.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            buttonDropHomeActionPerformed();
                        }
                    });
                    buttonBar2.add(buttonDropHome);

                    //---- buttonModifyHome ----
                    buttonModifyHome.setText("MODIFY");
                    buttonModifyHome.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            buttonModifyHomeActionPerformed();
                        }
                    });
                    buttonBar2.add(buttonModifyHome);
                }
                contentPanel.add(buttonBar2);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0};

                //---- cancelButton ----
                cancelButton.setText("Exit");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cancelButtonActionPerformed();
                    }
                });
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- helpButton ----
                helpButton.setText("Help");
                helpButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        helpButtonActionPerformed();
                    }
                });
                buttonBar.add(helpButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
    }

    public static Container generateContainBox(JFrame jframe){

        JFrame.setDefaultLookAndFeelDecorated(true);
        Container homePanel = jframe.getContentPane();
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        homePanel.setLayout(new BoxLayout(homePanel,BoxLayout.PAGE_AXIS));

        jframe.pack();
        return homePanel;
    }

    public static JLabel buildTitle(String titleStr){
        JLabel title = new JLabel(titleStr,SwingConstants.CENTER);
        title.setFont(new Font("", Font.BOLD,75));
        return title;
    }

    public static synchronized ControllerImp getInstance( ) {
        if (control == null)
            control = new ControllerImp();
        return control;
    }
}
