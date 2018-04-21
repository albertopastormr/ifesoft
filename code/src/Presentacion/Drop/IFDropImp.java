package Presentacion.Drop;

import Presentacion.Events.Event;
import Presentacion.Shows.GUIShow;
import Presentacion.UI;

public class IFDropImp extends IFDrop {

    @Override
    public UI generateSpecificView(int event, Object data) {
        switch (event){
            case Event.DROP_FAIR:
                return new GUIDrop();

        }
        return null;
    }
}
