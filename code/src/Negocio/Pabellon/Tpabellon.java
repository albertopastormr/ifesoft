package Negocio.Pabellon;

import java.sql.Date;

public class Tpabellon {

    private int id;
    private int capacity;
    private int total_m2;
    private int util_m2;
    private Boolean active;

	public Tpabellon() {
		this.id = -1;
		this.capacity = -1;
		this.total_m2 = -1;
		this.util_m2 = -1;
		this.active = null;
	}

	public Tpabellon(int id, int capacity, int total_m2, int util_m2, Boolean active) {
		this.id = id;
		this.capacity = capacity;
		this.total_m2 = total_m2;
		this.util_m2 = util_m2;
		this.active = active;
	}

	public Tpabellon(int capacity, int total_m2, int util_m2, Boolean active) {
		this.id = -1;
		this.capacity = capacity;
		this.total_m2 = total_m2;
		this.util_m2 = util_m2;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getTotal_m2() {
		return total_m2;
	}

	public void setTotal_m2(int total_m2) {
		this.total_m2 = total_m2;
	}

	public int getUtil_m2() {
		return util_m2;
	}

	public void setUtil_m2(int util_m2) {
		this.util_m2 = util_m2;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
