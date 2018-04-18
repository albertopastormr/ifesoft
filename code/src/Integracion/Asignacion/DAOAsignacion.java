package Integracion.Asignacion;

import Exceptions.DAOException;
import Negocio.Asignacion.Tasignacion;

import java.sql.SQLException;
import java.util.Collection;

public interface DAOAsignacion {
	Integer create(Tasignacion tasignacion) throws SQLException, DAOException;
	Collection<Tasignacion> readAll() throws DAOException;
	Collection<Tasignacion> readByFairName(String name) throws DAOException;
	Collection<Tasignacion> readByFairId(Integer id) throws DAOException;
	Collection<Tasignacion> readByPavilionId(Integer id) throws DAOException;
	Tasignacion readById(Integer id) throws DAOException;
	boolean update(Tasignacion tasignacion) throws DAOException;
	boolean delete(Integer id) throws DAOException;
	void deleteAll() throws DAOException;
}
