package Negocio.Stand;

import Integracion.Stand.DAOStand;
import Integracion.Stand.DAOStandImp;
import Negocio.Stand.IFDAOStand;

public class IFDAOStandImp extends IFDAOStand {
    public DAOStand generateDAOstand(){
        return new DAOStandImp();
    }
}
