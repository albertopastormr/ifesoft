package Negocio;

public abstract class IFDAO {
    private static IFDAO instance;

    public static IFDAO getInstance() {
        if (instance == null)
            instance = new IFDAOimp();
        return instance;
    }

    public abstract DAOFeria generateDAOferia();
}
