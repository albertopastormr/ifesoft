package Integracion.Pabellon;

public class IFDAOPabellonImp extends IFDAOPabellon {
    public DAOPabellon generateDAOpabellon(){
        return new DAOPabellonImp();
    }
}
