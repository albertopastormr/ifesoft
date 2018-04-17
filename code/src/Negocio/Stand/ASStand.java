package Negocio.Stand;

import Exceptions.ASException;
import Exceptions.DAOException;

import java.util.Collection;

public interface ASStand {
    Integer create(Tstand stand) throws ASException, DAOException;

    Integer drop(Tstand stand) throws ASException, DAOException;

    Integer modify(Tstand stand) throws ASException, DAOException;

    Collection<Tstand> list() throws ASException, DAOException;

    Tstand showById(Integer id) throws ASException, DAOException;

    Collection<Tstand> showByAssignation(Integer fair_id, Integer pavilion_id) throws ASException, DAOException;


    Collection<Tstand> showByParticipation(Integer fair_id, Integer client_id) throws ASException, DAOException;

}
