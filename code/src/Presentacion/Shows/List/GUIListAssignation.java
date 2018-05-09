package Presentacion.Shows.List;

import Negocio.Asignacion.Tasignacion;
import Controller.Controller;
import Presentacion.Events.Event;
import Presentacion.UIStructureFrame;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collection;

public class GUIListAssignation extends UIStructureFrame {

    private String[] columnNames = {"ID ASSIGNATION" ,"ID FAIR","ID PAVILION", "USED M2", "TOTAL M2"};
    private Object[][] data;

    private Collection<Tasignacion> assignation;

    private Font fTitle  = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fTable = new Font(Font.DIALOG, Font.PLAIN, 24);

    public GUIListAssignation(Collection<Tasignacion> assignation){
        super("List Assignation");
        this.assignation = assignation;
        this.initComponents();
    }

    @Override
    protected void okButtonActionPerformed(ActionEvent e) throws Exception {

    }

    public void cancelButtonActionPerformed(ActionEvent e) throws Exception {
        this.setVisible(false);
        Controller.getInstance().execute(Event.SHOW_HALF, null);
    }

    protected void helpButtonActionPerformed(ActionEvent e) {

    }

    private void changeTransferToCollection(Collection<Tasignacion> transfer) {
        int i = 0;

        for (Tasignacion assignation: transfer){
            this.data[i][0] = assignation.getId();
            this.data[i][1] = assignation.getFair_id();
            this.data[i][2] = assignation.getPavilion_id();
            this.data[i][3] = assignation.getUsed_m2();
            this.data[i][4] = assignation.getTotal_m2();
            i++;
        }

    }

    protected void setUpTitle(){
        title = new JLabel();
        title .setText("List Assignation");
        title .setFont(fTitle);
        title .setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));

    }

    protected void setUpCenter(){
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

    @Override
    public void update(int event, Object data) {

    }
}
