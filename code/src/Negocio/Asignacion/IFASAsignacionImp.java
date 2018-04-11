package Negocio.Asignacion;

import Negocio.Asignacion.ASAsignacion;
import Negocio.Asignacion.IFASAsignacion;

public class IFASAsignacionImp extends IFASAsignacion {
	@Override
	public ASAsignacion generateASAsignacion() {
		return new ASAsignacionImp();
	}
}
