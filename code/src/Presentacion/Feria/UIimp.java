package Presentacion.Feria;

import Presentacion.Feria.views.events.Event;
import Presentacion.Feria.views.events.EventGUI;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.border.*;

public class UIimp extends JFrame implements UI{

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

    private Color colorButton;
    private Font propFont;
    private Font propButtonOption;

    public UIimp() {
        super("IFESOFT SOFTWARE");

        // Se le da un valor a controller
        control = new ControllerImp();

        this.colorButton = new Color(65,65,65);
        this.propFont = new Font(Font.MONOSPACED, Font.PLAIN, 60);
        this.propButtonOption = new Font(Font.MONOSPACED, Font.PLAIN, 30);

        start();
        this.setBounds(100,100, 1200,1200);
        this.setVisible(true);
    }

    @Override
    public void update(int event, Object object) {
        switch (event){
            case EventGUI.UPDATE_CREATE_FERIA_OK:

                break;
            case EventGUI.UPDATE_CREATE_FERIA_FAIL:

                break;

            // .....
        }
    }

    @Override
    public void start() {
        initComponents();
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

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== Panel ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));

            // JFormDesigner evaluation mark
            dialogPane.setBorder(new CompoundBorder(
                new TitledBorder(new EmptyBorder(0, 0, 0, 0),
                    "", TitledBorder.CENTER,
                    TitledBorder.BOTTOM, new Font("Dialog", Font.BOLD, 12),
                    Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new PropertyChangeListener(){public void propertyChange(PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

                //---- JlabelTitle ----

                JlabelTitle.setText("IFESOFT SOFTWARE");
                JlabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
                JlabelTitle.setFont(propFont);

                contentPanel.add(JlabelTitle);

                //======== button ========
                {
                    buttonBar2.setBorder(new EmptyBorder(12, 0, 0, 0));
                    buttonBar2.setLayout(new GridLayout(2, 2, 50, 80));

                    //---- buttonCreateHome ----
                    buttonCreateHome.setText("CREATE");
                    buttonCreateHome.setFont(propFont);
                    buttonCreateHome.setBackground(colorButton);
                    buttonCreateHome.setForeground(Color.WHITE);
                    buttonCreateHome.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            buttonCreateHomeActionPerformed();
                        }
                    });
                    buttonBar2.add(buttonCreateHome);

                    //---- buttonShowHome ----
                    buttonShowHome.setText("SHOW");
                    buttonShowHome.setFont(propFont);
                    buttonShowHome.setBackground(colorButton);
                    buttonShowHome.setForeground(Color.WHITE);
                    buttonShowHome.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            buttonShowHomeActionPerformed();
                        }
                    });
                    buttonBar2.add(buttonShowHome);

                    //---- buttonDropHome ----
                    buttonDropHome.setText("DROP");
                    buttonDropHome.setFont(propFont);
                    buttonDropHome.setBackground(colorButton);
                    buttonDropHome.setForeground(Color.WHITE);
                    buttonDropHome.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            buttonDropHomeActionPerformed();
                        }
                    });
                    buttonBar2.add(buttonDropHome);

                    //---- buttonModifyHome ----
                    buttonModifyHome.setText("MODIFY");
                    buttonModifyHome.setFont(propFont);
                    buttonModifyHome.setBackground(colorButton);
                    buttonModifyHome.setForeground(Color.WHITE);
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
                cancelButton.setForeground(Color.WHITE);
                cancelButton.setBackground(new Color(147, 28, 0));
                cancelButton.setFont(propButtonOption);
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
                helpButton.setBackground(new Color(49, 85, 133));
                helpButton.setFont(propButtonOption);
                helpButton.setForeground(Color.WHITE);
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

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }
}
