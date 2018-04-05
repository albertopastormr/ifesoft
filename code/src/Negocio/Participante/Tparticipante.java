package Negocio.Participante;

import java.sql.Date;

public class Tparticipante {

    private int id;
    private String name;
    private long phone;
    private Boolean active;

    public Tparticipante() {
    	id = -1;
        name = null;
        phone = -1;
        active = false;
    }

	public Tparticipante(int id, String name, long phone, Boolean active) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.active = active;
	}

	public Tparticipante(String name, long phone,  Boolean active) {
		id = -1;
		this.name = name;
		this.phone = phone;
		this.active = active;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
