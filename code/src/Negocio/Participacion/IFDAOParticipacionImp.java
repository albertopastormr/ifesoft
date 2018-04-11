package Negocio.Participacion;

import Integracion.Feria.DAOFeria;
import Integracion.Feria.DAOFeriaImp;
import Integracion.Participacion.DAOParticipacion;
import Integracion.Participacion.DAOParticipacionImp;
import Negocio.Feria.IFDAOFeria;

public class IFDAOParticipacionImp extends IFDAOParticipacion {
    public DAOParticipacion generateDAOparticipacion(){
        return new DAOParticipacionImp();
    }
}
