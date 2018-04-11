package Negocio.Participacion;

import Exceptions.ASException;
import Exceptions.DAOException;

import java.util.Collection;

public interface ASParticipacion {
    Integer create(Tparticipacion participacion) throws ASException, DAOException;

    Integer drop(Tparticipacion participacion) throws ASException, DAOException;

    Integer modify(Tparticipacion participacion) throws ASException, DAOException;

    Collection<Tparticipacion> list() throws ASException, DAOException;

    Tparticipacion show(Tparticipacion participacion) throws ASException, DAOException;
}
