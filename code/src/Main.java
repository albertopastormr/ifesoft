import Presentacion.GUIHome;

import javax.swing.*;

public class Main {
    public static void main(String[] args) { //crea una hebra de Swing
        SwingUtilities.invokeLater(new Runnable() {
            public void run() { new GUIHome(); }
        });
    }
}
