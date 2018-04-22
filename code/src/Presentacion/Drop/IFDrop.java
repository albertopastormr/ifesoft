package Presentacion.Drop;

import Presentacion.UI;

public abstract class IFDrop {

    private static IFDrop instance;

    public static IFDrop getInstance() {
        if (instance == null)
            instance = new IFDropImp();
        return instance;
    }

    public abstract UI generateSpecificView(int event, Object data);

}
