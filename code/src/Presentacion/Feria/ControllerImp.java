package Presentacion.Feria;

import Negocio.Feria.ASferia;
import Presentacion.Feria.views.*;

public class ControllerImp implements Controller  {

    private ASferia asFeria;
    private UIimp gui;

    @Override
    public void execute(int event, Object data) {

        switch (event){
            case Event.HOME:
            case Event.BACK_CREATE_HALF_FERIA:
                new UIimp();
                // Lanzamos lo que sea de modelo
                break;
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
            case Event.INSERT_FORM_FERIA:
                new ViewsFormCreateFeria();
                break;
            case Event.DROP_HALF_FERIA:
                new ViewDropVerificate();
                break;
        }
    }
}
