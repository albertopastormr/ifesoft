package Presentacion;

import Exceptions.ASException;
import Exceptions.DAOException;
import Negocio.Asignacion.ASAsignacion;
import Negocio.Asignacion.Tasignacion;
import Negocio.Feria.ASFeria;
import Negocio.Feria.Tferia;
import Negocio.Pabellon.ASPabellon;
import Negocio.Pabellon.Tpabellon;
import Negocio.Participacion.ASParticipacion;
import Negocio.Participacion.Tparticipacion;
import Negocio.Participante.ASParticipante;
import Negocio.Participante.Tparticipante;
import Negocio.Stand.ASStand;
import Negocio.Stand.Tstand;
import Presentacion.utils.Utilities;
import Presentacion.views.forms.*;
import Presentacion.views.shows.List.ListFairs;
import Presentacion.views.viewsHalf.*;
import Presentacion.views.events.Event;
import Presentacion.views.events.EventGUI;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

public class ControllerImp extends Controller {

    private ASFeria asFeria;
    private ASAsignacion asAsignation;
    private ASPabellon asPavilion;
    private ASStand asStand;
    private ASParticipante asClient;
    private ASParticipacion asParticipation;
    private UI gui;

    @Override
    public void execute(int event, Object data) {

        Tferia tFeria = null;

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
                    asFeria.drop((Tferia)data);
                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;
            case Event.DROP_ASIGNACION:
                try {
                    asAsignation.drop((Tasignacion)data);
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
                    asAsignation.drop((Tasignacion)data);
                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }
                break;

            /** Form Views*/

            case Event.INSERT_FORM_FERIA:
                new ViewsFormFeria();
                break;
            case Event.INSERT_FORM_ASIGNACION:
                new ViewsFormAsignacion();
                break;
            case Event.INSERT_FORM_PARTICIPANTE:
                new ViewsFormParticipante();
                break;
            case Event.INSERT_FORM_PABELLON:
                new ViewsFormPabellon();
                break;
            case Event.INSERT_FORM_STAND:
                new ViewsFormStand();
                break;
            case Event.INSERT_FORM_PARTICIPACION:
                new ViewsFormParticipacion();
                break;


            /** HACER ESTO CON TODOS LOS MODULOS*/

            case Event.MODIFY_FORM_FERIA:
               try {
                    tFeria = asFeria.showById((Tferia) data);
                    new ViewsFormFeria(tFeria);
                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;
            case Event.MODIFY_FAIR:
                tFeria = (Tferia) data;
                try {
                    int res = asFeria.modify(tFeria);
                    if (res>0) gui.update(EventGUI.UPDATE_UPDATE_FERIA_OK, res);
                    else gui.update(EventGUI.UPDATE_UPDATE_FERIA_FAIL, null);

                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;

            case Event.INSERT_FAIR:
                tFeria = (Tferia) data;
                try {
                    int res = asFeria.create(tFeria);
                    if (res>0) gui.update(EventGUI.UPDATE_CREATE_FERIA_OK, res);
                    else gui.update(EventGUI.UPDATE_CREATE_FERIA_FAIL, null);

                } catch (ASException e) {
                    e.printStackTrace();
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
                    asFeria.showById((Tferia) data);
                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_FAIR_LIST:
                try {
                    new ListFairs(asFeria.list());
                } catch (ASException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_FAIR_LIST_DATES:
                try {
                    asFeria.listDates((Tferia) data);
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
                    asAsignation.showByIdFair((Tferia)data);
                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }
                break;
            case Event.SHOW_ASSIGNATION_PAVILION:
                try {
                    asAsignation.showByIdPavilion((Tpabellon)data);
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
