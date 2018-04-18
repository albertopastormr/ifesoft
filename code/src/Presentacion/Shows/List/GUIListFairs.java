package Presentacion.Shows.List;

import Negocio.Feria.Tferia;
import Presentacion.UI;

import javax.swing.*;
import java.util.*;

public class GUIListFairs extends JFrame implements UI {

    String[] columnNames = {"NAME","DESCRIPTION","DATE START","DATE END"};
    Object[][] data;

    Collection<Tferia> tferias;


    public GUIListFairs(Collection<Tferia> tferias){
        super();
        this.tferias = tferias;
        this.initGUI();
    }

    private void initGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        this.data = new Object[tferias.size()][columnNames.length];

        changeTransferToCollection(tferias);

        new GUITableList(mainPanel, columnNames, data);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);

    }

    public void changeTransferToCollection(Collection<Tferia> transfer) {
        int i = 0;

        for (Tferia tferia: transfer){
            this.data[i][0] = tferia.getName();
            this.data[i][1] = tferia.getDescription();
            this.data[i][2] = tferia.getIniDate();
            this.data[i][3] = tferia.getEndDate();
            i++;
        }

    }

    @Override
    public void update(int event, Object data) {

    }
}
