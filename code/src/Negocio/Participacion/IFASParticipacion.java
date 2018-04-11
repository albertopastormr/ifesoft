package Negocio.Participacion;

public abstract class IFASParticipacion {
	private static IFASParticipacion instance;

	public static IFASParticipacion getInstance() {
		if (instance == null)
			instance = new IFASParticipacionImp();
		return instance;
	}

	public abstract ASParticipacion generateASParticipacion();
}
