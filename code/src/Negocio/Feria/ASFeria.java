package Negocio.Feria;

import Exceptions.ASException;
import Exceptions.DAOException;

import java.sql.SQLException;
import java.util.Collection;

public interface ASFeria {
    Integer create(Tferia feria) throws ASException, SQLException, DAOException, ClassNotFoundException;

    Integer drop(Tferia feria) throws ASException, DAOException;

    Integer modify(Tferia feria) throws ASException, DAOException;

    Collection<Tferia> list() throws ASException, DAOException;

    Collection<Tferia> listDates(Tferia feria) throws ASException, DAOException;

    Tferia showByName(Tferia feria) throws ASException, DAOException;

    Tferia showById(Tferia feria) throws ASException, DAOException;

}
