package Negocio.Stand;

import Integracion.Feria.DAOFeria;
import Integracion.Stand.DAOStand;
import Negocio.Feria.IFDAOFeriaImp;

public abstract class IFDAOStand {
    private static IFDAOStand instance;

    public static IFDAOStand getInstance() {
        if (instance == null)
            instance = new IFDAOStandImp();
        return instance;
    }

    public abstract DAOStand generateDAOstand();
}
