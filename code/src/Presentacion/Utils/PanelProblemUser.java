package Presentacion.utils;

import javax.swing.*;

public class PanelProblemUser extends JFrame {

    public PanelProblemUser(String errorMessage){
        super(errorMessage);
        showProblem(errorMessage);
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
