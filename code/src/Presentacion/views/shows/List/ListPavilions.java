package Presentacion.views.shows.List;

import Negocio.Feria.Tferia;
import Negocio.Pabellon.Tpabellon;

import javax.swing.*;
import java.util.Collection;

public class ListPavilions extends JFrame {

    String[] columnNames = {"NUMBER","TOTAL M2","UTIL M2","CAPACITY"};
    Object[][] data;

    Collection<Tpabellon> tPavilions;


    public ListPavilions(Collection<Tpabellon> tpavilions){
        super();
        this.tPavilions = tpavilions;
        this.initGUI();
    }

    private void initGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        this.data = new Object[tPavilions.size()][columnNames.length];

        changeTransferToCollection(tPavilions);

        new TableList(mainPanel, columnNames, data);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);

    }

    public void changeTransferToCollection(Collection<Tpabellon> transfer) {
        int i = 0;

        for (Tpabellon tpavilion: transfer){
            this.data[i][0] = tpavilion.getId();
            this.data[i][1] = tpavilion.getTotal_m2();
            this.data[i][2] = tpavilion.getUtil_m2();
            this.data[i][3] = tpavilion.getCapacity();
            i++;
        }

    }

}
