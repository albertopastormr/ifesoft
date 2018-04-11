package Negocio.Pabellon;

import Integracion.Feria.DAOFeria;
import Integracion.Pabellon.DAOPabellon;
import Negocio.Feria.IFDAOFeriaImp;

public abstract class IFDAOPabellon {
    private static IFDAOPabellon instance;

    public static IFDAOPabellon getInstance() {
        if (instance == null)
            instance = new IFDAOPabellonImp();
        return instance;
    }

    public abstract DAOPabellon generateDAOpabellon();
}
