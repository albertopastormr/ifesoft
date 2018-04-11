package Negocio.Asignacion;

import Integracion.Asignacion.DAOAsignacion;
import Integracion.Asignacion.DAOAsignacionImp;
import Integracion.Feria.DAOFeria;
import Integracion.Feria.DAOFeriaImp;
import Negocio.Feria.IFDAOFeria;

public class IFDAOAsignacionImp extends IFDAOAsignacion {
    public DAOAsignacion generateDAOasignacion(){
        return new DAOAsignacionImp();
    }
}
