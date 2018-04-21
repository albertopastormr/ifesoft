package Presentacion.Home;

import Presentacion.UI;

public class IFHomeImp extends IFHome {
    @Override
    public UI generateHome() {
        return new GUIHome();
    }
}
