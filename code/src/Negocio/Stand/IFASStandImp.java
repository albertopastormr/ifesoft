package Negocio.Stand;

import Negocio.Stand.ASStand;
import Negocio.Stand.ASStandImp;
import Negocio.Stand.IFASStand;

public class IFASStandImp extends IFASStand {
	@Override
	public ASStand generateASStand() {
		return new ASStandImp();
	}
}
