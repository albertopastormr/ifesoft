package Negocio.Asignacion;

import java.sql.Date;

public class Tasignacion {

    private int fair_id; // Fair ==> Feria
    private int pavilion_id; // Pavilion ==> Pabellon
    private int stand_id;
    private int used_m2;
    private Boolean active;

	public Tasignacion() {
		this.fair_id = -1;
		this.pavilion_id = -1;
		this.stand_id = -1;
		this.used_m2 = -1;
		this.active = null;
	}

	public Tasignacion(int fair_id, int pavilion_id, int stand_id, int used_m2, Boolean active) {
		this.fair_id = fair_id;
		this.pavilion_id = pavilion_id;
		this.stand_id = stand_id;
		this.used_m2 = used_m2;
		this.active = active;
	}

	public int getFair_id() {
		return fair_id;
	}

	public void setFair_id(int fair_id) {
		this.fair_id = fair_id;
	}

	public int getPavilion_id() {
		return pavilion_id;
	}

	public void setPavilion_id(int pavilion_id) {
		this.pavilion_id = pavilion_id;
	}

	public int getStand_id() {
		return stand_id;
	}

	public void setStand_id(int stand_id) {
		this.stand_id = stand_id;
	}

	public int getUsed_m2() {
		return used_m2;
	}

	public void setUsed_m2(int used_m2) {
		this.used_m2 = used_m2;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
