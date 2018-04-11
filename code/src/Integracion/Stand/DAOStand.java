package Integracion.Stand;

import Exceptions.DAOException;
import Negocio.Stand.Tstand;

import java.sql.SQLException;
import java.util.Collection;

public interface DAOStand {
	 Integer create(Tstand tstand) throws SQLException, DAOException;
	 Collection<Tstand> readAll() throws DAOException;
	 Tstand readByName(String nombre) throws DAOException;
	 Integer update(Tstand tstand) throws DAOException;
	 boolean delete(Integer id) throws DAOException;
	public void deleteAll() throws DAOException;
}
