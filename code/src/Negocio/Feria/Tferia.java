package Negocio.Feria;

import java.util.Date;

public class Tferia {

    private int id;
    private String name;
    private String description;
    private Date iniDate;
    private Date endDate;
    private Boolean active;

    public Tferia(int id, String name, String description, Date iniDate, Date endDate, Boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.iniDate = iniDate;
        this.endDate = endDate;
        this.active = active;
    }

    public Tferia(String name, String description, Date iniDate, Date endDate, Boolean active) {
        this.id = -1;
        this.name = name;
        this.description = description;
        this.iniDate = iniDate;
        this.endDate = endDate;
        this.active = active;
    }

    public Tferia(String name, String description, Date iniDate, Date endDate) { //usado en DAOFeriaImp.create()
        this.id = -1;
        this.name = name;
        this.description = description;
        this.iniDate = iniDate;
        this.endDate = endDate;
        this.active = true;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getIniDate() {
        return iniDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Boolean getActive() {
        return active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIniDate(Date iniDate) {
        this.iniDate = iniDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean equals(Tferia feria) {
        return name.equals(feria.name) && iniDate == feria.iniDate && endDate == feria.endDate;
    }
}
