package Presentacion.Shows.List;

import Controller.Controller;
import Negocio.Feria.Tferia;
import Presentacion.Events.Event;
import Presentacion.UIStructureFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class GUIListFairs extends UIStructureFrame {

    private String[] columnNames = {"ID FAIR","NAME","DESCRIPTION","DATE START","DATE END"};
    private Object[][] data;

    private Collection<Tferia> fair;

    private Font fTitle  = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fTable = new Font(Font.DIALOG, Font.PLAIN, 24);

    public GUIListFairs(Collection<Tferia> fair){
        super("List Fairs");
        this.fair = fair;
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

    private void changeTransferToCollection(Collection<Tferia> transfer) {
        int i = 0;

        for (Tferia tferia: transfer){
            this.data[i][0] = tferia.getId();
            this.data[i][1] = tferia.getName();
            this.data[i][2] = tferia.getDescription();
            this.data[i][3] = tferia.getIniDate();
            this.data[i][4] = tferia.getEndDate();
            i++;
        }

    }

    @Override
    protected void setUpTitle(){
        title = new JLabel();
        title .setText("List Fairs");
        title .setFont(fTitle);
        title .setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));

    }

    @Override
    protected void setUpCenter(){
        centerPanel = new JPanel();
        this.data = new Object[fair.size()][columnNames.length];

        changeTransferToCollection(fair);

        JTable table = new JTable(data, columnNames){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
            Component component = super.prepareRenderer(renderer, row, column);
            int rendererWidth = component.getPreferredSize().width + 10;
            TableColumn tableColumn = getColumnModel().getColumn(column);
            tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
            return component;
        }};


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
            Component comp1 = renderer.getTableCellRendererComponent(table, col.getHeaderValue(), false,
                    false, 0, 0);

            width = comp1.getPreferredSize().width + 10;
            col.setPreferredWidth(width+2);
        }
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);

        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(1042, 500));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.add(scrollPane);
    }

    @Override
    public void update(int event, Object data) {

    }
}
