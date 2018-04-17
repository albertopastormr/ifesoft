package Presentacion.views.optionsPanel;

import javax.swing.*;

public class ActionHelp extends JOptionPane {

    String message;

    public ActionHelp(String message) {
        super();
        this.message = message;
        iniGUI();
    }

    public static String strHelpBasic() {
        return "\nPara mas informacion pulsa el boton de ayuda.";
    }

    private void iniGUI() {
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(message);
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog(null, "Help");
        dialog.setVisible(true);
    }

    public int getMaxCharactersPerLineCount() {
        return 50;
    }
}
