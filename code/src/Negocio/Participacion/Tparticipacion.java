package Negocio.Participacion;

import java.sql.Date;

public class Tparticipacion {

	private int fair_id; // Fair ==> Feria
	private int client_id; // Client ==> participante
	private Boolean active;

	public Tparticipacion() {
		this.fair_id = -1;
		this.client_id = -1;
		this.active = null;
	}

	public Tparticipacion(int fair_id, int client_id, Boolean active) {
		this.fair_id = fair_id;
		this.client_id = client_id;
		this.active = active;
	}

	public int getFair_id() {
		return fair_id;
	}

	public void setFair_id(int fair_id) {
		this.fair_id = fair_id;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
