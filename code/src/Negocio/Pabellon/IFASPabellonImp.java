package Negocio.Pabellon;

import Negocio.Pabellon.ASPabellon;
import Negocio.Pabellon.ASPabellonImp;
import Negocio.Pabellon.IFASPabellon;

public class IFASPabellonImp extends IFASPabellon {
	@Override
	public ASPabellon generateASPabellon() {
		return new ASPabellonImp();
	}
}
