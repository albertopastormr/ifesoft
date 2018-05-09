package Presentacion.Shows.List;

import Negocio.Pabellon.Tpabellon;
import Controller.Controller;
import Presentacion.Events.Event;
import Presentacion.UIStructureFrame;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collection;

public class GUIListPavilions extends UIStructureFrame{

    private String[] columnNames = {"NUMBER","TOTAL M2","UTIL M2","CAPACITY"};
    private Object[][] data;

    private Collection<Tpabellon> tPavilions;

    private Font fTitle  = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fTable = new Font(Font.DIALOG, Font.PLAIN, 24);

    public GUIListPavilions(Collection<Tpabellon> tpavilions){
        super("List Pavilions");
        this.tPavilions = tpavilions;
        this.initComponents();
    }

    @Override
    protected void okButtonActionPerformed(ActionEvent e) throws Exception {

    }

    @Override
    protected void cancelButtonActionPerformed(ActionEvent e) throws Exception {
        this.setVisible(false);
        Controller.getInstance().execute(Event.SHOW_HALF, null);
    }

    @Override
    protected void helpButtonActionPerformed(ActionEvent e) {

    }

    private void changeTransferToCollection(Collection<Tpabellon> transfer) {
        int i = 0;

        for (Tpabellon tpavilion: transfer){
            this.data[i][0] = tpavilion.getId();
            this.data[i][1] = tpavilion.getTotal_m2();
            this.data[i][2] = tpavilion.getCapacity();
            i++;
        }

    }

    @Override
    protected void setUpTitle(){

        title = new JLabel();
        title .setText("List Pavilions");
        title .setFont(fTitle);
        title .setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));

    }

    @Override
    protected void setUpCenter(){
        centerPanel = new JPanel();
        this.data = new Object[tPavilions.size()][columnNames.length];

        changeTransferToCollection(tPavilions);

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
            int width;

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
        scrollPane.setPreferredSize(new Dimension(480, 500));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.add(scrollPane);
    }

    @Override
    public void update(int event, Object data) {

    }
}
