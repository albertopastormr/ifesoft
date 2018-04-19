package Integracion.Participacion;

import Exceptions.DAOException;
import Negocio.Participacion.Tparticipacion;

import java.sql.SQLException;
import java.util.Collection;

public interface DAOParticipacion {
	Integer create(Tparticipacion tParticipacion) throws SQLException, DAOException;
	Collection<Tparticipacion> readAll() throws DAOException;
	Collection<Tparticipacion> readByFairName(String nombre) throws DAOException;
	Collection<Tparticipacion> readByClientName(String nombre) throws DAOException;
	Collection<Tparticipacion> readByFairId(Integer id) throws DAOException;
	Collection<Tparticipacion> readByClientId(Integer id) throws DAOException;
	Tparticipacion readById(Integer id) throws DAOException;
	Tparticipacion readByFairIdClientId(Integer fair_id, Integer client_id) throws DAOException;
	Integer update(Tparticipacion tParticipacion) throws DAOException;
	boolean delete(Integer id) throws DAOException;
	void deleteAll() throws DAOException;
}
