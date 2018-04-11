package Integracion.Asignacion;

import Exceptions.DAOException;
import Negocio.Asignacion.Tasignacion;

import java.sql.SQLException;
import java.util.Collection;

public interface DAOAsignacion {
	 Integer create(Tasignacion tasignacion) throws SQLException, DAOException;
	 Collection<Tasignacion> readAll() throws DAOException;
	 Collection<Tasignacion> readByFairName(String name) throws DAOException;
	 Collection<Tasignacion> readByPavilionName(String name) throws DAOException;
	 Integer update(Tasignacion tasignacion) throws DAOException;
	 boolean delete(Integer fair_id, Integer client_id, Integer stand_id) throws DAOException;
}
