package Integracion.Stand;

import Exceptions.DAOException;
import Negocio.Stand.Tstand;

import java.sql.SQLException;
import java.util.Collection;

public interface DAOStand {
	Integer create(Tstand tstand) throws SQLException, DAOException;
	Collection<Tstand> readAll() throws DAOException;
	Tstand readById(Integer id) throws DAOException;
	Collection<Tstand> readByAssignation(Integer fair_id, Integer pavilion_id) throws DAOException;
	Collection<Tstand> readByParticipation(Integer fair_id, Integer pavilion_id) throws DAOException;
	Integer update(Tstand tstand) throws DAOException;
	boolean delete(Integer id) throws DAOException;
	void deleteAll() throws DAOException;
}
