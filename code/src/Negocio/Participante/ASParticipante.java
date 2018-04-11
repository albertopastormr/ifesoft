package Negocio.Participante;

import Exceptions.ASException;
import Exceptions.DAOException;

import java.util.Collection;

public interface ASParticipante {
    Integer create(Tparticipante participante) throws ASException, DAOException;

    Integer drop(Tparticipante participante) throws ASException, DAOException;

    Integer modify(Tparticipante participante) throws ASException, DAOException;

    Collection<Tparticipante> list() throws ASException, DAOException;

    Tparticipante show(Tparticipante participante) throws ASException, DAOException;
}
