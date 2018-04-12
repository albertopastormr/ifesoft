package Negocio.Feria;

public class IFASFeriaImp extends IFASFeria {
	@Override
	public ASFeria generateASferia() {
		return new ASFeriaImp();
	}
}
