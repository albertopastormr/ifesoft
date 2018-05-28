package Integracion.Participante;

public class IFDAOParticipanteImp extends IFDAOParticipante {
    public DAOParticipante generateDAOparticipante(){
        return new DAOParticipanteImp();
    }
}
