package Integracion.Asignacion;

public class IFDAOAsignacionImp extends IFDAOAsignacion {
    public DAOAsignacion generateDAOasignacion(){
        return new DAOAsignacionImp();
    }
}
