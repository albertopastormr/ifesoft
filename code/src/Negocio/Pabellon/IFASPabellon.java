package Negocio.Pabellon;

import Negocio.Feria.IFASFeriaImp;

public abstract class IFASPabellon {
	private static IFASPabellon instance;

	public static IFASPabellon getInstance() {
		if (instance == null)
			instance = new IFASPabellonImp();
		return instance;
	}

	public abstract ASPabellon generateASPabellon();
}
