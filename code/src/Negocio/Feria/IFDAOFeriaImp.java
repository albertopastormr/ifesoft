package Negocio.Feria;

import Integracion.Feria.DAOFeria;
import Integracion.Feria.DAOFeriaImp;

public class IFDAOFeriaImp extends IFDAOFeria {
    public DAOFeria generateDAOferia(){
        return new DAOFeriaImp();
    }
}
