package Presentacion.Shows.List;

import Negocio.Asignacion.Tasignacion;
import Controller.Controller;
import Presentacion.Events.Event;
import Presentacion.UI;
import Presentacion.Utils.PanelProblemUser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class GUIListAssignation extends JFrame implements UI {

    private String[] columnNames = {"ID ASSIGNATION" ,"ID FAIR","ID PAVILION", "USED M2", "TOTAL M2"};
    private Object[][] data;

    private Collection<Tasignacion> assignation;

    private Dimension minScreenSize = new Dimension(1600, 1000);

    private JPanel centerPanel;
    private JPanel buttonBar;
    private JLabel title;

    private Font fTitle  = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fButton  = new Font(Font.DIALOG, Font.PLAIN, 30);
    private Font fTable = new Font(Font.DIALOG, Font.PLAIN, 24);

    private Color cHelpButton = new Color(66,35,146);
    private Color cCancelButton = new Color(146, 35, 59);

    public GUIListAssignation(Collection<Tasignacion> assignation){
        this.assignation = assignation;
        this.initComponents();
    }

    private void cancelButtonActionPerformed(ActionEvent e) throws Exception {
        this.setVisible(false);
        Controller.getInstance().execute(Event.HOME, null);
    }

    private void helpButtonActionPerformed(ActionEvent e) {

    }


    private void changeTransferToCollection(Collection<Tasignacion> transfer) {
        int i = 0;

        for (Tasignacion assignation: transfer){
            this.data[i][0] = assignation.getId();
            this.data[i][1] = assignation.getFair_id();
            this.data[i][2] = assignation.getPavilion_id();
            this.data[i][2] = assignation.getUsed_m2();
            this.data[i][2] = assignation.getTotal_m2();
            i++;
        }

    }

    private void setUpTitle(){

        title = new JLabel();
        title .setText("List Assignation");
        title .setFont(fTitle);
        title .setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));

    }

    private void setUpButtonBar(){

        Dimension buttonDim = new Dimension(150, 80);

        //---- cancelButton ----
        JButton cancelButton = new JButton();
        cancelButton.setText("Cancel");
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


        buttonBar = new JPanel();
        FlowLayout layout = new FlowLayout();
        layout.setHgap(25);
        buttonBar.setLayout(layout);
        buttonBar.add(cancelButton);
        buttonBar.add(helpButton);
        buttonBar.add(Box.createHorizontalStrut(500));



    }

    private void setUpCenter(){
        centerPanel = new JPanel();
        this.data = new Object[assignation.size()][columnNames.length];

        changeTransferToCollection(assignation);

        JTable table = new JTable(data, columnNames);


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );



        table.setDefaultRenderer(Object.class, centerRenderer);
        table.setFont(fTable);
        int rowHeight = 50;
        table.setRowHeight(rowHeight);
        table.getTableHeader().setFont(fTable);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for(int i=0;i<table.getColumnCount();i++){
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
            TableColumn col = colModel.getColumn(i);
            int width = 0;

            TableCellRenderer renderer = col.getHeaderRenderer();
            if (renderer == null) {
                renderer = table.getTableHeader().getDefaultRenderer();
            }
            Component comp = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false,
                    false, 0, 0);
            width = comp.getPreferredSize().width + 10;
            col.setPreferredWidth(width+2);
        }
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(701, 500));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.add(scrollPane);
    }

    private void initComponents() {

        //======== this ========
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        ImageIcon img = new ImageIcon("Resources//Icon.png");
        this.setIconImage(img.getImage());

        //======== dialogPanel ========

        JPanel dialogPanel = new JPanel();
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
        setUpTitle();
        dialogPanel.add(title, BorderLayout.PAGE_START);

        //======== contentPanel ========

        setUpCenter();
        dialogPanel.add(centerPanel, BorderLayout.CENTER);

        //========= ButtonBar ========

        setUpButtonBar();
        dialogPanel.add(buttonBar, BorderLayout.PAGE_END);

        contentPane.add(dialogPanel, BorderLayout.CENTER);
        this.setVisible(true);
        pack();
        setLocationRelativeTo(getOwner());
    }

    @Override
    public void update(int event, Object data) {

    }
}
