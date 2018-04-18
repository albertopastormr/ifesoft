package Controller;

import Exceptions.ASException;
import Exceptions.DAOException;
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
import Presentacion.Create_Modify.Create.GUICreate;
import Presentacion.Create_Modify.Forms.*;
import Presentacion.Create_Modify.Modify.GUIModify;
import Presentacion.Drop.GUIDrop;
import Presentacion.Shows.GUIShow;
import Presentacion.GUIHome;
import Presentacion.UI;
import Presentacion.UIimp;
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
        this.gui = UI.getInstance();
    }

    @Override
    public void execute(int event, Object data) throws Exception {

        Tferia tFair = null;
        Tpabellon tPavilion = null;
        Tparticipante tClient = null;
        Tasignacion tAssignation = null;
        Tparticipacion tParticipation = null;
        Tstand tStand = null;

        switch (event){

            /** Main Views*/

            case Event.HOME:
                new GUIHome();
                break;

            /** Half Views*/

            case Event.CREATE_HALF:
                new GUICreate();
                break;
            case Event.MODIFY_HALF:
                new GUIModify();
                break;
            case Event.SHOW_HALF:
                new GUIShow();
                break;
            case Event.DROP_HALF:
                new GUIDrop();
                break;

            /** DROPS*/

            case Event.DROP_FERIA:
                try {
                    asFair.drop((Tferia)data);
                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;
            case Event.DROP_ASIGNACION:
                try {
                    asAssignation.drop((Tasignacion)data);
                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }
                break;
            case Event.DROP_PARTICIPACION:
                try {
                    asParticipation.drop((Tparticipacion)data);
                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }
                break;
            case Event.DROP_STAND:
                try {
                    asStand.drop((Tstand)data);
                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }
                break;
            case Event.DROP_CLIENT:
                try {
                    asClient.drop((Tparticipante)data);
                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;
            case Event.DROP_PABELLON:
                try {
                    asAssignation.drop((Tasignacion)data);
                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }
                break;

            /** Form Views*/

            case Event.INSERT_FORM_FERIA:
                new GUIFormFair();
                break;
            case Event.INSERT_FORM_ASIGNACION:
                new GUIFormAssignation();
                break;
            case Event.INSERT_FORM_PARTICIPANTE:
                new GUIFormClient();
                break;
            case Event.INSERT_FORM_PABELLON:
                new GUIFormPavilion();
                break;
            case Event.INSERT_FORM_STAND:
                new GUIFormStand();
                break;
            case Event.INSERT_FORM_PARTICIPACION:
                new GUIFormParticipation();
                break;


            /** HACER ESTO CON TODOS LOS MODULOS*/

            case Event.MODIFY_FORM_FERIA:
               try {
                    tFair = asFair.showById((Integer) data);
                    new GUIFormFair(tFair);
                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;
            case Event.MODIFY_FAIR:
                tFair = (Tferia) data;
                try {
                    int res = IFASFeria.getInstance().generateASferia().modify(tFair);
                    if (res>0) gui.update(EventGUI.UPDATE_UPDATE_FERIA_OK, res);
                    else gui.update(EventGUI.UPDATE_UPDATE_FERIA_FAIL, null);

                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;

            case Event.INSERT_FAIR:
                tFair = (Tferia) data;
                try {
                    int res = asFair.create(tFair);
                    if (res>0) gui.update(EventGUI.UPDATE_CREATE_FERIA_OK, res);
                    else gui.update(EventGUI.UPDATE_CREATE_FERIA_FAIL, null);

                } catch (ASException e) {
                    throw new Exception(e.getMessage() + ActionHelp.strHelpBasic());
                }
                break;

            case Event.INSERT_PAVILION:
                tPavilion = (Tpabellon) data;
                try {
                    int res = asPavilion.create(tPavilion);
                    if (res>0) gui.update(EventGUI.UPDATE_CREATE_PAVILION_OK, res);
                    else gui.update(EventGUI.UPDATE_CREATE_PAVILION_FAIL, null);

                } catch (ASException e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;

            case Event.INSERT_STAND:
                tStand = (Tstand) data;
                try {
                    int res = asStand.create(tStand);
                    if (res>0) gui.update(EventGUI.UPDATE_CREATE_STAND_OK, res);
                    else gui.update(EventGUI.UPDATE_CREATE_STAND_FAIL, null);

                } catch (ASException e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;

            case Event.INSERT_ASSIGNATION:
                tAssignation = (Tasignacion) data;
                try {
                    boolean res = asAssignation.create(tAssignation);
                    if (res) gui.update(EventGUI.UPDATE_CREATE_ASSIGNATION_OK, res);
                    else gui.update(EventGUI.UPDATE_CREATE_ASSIGNATION_FAIL, null);

                } catch (ASException e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;
            case Event.INSERT_PARTICIPACION:
                tParticipation = (Tparticipacion) data;
                try {
                    boolean res = asParticipation.create(tParticipation);
                    if (res) gui.update(EventGUI.UPDATE_CREATE_PARTICIPATION_OK, res);
                    else gui.update(EventGUI.UPDATE_CREATE_PARTICIPATION_FAIL, null);

                } catch (ASException e) {
                    throw new Exception(e.getMessage()+ ActionHelp.strHelpBasic());
                }
                break;

            /** ---------------------------- */

            case Event.SHOW_CLIENT_INDIVIDUAL:
                try {
                    asClient.showById((Tparticipante) data);
                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_CLIENT_LIST:
                try {
                    asClient.list();
                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_FAIR_INDIVIDUAL:
                try {
                    new GUIViewFair(asFair.showById((Integer) data));
                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_FAIR_LIST:
                try {
                    new GUIListFairs(asFair.list());
                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_FAIR_LIST_DATES:
                try {
                    asFair.listDates((Tferia) data);
                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_PAVILION_INDIVIDUAL:
                try {
                    asPavilion.showById((Integer)data);
                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_PAVILION_LIST:
                try {
                    asPavilion.list();
                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_ASSIGANTION_FAIR:
                try {
                    asAssignation.showByIdFair((Integer)data);
                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_ASSIGNATION_PAVILION:
                try {
                    asAssignation.showByIdPavilion((Integer)data);
                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_PARTICIPATION_FAIR:
                try {
                    asParticipation.showByFairId((Integer) data);
                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_PARTICIPACION_CLIENT:
                try {
                    asParticipation.showByClientId((Integer)data);
                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
