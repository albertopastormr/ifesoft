package Negocio.Asignacion;

public abstract class IFASAsignacion {
	private static IFASAsignacion instance;

	public static IFASAsignacion getInstance() {
		if (instance == null)
			instance = new IFASAsignacionImp();
		return instance;
	}

	public abstract ASAsignacion generateASAsignacion();
}
