package Negocio.Feria;

import Exceptions.ASException;
import Exceptions.DAOException;

import java.sql.SQLException;
import java.util.Collection;

public interface ASferia {
    Integer create(Tferia feria) throws ASException, SQLException, DAOException;

    Integer drop(Tferia feria) throws ASException, DAOException;

    Integer modify(Tferia feria) throws ASException, DAOException;

    Collection<Tferia> list() throws DAOException;

    Tferia show(Tferia feria) throws ASException, DAOException;
}
