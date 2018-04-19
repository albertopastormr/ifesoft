package Negocio.Participacion;

import Exceptions.ASException;
import Exceptions.DAOException;
import Negocio.Feria.Tferia;
import Negocio.Participante.Tparticipante;

import java.util.Collection;

public interface ASParticipacion {
    Integer create(Tparticipacion participacion) throws ASException, DAOException;

    Integer drop(Tparticipacion participacion) throws ASException, DAOException;

    Integer modify(Tparticipacion participacion) throws ASException, DAOException;

    Collection<Tparticipacion> list() throws ASException, DAOException;

    Tparticipacion show(Integer participation_id) throws ASException, DAOException;

    Collection<Tparticipacion> showByFairId(Integer fair_id) throws ASException, DAOException;

    Collection<Tparticipacion> showByClientId(Integer client_id) throws ASException, DAOException;
}
