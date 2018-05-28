package Integracion.Participacion;

public class IFDAOParticipacionImp extends IFDAOParticipacion {
    public DAOParticipacion generateDAOparticipacion(){
        return new DAOParticipacionImp();
    }
}
