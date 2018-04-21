package Controller;

import Negocio.Asignacion.ASAsignacion;
import Negocio.Asignacion.IFASAsignacion;
import Negocio.Asignacion.Tasignacion;
import Negocio.Feria.ASFeria;
import Negocio.Feria.IFASFeria;
import Negocio.Feria.Tferia;
import Negocio.Pabellon.ASPabellon;
import Negocio.Pabellon.IFASPabellon;
import Negocio.Pabellon.Tpabellon;
import Negocio.Participacion.ASParticipacion;
import Negocio.Participacion.IFASParticipacion;
import Negocio.Participacion.Tparticipacion;
import Negocio.Participante.ASParticipante;
import Negocio.Participante.IFASParticipante;
import Negocio.Participante.Tparticipante;
import Negocio.Stand.ASStand;
import Negocio.Stand.IFASStand;
import Negocio.Stand.Tstand;
import Presentacion.Create_Modify.IFCreateModifyForm;
import Presentacion.Drop.IFDrop;
import Presentacion.Home.IFHome;
import Presentacion.Shows.IFViewList;
import Presentacion.UI;
import Presentacion.Utils.ActionHelp;
import Presentacion.Shows.List.GUIListFairs;
import Presentacion.Shows.individual.GUIViewFair;
import Presentacion.Events.Event;
import Presentacion.Events.EventGUI;

public class ControllerImp extends Controller {

    private ASFeria asFair;
    private ASAsignacion asAssignation;
    private ASPabellon asPavilion;
    private ASStand asStand;
    private ASParticipante asClient;
    private ASParticipacion asParticipation;
    private UI gui;

    public ControllerImp(){
        this.asFair = IFASFeria.getInstance().generateASferia();
        this.asAssignation = IFASAsignacion.getInstance().generateASAsignacion();
        this.asPavilion = IFASPabellon.getInstance().generateASPabellon();
        this.asStand = IFASStand.getInstance().generateASStand();
        this.asClient = IFASParticipante.getInstance().generateASParticipante();
        this.asParticipation = IFASParticipacion.getInstance().generateASParticipacion();
    }

