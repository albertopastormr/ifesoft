package Presentacion;

import Presentacion.Create_Modify.Forms.*;
import Presentacion.Events.EventGUI;

private GUIHome guiHome;
private GUICreate guiCreate;
private GUIFormFair guiFormFair;
private GUIFormPavilion guiFormPavilion;
private GUIFormClient guiFormClient;
private GUIFormParticipation guiFormParticipation;
private GUIFormStand guiFormStand;
private GUIFormAssignation guiFormAssignation;

import javax.swing.*;

public class IFASUpdate {

    private static IFASUpdate instance;

    public static IFASUpdate getInstance() {
        if (instance == null)
            instance = new IFASUpdateImp();
        return instance;
    }

    @Override
    public void update(int event, Object data) {
        switch (event){
            case EventGUI.UPDATE_CREATE_FERIA_OK:
                guiHome = new GUIHome();
                guiHome.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_FERIA_FAIL:
                guiFormFair = new GUIFormFair();
                guiFormFair.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_PAVILION_OK:
                guiHome = new GUIHome();
                guiHome.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_PAVILION_FAIL:
                guiFormPavilion = new GUIFormPavilion();
                guiFormPavilion.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_CLIENT_OK:
                guiHome = new GUIHome();
                guiHome.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_CLIENT_FAIL:
                guiFormClient = new GUIFormClient();
                guiFormClient.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_PARTICIPATION_OK:
                guiHome = new GUIHome();
                guiHome.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_PARTICIPATION_FAIL:
                guiFormParticipation = new GUIFormParticipation();
                guiFormParticipation.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_STAND_OK:
                guiHome = new GUIHome();
                guiHome.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_STAND_FAIL:
                guiFormStand = new GUIFormStand();
                guiFormStand.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_ASSIGNATION_OK:
                guiHome = new GUIHome();
                guiHome.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_ASSIGNATION_FAIL:
                guiFormAssignation = new GUIFormAssignation();
                guiFormAssignation.update(event, data);
                break;
        }
    }

    public abstract ASUpdate generateASUpdate();
}
