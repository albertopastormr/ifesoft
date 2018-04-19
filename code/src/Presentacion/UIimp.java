package Presentacion;

import Presentacion.Create_Modify.Create.GUICreate;
import Presentacion.Create_Modify.Forms.*;
import Presentacion.Events.EventGUI;

import javax.swing.*;

public class UIimp extends UI {

    private GUIHome guiHome;
    private GUICreate guiCreate;
    private GUIFormFair guiFormFair;
    private GUIFormPavilion guiFormPavilion;
    private GUIFormClient guiFormClient;
    private GUIFormParticipation guiFormParticipation;
    private GUIFormStand guiFormStand;
    private GUIFormAssignation guiFormAssignation;

    @Override
    public void update(int event, Object data) {
        switch (event){
            case EventGUI.UPDATE_CREATE_FERIA_OK:
                JOptionPane.showMessageDialog(null,"The Fair has been created successfully");
                guiHome = new GUIHome();
                guiHome.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_FERIA_FAIL:
                JOptionPane.showMessageDialog(null, "A problem in the creation process occurred, insert Fair's data another time please", "Error",
                        JOptionPane.ERROR_MESSAGE);
                guiFormFair = new GUIFormFair();
                guiFormFair.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_PAVILION_OK:
                JOptionPane.showMessageDialog(null, "The Pavilion has been created successfully");
                guiHome = new GUIHome();
                guiHome.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_PAVILION_FAIL:
                JOptionPane.showMessageDialog(null, "A problem in the creation process occurred, insert Pavilion's data another time please", "Error",
                        JOptionPane.ERROR_MESSAGE);
                guiFormPavilion = new GUIFormPavilion();
                guiFormPavilion.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_CLIENT_OK:
                JOptionPane.showMessageDialog(null, "The Client has been created successfully");
                guiHome = new GUIHome();
                guiHome.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_CLIENT_FAIL:
                JOptionPane.showMessageDialog(null, "A problem in the creation process occurred, insert Client's data another time please", "Error",
                        JOptionPane.ERROR_MESSAGE);
                guiFormClient = new GUIFormClient();
                guiFormClient.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_PARTICIPATION_OK:
                JOptionPane.showMessageDialog(null, "The Participation has been created successfully");
                guiHome = new GUIHome();
                guiHome.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_PARTICIPATION_FAIL:
                JOptionPane.showMessageDialog(null, "A problem in the creation process occurred, insert Participation's data another time please", "Error",
                        JOptionPane.ERROR_MESSAGE);
                guiFormParticipation = new GUIFormParticipation();
                guiFormParticipation.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_STAND_OK:
                JOptionPane.showMessageDialog(null,"The Stand has been created successfully");
                guiHome = new GUIHome();
                guiHome.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_STAND_FAIL:
                JOptionPane.showMessageDialog(null, "A problem in the creation process occurred, insert Stand's data another time please", "Error",
                        JOptionPane.ERROR_MESSAGE);
                guiFormStand = new GUIFormStand();
                guiFormStand.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_ASSIGNATION_OK:
                JOptionPane.showMessageDialog(null,"The Assignation has been created successfully");
                guiHome = new GUIHome();
                guiHome.update(event, data);
                break;
            case EventGUI.UPDATE_CREATE_ASSIGNATION_FAIL:
                JOptionPane.showMessageDialog(null, "A problem in the creation process occurred, insert Assignation's data another time please", "Error",
                        JOptionPane.ERROR_MESSAGE);
                guiFormAssignation = new GUIFormAssignation();
                guiFormAssignation.update(event, data);
                break;
        }
    }
}
