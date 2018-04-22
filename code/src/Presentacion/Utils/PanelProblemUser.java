package Presentacion.Utils;

import Presentacion.Home.IFHome;

import javax.swing.*;

public class PanelProblemUser extends JFrame {

    public PanelProblemUser(String errorMessage){
        super(errorMessage);
        showProblem(errorMessage);
        IFHome.getInstance().generateHome();
    }

    private void showProblem(String errorMessage) {

        JOptionPane.showMessageDialog(
               this,
                errorMessage,
                "Informative Panel",
                JOptionPane.WARNING_MESSAGE
        );
    }


}
