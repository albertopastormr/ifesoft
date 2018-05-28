package Integracion.Participacion;

public abstract class IFDAOParticipacion {
    private static IFDAOParticipacion instance;

    public static IFDAOParticipacion getInstance() {
        if (instance == null)
            instance = new IFDAOParticipacionImp();
        return instance;
    }

    public abstract DAOParticipacion generateDAOparticipacion();
}
