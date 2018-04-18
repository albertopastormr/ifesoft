package Presentacion.Shows.List;

import Presentacion.UI;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GUITableList extends AbstractTableModel implements UI {

    private String[] columnNames;
    private Object[][] rowData;
    private int initNumOfRows;
    private int currNumOfCols;

    public GUITableList(JPanel mainPanel , String[] columnNames , Object[][] data) {
        this.columnNames = columnNames;
        this.initNumOfRows = 1;
        iniComponet(mainPanel, data);
    }

    public GUITableList(JPanel mainPanel , String[] columnNames , Object[][] data, int initNumOfRows, int currNumOfCols) {
        this.columnNames = columnNames;
        this.initNumOfRows = initNumOfRows;
        this.currNumOfCols = currNumOfCols;
        iniComponet(mainPanel, data);
    }

    void iniComponet(JPanel mainPanel, Object[][] data){
        DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(dtm);

        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        JScrollPane scroll = new JScrollPane(table);
        mainPanel.add(scroll);
    }


    @Override
    public int getRowCount() {
        return rowData.length;
    }

    @Override
    public int getColumnCount() {
        return currNumOfCols;
    }
    @Override
    public Object getValueAt(int row, int col) {
        return rowData[row][col];
    }

    @Override
    public String getColumnName(int col) {
        return this.columnNames[col];
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        rowData[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    public void printTable() {
        for (int i = 0; i < rowData.length; i++) {
            for (int j = 0; j < columnNames.length; j++)
                System.out.print(rowData[i][j] + " ");
            System.out.println();
        }
    }

    public void incrNumOfCols() {
        if (currNumOfCols < columnNames.length) {
            currNumOfCols++;
            fireTableStructureChanged();
        }
    }

    @Override
    public void update(int event, Object data) {

    }
}
