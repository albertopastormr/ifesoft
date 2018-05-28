package Integracion.Stand;

public class IFDAOStandImp extends IFDAOStand {
    public DAOStand generateDAOstand(){
        return new DAOStandImp();
    }
}
