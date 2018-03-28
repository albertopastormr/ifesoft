package Integracion.Feria;

import Exceptions.DAOException;

import java.sql.SQLException;
import java.util.Collection;

public interface DAOFeria {
	 Integer create(Tferia tUsuario) throws SQLException, DAOException;
	 Collection<Tferia> readAll();
	 Tferia readByName(String nombre);
	 Integer update(Tferia tUsuario);
	 Integer drop (Integer id);
	 Integer activate (Integer id);
}
