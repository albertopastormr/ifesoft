package Integracion.Pabellon;

import Exceptions.DAOException;
import Negocio.Pabellon.Tpabellon;

import java.sql.SQLException;
import java.util.Collection;

public interface DAOPabellon {
	Integer create(Tpabellon tpabellon) throws SQLException, DAOException;
	Collection<Tpabellon> readAll() throws DAOException;
	Tpabellon readById(Integer id) throws DAOException;
	Integer update(Tpabellon tpabellon) throws DAOException;
	boolean delete(Integer id) throws DAOException;
	void deleteAll() throws DAOException;
}
