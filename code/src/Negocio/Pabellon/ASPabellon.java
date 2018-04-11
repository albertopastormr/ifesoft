package Negocio.Pabellon;

import Exceptions.ASException;
import Exceptions.DAOException;

import java.util.Collection;

public interface ASPabellon {
    Integer create(Tpabellon pabellon) throws ASException, DAOException;

    Integer drop(Tpabellon pabellon) throws ASException, DAOException;

    Integer modify(Tpabellon pabellon) throws ASException, DAOException;

    Collection<Tpabellon> list() throws ASException, DAOException;

    Tpabellon show(Tpabellon pabellon) throws ASException, DAOException;
}
