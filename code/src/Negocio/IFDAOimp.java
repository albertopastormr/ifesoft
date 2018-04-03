package Negocio;

import Integracion.Feria.DAOFeria;
import Integracion.Feria.DAOFeriaImp;

public class IFDAOimp extends IFDAO {
    public DAOFeria generateDAOferia(){
        return new DAOFeriaImp();
    }
}
