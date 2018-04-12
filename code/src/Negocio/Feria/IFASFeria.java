package Negocio.Feria;

public abstract class IFASFeria {
	private static IFASFeria instance;

	public static IFASFeria getInstance() {
		if (instance == null)
			instance = new IFASFeriaImp();
		return instance;
	}

	public abstract ASFeria generateASferia();
}
