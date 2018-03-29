package Presentacion.Feria;

import Negocio.Feria.ASferia;
import Presentacion.Feria.views.ViewsHalfCreate;

public class ControllerImp implements Controller  {

    private ASferia asFeria;
    private UIimp gui;

    @Override
    public void execute(int event, Object data) {

        switch (event){
            case Event.HOME:

                // Lanzamos lo que sea de modelo
                break;
            case Event.CREATE_HALF:
                new ViewsHalfCreate();
                break;
            case Event.MODIFY_HALF:

                break;
            case Event.SHOW_HALF:

                break;
            case Event.DROP_HALF:

                break;
        }
    }
}
