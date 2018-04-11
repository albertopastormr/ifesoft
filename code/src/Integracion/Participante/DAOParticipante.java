package Integracion.Participante;

import Exceptions.DAOException;
import Negocio.Participante.Tparticipante;

import java.sql.SQLException;
import java.util.Collection;

public interface DAOParticipante {
	 Integer create(Tparticipante tparticipante) throws SQLException, DAOException;
	 Collection<Tparticipante> readAll() throws DAOException;
	 Tparticipante readByName(String name) throws DAOException;
	 Integer update(Tparticipante tparticipante) throws DAOException;
	 boolean delete(Integer id) throws DAOException;
	public void deleteAll() throws DAOException;
}
