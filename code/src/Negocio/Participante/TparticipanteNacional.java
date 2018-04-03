package Negocio.Participante;

public class TparticipanteNacional extends Tparticipante {

	private String region;

	public TparticipanteNacional(String region) {
		this.region = region;
	}

	public TparticipanteNacional(int id, String name, long phone, Boolean active, String region) {
		super(id, name, phone, active);
		this.region = region;
	}

	public TparticipanteNacional(String name, long phone, Boolean active, String region) {
		super(name, phone, active);
		this.region = region;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
}
