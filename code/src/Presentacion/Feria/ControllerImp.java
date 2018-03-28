package Presentacion.Feria;


import Negocio.Feria.ASferia;

public class ControllerImp implements Controller  {

    private ASferia asFeria;
    private UI gui;

    @Override
    public void execute(int event, Object data) {

        switch (event){
            case Event.INSERT_FERIA:

                // Lanzamos lo que sea de modelo
                break;
            case Event.DROP_FERIA:

                break;
            case Event.WRITE_DATA_FERIA:

                break;
        }
    }
}
