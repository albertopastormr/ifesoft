package Negocio.Stand;

public class Tstand {

    private int id;
    private int assignation_id;
    private int participation_id;
    private int num_at_fair;
    private double cost;
    private int total_m2;
    private Boolean active;

	public Tstand() {
		this.id = -1;
		this.assignation_id = -1;
		this.participation_id = -1;
		this.num_at_fair = -1;
		this.cost = -1;
		this.total_m2 = -1;
		this.active = null;
	}

	public Tstand(int assignation_id, int participation_id, int num_at_fair, double cost, int total_m2, Boolean active) {
		this.id = -1;
		this.assignation_id = assignation_id;
		this.participation_id = participation_id;
		this.num_at_fair = num_at_fair;
		this.cost = cost;
		this.total_m2 = total_m2;
		this.active = active;
	}

	public Tstand(int id, int assignation_id, int participation_id, int num_at_fair, double cost, int total_m2, Boolean active) {
		this.id = id;
		this.assignation_id = assignation_id;
		this.participation_id = participation_id;

		this.num_at_fair = num_at_fair;
		this.cost = cost;
		this.total_m2 = total_m2;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAssignation_id() {
		return assignation_id;
	}

	public void setAssignation_id(int assignation_id) {
		this.assignation_id = assignation_id;
	}

	public int getParticipation_id() {
		return participation_id;
	}

	public void setParticipation_id(int participation_id) {
		this.participation_id = participation_id;
	}

	public int getNum_at_fair() {
		return num_at_fair;
	}

	public void setNum_at_fair(int num_at_fair) {
		this.num_at_fair = num_at_fair;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getTotal_m2() {
		return total_m2;
	}

	public void setTotal_m2(int total_m2) {
		this.total_m2 = total_m2;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
