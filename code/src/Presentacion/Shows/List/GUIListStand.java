package Presentacion.Shows.List;

import Negocio.Stand.Tstand;
import Presentacion.UI;
import Presentacion.UIimp;

import javax.swing.*;
import java.util.Collection;

public class GUIListStand extends UIimp {

    String[] columnNames = {"COST","NUMBER AT FEAR","M2"};
    Object[][] data;

    Collection<Tstand> tstand;


    public GUIListStand(Collection<Tstand> tferias){
        super();
        this.tstand = tferias;
        this.initGUI();
    }

    private void initGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        this.data = new Object[tstand.size()][columnNames.length];

        changeTransferToCollection(tstand);

        new GUITableList(mainPanel, columnNames, data);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);

    }

    public void changeTransferToCollection(Collection<Tstand> transfer) {
        int i = 0;

        for (Tstand tstand: transfer){
            this.data[i][0] = tstand.getCost();
            this.data[i][1] = tstand.getNum_at_fair();
            this.data[i][2] = tstand.getTotal_m2();
            i++;
        }

    }

    @Override
    public void update(int event, Object data) {

    }
}