    @Override
    public void execute(int event, Object data) throws Exception {

        Tferia tFair;
        Tpabellon tPavilion;
        Tparticipante tClient;
        Tasignacion tAssignation;
        Tparticipacion tParticipation;
        Tstand tStand;

        switch (event){

            /** -------- Main Views -------- */

            case Event.HOME:
                IFHome.getInstance().generateHome();
                break;

            /** -------- Views to select the different modules -------- */

            case Event.CREATE_HALF:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;
            case Event.MODIFY_HALF:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;
            case Event.SHOW_HALF:
                IFViewList.getInstance().generateSpecificView(event, data);
                break;
            case Event.DROP_HALF:
                IFDrop.getInstance().generateSpecificView(event, data);
                break;



            /** -------- Insertion forms -------- */

            case Event.INSERT_FORM_FAIR:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;
            case Event.INSERT_FORM_PAVILION:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;
            case Event.INSERT_FORM_CLIENT:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;
            case Event.INSERT_FORM_ASSIGNATION:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;
            case Event.INSERT_FORM_PARTICIPATION:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;
            case Event.INSERT_FORM_STAND:
                IFCreateModifyForm.getInstance().generateSpecificView(event, data);
                break;



            /** -------- Modification forms -------- */

            case Event.MODIFY_FORM_FAIR:
                try {
                    tFair = asFair.showById((Integer) data);
                    IFCreateModifyForm.getInstance().generateSpecificView(event, tFair);
                } catch (Exception e) { throw new Exception(e.getMessage() + ActionHelp.strHelpBasic()); }
                break;
            case Event.MODIFY_FORM_PAVILION:
                try {
                    tPavilion = asPavilion.showById((Integer) data);
                    IFCreateModifyForm.getInstance().generateSpecificView(event, tPavilion);
                } catch (Exception e) { throw new Exception(e.getMessage() + ActionHelp.strHelpBasic()); }
                break;
            case Event.MODIFY_FORM_CLIENT:
                try {
                    tClient = asClient.showById((Tparticipante) data);
                    IFCreateModifyForm.getInstance().generateSpecificView(event, tClient);
                } catch (Exception e) { throw new Exception(e.getMessage() + ActionHelp.strHelpBasic()); }
                break;
            case Event.MODIFY_FORM_ASSIGNATION:
                try {
                    tAssignation = asAssignation.show((Integer) data);
                    IFCreateModifyForm.getInstance().generateSpecificView(event, tAssignation);
                } catch (Exception e) { throw new Exception(e.getMessage() + ActionHelp.strHelpBasic()); }
                break;
            case Event.MODIFY_FORM_PARTICIPATION:
                try {
                    tParticipation = asParticipation.show((Integer) data);
                    IFCreateModifyForm.getInstance().generateSpecificView(event, tParticipation);
                } catch (Exception e) { throw new Exception(e.getMessage() + ActionHelp.strHelpBasic()); }
                break;
            case Event.MODIFY_FORM_STAND:
                try {
                    tStand = asStand.showById((Integer) data);
                    IFCreateModifyForm.getInstance().generateSpecificView(event, tStand);
                } catch (Exception e) { throw new Exception(e.getMessage() + ActionHelp.strHelpBasic()); }
                break;



            /** -------- Inserts modules -------- */

            case Event.INSERT_FAIR:
                tFair = (Tferia) data;
                try {
                    int res = asFair.create(tFair);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_FERIA_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_FERIA_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage() + ActionHelp.strHelpBasic());
                }
                break;
            case Event.INSERT_PAVILION:
                tPavilion = (Tpabellon) data;
                try {
                    int res = asPavilion.create(tPavilion);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_PAVILION_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_PAVILION_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.INSERT_CLIENT:
                tClient = (Tparticipante) data;
                try {
                    int res = asClient.create(tClient);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_CLIENT_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_CLIENT_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.INSERT_ASSIGNATION:
                tAssignation = (Tasignacion) data;
                try {
                    int res = asAssignation.create(tAssignation);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_ASSIGNATION_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_ASSIGNATION_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;

            case Event.INSERT_PARTICIPACION:
                tParticipation = (Tparticipacion) data;
                try {
                    int res = asParticipation.create(tParticipation);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_PARTICIPATION_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_PARTICIPATION_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.INSERT_STAND:
                tStand = (Tstand) data;
                try {
                    int res = asStand.create(tStand);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_STAND_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CREATE_STAND_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;



            /** -------- Modify modules -------- */

            case Event.MODIFY_FAIR:
                tFair = (Tferia) data;
                try {
                    int res = asFair.modify(tFair);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_FERIA_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_FERIA_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.MODIFY_PAVILION:
                tPavilion = (Tpabellon) data;
                try {
                    int res = asPavilion.modify(tPavilion);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_PAVILION_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_PAVILION_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.MODIFY_CLIENT:
                tClient = (Tparticipante) data;
                try {
                    int res = asClient.modify(tClient);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CLIENT_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_CLIENT_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.MODIFY_ASSIGNATION:
                tAssignation = (Tasignacion) data;
                try {
                    int res = asAssignation.modify(tAssignation);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_ASSIGNATION_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_ASSIGNATION_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.MODIFY_PARTICIPACION:
                tParticipation = (Tparticipacion) data;
                try {
                    int res = asParticipation.modify(tParticipation);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_PARTICIPATION_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_PARTICIPATION_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.MODIFY_STAND:
                tStand = (Tstand) data;
                try {
                    int res = asStand.modify(tStand);
                    if (res>0) IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_STAND_OK, res);
                    else IFCreateModifyForm.getInstance().generateSpecificView(event, data).update(EventGUI.UPDATE_STAND_FAIL, null);
                } catch (Exception e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;



            /** -------- Drops modules -------- */

            case Event.DROP_FAIR:
                try {
                    asFair.drop((Tferia)data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.DROP_PAVILION:
                try {
                    asAssignation.drop((Tasignacion)data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.DROP_CLIENT:
                try {
                    asClient.drop((Tparticipante)data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.DROP_ASSIGNATION:
                try {
                    asAssignation.drop((Tasignacion)data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.DROP_PARTICIPATION:
                try {
                    asParticipation.drop((Tparticipacion)data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.DROP_STAND:
                try {
                    asStand.drop((Tstand)data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;


            /** -------- Individuals shows -------- */

            case Event.SHOW_FAIR_INDIVIDUAL:
                try {
                    new GUIViewFair(asFair.showById((Integer) data));
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_PAVILION_INDIVIDUAL:
                try {
                    asPavilion.showById((Integer)data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_CLIENT_INDIVIDUAL:
                try {
                    asClient.showById((Tparticipante) data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_ASSIGNATION_INDIVIDUAL:
                try {
                    asAssignation.show((Integer) data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_PARTICIPATION_INDIVIDUAL:
                try {
                    asParticipation.show((Integer) data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_STAND_INDIVIDUAL:
                try {
                    asStand.showById((Integer) data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;

            /** -------- List shows -------- */

            case Event.SHOW_FAIR_LIST:
                try {
                    new GUIListFairs(asFair.list());
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_FAIR_LIST_DATES:
                try {
                    asFair.listDates((Tferia) data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_PAVILION_LIST:
                try {
                    asPavilion.list();
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_CLIENT_LIST:
                try {
                    asClient.list();
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_ASSIGANTION_FAIR:
                try {
                    asAssignation.showByIdFair((Integer)data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_ASSIGNATION_PAVILION:
                try {
                    asAssignation.showByIdPavilion((Integer)data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_PARTICIPATION_FAIR:
                try {
                    asParticipation.showByFairId((Integer) data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_PARTICIPACION_CLIENT:
                try {
                    asParticipation.showByClientId((Integer)data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_STAND_ASSIGNATION:
                try {
                    asStand.showByAssignation((Integer) data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;
            case Event.SHOW_STAND_PARTICIPATION:
                try {
                    asStand.showByParticipation((Integer)data);
                } catch (Exception e) { throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic()); }
                break;

            case Event.SHOW_REGION_PABELLON:

                break;
            case Event.SHOW_PAIS_PABELLON:

                break;
        }
    }
}

// SHOW hacer factoria para esta y gestionarla
// Empezar a hacer pruebas
