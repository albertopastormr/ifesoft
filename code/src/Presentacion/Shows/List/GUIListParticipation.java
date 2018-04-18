package Presentacion.Shows.List;

import Negocio.Participacion.Tparticipacion;
import Presentacion.UI;

import javax.swing.*;
import java.util.Collection;

public class GUIListParticipation extends JFrame implements UI {

    String[] columnNames = {"ID FAIR","ID CLIENT","ID STAND","USED M2"};
    Object[][] data;

    Collection<Tparticipacion> tparticipation;


    public GUIListParticipation(Collection<Tparticipacion> tassignation){
        super();
        this.tparticipation = tassignation;
        this.initGUI();
    }

    private void initGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        this.data = new Object[tparticipation.size()][columnNames.length];

        changeTransferToCollection(tparticipation);

        new GUITableList(mainPanel, columnNames, data);

        this.add(mainPanel);
        this.pack();
        this.setVisible(true);

    }

    public void changeTransferToCollection(Collection<Tparticipacion> transfer) {
        int i = 0;

        for (Tparticipacion tparticipation: transfer){
            this.data[i][0] = tparticipation.getFair_id();
            this.data[i][1] = tparticipation.getClient_id();
            this.data[i][2] = tparticipation.getStand_id();
            i++;
        }

    }

    @Override
    public void update(int event, Object data) {

    }
}
