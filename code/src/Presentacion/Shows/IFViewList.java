package Presentacion.Shows;

import Presentacion.UI;

public abstract class IFViewList {

    private static IFViewList instance;

    public static IFViewList getInstance() {
        if (instance == null)
            instance = new IFViewListImp();
        return instance;
    }

    public abstract UI generateSpecificView(int event, Object data);

}
