package Negocio.Asignacion;

import Exceptions.ASException;
import Exceptions.DAOException;

import java.util.Collection;

public interface ASAsignacion {
    Integer create(Tasignacion asignacion) throws ASException, DAOException;

    Integer drop(Tasignacion asignacion) throws ASException, DAOException;

    Integer modify(Tasignacion asignacion) throws ASException, DAOException;

    Collection<Tasignacion> list() throws ASException, DAOException;

    Tasignacion show(Tasignacion asignacion) throws ASException, DAOException;
}
