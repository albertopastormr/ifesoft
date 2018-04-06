package Presentacion.Feria;

import Exceptions.ASException;
import Exceptions.DAOException;
import Negocio.Feria.ASferia;
import Negocio.Feria.Tferia;
import Presentacion.Feria.views.*;
import Presentacion.Feria.views.events.Event;
import Presentacion.Feria.views.events.EventGUI;
import Presentacion.Feria.views.forms.ViewsFormFeria;

import java.sql.SQLException;

public class ControllerImp implements Controller  {

    private ASferia asFeria;
    private UI gui;

    @Override
    public void execute(int event, Object data) {

        Tferia tFeria = null;

        switch (event){

            /** Main Views*/

            case Event.HOME:
            case Event.BACK_CREATE_HALF_FERIA:
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
            case Event.DROP_HALF_FERIA:
                new ViewDropVerificate();
                break;


            /** Form Views*/

            case Event.INSERT_FORM_FERIA:
                new ViewsFormFeria();
                break;
            case Event.MODIFY_FORM_FERIA:
                try {
                    tFeria = asFeria.show((Tferia) data);
                    new ViewsFormFeria(tFeria);

                } catch (ASException | DAOException e) {
                    e.printStackTrace();
                }
                break;
            case Event.INSERT_FERIA:
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
