package Presentacion.Drop;

import Presentacion.Events.Event;
import Presentacion.Home.GUIHome;
import Presentacion.Home.IFHome;
import Presentacion.Shows.GUIShow;
import Presentacion.UI;

public class IFDropImp extends IFDrop {

    @Override
    public UI generateSpecificView(int event, Object data) {
        switch (event){
            case Event.DROP_HALF:
                return new GUIDrop();
            default:
                return new GUIHome();

        }
    }
}
