package Negocio.Asignacion;

import Exceptions.ASException;
import Exceptions.DAOException;
import Negocio.Feria.Tferia;
import Negocio.Pabellon.Tpabellon;
import Negocio.Participante.Tparticipante;

import java.util.Collection;

public interface ASAsignacion {
    Integer create(Tasignacion asignacion) throws ASException, DAOException;

    Integer drop(Tasignacion asignacion) throws ASException, DAOException;

    Integer modify(Tasignacion asignacion) throws ASException, DAOException;

    Collection<Tasignacion> list() throws ASException, DAOException;

    Tasignacion show(Integer assignation_id) throws ASException, DAOException;

    Collection<Tasignacion> showByIdPavilion(Integer pavilion_id) throws ASException, DAOException;

    Collection<Tasignacion> showByIdFair(Integer fair_id) throws ASException, DAOException;
}
