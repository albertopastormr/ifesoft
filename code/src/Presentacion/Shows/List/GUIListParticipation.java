package Presentacion.Shows.List;

import Controller.Controller;
import Negocio.Participacion.Tparticipacion;
import Presentacion.Events.Event;
import Presentacion.UIStructureFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collection;

public class GUIListParticipation extends UIStructureFrame{

    private String[] columnNames = {"ID PARTICIPATION","ID FAIR","ID CLIENT", "ACTIVE"};
    private Object[][] data;

    private Collection<Tparticipacion> participation;

    private Font fTitle  = new Font(Font.MONOSPACED, Font.BOLD, 80);
    private Font fTable = new Font(Font.DIALOG, Font.PLAIN, 24);

    public GUIListParticipation(Collection<Tparticipacion> participation){
        super("List Participations");
        this.participation = participation;
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

    private void changeTransferToCollection(Collection<Tparticipacion> transfer) {
        int i = 0;

        for (Tparticipacion tParticipation: transfer){
            this.data[i][0] = tParticipation.getId();
            this.data[i][1] = tParticipation.getFair_id();
            this.data[i][2] = tParticipation.getClient_id();
            this.data[i][3] = tParticipation.getActive();
            i++;
        }

    }

    @Override
    protected void setUpTitle(){

        title = new JLabel();
        title .setText("List Participation");
        title .setFont(fTitle);
        title .setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 70, 0));

    }

    @Override
    protected void setUpCenter(){
        centerPanel = new JPanel();
        this.data = new Object[participation.size()][columnNames.length];

        changeTransferToCollection(participation);

        JTable table = new JTable(data, columnNames);


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );

        table.setEnabled(false);

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
        scrollPane.setPreferredSize(new Dimension(768, 500));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        centerPanel.add(scrollPane);
    }

    @Override
    public void update(int event, Object data) {

    }
}
