package Presentacion;

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
import Presentacion.views.forms.*;
import Presentacion.views.optionsPanel.ActionHelp;
import Presentacion.views.shows.List.ListFairs;
import Presentacion.views.shows.individual.ViewFair;
import Presentacion.views.viewsHalf.*;
import Presentacion.views.events.Event;
import Presentacion.views.events.EventGUI;

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
                new UIimp();
                break;

            /** Half Views*/

            case Event.CREATE_HALF:
                new ViewsHalfCreate();
                break;
            case Event.MODIFY_HALF:
                new ViewHalfModify();
                break;
            case Event.SHOW_HALF:
                new ViewHalfShow();
                break;
            case Event.DROP_HALF:
                new ViewHalfDrop();
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
                new ViewsFormFair();
                break;
            case Event.INSERT_FORM_ASIGNACION:
                new ViewsFormAssignation();
                break;
            case Event.INSERT_FORM_PARTICIPANTE:
                new ViewsFormClient();
                break;
            case Event.INSERT_FORM_PABELLON:
                new ViewsFormPavilion();
                break;
            case Event.INSERT_FORM_STAND:
                new ViewsFormStand();
                break;
            case Event.INSERT_FORM_PARTICIPACION:
                new ViewsFormParticipation();
                break;


            /** HACER ESTO CON TODOS LOS MODULOS*/

            case Event.MODIFY_FORM_FERIA:
               try {
<<<<<<< HEAD
                    tFair = asFair.showById((Tferia) data);
                    new ViewsFormFeria(tFair);
=======
                    tFeria = asFeria.showById((Tferia) data);
                    new ViewsFormFair(tFeria);
>>>>>>> a3d2ff1d069289d338f53e0ad38c39a79a83a6a3
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
                    new ViewFair(asFair.showById((Tferia) data));
                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_FAIR_LIST:
                try {
                    new ListFairs(asFair.list());
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
                    asPavilion.showById((Tpabellon)data);
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
                    asAssignation.showByIdFair((Tferia)data);
                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_ASSIGNATION_PAVILION:
                try {
                    asAssignation.showByIdPavilion((Tpabellon)data);
                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_PARTICIPATION_FAIR:
                try {
                    asParticipation.showByIdFair((Tferia)data);
                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_PARTICIPACION_CLIENT:
                try {
                    asParticipation.showByIdClient((Tparticipante)data);
                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
