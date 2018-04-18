package Presentacion.Shows.List;

import Negocio.Participante.Tparticipante;
import Presentacion.UI;

import javax.swing.*;
import java.util.Collection;

public class GUIListClient extends JFrame implements UI {

    String[] columnNames = {"NAME","PHONE NUMBER"};
    Object[][] data;

    Collection<Tparticipante> tclient;


    public GUIListClient(Collection<Tparticipante> tclient){
        super();
        this.tclient = tclient;
        this.initGUI();
    }

    private void initGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        this.data = new Object[tclient.size()][columnNames.length];

        changeTransferToCollection(tclient);

        new GUITableList(mainPanel, columnNames, data);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);

    }

    public void changeTransferToCollection(Collection<Tparticipante> transfer) {
        int i = 0;

        for (Tparticipante tclient: transfer){
            this.data[i][0] = tclient.getName();
            this.data[i][1] = tclient.getPhone();
            i++;
        }

    }


    @Override
    public void update(int event, Object data) {

    }
}
