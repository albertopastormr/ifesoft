package Presentacion.Shows;

import Presentacion.Create_Modify.Create.GUICreate;
import Presentacion.Create_Modify.Modify.GUIModify;
import Presentacion.Events.Event;
import Presentacion.UI;

public class IFViewListImp extends IFViewList {

    @Override
    public UI generateSpecificView(int event, Object data) {
        switch (event){
            case Event.SHOW_HALF:
                return new GUIShow();

        }
        return null;
    }
}
