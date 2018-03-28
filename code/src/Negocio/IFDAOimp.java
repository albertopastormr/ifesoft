package Negocio;

public class IFDAOimp extends IFDAO {
    public DAOFeria generateDAOferia(){
        return new DAOFeriaImp();
    }
}
