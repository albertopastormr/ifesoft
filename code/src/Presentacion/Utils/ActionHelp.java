package Presentacion.Utils;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class ActionHelp extends JOptionPane {

    private String message;

    public ActionHelp(String message) {
        super();
        this.message = message;
        iniGUI();
    }

    public static String strHelpBasic() {
        return "\nFor more information press Help button.";
    }

    private void iniGUI() {

        JLabel label = new JLabel(message);
        label.setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
        JOptionPane optionPane = new JOptionPane();
        optionPane.setMessage(message);
        showMessageDialog(null,label,"Help",JOptionPane.PLAIN_MESSAGE);
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font(Font.DIALOG,Font.PLAIN,20)));
        UIManager.put("OptionPane.okButtonText", "OK");


    }

    public int getMaxCharactersPerLineCount() {
        return 50;
    }
}
