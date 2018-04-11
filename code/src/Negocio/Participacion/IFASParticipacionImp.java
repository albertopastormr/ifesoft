package Negocio.Participacion;

import Negocio.Participacion.ASParticipacion;
import Negocio.Participacion.ASParticipacionImp;
import Negocio.Participacion.IFASParticipacion;

public class IFASParticipacionImp extends IFASParticipacion {
	@Override
	public ASParticipacion generateASParticipacion() {
		return new ASParticipacionImp();
	}
}
