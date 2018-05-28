package Integracion.Participante;

public abstract class IFDAOParticipante {
    private static IFDAOParticipante instance;

    public static IFDAOParticipante getInstance() {
        if (instance == null)
            instance = new IFDAOParticipanteImp();
        return instance;
    }

    public abstract DAOParticipante generateDAOparticipante();
}
