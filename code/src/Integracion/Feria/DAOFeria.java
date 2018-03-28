package Integracion.Feria;

import Exceptions.DAOException;
import Negocio.Feria.Tferia;

import java.sql.SQLException;
import java.util.Collection;

public interface DAOFeria {
	 Integer create(Tferia tUsuario) throws SQLException, DAOException;
	 Collection<Tferia> readAll() throws DAOException;
	 Tferia readByName(String nombre) throws DAOException;
	 Integer update(Tferia tUsuario) throws DAOException;
	 boolean drop (Integer id) throws DAOException;
}
