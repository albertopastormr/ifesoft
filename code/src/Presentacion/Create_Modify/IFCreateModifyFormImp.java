package Presentacion.Create_Modify;

import Presentacion.Create_Modify.Create.GUICreate;
import Presentacion.Create_Modify.Modify.GUIModify;
import Presentacion.Events.Event;
import Presentacion.UI;

public class IFCreateModifyFormImp extends IFCreateModifyForm {

    @Override
    public UI generateSpecificView(int event, Object data) {
        switch (event){
            case Event.CREATE_HALF:
                return new GUICreate();
            case Event.MODIFY_FAIR:
                return new GUIModify();
        }
        return null;
    }
}
