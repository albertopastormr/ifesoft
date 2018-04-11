package Presentacion;

import Exceptions.ASException;
import Exceptions.DAOException;
import Negocio.Feria.ASFeria;
import Negocio.Feria.Tferia;
import Presentacion.views.forms.*;
import Presentacion.views.viewsHalf.*;
import Presentacion.views.events.Event;
import Presentacion.views.events.EventGUI;

import java.sql.SQLException;

public class ControllerImp extends Controller {

    private ASFeria asFeria;
    //private ASasignation asAsignation;
    //private ASpavilion asPavilion;
    //private ASstand asStand;
    //private ASClient asClient;
    //private ASpaticipation asParticipation;
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

            /* NO BORRAR - DESCOMENTAR CUANDO ESTEN HECHOS LOS "AS"


            case Event.DROP_FERIA:
                try {
                    asFeria.drop((Tferia)data);
                } catch (ASException | DAOException e) {
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
                } catch (ASException | DAOException e) {
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
            */

            /** Form Views*/

            case Event.INSERT_FORM_FERIA:
                //new ViewsFormFeria();
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

            case Event.MODIFY_FORM_FERIA:
               /* try {
                    tFeria = asFeria.show((Tferia) data);
                    //new ViewsFormFeria(tFeria);

                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }*/
                break;
            case Event.INSERT_FAIR:
                tFeria = (Tferia) data;
                try {
                    int res = asFeria.create(tFeria);
                    if (res>0) gui.update(EventGUI.UPDATE_CREATE_FERIA_OK, res);
                    else gui.update(EventGUI.UPDATE_CREATE_FERIA_FAIL, null);

                } catch (ASException | ClassNotFoundException | DAOException | SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
