package Negocio.Feria;

import java.sql.*;

public class Tferia {
    private String name;
    private String description;
    private Time iniTime;
    private Time endTime;
    private Boolean active;

    public Tferia() {
        name = null;
        description = null;
        iniTime = null;
        endTime = null;
        active = false;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setNombre(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFecha_ini(Time iniTime) {
        this.iniTime = iniTime;
    }

    public void setFecha_fin(Time endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Time getIniTime() {
        return iniTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public Boolean getActive() {
        return active;
    }

}
