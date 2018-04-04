package Presentacion.Feria;

import Negocio.Feria.ASferia;
import Negocio.Feria.Tferia;
import Presentacion.Feria.views.*;
import Presentacion.Feria.views.forms.ViewsFormFeria;

public class ControllerImp implements Controller  {

    private ASferia asFeria;
    private UIimp gui;

    @Override
    public void execute(int event, Object data) {

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
            case Event.INSERT_FERIA:

                // HACER

                new ViewsFormFeria();
                break;
        }
    }
}
