package Negocio.Participante;


public class TparticipanteInternacional extends Tparticipante {
	private String country;

	public TparticipanteInternacional(String country) {
		this.country = country;
	}

	public TparticipanteInternacional(int id, String name, long phone, Boolean active, String country) {
		super(id, name, phone, active);
		this.country = country;
	}

	public TparticipanteInternacional(String name, long phone, Boolean active, String country) {
		super(name, phone, active);
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
