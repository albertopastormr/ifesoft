package Negocio.Participacion;

import Integracion.Feria.DAOFeria;
import Integracion.Participacion.DAOParticipacion;
import Negocio.Feria.IFDAOFeriaImp;

public abstract class IFDAOParticipacion {
    private static IFDAOParticipacion instance;

    public static IFDAOParticipacion getInstance() {
        if (instance == null)
            instance = new IFDAOParticipacionImp();
        return instance;
    }

    public abstract DAOParticipacion generateDAOparticipacion();
}
