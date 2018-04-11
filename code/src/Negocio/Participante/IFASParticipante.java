package Negocio.Participante;

import Negocio.Participante.ASParticipante;

public abstract class IFASParticipante {
	private static IFASParticipante instance;

	public static IFASParticipante getInstance() {
		if (instance == null)
			instance = new IFASParticipanteImp();
		return instance;
	}

	public abstract ASParticipante generateASParticipante();
}
