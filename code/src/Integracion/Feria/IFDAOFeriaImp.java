package Integracion.Feria;

public class IFDAOFeriaImp extends IFDAOFeria {
    public DAOFeria generateDAOferia(){
        return new DAOFeriaImp();
    }
}
