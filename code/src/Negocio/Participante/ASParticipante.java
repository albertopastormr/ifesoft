package Negocio.Participante;

import Exceptions.ASException;
import Exceptions.DAOException;

import java.util.Collection;

public interface ASParticipante {
    Integer create(Tparticipante participante) throws ASException;

    Integer drop(Tparticipante participante) throws ASException;

    Integer modify(Tparticipante participante) throws ASException;

    Collection<Tparticipante> list() throws ASException;

    Tparticipante showById(Tparticipante participante) throws ASException;
}
