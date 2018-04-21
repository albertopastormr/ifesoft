import Presentacion.Home.IFHome;

import javax.swing.*;

public class Main {
    public static void main(String[] args) { //crea una hebra de Swing
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                IFHome.getInstance().generateHome();
            }
        });
    }
}
