package Negocio.Stand;

import Negocio.Stand.ASStand;

public abstract class IFASStand {
	private static IFASStand instance;

	public static IFASStand getInstance() {
		if (instance == null)
			instance = new IFASStandImp();
		return instance;
	}

	public abstract ASStand generateASStand();
}
