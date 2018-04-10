package Negocio.Feria;

import Integracion.Feria.DAOFeria;

public abstract class IFDAOFeria {
    private static IFDAOFeria instance;

    public static IFDAOFeria getInstance() {
        if (instance == null)
            instance = new IFDAOFeriaImp();
        return instance;
    }

    public abstract DAOFeria generateDAOferia();
}
