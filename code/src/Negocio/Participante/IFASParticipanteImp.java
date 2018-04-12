package Negocio.Participante;

import Negocio.Participante.ASParticipante;
import Negocio.Participante.ASParticipanteImp;
import Negocio.Participante.IFASParticipante;

public class IFASParticipanteImp extends IFASParticipante {
	@Override
	public ASParticipante generateASParticipante() {
		return new ASParticipanteImp();
	}
}
