package Presentacion.views.shows.List;

import Negocio.Asignacion.Tasignacion;
import Negocio.Pabellon.Tpabellon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collection;

public class ListAssignation extends JFrame {

    String[] columnNames = {"ID FAIR","ID PAVILION","ID STAND","USED M2"};
    Object[][] data;

    JTable table;

    Collection<Tasignacion> tAssignaction;


    public ListAssignation(Collection<Tasignacion> tassignation){
        super();
        this.setLayout(new BorderLayout());
        this.tAssignaction = tassignation;
        this.initGUI();
    }

    private void initGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        this.data = new Object[tAssignaction.size()][columnNames.length];

        changeTransferToCollection(tAssignaction);

        table = new JTable(data, columnNames);
        DefaultTableModel tableModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane pane = new JScrollPane(table);
        add(pane, BorderLayout.CENTER);


        table.setModel(tableModel);
        mainPanel.add(pane);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);

    }

    public void changeTransferToCollection(Collection<Tasignacion> transfer) {
        int i = 0;

        for (Tasignacion tassignation: transfer){
            this.data[i][0] = tassignation.getFair_id();
            this.data[i][1] = tassignation.getPavilion_id();
            this.data[i][2] = tassignation.getStand_id();
            this.data[i][3] = tassignation.getUsed_m2();
            i++;
        }

    }

}
