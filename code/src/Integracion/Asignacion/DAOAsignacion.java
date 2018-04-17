package Integracion.Asignacion;

import Exceptions.DAOException;
import Negocio.Asignacion.Tasignacion;

import java.sql.SQLException;
import java.util.Collection;

public interface DAOAsignacion {
	boolean create(Tasignacion tasignacion) throws SQLException, DAOException;
	Collection<Tasignacion> readAll() throws DAOException;
	Collection<Tasignacion> readByFairName(String name) throws DAOException;
	Collection<Tasignacion> readByFairId(Integer id) throws DAOException;
	Collection<Tasignacion> readByPavilionId(Integer id) throws DAOException;
	Tasignacion readById(Integer fair_id, Integer pavilion_id, Integer stand_id) throws DAOException;
	boolean update(Tasignacion tasignacion) throws DAOException;
	boolean delete(Integer fair_id, Integer client_id, Integer stand_id) throws DAOException;
	void deleteAll() throws DAOException;
}
