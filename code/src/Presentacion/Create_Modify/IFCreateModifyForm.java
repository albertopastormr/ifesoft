package Presentacion.Create_Modify;

import Presentacion.UI;

public abstract class IFCreateModifyForm {

    private static IFCreateModifyForm instance;

    public static IFCreateModifyForm getInstance() {
        if (instance == null)
            instance = new IFCreateModifyFormImp();
        return instance;
    }

    public abstract UI generateSpecificView(int event, Object data);

}
