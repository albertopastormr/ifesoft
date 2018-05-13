package Negocio.Pabellon;

import Exceptions.ASException;
import Exceptions.DAOException;

import java.util.Collection;

public interface ASPabellon {
    Integer create(Tpabellon pabellon) throws ASException;

    Integer drop(Integer id) throws ASException;

    Integer modify(Tpabellon pabellon) throws ASException;

    Collection<Tpabellon> list() throws ASException;

    Tpabellon showById(Integer id) throws ASException;
}
