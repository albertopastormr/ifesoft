package Integracion.Asignacion;

public abstract class IFDAOAsignacion {
    private static IFDAOAsignacion instance;

    public static IFDAOAsignacion getInstance() {
        if (instance == null)
            instance = new IFDAOAsignacionImp();
        return instance;
    }

    public abstract DAOAsignacion generateDAOasignacion();
}
