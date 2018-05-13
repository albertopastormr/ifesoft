package Negocio.Feria;

import Exceptions.ASException;
import Exceptions.DAOException;

import java.sql.SQLException;
import java.util.Collection;

public interface ASFeria {
    Integer create(Tferia feria) throws ASException;

    Integer drop(Integer id) throws ASException;

    Integer modify(Tferia feria) throws ASException;

    Collection<Tferia> list() throws ASException;

    Collection<Tferia> listDates(Tferia feria) throws ASException;

    Tferia showByName(String name) throws ASException;

    Tferia showById(Integer id) throws ASException;

}
