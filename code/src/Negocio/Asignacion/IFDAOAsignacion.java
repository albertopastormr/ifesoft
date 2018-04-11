package Negocio.Asignacion;

import Integracion.Asignacion.DAOAsignacion;
import Integracion.Feria.DAOFeria;
import Negocio.Feria.IFDAOFeriaImp;

public abstract class IFDAOAsignacion {
    private static IFDAOAsignacion instance;

    public static IFDAOAsignacion getInstance() {
        if (instance == null)
            instance = new IFDAOAsignacionImp();
        return instance;
    }

    public abstract DAOAsignacion generateDAOasignacion();
}
