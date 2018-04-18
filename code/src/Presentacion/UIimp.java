package Presentacion;

import Presentacion.Create_Modify.Create.GUICreate;
import Presentacion.Create_Modify.Forms.GUIFormFair;
import Presentacion.Events.EventGUI;

import javax.swing.*;

public class UIimp extends UI {

    private static GUIHome guiHome;
    private GUICreate guiCreate;
    private GUIFormFair guiFormFair;

    @Override
    public void update(int event, Object data) {
        switch (event){
            case EventGUI.UPDATE_CREATE_FERIA_OK:
                System.out.println("Yeeeeee");
                guiHome = new GUIHome();
                guiHome.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_FERIA_FAIL:

                break;

        }
    }
}
