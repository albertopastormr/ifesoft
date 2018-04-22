package Negocio.Participacion;

import Exceptions.ASException;
import Exceptions.DAOException;
import Negocio.Feria.Tferia;
import Negocio.Participante.Tparticipante;

import java.util.Collection;

public interface ASParticipacion {
    Integer create(Tparticipacion participacion) throws ASException;

    Integer drop(Tparticipacion participacion) throws ASException;

    Integer modify(Tparticipacion participacion) throws ASException;

    Collection<Tparticipacion> list() throws ASException;

    Tparticipacion show(Integer participation_id) throws ASException;

    Collection<Tparticipacion> showByFairId(Integer fair_id) throws ASException;

    Collection<Tparticipacion> showByClientId(Integer client_id) throws ASException;
}
