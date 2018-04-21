package Presentacion.Create_Modify;

import Negocio.Asignacion.Tasignacion;
import Negocio.Feria.Tferia;
import Negocio.Pabellon.Tpabellon;
import Negocio.Participacion.Tparticipacion;
import Negocio.Participante.Tparticipante;
import Negocio.Stand.Tstand;
import Presentacion.Create_Modify.Create.GUICreate;
import Presentacion.Create_Modify.Forms.*;
import Presentacion.Create_Modify.Modify.GUIModify;
import Presentacion.Events.Event;
import Presentacion.Home.GUIHome;
import Presentacion.UI;

public class IFCreateModifyFormImp extends IFCreateModifyForm {

    @Override
    public UI generateSpecificView(int event, Object data) {
        switch (event){
            case Event.CREATE_HALF:
                return new GUICreate();
            case Event.MODIFY_FAIR:
                return new GUIModify();

            /** -------- INSERT FORMS -------- */

            case Event.INSERT_FORM_FAIR:
                return new GUIFormFair();
            case Event.INSERT_FORM_ASSIGNATION:
                return new GUIFormAssignation();
            case Event.INSERT_FORM_CLIENT:
                return new GUIFormClient();
            case Event.INSERT_FORM_PAVILION:
                return new GUIFormPavilion();
            case Event.INSERT_FORM_STAND:
                return new GUIFormStand();
            case Event.INSERT_FORM_PARTICIPATION:
               return new GUIFormParticipation();

            /** -------- MODIFY FORMS -------- */

            case Event.MODIFY_FORM_FAIR:
                return new GUIFormFair((Tferia) data);
            case Event.MODIFY_FORM_PAVILION:
                return new GUIFormPavilion((Tpabellon) data);
            case Event.MODIFY_FORM_CLIENT:
                return new GUIFormClient((Tparticipante) data);
            case Event.MODIFY_FORM_ASSIGNATION:
                return new GUIFormAssignation((Tasignacion) data);
            case Event.MODIFY_FORM_PARTICIPATION:
                return new GUIFormParticipation((Tparticipacion) data);
            case Event.MODIFY_FORM_STAND:
                return new GUIFormStand((Tstand) data);







            default:
                // In case of error
                return new GUIHome();
        }
    }
}
