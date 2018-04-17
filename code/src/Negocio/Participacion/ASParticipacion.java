package Negocio.Participacion;

import Exceptions.ASException;
import Exceptions.DAOException;
import Negocio.Feria.Tferia;
import Negocio.Participante.Tparticipante;

import java.util.Collection;

public interface ASParticipacion {
    boolean create(Tparticipacion participacion) throws ASException, DAOException;

    boolean drop(Tparticipacion participacion) throws ASException, DAOException;

    boolean modify(Tparticipacion participacion) throws ASException, DAOException;

    Collection<Tparticipacion> list() throws ASException, DAOException;

    Tparticipacion show(Integer fair_id, Integer client_id, Integer stand_id) throws ASException, DAOException;

    Collection<Tparticipacion> showByFairId(Integer fair_id) throws ASException, DAOException;

    Collection<Tparticipacion> showByClientId(Integer client_id) throws ASException, DAOException;
}
