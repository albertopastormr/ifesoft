package Negocio.Pabellon;

import Integracion.Feria.DAOFeria;
import Integracion.Feria.DAOFeriaImp;
import Integracion.Pabellon.DAOPabellon;
import Integracion.Pabellon.DAOPabellonImp;
import Negocio.Feria.IFDAOFeria;

public class IFDAOPabellonImp extends IFDAOPabellon {
    public DAOPabellon generateDAOpabellon(){
        return new DAOPabellonImp();
    }
}
